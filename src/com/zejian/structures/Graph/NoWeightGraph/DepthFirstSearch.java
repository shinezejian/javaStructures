package com.zejian.structures.Graph.NoWeightGraph;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 图的深度优先遍历(顶点均为数字类型)
 */
public class DepthFirstSearch {

    private boolean[] visited;
    private int count;

    public DepthFirstSearch(Graph graph, int s){
        assert graph != null;
        visited = new boolean[graph.V()];
        DFS(graph,s);//从第一个顶点开始遍历
    }

    private void DFS(Graph graph , int v){
        visited[v] = true; //标记该顶点已被访问
        //查找v顶点的相邻顶点
        Iterable<Integer> adj = graph.adj(v);
        for(int w : adj){
            if(!visited[w]){
                DFS(graph,w);
            }
        }
    }



}
