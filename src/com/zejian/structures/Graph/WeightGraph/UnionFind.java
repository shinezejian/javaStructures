package com.zejian.structures.Graph.WeightGraph;

/**
 * Created by zejian on 2018/1/30.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 并查集,路径压缩 UF
 * 主要用于判断是否形成环
 */
public class UnionFind {

    private int rank[];  // rank[i]表示以i为根的集合所表示的树的层数
    private int parent[];// parent[i]表示第i个元素所指向的父节点
    private int count;    // 数据个数


    public UnionFind(int count){
        assert count > 0;
        this.count = count;
        rank = new int[count];
        parent = new int[count];
        //初始化所有结点的父结点都指向自己;
        for (int i = 0; i <count ; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找某个结点的根结点
     * 时间复杂父为O(h) h为树的高度
     * @param p
     * @return
     */
    private int findRoot(int p){
        assert( p >= 0 && p < count );
        while(p != parent[p]) {
            //尝试进行路径压缩
            //把p的父结点修改为parent[p]的父结点,从而进行路径压缩
            parent[p] = parent[parent[p]];
            //p 赋值为 现在的父结点parent[p],进行下一轮循环
            p = parent[p];
        }
        return p;
    }

    /**
     * 判断两个结点是否相连
     * 或者查看元素p和元素q是否所属一个集合
     * O(h)复杂度, h为树的高度
     * @param p
     * @param q
     * @return
     */
    public  boolean isConnected(int p , int q){
        return findRoot(p) == findRoot(q);
    }



    /**
     *  合并元素p和元素q所属的集合, O(h)复杂度, h为树的高度
     * @param p
     * @param q
     */
    public void unionElements(int p, int q){

        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        //如果根结点相同说明已关联
        if (pRoot == qRoot) return;

        //如果pRoot的树层数小于qRoot层数,为了路径更短,将pRoot挂到qRoot树下
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else {
            // rank[pRoot] == rank[qRoot]
            //如果根结点层数相同,随机选择一个并入即可但要更新rank
            parent[qRoot] = pRoot;
            rank[qRoot]++;
        }
    }

}
