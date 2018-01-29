package com.zejian.structures.heap;

import java.util.Arrays;

/**
 * Created by zejian on 2017/9/18.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 最小堆的实现
 */
public class MinHeap<T extends Comparable<T>> {

    private int size = 0;
    private T[] queue ;

    public MinHeap(int capacity){
        queue = (T[]) new Comparable[capacity+1];
    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    public int size(){
        return size;
    }

    public void insert(T key){
        if (key == null) {
            throw new NullPointerException("key is not be null !");
        }

        if (size >= queue.length) {
            grow();//扩容
        }

        queue[++size] = key;
        siftUp(size);
    }

    public T deleteMin(){
        if(size == 0){
            return null;
        }
        T oldKey = queue[1];
        exch(1,size);
        queue[size] = null;
        size--;
        siftDowm(1);
        return oldKey;
    }

    private void siftDowm(int k){
        while (2*k <= size){
            int j = 2*k ;//左子节点

            if(j + 1 <= size && !less(j,j+1)){
                j++;
            }

            //如果父节点小于子节点,那么就不用交换
            if(less(k,j)){
                break;
            }

            //否则执行元素交换
            exch(k,j);

            k = j;
        }
    }

    private void siftUp(int k){
        //k/2找到父结点
        while (k  > 1 && !less(k/2,k)){
            exch(k/2,k);
            k  = k/2;
        }
    }


    /**
     * 比较i位置的元素是否比j位置的元素小
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i , int j){
        return queue[i].compareTo(queue[j]) < 0;
    }

    /**
     * 交换两个位置的值
     * @param i
     * @param j
     */
    private void exch(int i , int j){
        T temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    private void grow() {
        int oldCapacity = queue.length;
        // 如果小于64,那么就拓展1倍,否则拓展1/2倍
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        queue = Arrays.copyOf(queue, newCapacity);
    }

    public static void main(String[] args){
        MinHeap<String> minHeap = new MinHeap<>(10);
        minHeap.insert("z");
        minHeap.insert("a");
        minHeap.insert("q");
        minHeap.insert("b");
        minHeap.insert("x");
        minHeap.insert("c");

        StringBuilder sb = new StringBuilder();
        while(!minHeap.isEmpty()){
            sb.append(minHeap.deleteMin()+",");
        }
        String min = sb.toString();

        System.out.print(min.substring(0,min.length()-1));
    }

}
