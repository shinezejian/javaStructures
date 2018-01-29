package com.zejian.structures.Graph.DirectedGraph;

import com.zejian.structures.Graph.NoWeightGraph.Graph;

import java.util.Stack;

/**
 * Created by zejian on 2018/1/27.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 寻找有向环,基于深度优先搜索实现,记录当前递归调用栈正在访问的所有顶点并标记为true,
 *          调用结束恢复为false,如果当前访问的顶点已在栈中,那么说明该有向图存在环.
 */
public class DirectedGraphCycle {

    private boolean marked[]; //标记存放已被访问过顶点
    private int edgeTo[]; //记录访问路径
    private boolean onStack[];//标记当前顶点是否在栈上
    private Stack<Integer> cycle;//记录环的元素

    public DirectedGraphCycle(Graph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];

        //遍历所有顶点,判断是否存在环
        for(int i= 0; i < G.V(); i++){
            if (!marked[i]) {
                dfs(G, i);
            }
        }

    }

    /**
     * 利用深度优先搜索判断是否有环
     * 如果被访问的顶点已在栈上,那么说明该有向图存在环.
     * @param G
     * @param v
     */
    private void dfs(Graph G , int v){
        marked[v] = true;
        onStack[v] = true;

        //访问邻边
        for(int w : G.adj(v)){
            if(hasCycle()){
                return;
            }else if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                for(int x = v ; x != w ; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] = false;
    }


    public boolean hasCycle(){
        return cycle != null;
    }

    /**
     * 返回环中所有顶点
     * @return
     */
    public Iterable<Integer> getCycle(){
        return cycle;
    }
}
