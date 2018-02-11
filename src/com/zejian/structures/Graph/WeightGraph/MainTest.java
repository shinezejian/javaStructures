package com.zejian.structures.Graph.WeightGraph;

/**
 * Created by zejian on 2018/2/1.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 比较 KruskaMST LazyPrimMST PrimMST 效率
 */
public class MainTest {

    public static void main(String[] args){

        String filename1 = "testWG1.txt";
        int V1 = 8;

        String filename2 = "testWG2-250.txt";
        int V2 = 250;

        String filename3 = "testWG3-1000.txt";
        int V3 = 1000;

        String filename4 = "testWG4-10000.txt";
        int V4 = 10000;

        // 文件读取
        WeightSparseGraph<Double> g1 = new WeightSparseGraph<Double>(V1, false);
        g1.readGraph(filename1);
        System.out.println( filename1 + " load successfully.");

        WeightSparseGraph<Double> g2 = new WeightSparseGraph<Double>(V2, false);
        g2.readGraph(filename2);
        System.out.println( filename2 + " load successfully.");

        WeightSparseGraph<Double> g3 = new WeightSparseGraph<Double>(V3, false);
        g3.readGraph(filename3);
        System.out.println( filename3 + " load successfully.");

        WeightSparseGraph<Double> g4 = new WeightSparseGraph<Double>(V4, false);
        g4.readGraph(filename4);
        System.out.println( filename4 + " load successfully.");

        System.out.println();


        long startTime, endTime;

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST1 = new LazyPrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST2 = new LazyPrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST3 = new LazyPrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST4 = new LazyPrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

        System.out.println();


        // Test Prim MST
        System.out.println("Test Prim MST:");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST1 = new PrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST2 = new PrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST3 = new PrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST4 = new PrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

        System.out.println();


        // Test Kruskal MST
        System.out.println("Test Kruskal MST:");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST1 = new KruskalMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST2 = new KruskalMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST3 = new KruskalMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST4 = new KruskalMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

        System.out.println();
    }



}
