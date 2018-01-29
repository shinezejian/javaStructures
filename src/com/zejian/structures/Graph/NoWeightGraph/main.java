package com.zejian.structures.Graph.NoWeightGraph;

/**
 * Created by zejian on 2018/1/21.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public class main {
    public static void main(String[] args){
        String fileName = "testG2.txt";
        //读取稀疏图并打印
//        System.out.println("----------------稀疏图信息结构------------------------");
//        WeightSparseGraph sparseGraph = new WeightSparseGraph(13,false);
//        sparseGraph.readGraph(fileName);
//        sparseGraph.show();
//        System.out.println("----------------稠密图信息结构------------------------");
//        DenseGraph denseGraph = new DenseGraph(13,false);
//        denseGraph.readGraph(fileName);
//        denseGraph.show();



        //测试路径.深度优先搜索.广度优先搜索
//        WeightSparseGraph sparseGraph = new WeightSparseGraph(6,false);
//        sparseGraph.readGraph(fileName);
//        sparseGraph.show();
//        System.out.println("----------------路径信息结构------------------------");
//        int s = 0;
//        Paths paths = new Paths(sparseGraph,s);
//
//        for (int v = 0; v<sparseGraph.V(); v++){
//            paths.showPath(v);
//        }
//        System.out.println("----------------广度路径信息结构------------------------");
//
//        BreadthFirstSearch bfs = new BreadthFirstSearch(sparseGraph,s);
//        for (int v = 0; v<sparseGraph.V(); v++){
//            bfs.showPath(v);
//        }



        //测试连通分量
        String fileName3 = "testG3.txt";//13
        String fileName4 = "testG4.txt";//7

        SparseGraph sparseGraph3 = new SparseGraph(13,false);
        sparseGraph3.readGraph(fileName3);
        ConnectedComponent cc = new ConnectedComponent(sparseGraph3);
        System.out.println("图3的连通分量个数为ccount:"+cc.getCCount());

        SparseGraph sparseGraph4 = new SparseGraph(7,false);
        sparseGraph4.readGraph(fileName4);
        ConnectedComponent cc1 = new ConnectedComponent(sparseGraph4);
        System.out.println("图4的连通分量个数为ccount:"+cc1.getCCount());
    }
}
