package com.zejian.structures.Graph.WeightGraph;

import com.zejian.structures.heap.MinHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zejian on 2018/1/30.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 利用Kruskal算法求最小生成树(只适合无向图)
 * 算法思想:按照边的权重排序(最小堆数据结构),每次将最小权重边加入最小生成树(List数据结构存储就行)
 * 但在加入前要判断加入的边是否会与已存在的生成树中的边形成环,如果形成环,那么就丢弃该边,不加入最小
 * 生成树,以次循环直到最小生成树含有V-1条边,那么就结束操作,此时List中存储的边就是一棵最小生成树.
 *
 * 需要的工具:最小堆数据结构(排序所有边)+List(存储最小生成树)+并查集UF(用于判断是否形成环)
 */
public class KruskalMST<Weight extends Number & Comparable<Weight>> {

    private List<Edge<Weight>> mst;//最小生成树所包含的所有边
    private Number mstWeight; //最小生成树的总权值
    private int count = 0;
    public KruskalMST(WeightGraph graph){
        assert graph != null;
        mst = new ArrayList<Edge<Weight>>();

        MinHeap<Edge<Weight>>  pq = new MinHeap<Edge<Weight>>(graph.E());
        //遍历所有边并加入pq
        for (int i = 0; i <graph.V() ; i++) {
            for (Object item: graph.adj(i)) {
                if(item != null) {
                    Edge<Weight> e = (Edge<Weight>) item;
                    //因为是无向图,存在重复边.所以这里需要判断一下如(0,1)和(1,0)是同一条边
                    if (e.v() < e.w()) {
                        count ++;
//                        System.out.println("count:"+count+",e="+e.toString());
                        pq.insert(e);
                    }
                }
            }
        }
        //创建并查集辅助类,用于判断是否存在环
        UnionFind uf = new UnionFind(graph.V());

        //最小生成树含有V-1条边时停止
        while (!pq.isEmpty()&& mst.size() < graph.V() - 1 ){
            //取出最小权值值边
            Edge<Weight> e = pq.deleteMin();

            int v = e.either();
            int w = e.other(v);
            //判断要相连的两个顶点是否有相同根结点,没有就可以加入生成树
            //如果有那么说明相连后肯定形成环,抛弃该边
            if(uf.isConnected(v,w)){
               continue;
            }

            mst.add(e);
            //把已访问过的最小生成树的边结点加入并查集
            uf.unionElements(v,w);
        }

        //计算最小生成树的总权值
        mstWeight = mst.get(0).wt().doubleValue();
        for (int i = 1; i < mst.size() ; i++) {
            mstWeight =mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }

    // 返回最小生成树的所有边
    List<Edge<Weight>> getMstEdgeList(){
        return mst;
    }

    // 返回最小生成树的权值
    Number mstWeight(){
        return mstWeight;
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {

        String filename = "testWG1.txt";
        WeightSparseGraph<Double> spare = new WeightSparseGraph<Double>(8,false);
        spare.readGraph(filename);
        spare.show();

        KruskalMST<Double> kMST = new KruskalMST<Double>(spare);
        List<Edge<Double>> mstList = kMST.getMstEdgeList();
        for( int i = 0 ; i < mstList.size() ; i ++ )
            System.out.println(mstList.get(i));
        System.out.println("总权值为:"+kMST.mstWeight());


    }
}
