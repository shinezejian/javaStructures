package com.zejian.structures.Graph.NoWeightGraph;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 连通分量,利用深度优先搜索算法即可,同时使用并查集标记顶点之间是否连通
 */
public class ConnectedComponent {

    private boolean[] visited;
    private int ccount;//连通分量的个数
    private int[] id ; //用于标记顶点间是否连通

    public ConnectedComponent(Graph graph){
        assert graph != null;
        visited = new boolean[graph.V()];
        id =  new int[graph.V()];
        for(int i = 0 ; i < graph.V() ; i++){
            id[i] = -1;
        }
        for(int v = 0; v < graph.V(); v++){
            if(!visited[v]) {
                ccount++;
                dfs(graph, v);
            }
        }

    }

    private void dfs(Graph graph, int v){
        visited[v] = true;
        id[v] = ccount;
        for(int w : graph.adj(v)){
            if(!visited[w]){
                dfs(graph,w);
            }
        }
    }

    /**
     * 判断两个顶点是否联通
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v , int w){
        return id[v] == id[w];
    }

    /**
     * 返回连通分量的个数
     * @return
     */
    public int getCCount(){
        return ccount;
    }
}
