package com.zejian.structures.Graph.WeightGraph;

import java.util.LinkedList;

/**
 * Created by zejian on 2018/1/20.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 带权的稀疏图 - 邻接表
 */
public class WeightSparseGraph<Weight extends Number & Comparable<Weight>> extends WeightGraph {

    private int V; //顶点数量
    private int E; //边的数量
    private boolean directed;//是否为有向图
    private LinkedList<Edge<Weight>>[] g;//存储边的链表,图的具体数据

    public WeightSparseGraph(int v, boolean isDirected) {
        this.V = v;
        this.directed = isDirected;
        g = (LinkedList<Edge<Weight>>[])new LinkedList[V];
        //初始化
        for(int i=0; i < V ; i++){
            g[i] = new LinkedList<Edge<Weight>>();
        }
    }


    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }


    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < V ;
        assert e.w() >= 0 && e.w() < V ;
        g[e.v()].add(new Edge<Weight>(e));
        if( e.v() != e.w() && !directed ) {
            g[e.w()].add(new Edge<Weight>(e.w(),e.v(), (Weight) e.wt()));
        }
        E++;
    }

    /**
     * 判断图中是否有从v到w的边
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v , int w){
        assert v >= 0 && v < V ;
        assert w >= 0 && w < V ;
        for (int i=0;i<g[v].size();i++){
            if(g[v].get(i).other(v) == w){
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < V;
        return g[v];
    }

    // 显示图的信息
    public void show(){
        System.out.println("----------------带权的稀疏图信息结构------------------------");
        for( int i = 0 ; i < V ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].get(j).toString() + "\t");
            System.out.println();
        }
    }

    /**
     * Test
     * @param args
     */
    public static void main(String[] args){
        String filename = "testWG1.txt";

        WeightSparseGraph<Double> weightSparseGraph = new WeightSparseGraph<>(8,false);
        weightSparseGraph.readGraph(filename);
        weightSparseGraph.show();
    }


}
