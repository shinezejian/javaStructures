package com.zejian.structures.Tree.BinaryTree;

/**
 * Created by zejian on 2016/12/14.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public interface Tree<T extends Comparable> {

    /**
     * 判空
     * @return
     */
    boolean isEmpty();

    /**
     * 二叉树的结点个数
     * @return
     */
    int size();

    /**
     * 返回二叉树的高度或者深度,即结点的最大层次
     * @return
     */
    int height();

    /**
     * 先根次序遍历
     */
    String preOrder();

    /**
     * 中根次序遍历
     */
    String inOrder();

    /**
     * 后根次序遍历
     */
    String postOrder();

    /**
     * 层次遍历
     */
    String levelOrder();

    /**
     * 将data 插入
     * @return
     */
    void insert(T data);


    /**
     * 删除
     */
    void remove(T data);

    /**
     * 查找最大值
     * @return
     */
    T findMin();

    /**
     * 查找最小值
     * @return
     */
    T findMax();

    /**
     * 根据值找到结点
     * @param data
     * @return
     */
    BinaryNode findNode(T data);

    /**
     * 是否包含某个值
     * @param data
     * @return
     */
    boolean contains(T data) throws Exception;


    /**
     * 清空
     */
    void clear();
}
