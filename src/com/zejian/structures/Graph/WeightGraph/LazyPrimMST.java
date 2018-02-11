package com.zejian.structures.Graph.WeightGraph;

import com.zejian.structures.heap.MinHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zejian on 2018/1/29.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 基于切分定理实现的延时最小生成树算法(针对无向图):
 * 利用深度优先搜索算法遍历图,并标记已被访问过的顶点,同时利用最小堆的特性
 * 每一次切分,都将新的边的权值加入堆中,并获取从其中获取最小权值的元素,必
 * 为最小生成树的一条边的权值
 *
 * 时间复杂度 E * LogE（deleteMin方法执行的总时间复杂度） + E * LogE（visit方法执行的总时间复杂度） = E*LogE级别
 */
public class LazyPrimMST<Weight extends Number & Comparable<Weight>> {
    private boolean visited[]; //标记已被访问过的顶点
    private MinHeap<Edge<Weight>> minHeap; //存储权值的堆结构
    private Number mWeight; //最小生成树的总权值
    private List<Edge<Weight>> mst;  //存放最小生成树的所有顶点


    public LazyPrimMST(WeightGraph graph){
        assert graph != null;

        visited = new boolean[graph.V()];
        minHeap = new MinHeap<Edge<Weight>>(graph.E());
        mst = new ArrayList<Edge<Weight>>(graph.V());

        //从第一个顶点开始访问
        visit(graph,0);
        while (!minHeap.isEmpty()){
            //获取每次分切后最小的权值的边
            Edge<Weight> e = minHeap.deleteMin();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            if( visited[e.v()] && visited[e.w()] )
                continue;
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);

            if(!visited[e.v()]){
                visit(graph,e.v());
            }else if(!visited[e.w()]){
                visit(graph,e.w());
            }
        }

        //计算最小生成树的权值
        mWeight = mst.get(0).wt();
        for (int i = 1; i <mst.size() ; i++) {
            mWeight = mWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }

    }
    private void visit(WeightGraph graph , int v){
        if(!visited[v]) {
            visited[v] = true;
            //访问v顶点相关的邻边
            for (Edge<Weight> e : (Iterable<Edge<Weight>>) graph.adj(v)) {
                int w = e.other(v);
                if (!visited[w]) {
                    minHeap.insert(e);
                }
            }
        }
    }

    /**
     * 最小生成树的总权值
     * @return
     */
    public Number mstWeight(){
        return mWeight;
    }

    public List<Edge<Weight>> getMstEdgeList(){
        return mst;
    }

    public static void main(String[] args){
        String filename = "weighttestG3.txt";

        WeightSparseGraph<Double> weightSparseGraph = new WeightSparseGraph<Double>(8,false);
        weightSparseGraph.readGraph(filename);
        weightSparseGraph.show();

        LazyPrimMST<Double> lazy = new LazyPrimMST<Double>(weightSparseGraph);
        System.out.println("总权值是:"+lazy.mstWeight());
        for (int i = 0; i <lazy.getMstEdgeList().size() ; i++) {
            System.out.println("e:"+lazy.getMstEdgeList().get(i));
        }
    }
}
