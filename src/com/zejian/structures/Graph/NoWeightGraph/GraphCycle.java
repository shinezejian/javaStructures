package com.zejian.structures.Graph.NoWeightGraph;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 判断无向图是否存在环
 * 利用深度优先搜索算法,由于深度优先算法访问过的路径最终是一棵有尽头的树,
 * 倘若访问过程搜索过程中存在某个顶点被访问2次,则说明该图存在环.
 */
public class GraphCycle {

    private boolean[] visited;
    private boolean hasCycle;

    public GraphCycle(Graph graph){
        assert graph != null;

        visited = new boolean[graph.V()];


        for (int v = 0 ; v < graph.V(); v++){
            if(!visited[v]){
                dfs(graph,v,v);
            }
        }

    }

    private void dfs(Graph graph , int v , int u){
        visited[v] = true;
        for(int w : graph.adj(v)){
            if(!visited[w]){
                visited[w] = true;
                dfs(graph,w , v);
            }else if( w != u){
                hasCycle = true;
            }
        }
    }

    /**
     * 判断是否存在环
     * @return
     */
    public boolean hasCycle(){
        return hasCycle;
    }


}
