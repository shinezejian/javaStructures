package com.zejian.structures.Graph.WeightGraph;

import java.util.LinkedList;

/**
 * Created by zejian on 2018/1/20.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 带权重稠密图 - 邻接矩阵 使用二维数组表示
 */
public class WeightDenseGraph<Weight extends Number & Comparable<Weight>> extends WeightGraph {
    private int V; //顶点数量
    private int E; //边的数量
    private boolean directed;//是否为有向图
    private Edge<Weight>[][] g;//图的具体数据,存在边设置为true,否则为false

    public WeightDenseGraph(int v , boolean directed){
        this.V = v;
        this.directed = directed;
        g = new Edge[v][v];
        for(int i = 0 ; i < v ; i ++)
            for(int j = 0 ; j < v ; j ++)
                g[i][j] = null;
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
        assert e.v() >= 0 && e.v() < V;
        assert e.w() >= 0 && e.w() < V;

        g[e.v()][e.w()] = new Edge<Weight>(e);
        //如果是无向图
        if(e.v() != e.w() && !directed){
            g[e.w()][e.v()] =new Edge<Weight>(e.w(),e.v(), (Weight) e.wt());
        }
        E++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < V ;
        assert w >= 0 && w < V ;
        return g[v][w] != null;
    }

    /**
     * 显示图的结构信息
     */
    public void show(){
        System.out.println("----------------稠密图信息结构------------------------");

        for (int i = 0; i < V ; i++){
            if (i == 0){
                System.out.print("\t"+"  "+i + "\t"+ "\t");
            }else {
                System.out.print(" "+i + "\t" + "\t");
            }
        }
        System.out.println();
        for( int i = 0 ; i < V ; i ++ ){
            for( int j = 0 ; j < V ; j ++ ) {
                if(j==0){
                    if (g[i][j] != null) {
                        System.out.print(i + "\t" + g[i][j].wt() + "\t");
                    }else {
                        System.out.print(i + "\t" + g[i][j] + "\t");
                    }
                }else {
                    if (g[i][j] != null) {
                        System.out.print(g[i][j].wt() + "\t");
                    }else {
                        System.out.print(g[i][j] + "\t");
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < V;

        LinkedList<Edge<Weight>> list =new LinkedList<Edge<Weight>>();

        for (int i=0; i<V;i++){
            if(g[v][i] != null){
                list.add(g[v][i]);
            }
        }
        return list;
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args){
        String filename = "weighttestG3.txt";

        WeightDenseGraph<Double> weightDenseGraph = new WeightDenseGraph<>(8,false);
        weightDenseGraph.readGraph(filename);
        weightDenseGraph.show();
    }
}
