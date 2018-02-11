package com.zejian.structures.Graph.DirectedWeightGraph;

import com.zejian.structures.Graph.WeightGraph.Edge;
import com.zejian.structures.Graph.WeightGraph.WeightGraph;
import com.zejian.structures.Graph.WeightGraph.WeightSparseGraph;
import com.zejian.structures.heap.IndexMinPQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zejian on 2018/2/10.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * Dijkstra算法(迪杰斯特拉算法),解决无负权边的单源最短路径问题,利用广度优先搜索算法
 * 算法思想:
 *  Dijkstra算法采用的是一种贪心的策略,声明数组 distTo 和 最小索引堆IndexMinPQ ,
 *  其中distTo 记录s到每个顶点的最短距离,初始时distTo数组中源点 s 的路径权重为0(distTo[s] = 0)
 *  若对于顶点 s 存在能直接到达的边（s,m），则把distTo[m]设为w（s, m）,同时把所有其他（s不能直接到达的）
 *  顶点的路径权重设置为-1。
 *  利用最小索引堆IndexMinPQ对边的权重进行排序,每次获取某个顶点关联最小权重的边
 *  并将当前顶点标记位已被访问,说明源点到该顶点的最短距离已找到.同时搜索该顶点的邻边,将其
 *  对应边的权重加入索引堆中以便下轮循环使用,此时需要对顶点进行一次松弛操作更新distTo数组对应值.
 *  下次循环中再次从最小索引堆中获取权重最小的边,依次进行上述操作.
 *
 */
public class Dijkstra<Weight extends Number & Comparable<Weight>> {

    private WeightGraph G;
    private int s; //源点
    private Number distTo[];//记录s到每个顶点的最短距离
    private Edge<Weight> edgeTo[]; //edgeTo[i]记录最短路径中, 到达i点的边是哪一条 可以用来恢复整个最短路径
    private boolean marked[]; //标记已被访问的顶点

    public Dijkstra(WeightSparseGraph G , int s){
        assert G != null;
        assert s >= 0;
        this.G = G;
        this.s = s;

        distTo = new Number[G.V()];
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];

        for (int i = 0; i <G.V() ; i++) {
            distTo[i] = -1;
        }

        //算法start
        //利用最小索引堆对权重进行排序,保证每次取出来的都是当前集合的最小权重
        IndexMinPQ<Weight> pq = new IndexMinPQ<Weight>(G.V());

        distTo[s] = 0.0;
        Edge<Weight> es = new Edge<Weight>(s,s,(Weight) (Number) 0.0);
        edgeTo[s] = es;
        pq.insert(s,es.wt());
        while (!pq.isEmpty()){
            int v = pq.deleteMin();
            marked[v] = true;

            //遍历邻边
            for (Object o: G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) o;

                int w = e.other(v);
                //如果邻边的顶点最短路径还没有找到,即未被访问
                if(!marked[w]){
                    // 如果w点以前没有访问过,
                    // 或者访问过, 但是通过当前的v点到w点距离更短, 则进行更新
                    if(edgeTo[w] == null || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue()){
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        edgeTo[w] = e;
                        //更新
                        if(pq.contains(w)){
                            pq.change(w,e.wt());
                        }else {
                            pq.insert(w,e.wt());
                        }
                    }

                }
            }
        }
    }

    /**
     * 获取s到w顶点的最短路径距离
     * @param w
     * @return
     */
    public Number getShortestPathTo(int w){
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        return distTo[w];
    }

    /**
     * 从s到w是否可达
     * @param w
     * @return
     */
    public boolean hasPathTo(int w){
        assert w >= 0 && w < G.V();
        return marked[w];
    }


    /**
     *  寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
     * @return
     */
    private List<Edge<Weight>> getShortestPath(int w){
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        Stack<Edge<Weight>> path = new Stack<>();

        Edge<Weight> e = edgeTo[w];

        while (e.v() != this.s){
            path.push(e);
            e = edgeTo[e.v()];
        }

        path.push(e);

        List<Edge<Weight>> pathList = new ArrayList<>();

        while (!path.empty()){
            pathList.add(path.pop());
        }

        return pathList;
    }

    /**
     * 打印从s到w顶点的最短路径轨迹
     * @param w
     */
    public void showShortestPath(int w){

        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        List<Edge<Weight>> pathList = getShortestPath(w);

        for (int i = 0; i < pathList.size() ; i++) {
            System.out.print( pathList.get(i).v() + " -> ");
            if( i == pathList.size()-1 )
                System.out.println(pathList.get(i).w());
        }
    }

    // 测试
    public static void main(String[] args) {
        String filename = "testDirectedWeightG1.txt";
        WeightSparseGraph<Double> wSparseGraph = new WeightSparseGraph<Double>(5,false);
        wSparseGraph.readGraph(filename);
        wSparseGraph.show();

        Dijkstra<Double> d = new Dijkstra<>(wSparseGraph,0);

        for (int i = 0; i <wSparseGraph.V() ; i++) {
            System.out.println("从s到"+i+"顶点的最短路径w="+d.getShortestPathTo(i));
            d.showShortestPath(i);
        }
    }

}
