package com.zejian.structures.Graph.NoWeightGraph;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 利用深度优先搜索寻找路径
 */
public class Paths {
    private boolean visited[]; //记录顶点是否被访问过
    private int from[]; //记录从哪个顶点过来的,方便查找路径
    private int s;
    private Graph graph;

    public Paths(Graph graph , int s){
        assert graph != null;
        assert s >= 0 && s < graph.V();
        this.s = s;
        this.graph = graph;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0 ; i < graph.V() ; i++){
            from[i] = -1;
        }
        dfs(graph,s);
    }

    /**
     * 深度优先搜索
     * @param graph
     * @param v
     */
    public void dfs(Graph graph , int v){
        visited[v] = true;
        //获取v所有邻边
        Iterable<Integer> adj = graph.adj(v);
        for(int w : adj){
            if(!visited[w]){
                from[w] = v; //记录从哪个顶点过来的
                dfs(graph,w);
            }
        }
    }

    /**
     * 判断是否存在从s到w的路径
     * @param w
     * @return
     */
    public boolean hasPathTo(int w){
        assert w >= 0 && w < graph.V();
        return visited[w];
    }

    /**
     * 获取s到w路径的顶点集合
     * @param w
     * @return
     */
    public ArrayList<Integer> pathTo(int w){
        assert w >= 0 && w < graph.V();
        if (!hasPathTo(w)) return null;
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1){
            stack.push(p);
            p = from[p];
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    /**
     * 打印出从s点到w点的路径
     * @param w
     */
    void showPath(int w){
        System.out.print(s+" to " + w + ": ");
        assert hasPathTo(w) ;
        ArrayList<Integer> vec = pathTo(w);
        if(vec != null) {
            for (int i = 0; i < vec.size(); i++) {
                System.out.print(vec.get(i));
                if (i == vec.size() - 1)
                    System.out.println();
                else
                    System.out.print(" -> ");
            }
        }else {
            System.out.println();
        }
    }
}
