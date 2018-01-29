package com.zejian.structures.Graph.NoWeightGraph;

import com.zejian.structures.LinkedList.MyCollection.MylinkeList;

/**
 * Created by zejian on 2018/1/20.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 稠密图 - 邻接矩阵 使用二维数组表示
 */
public class DenseGraph extends Graph {
    private int V; //顶点数量
    private int E; //边的数量
    private boolean directed;//是否为有向图
    private boolean[][] g;//图的具体数据,存在边设置为true,否则为false

    public DenseGraph(int v , boolean directed){
        this.V = v;
        this.directed = directed;
        g = new boolean[V][V];
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
    public void addEdge(int v, int w) {
        assert v >= 0 && v < V;
        assert w >= 0 && w < V;
        g[v][w] = true;
        //如果是无向图
        if(v != w && !directed){
            g[w][v] = true;
        }
        E++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < V ;
        assert w >= 0 && w < V ;
        return g[v][w];
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
                    System.out.print(i+"\t" + g[i][j] + "\t");
                }else {
                    System.out.print(g[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < V;

        MylinkeList<Integer> list =new MylinkeList<Integer>();

        for (int i=0; i<V;i++){
            if(g[v][i]){
                list.add(i);
            }
        }

        return list;
    }
}
