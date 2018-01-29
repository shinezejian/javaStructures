package com.zejian.structures.Graph.DirectedGraph;

import com.zejian.structures.Graph.NoWeightGraph.Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zejian on 2018/1/27.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 有向图中基于深度优先搜索的顶点排序
 * 基本思想:深度优先搜索正好只会访问每个顶点一次,如果将dfs()访问的每个顶点保存到一个
 *        数据结构中,那么遍历这个数据结构就能访问到有向图的所有顶点.遍历的顺序取决于
 *        这个数据结构的性质以及在递归调用之前还是之后进行保存.
 *
 *        前序遍历:在递归调用之前将顶点加入队列
 *        后序遍历:在递归调用之后将顶点加入队列
 *        逆后序: 在递归调用之后将顶点压入栈中
 */
public class DepthFirstOrder {


    private boolean marked[];
    private Queue<Integer> pre; //前序排序
    private Queue<Integer> post; //后序排序
    private Stack<Integer> reversePost; //逆后序

    public DepthFirstOrder(Graph G){
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();

        for(int v = 0; v < G.V(); v++){
            if (!marked[v]){
                dfs(G , v);
            }
        }

    }

    private void dfs(Graph G , int v){
        marked[v] = true;
        pre.add(v);//递归调用之前入队

        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G,w);
            }
        }

        //递归调用之后
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
