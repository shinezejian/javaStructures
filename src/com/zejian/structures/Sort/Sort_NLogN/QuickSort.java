package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.SortTestHelper;
import com.zejian.structures.Sort.Sort_N_2.InsertionSort;

import java.util.Arrays;

/**
 * Created by zejian on 2018/2/13.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 普通快速排序算法基本思路
 * 1.从数组arr中随机挑选一个元素,定为基准点arr[p]
 * 2.对数组arr进行从新排序,使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p],
 *   这个操作过程称为分区(partition)
 * 3.使用递归的方式重复步骤1和2分别处理左侧数组arr[l...p-1] 和 右侧数组arr[p+1...r],
 *   直到无法分区,此时数组排序完成.
 *
 *   arr[l+1...j] < v < arr[j+1...i)
 *   时间复杂度分析:
 *   快速排序每次将待排序数组分为两个部分，在理想状况下，
 *   每一次都将待排序数组划分成等长两个部分，则需要logN次划分
 *   即存在 logN 层,而每层需要处理的元素个数都是一样的即N,此时时间
 *   复杂度为 N*logN
 *   而在最坏情况下，即数组已经有序或大致有序的情况下，每次划分
 *   只能减少一个元素，快速排序将不幸退化为冒泡排序，因为每次分
 *   区只可能交互一个元素,时间复杂度为O(N²)
 *   所以快速排序时间复杂度下界为O(N*logN)，最坏情况为O(N²)。
 *   在实际应用中，快速排序的平均时间复杂度为O(nlogn)。
 */
public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] arr){
        assert arr != null;
        quickSort(arr,0,arr.length-1);
    }

    /**
     * 使用递归实现快速排序
     * @param arr
     * @param l
     * @param r
     * @param <T>
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr,int l,int r){
        //如果排序的数组元素个数少于16直接使用插入排序即可(优化点)
        if(r-l < 16){
            InsertionSort.sort(arr,l,r);
            return;
        }

//        if (l >= r){
//            return;
//        }

        //获取分区的基准点的数组下标p
        int p = partition(arr,l,r);
        //使用递归的方式继续进行分区
        quickSort(arr,l,p-1); //处理左侧数组
        quickSort(arr,p+1,r); //处理右侧数组

    }

    /**
     * 进行分区操作
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     */
    private static <T extends Comparable<T>> int partition(T[] arr,int l ,int r){
        //随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        //之所以要随机选择,是为了防止在出现大规模近乎有序的数据时若只取左边的值可能会
        //造成快速排序算法退化到O(N²)级别,与二叉树退化成链表是同样的道理.
        swap(arr,l,(int)(Math.random()*(r-l+1))+l);
        //确定分组元素
        T e = arr[l];
        // 进行分区操作,使用得 arr[l+1...j] < v < arr[j+1...i) i是当前正在判断的元素
        int j = l;
        //注意i需要从1+1起
        for (int i = l+1; i <= r ; i++) {
            //当前元素比基准点元素小,移动到e的左侧.
            //当前元素比基准点元素大,无需移动.
            if(arr[i].compareTo(e) < 0){
                swap(arr,j+1,i);
                j++;//j往后移动一位
            }
        }

        //最后将基准点移动到j的位置,形成arr[l...j-1] < arr[j] < arr[j+1...r]
        swap(arr,j,l);
        //返回基准点下标
        return j;
    }


    public static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
        QuickSort.sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();

        //对于近乎有序的数组,优化后的归并排序效率更高
        int N = 2000;
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(N, 4);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sort", arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort","sort",arr3);

    }
}
