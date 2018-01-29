package com.zejian.structures.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zejian on 2017/9/19.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 最大堆排序(数组下标从0开始)
 */
public class MaxHeapSort<T extends Comparable<T>> {

    /**
     * 堆排序,每次取最大的排到数组尾部
     * @param array 注意索引从0开始
     * @return
     */
    public void heapSort(T[] array){
        if(array == null){
            throw new RuntimeException("array can't be null!");
        }

        int len = array.length;
        //先把array构建成最大堆,从第一个非叶子结点开始即i=len/2
        for(int i=len/2 ; i >= 0 ; i--){
            siftDownSort(array,i,len);
        }

        //堆排序
        while (len > 0){
            swap(array,0,--len);//注意索引从0开始
            siftDownSort(array,0,len);
        }
    }

    private void siftDownSort(T[] array,int i,int len){

        while ((2*i + 1) < len){
            int left = 2*i + 1;
            if((left + 1) < len && less(array,left,left+1)){
                left ++;            //array[left] < array[left+1]
            }

            //子结点小于父结点i,那么就不用交换
            if(!less(array,i,left)){ //array[i] > array[left]
                break;
            }

            swap(array,left,i);
            i = left;
        }
    }

    private void swap(T[] array,int i , int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean less(T[]a,int i,int j){
        return a[i].compareTo(a[j])<0;

    }


//
//    public static void main(String[] args){
//        MaxHeapSort<String> maxHeapSort = new MaxHeapSort<>();
//        String[] a = {"c","a","o","w","z","k"};
//        System.out.println("排序前:"+ Arrays.toString(a));
//        maxHeapSort.heapSort(a);
//        System.out.println("排序后:"+ Arrays.toString(a));
//
//        System.out.println("分割线--------------------------------------------");
//        MaxHeapSort<Integer> maxHeapSort2 = new MaxHeapSort<>();
//        Integer[] a2 = {0,88,10,22,55,11,6};
//        System.out.println("排序前:"+ Arrays.toString(a2));
//        maxHeapSort2.heapSort(a2);
//        System.out.println("排序后:"+ Arrays.toString(a2));
//    }

    public static void main(String args[]) {
        List<A> list = new ArrayList();
        list.add(new A(10));
        list.add(new A(20));
        list.add(new A(8));
        list.add(new A(3));
        list.add(new A(80));

        Collections.sort(list, new Comparator<A>() {
            @Override
            public int compare(A lhs, A rhs) {
                return lhs.age - rhs.age;
            }
        });

        for (int i =0; i < list.size();i++){
            System.out.println(list.get(i).age);
        }
    }
}

class A{
    int age = 0;
    A(int age){
        this.age =age;
    }
}
