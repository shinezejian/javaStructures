package com.zejian.structures.heap;

import java.util.Arrays;

/**
 * Created by zejian on 2017/9/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 最大堆实现
 */
public class MaxHeap<T extends Comparable<T>> {

    private T[] queue;
    private int size = 0;

    public MaxHeap(int maxLength){
        queue = (T[]) new Comparable[maxLength+1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /**
     * 添加元素
     * @param key
     */
    public void insert(T key){
        if (key == null) {
            throw new NullPointerException("key is not be null !");
        }

        if (size >= queue.length) {
            grow();//扩容
        }
        queue[++size] = key;
        //上浮元素
        siftUp(size);
    }

    /**
     * 删除最大元素
     * @return
     */
    public T deleteMax(){
        if(size == 0){
            return null;
        }
        T key = queue[1];
        exch(1,size);
        queue[size] = null;
        size--;
        siftDown(1);
        return key;
    }

    /**
     * 下沉操作
     */
    private void siftDown(int k){
       while (2*k <= size){
           int j = 2*k ;//左子节点

           //判断左子节点是否比右子节点小
           if(j + 1 < size && less(j,j+1)){
               j++;//说明右子节点比左子节点大,使用右子节点与父节点交换
           }

           //如果父节点大于子节点,那么就不用交换了
           if(!less(k,j)){
              break;
           }

           //否则执行元素交换
           exch(k,j);

           k = j;
       }
    }

    /**
     * 上浮操作
     */
    private void siftUp(int k){
        //上浮时,K位置的元素与父元素比较
        while(k > 1 && less(k/2,k) ){
            exch(k/2,k);
            k = k/2;
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

//    /**
//     * 利用堆排序(数组下标从1开始,第0位不存储数据)
//     * 1.先构建最大堆
//     * 2.利用siftDown算法排序
//     *
//     * @param array
//     */
//    public void heapSort(T[] array){
//        if(array == null){
//            throw new RuntimeException("array can't be null!");
//        }
//
//        int len = array.length;
//        //先把array构建成最大堆,从第一个非叶子结点开始即i=len/2
//
//        for (int i=len/2 ; i >= 1 ; i--){
//            siftDown(array,i,len);
//        }
//
//        //进行堆排序
//        while (len > 1){
//            exch(array,1,--len);
//            siftDown(array,1,len);
//        }
//
//
//    }
//
//    private void siftDown(T[] array,int i,int len){
////        int left = 2*i;
////        while (left <= len){
////            if((left + 1) < len && less(array,left,left+1)){
////                left ++;            //array[left] < array[left+1]
////            }
////
////            //子结点小于父结点i,那么就不用交换
////            if(!less(array,i,left)){ //array[i] > array[left]
////                break;
////            }
////
////            exch(array,left,i);
////
////            i = left;
////            left = i * 2;
////        }
//
//        while(2*i<=len){
//            int j = 2*i;
//
//            if(less(array,j,j+1)){
//                j++;//找到两个子节点中值比较大的那个
//            }
//
//            if(!less(array,i,j)){
//                break;
//            }
//
//            exch(array,i,j);
//            i = j;
//        }
//    }

    /**
     * 比较i位置的元素是否比j位置的元素小
     * @param i
     * @param j
     * @return
     */
    private boolean less(T[] array,int i , int j){
        return array[i].compareTo(array[j]) < 0;
    }

    /**
     * 交换两个位置的值
     * @param i
     * @param j
     */
    private void exch(T[] array,int i , int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args){
        MaxHeap<String> MaxHeap = new MaxHeap<>(10);
        MaxHeap.insert("z");
        MaxHeap.insert("a");
        MaxHeap.insert("q");
        MaxHeap.insert("b");
        MaxHeap.insert("x");
        MaxHeap.insert("c");
        MaxHeap.insert("d");
        MaxHeap.insert("y");
        MaxHeap.insert("n");

        StringBuilder sb = new StringBuilder();
        while(!MaxHeap.isEmpty()){
            sb.append(MaxHeap.deleteMax()+",");
        }
        String min = sb.toString();

        System.out.println(min.substring(0,min.length()-1));


//        String[] a = {null,"c","a","o","w","z","k"};
//        System.out.println("排序前:"+ Arrays.toString(a));
//        MaxHeap.heapSort(a);
//        System.out.println("排序后:"+ Arrays.toString(a));
    }
}
