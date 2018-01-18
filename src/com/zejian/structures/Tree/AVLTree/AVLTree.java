package com.zejian.structures.Tree.AVLTree;


import com.zejian.structures.Tree.BinaryTree.BinaryNode;
import com.zejian.structures.Tree.BinaryTree.BinarySearchTree;
import com.zejian.structures.Tree.BinaryTree.Tree;


/**
 * Created by zejian on 2016/12/25.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 平衡二叉搜索树(AVL树)
 */
public class AVLTree<T extends Comparable> implements Tree<T>{


    /** The tree root. */
    public AVLNode<T> root;


    @Override
    public boolean isEmpty() {
        return root==null;
    }


    @Override
    public int size() {
        return size(root);
    }


    public int size(AVLNode<T> subtree){
        if(subtree==null){
            return 0;
        }else {
            return size(subtree.left) + 1 + size(subtree.right);
        }


    }

    @Override
    public int height() {
        return height(root);
    }


    /**
     * @param p
     * @return
     */
    public int height(AVLNode<T> p){
        return p == null ? -1 : p.height;
    }


    @Override
    public String preOrder() {
        String sb=preOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 先根遍历
     * @param subtree
     * @return
     */
    public String preOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null) {
            //先访问根结点
            sb.append(subtree.data).append(",");
            //访问左子树
            sb.append(preOrder(subtree.left));
            //访问右子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
    }

    @Override
    public String inOrder() {
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 中根遍历
     * @param subtree
     * @return
     */
    private String inOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null) {
            //访问左子树
            sb.append(inOrder(subtree.left));
            //访问根结点
            sb.append(subtree.data).append(",");
            //访问右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }

    @Override
    public String postOrder() {
        String sb=postOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 后根遍历
     * @param subtree
     * @return
     */
    private String postOrder(AVLNode<T> subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null){
            //访问左子树
            sb.append(postOrder(subtree.left));
            //访问右子树
            sb.append(postOrder(subtree.right));
            //访问根结点
            sb.append(subtree.data).append(",");
        }
        return sb.toString();
    }

    @Override
    public String levelOrder() {
        /**
         * @see BinarySearchTree#levelOrder()
         * @return
         */
        return null;
    }


    /**
     * 插入方法
     * @param data
     */
    @Override
    public void insert(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t not be null ");
        }
        this.root=insert(data,root);
    }

    private AVLNode<T> insert(T data , AVLNode<T> p){

        //说明已没有孩子结点,可以创建新结点插入了.
        if(p==null){
            p=new AVLNode<T>(data);
        }

        int result=data.compareTo(p.data);

        if(result<0){//向左子树寻找插入位置
            p.left=insert(data,p.left);

            //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
            if(height(p.left)-height(p.right)==2){
                //判断data是插入点的左孩子还是右孩子
                if(data.compareTo(p.left.data)<0){
                    //进行LL旋转
                    p=singleRotateLeft(p);
                }else {
                    //进行左右旋转
                    p=doubleRotateWithLeft(p);
                }
            }
        }else if (result>0){//向右子树寻找插入位置
            p.right=insert(data,p.right);

            if(height(p.right)-height(p.left)==2){
                if (data.compareTo(p.right.data)<0){
                    //进行右左旋转
                    p=doubleRotateWithRight(p);
                }else {
                    p=singleRotateRight(p);
                }
            }
        }
        else
         ;//if exist do nothing
        //重新计算各个结点的高度
        p.height = Math.max( height( p.left ), height( p.right ) ) + 1;

        return p;//返回根结点
    }


