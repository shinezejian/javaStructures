package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.SortTestHelper;
import com.zejian.structures.Sort.Sort_N_2.InsertionSort;

import java.util.Arrays;

/**
 * Created by zejian on 2018/2/14.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 三路快速排序算法:也是为了解决排序元素中存在大规模重复元素的问题.只不过三路排序算法比
 * 双路快速排序算法性能更佳,因为针对于重复元素,三路快速排序不会再次进行交换位置或者纳入
 * 下一轮快速排序中.
 * 算法核心思想是把需要排序的数组分成3部分,分别是
 * arr[l+1 ... lt] arr[v,v,v,v,v,v...] arr[gt ... r]
 * 经过一轮分区后,再下一轮中只对arr[l+1 ... lt] 和 arr[gt ... r] 再次分区
 * 这样对于大量重复元素就无需处理,可以优化不少性能消耗
 *
 * 时间复杂度仍是 N*logN
 *
 * 三路快速排,无论是在大量重复元素的数据或者随机无序的大规模的数据或者近乎有序的大规模数据
 * 都能表现高效的处理优势.
 */
public class QuickSort3Ways {

    public static <T extends Comparable<T>> void quickSort3Ways(T[] arr) {
        assert arr != null;
        int len = arr.length;
        quickSort3Ways(arr,0,len-1);
    }


    public static <T extends Comparable<T>> void quickSort3Ways(T[] arr, int l, int r) {
        //优化
        if( r-l < 16){
            InsertionSort.sort(arr,l,r);
            return;
        }

//        if( l >= r){
//            return;
//        }

        //partition

        //随机选取一个基准点并与最左侧位置的元素交换
        QuickSort.swap(arr,l,(int)(Math.random()*(r - l))+l);

        //获取基准点
        T e = arr[l];

        int lt = l ; //arr[l+1 ... lt] < v
        int gt = r+1 ; //arr[gt  ... r] > v
        int i = l+1; //arr[lt+1...i) == v
        //由于gt不断减少,gt右边是已分区好的元素,所以i<gt时才执行
        while (i < gt){
            //如果当前元素小于e,则把当前元素与lt+1位的元素交换
            if (arr[i].compareTo(e) < 0){
                QuickSort.swap(arr,i,lt+1);
                i++;
                lt++;
            }else if(arr[i].compareTo(e) > 0){
                QuickSort.swap(arr,i,gt-1);
                gt--;
            }else {// ==v 的情况不用操作
                i++;
            }
        }
        //交换基准的位置
        QuickSort.swap(arr,l,lt);

        //递归进行三路快排
        quickSort3Ways(arr,l,lt-1);
        quickSort3Ways(arr,gt,r);

    }



    public static void main(String[] args) {
//        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//        QuickSort3Ways.quickSort3Ways(arr);
//        for( int i = 0 ; i < arr.length ; i ++ ){
//            System.out.print(arr[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        //对于近乎有序的数组,优化后的归并排序效率更高
        int N = 200000;
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(N, 4);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        System.out.println("处理近乎有序的数组:");
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort","sort",arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort2Ways","quickSort2Ways",arr3);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort3Ways","quickSort3Ways",arr4);



    }
}
