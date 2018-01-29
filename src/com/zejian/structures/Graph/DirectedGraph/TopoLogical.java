package com.zejian.structures.Graph.DirectedGraph;

import com.zejian.structures.Graph.NoWeightGraph.Graph;
import com.zejian.structures.Graph.NoWeightGraph.SparseGraph;

/**
 * Created by zejian on 2018/1/27.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 有向图的拓扑排序:给定一幅有向图,将所有顶点排序,使用所有的有向边均从排在前面的顶点指向
 *               排在后面的顶点
 *
 *               能实现拓扑排序的图必须是有向无环图.
 *
 *               注意:一幅有向无环图的拓扑顺序即为所有顶点的逆后序排序.
 */
public class TopoLogical {

    private Iterable<Integer> order;//顶点的拓扑排序

    public TopoLogical(Graph G){

        DirectedGraphCycle directedGraphCycle = new DirectedGraphCycle(G);

        //判断是否存在环
        if(!directedGraphCycle.hasCycle()){
            //利用有向图的深度优先搜索构建顶点的拓扑排序
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * 返回拓扑排序的所有顶点
     * @return
     */
    public Iterable<Integer> topoVetexOrder(){
        return order;
    }

    /**
     * 判断G是否为无环图
     * @return
     */
    public boolean isDAG(){
        return order != null;
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args){

        String fileName = "DirectedGraph.txt";

        SparseGraph sparseGraph = new SparseGraph(13,true);
        sparseGraph.readGraph(fileName);
        sparseGraph.show();

        TopoLogical topoLogical = new TopoLogical(sparseGraph);
        System.out.println("topoVetexOrder:");
        for (int w : topoLogical.topoVetexOrder()){
            System.out.print(w+",");
        }
        System.out.println();
    }
}
