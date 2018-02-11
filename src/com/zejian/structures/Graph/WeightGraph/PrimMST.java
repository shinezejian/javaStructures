package com.zejian.structures.Graph.WeightGraph;

import com.zejian.structures.heap.IndexMinPQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zejian on 2018/1/30.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 优化版的最小生成树算法
 * 时间复杂度 (V*LogV+E*LogV) = E*LogV
 * 利用最小索引堆
 */
public class PrimMST<Weight extends Number & Comparable<Weight>> {

    private IndexMinPQ<Weight> pq;//最小索引堆,只存储权值更小的边,不会存放所有边
    private boolean marked[];//标记数组, 在算法运行过程中标记节点i是否被访问
    private Edge edgeTo[];//访问的点所对应的边, 因为索引堆中没存放具体边信息
    private List<Edge<Weight>> mst;     // 最小生成树所包含的所有边
    private Number mstWeight; //最小生成树的总权值

    public PrimMST(WeightGraph G) {
        assert G != null;
        pq = new IndexMinPQ<Weight>(G.V());
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        mst = new ArrayList<Edge<Weight>>();

        for (int i = 0; i < G.V(); i++) {
            edgeTo[i] = null;
        }


        //prim算法
        visited(G, 0);
        while (!pq.isEmpty()) {
            int x = pq.deleteMin();
            if (edgeTo[x] != null) {
                mst.add(edgeTo[x]);
                visited(G,x);
            }
        }


        // 计算最小生成树的权值
        mstWeight = mst.get(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();

    }

    private void visited(WeightGraph graph, int v) {
        assert !marked[v];

        marked[v] = true; //设置访问标志

        //遍历邻边
        for (Object o : graph.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) o;

            int w = e.other(v);
            // 如果边的另一端点已被访问，跳过该顶点
            if (marked[w]) continue;
            // 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
            if (edgeTo[w] == null) {
                edgeTo[w] = e;
                pq.insert(w, e.wt());
            }
            //如果曾经考虑这个端点, 但现在的边比之前考虑的边更短, 则进行替换
            else if (edgeTo[w] != null && edgeTo[w].compareTo(e) > 0) {
                edgeTo[w] = e;
                pq.change(w, e.wt());
            }

        }
    }

    /**
     * 返回最小生成树的所有边
     * @return
     */
    public List<Edge<Weight>> getMstEdgeList(){
        return mst;
    }

    /**
     * 返回最小生成树的权值
     * @return
     */
    public Number mstWeight(){
        return mstWeight;
    }


    // 测试 Prim
    public static void main(String[] args) {
        String filename = "testWG1.txt";
        WeightSparseGraph<Double> wSparseGraph = new WeightSparseGraph<Double>(8,false);
        wSparseGraph.readGraph(filename);
        wSparseGraph.show();

        PrimMST<Double> primMst = new PrimMST<Double>(wSparseGraph);
        List<Edge<Double>> mstList = primMst.getMstEdgeList();
        for( int i = 0 ; i < mstList.size() ; i ++ )
            System.out.println(mstList.get(i));
        System.out.println("The MST weight is: " + primMst.mstWeight());
        System.out.println();
    }

}
