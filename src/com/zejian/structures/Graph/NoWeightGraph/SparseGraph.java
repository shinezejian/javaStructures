package com.zejian.structures.Graph.NoWeightGraph;

import com.zejian.structures.LinkedList.MyCollection.MylinkeList;

/**
 * Created by zejian on 2018/1/20.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 稀疏图 - 邻接表
 */
public class SparseGraph extends Graph {

    private int V; //顶点数量
    private int E; //边的数量
    private boolean directed;//是否为有向图
    private MylinkeList<Integer>[] g;//存储边的链表,图的具体数据

    public SparseGraph(int v,boolean isDirected) {
        this.V = v;
        this.directed = isDirected;
        g = (MylinkeList<Integer>[])new MylinkeList[V];
        //初始化
        for(int i=0; i < V ; i++){
            g[i] = new MylinkeList<Integer>();
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
    public void addEdge(int v, int w) {
        assert v >= 0 && v < V ;
        assert w >= 0 && w < V ;

        g[v].add(w);
        if( v != w && !directed ) {
            g[w].add(v);
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
            if(g[v].get(i) == w){
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < V;
        return g[v];
    }

    // 显示图的信息
    public void show(){
        System.out.println("----------------稀疏图信息结构------------------------");
        for( int i = 0 ; i < V ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].get(j) + "\t");
            System.out.println();
        }
    }


}
