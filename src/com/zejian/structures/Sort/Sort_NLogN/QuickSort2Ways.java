package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.SortTestHelper;
import com.zejian.structures.Sort.Sort_N_2.InsertionSort;

import java.util.Arrays;

/**
 * Created by zejian on 2018/2/14.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 双路快速排序算法:主要解决排序数据中存在大量重复值的情景,因为这种情况下使用前面实现
 * 的普通快速排序,会把等于v的元素归到 >=v 一方 而造成 < v 的一方数据过少
 *   arr[l....j] < v 而  [j+1 ... r] >= v [j+1 ... r]包含大量重复的v元素
 * 从而造成分区时树结构失去平衡性,最终可能使普通快速排序由 N*logN 退化为 O(N²)
 *
 * 双路快速排序就是为了解决该问题而出现的.其基本思路与普通快速排序不同,
 * 其将分区设置为 arr[l+1...i) <= v; arr(j...r] >= v
 * 这样的话无论是小于v的左侧数组还是大于v的右侧数组都包含了等于v的情况,在这种情况下
 * 即使出现了大量重复元素,也不会导致左右两边分区的数组元素个数差距过大,造成排序性能
 * 效率过低.
 * 缺点是重复元素值仍会被交换位置.
 *
 * 时间复杂度依然是 N*logN 级别
 *
 *
 */
public class QuickSort2Ways {

    /**
     * 双路快速排序
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void quickSort2Ways(T[] arr){
        assert arr !=null;
        int len = arr.length;
        quickSort2Ways(arr,0,len-1);
    }

    private static <T extends Comparable<T>> void quickSort2Ways(T[] arr , int l , int r){
//        //优化:数组个数小于16使用插入排序进行
        if(r-l < 16){
            InsertionSort.sort(arr,l,r);
            return;
        }
//        if( l >= r){
//            return;
//        }

        //计算并获取基准点的下标
        int p = partition(arr,l,r);
        //继续递归执行
        quickSort2Ways(arr,l,p-1);
        quickSort2Ways(arr,p+1,r);

    }

    /**
     * 双路快速排序的分区采用不同的分区思路
     * @return
     */
    private static <T extends Comparable<T>> int partition(T[] arr , int l , int r){

        //随机选取一个基准点并与最左侧位置的元素交换
        QuickSort.swap(arr,l,(int)(Math.random()*(r - l))+l);

        //获取基准点
        T e = arr[l];

        //下标从l+1开始
        int i = l+1; //arr[l+1 ... i) <= v
        int j = r; //arr(j...r] >= v

        //进行分区操作
        while (true){
            //如果当前元素比基准点元素v小,不动,继续循环直到找到比较v大的停止
            while ( i <= r && arr[i].compareTo(e) < 0) i++;
            //如果当前元素比基准点元素v大,不动,继续循环直到找到比较v小的停止
            while ( j > l && arr[j].compareTo(e) > 0) j--;

            //如果此时i>j说明已分区完成
            if(i > j){
                break;
            }

            //交换i与j的元素
            QuickSort.swap(arr,i,j);
            i++;
            j--;
        }


        //最后交换基准元素的位置,这里之所以使用j,是因为上述while结束时,i>j
        //此时左侧分区j肯定是 arr[l+1...j] 而右侧分区肯定是arr[i...r]
        QuickSort.swap(arr,l,j);

        return j;
    }


    public static void main(String[] args) {
//        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//        QuickSort2Ways.quickSort2Ways(arr);
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



    }

}