    /**
     * 删除方法
     * @param data
     */
    @Override
    public void remove(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t not be null ");
        }
        this.root=remove(data,root);
    }

    /**
     * 删除操作
     * @param data
     * @param p
     * @return
     */
    private AVLNode<T> remove(T data,AVLNode<T> p){

        if(p ==null)
            return null;

        int result=data.compareTo(p.data);

        //从左子树查找需要删除的元素
        if(result<0){
            p.left=remove(data,p.left);

            //检测是否平衡
            if(height(p.right)-height(p.left)==2){
                AVLNode<T> currentNode=p.right;
                //判断需要那种旋转
                if(height(currentNode.left)>height(currentNode.right)){
                    //RL
                    p=doubleRotateWithRight(p);
                }else{
                    //RR
                    p=singleRotateRight(p);
                }
            }

        }
        //从右子树查找需要删除的元素
        else if(result>0){
            p.right=remove(data,p.right);
            //检测是否平衡
            if(height(p.left)-height(p.right)==2){
                AVLNode<T> currentNode=p.left;
                //判断需要那种旋转
                if(height(currentNode.right)>height(currentNode.left)){
                    //LR
                    p=doubleRotateWithLeft(p);
                }else{
                    //LL
                    p=singleRotateLeft(p);
                }
            }
        }
        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
        else if(p.right!=null&&p.left!=null){

            //寻找替换结点
            p.data=findMin(p.right).data;

            //移除用于替换的结点
            p.right = remove( p.data, p.right );
        }
        else {
            //只有一个孩子结点或者只是叶子结点的情况
            p=(p.left!=null)? p.left:p.right;
        }

        //更新高度值
        if(p!=null)
            p.height = Math.max( height( p.left ), height( p.right ) ) + 1;
        return p;
    }


    /**
     * 查找最小值结点
     * @param p
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.left==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }

    @Override
    public T findMin() {
        return findMin(root).data;
    }

    @Override
    public T findMax() {
        return findMax(root).data;
    }

    /**
     * 查找最大值结点
     * @param p
     * @return
     */
    private AVLNode<T> findMax(AVLNode<T> p){
        if (p==null)
            return null;
        else if (p.right==null)//如果没有右结点,那么t就是最大的
            return p;
        return findMax(p.right);
    }

    @Override
    public BinaryNode findNode(T data) {
        /**
         * @see BinarySearchTree#findNode(Comparable)
         * @return
         */
        return null;
    }

    @Override
    public boolean contains(T data) {
        return data != null && contain(data, root);
    }

    public boolean contain(T data , AVLNode<T> subtree){

        if (subtree==null)
            return false;

        int result =data.compareTo(subtree.data);

        if (result<0){
            return contain(data,subtree.left);
        }else if(result>0){
            return contain(data,subtree.right);
        }else {
            return true;
        }
    }

    @Override
    public void clear() {
       this.root=null;
    }

    /**
     * 左左单旋转(LL旋转) w变为x的根结点, x变为w的右子树
     * @param x
     * @return
     */
    private AVLNode<T> singleRotateLeft(AVLNode<T> x){
        //把w结点旋转为根结点
        AVLNode<T> w=  x.left;
        //同时w的右子树变为x的左子树
        x.left=w.right;
        //x变为w的右子树
        w.right=x;
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return w;//返回新的根结点
    }

    /**
     * 右右单旋转(RR旋转) x变为w的根结点, w变为x的左子树
     * @return
     */
    private AVLNode<T> singleRotateRight(AVLNode<T> w){

        AVLNode<T> x=w.right;

        w.right=x.left;
        x.left=w;

        //重新计算x/w的高度
        x.height=Math.max(height(x.left),w.height)+1;
        w.height=Math.max(height(w.left),height(w.right))+1;

        //返回新的根结点
        return x;
    }

    /**
     * 左右旋转(LR旋转) x(根) w y 结点 把y变成根结点
     * @return
     */
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x){
        //w先进行RR旋转
        x.left=singleRotateRight(x.left);
        //再进行x的LL旋转
        return singleRotateLeft(x);
    }

    /**
     * 右左旋转(RL旋转)
     * @param w
     * @return
     */
    private AVLNode<T> doubleRotateWithRight(AVLNode<T> w){
        //先进行LL旋转
        w.right=singleRotateLeft(w.right);
        //再进行RR旋转
        return singleRotateRight(w);
    }


    private void printTree( AVLNode<T> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.data );
            printTree( t.right );
        }
    }


    /**
     * 测试
     * @param arg
     */
    public  static void main(String arg[]){

        AVLTree<Integer> avlTree=new AVLTree<>();

        for (int i = 1; i <18 ; i++) {
            avlTree.insert(i);
        }

        avlTree.printTree(avlTree.root);
        //删除11,8以触发旋转平衡操作
        avlTree.remove(11);
        avlTree.remove(8);

        System.out.println("================");

        avlTree.printTree(avlTree.root);

        System.out.println("findMin:"+avlTree.findMin());

        System.out.println("findMax:"+avlTree.findMax());

        System.out.println("15 exist or not : " + avlTree.contains(15) );

        System.out.println("先根遍历:"+avlTree.preOrder());

        System.out.println("中根遍历:"+avlTree.inOrder());

        System.out.println("后根遍历:"+avlTree.postOrder());

    }

}
