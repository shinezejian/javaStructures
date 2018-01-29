package com.zejian.structures.heap;

import java.util.Arrays;

/**
 * Created by zejian on 2017/9/17.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 最小索引优先队列(下标从1开始)
 */
public class IndexMinPQ<T extends Comparable<T>> {

    private int size;//队列大小

    private int[] priorityQueue; //存储优先级元素的索引的数组,二叉堆

    private int[] qp; //维护keys中元素在二叉堆中位置

    private Object[] keys; //优先级元素

    private int capacity;

    public IndexMinPQ(int capacity){
        this.capacity = capacity + 1;
        keys = new Object[capacity+1];
        priorityQueue = new int[capacity + 1];
        qp = new int[capacity + 1];

        Arrays.fill(qp,-1);//将元素初始化为-1
    }



    public void insert(int idx , T key){
        idx++;
        checkElementIndex(idx);

        if(qp[idx] != -1){
            keys[idx] = key;
            siftUp(qp[idx]);
            siftDown(qp[idx]);
            return;
        }

        size++;
        priorityQueue[size] = idx;//优先队列中记录添加元素在keys中的下标
        qp[idx] = size;
        keys[idx] = key;
        //向上浮,维护队列的堆结构
        siftUp(size);
    }

    /**
     * 删除最小key并返回其值
     * @return
     */
    public int deleteMin(){
        if(size == 0){
            throw new IllegalArgumentException();
        }
        int indexOfMin = priorityQueue[1];
        swap(priorityQueue,1,size);
        swap(qp, priorityQueue[1], priorityQueue[size]);
        keys[priorityQueue[size]] = null;
        qp[priorityQueue[size]] = -1;
        size--;
        siftDown(1);
        return --indexOfMin;
    }


    public T min(){
        return (T) keys[priorityQueue[1]];
    }

    public int minIndex(){
        return priorityQueue[1];
    }

    /**
     * 替换某个位置的值
     * @param k
     * @param key
     */
    public void change(int k , T key){
        k++;
        checkElementIndex(k);
        keys[k] = key;
        siftUp(qp[k]);
        siftDown(qp[k]);
    }

    /**
     * 根据索引删除元素
     * @param idx
     * @return
     */
    public T delete(int idx){
        idx++;
        checkElementIndex(idx);
        //获取优先队列中要被删除元素的下标
        int index = qp[idx];
        //优先队列,与最后一个元素交换
        swap(priorityQueue,idx,size);
        //调整堆结构
        siftUp(idx);
        siftDown(idx);
        T key = (T) keys[idx];
        keys[idx] = null;
        qp[idx] = -1;
        size--;

        return key;
    }




    /**
     * 对pq队列索引值进行下沉操作
     * @param parent 指元素存储在优先队列pq中的下标
     */
    private void siftDown(int parent){
        //找到左子结点
        int child = 2 * parent;
        while (child <= size){ //确保root结点也能到达
            //判断左子结点是否大于右子结点
            if (child + 1<= size && !less(child , child+1)){
                child ++ ;
            }

            //如果父结点大于子结点,则交换
            if(less(child,parent)){
                //交换优先队列的值
                swap(priorityQueue,child,parent);
                //更新qp
                swap(qp,priorityQueue[child],priorityQueue[parent]);
                parent = child;
                child = parent*2;
            }else {
                break;
            }
        }
    }

    /**
     * 对pq队列索引值进行上沉操作
     * @param child 优先队列下标
     */
    private void siftUp(int child){
        int parent = child / 2;

        while (parent > 0){
            //判断子结点是否小于父结点,true向上浮动
            if(less(child,parent)){
                swap(priorityQueue,child,parent);//交换优先队列的值
                //更新qp数组
                swap(qp, priorityQueue[child], priorityQueue[parent]);
                child = parent;
                parent = child / 2 ;
            }else {
                break;
            }

        }


    }

    /**
     * 比较i位置的元素是否比j位置的元素小
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i , int j) {
        T key1 = (T)keys[priorityQueue[i]];
        T key2 = (T)keys[priorityQueue[j]];
        int result = key1.compareTo(key2);
        return result < 0;
    }


    /**
     * 交换索引位置的值
     * @param i
     * @param j
     */
    private void swap(int[] a, int i, int j){
        int tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(int index){
        index ++ ;
        checkElementIndex(index);
       return qp[index] != -1;
    }

    private void checkElementIndex(int index) {
        if (!(index > 0 && index < capacity))
            throw new IndexOutOfBoundsException("Index: "+ --index +", capacity: "+ --capacity);
    }

    public static void main(String[] args){
        IndexMinPQ<String> impq = new IndexMinPQ<String>(11);
        impq.insert(0, "o");
        impq.insert(6, "d");
        impq.insert(3, "g");
        impq.insert(4, "c");
        impq.insert(10, "a");

        while(!impq.isEmpty()){
            System.out.println(impq.deleteMin());
        }
    }


}
