package com.zejian.structures.Graph.NoWeightGraph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 图的广度优先搜索,需要借助队列,可以解决无权图单源最短路径问题
 */
public class BreadthFirstSearch {

    private boolean visited[];
    private int from[];
    private int s;
    private Graph graph;

    public BreadthFirstSearch(Graph graph , int s){
        assert graph != null;
        assert s >= 0 && s < graph.V();

        this.graph = graph;
        this.s = s;

        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0 ; i < graph.V(); i++){
            from[i] = -1;
        }
        //广度优先搜索算法
        bfs(graph,s);
    }

    /**
     * 广度优先搜索算法,非递归
     * @param graph
     * @param v
     */
    public void bfs(Graph graph, int v){
        Queue<Integer> queue = new LinkedList<>();
        visited[v] =true;
        queue.add(v);
        while (!queue.isEmpty()){
            int k = queue.poll();
                //获取邻边入队
                for(int w : graph.adj(k)){
                    if(!visited[w]){
                        from[w] = k;
                        visited[w] = true;
                        queue.add(w);
                    }
                }
        }
    }

    public boolean hasPathTo(int v){
        return visited[v];
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
