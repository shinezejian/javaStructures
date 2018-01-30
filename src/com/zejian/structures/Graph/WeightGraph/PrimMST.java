package com.zejian.structures.Graph.WeightGraph;

import com.zejian.structures.heap.IndexMinPQ;

import java.util.List;

/**
 * Created by zejian on 2018/1/30.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 优化版的最小生成树算法
 * 利用最小索引堆
 */
public class PrimMST<Weight extends Number & Comparable<Weight>> {

    private IndexMinPQ<Weight>  pq;//最小索引堆,只存储权值更小的边,不会存放所有边
    private boolean marked[];//标记数组, 在算法运行过程中标记节点i是否被访问
    private Edge edgeTo[];//访问的点所对应的边, 因为索引堆中没存放具体边信息
    private List<Edge<Weight>> mst;     // 最小生成树所包含的所有边
    private Number mWeight; //最小生成树的总权值


}
