package com.zejian.structures.Graph.WeightGraph;

/**
 * Created by zejian on 2018/1/28.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 图的边
 */
public class Edge<Weight extends Number & Comparable<Weight>> implements Comparable<Edge<Weight>> {

    private int v , w; // 边的两个端点
    private Weight weight; //边的权值

    public Edge(int v, int w, Weight weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Edge(Edge<Weight> e)
    {
        this.v = e.v();
        this.w = e.w();
        this.weight = e.wt();
    }

    /**
     * 当两个顶点都未知时,使用该方法获取其中一个顶点
     * @return
     */
    public int either(){
        return v;
    }

    /**
     * 根据传入顶点返回该边的另一端的顶点
     * @param x
     * @return
     */
    public int other(int x){
        assert x == v || x == w;
        return x == v ? w : v;
    }

    @Override
    public int compareTo(Edge<Weight> o) {
        if(weight.compareTo(o.wt()) < 0){
            return  -1;
        }else if(weight.compareTo(o.wt()) > 0){
            return 1;
        }else{
            return 0;
        }
    }

    public int v(){ return v;} // 返回第一个顶点
    public int w(){ return w;} // 返回第二个顶点
    public Weight wt(){ return weight;}    // 返回权值

    // 输出边的信息
    public String toString(){
        return "" + v + "-" + w + ": " + weight;
    }
}
