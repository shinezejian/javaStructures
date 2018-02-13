package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.Sort_N_2.InsertionSort;

/**
 * Created by zejian on 2018/2/13.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 自底向上的归并排序算法
 * 自底向上的归并排序算法的思想就是数组中先一个一个归并成两两有序的数组，
 * 两两有序的数组归并成四个四个有序的数组，然后四个四个有序的序列归并八
 * 个八个有序的数组，以此类推，直到，归并的长度大于整个数组的长度，此时
 * 整个数组有序。需要注意的是数组按照归并长度划分，最后一个子数组可能不
 * 满足长度要求，这种情况需要注意。自顶下下的归并排序算法一般用递归
 * 来实现，而自底向上可以用循环来实现。
 *                                         ∧
 *          [1 2 3 4 5 6 7 8]              ‖上    最后合并成一个有序数组
 *        [1 2 5 8]  [3 4 6 7]             ‖向    再归并成四四分组
 *       [5 8] [1 2] [6 7] [3 4]           ‖底    先按两两分组,然后排序
 *   [8] [5] [1] [2] [7] [6] [3] [4]       ‖自    一一分组然后归并
 *         [8 5 1 2 7 6 3 4]               ‖      未分组
 *
 */
public class MergeSortBottomUp {


    public static <T extends Comparable<T>> void sort(T[] array){
        assert array != null;
        int len = array.length;
        //分组循环排序,sz表示每组分组的个数,第一轮分组数组个数为1
        for (int sz = 1; sz < len ; sz += sz) {
            //对分组进行两两归并
            //由于每次归并的是两个分组个数,所以归并的间隔是 sz + sz = 2 * sz
            for (int i = 0; i < len ; i += 2*sz) {
                //i+sz 代表分组的下标最大值,由于下标从0开始,所以需要i+sz-1
                //用于归并的第二组起始点是i+sz,结束点:i + sz - 1 + sz
                //对array[i....i+sz-1] 和 array[i+sz.....i+sz+sz-1]进行归并
                int l = i;
                int mid = i+sz-1;
                int r = i+sz+sz-1;
                //进行合并操作,Math.min(r,len-1)防止数组越界,存在最后一个分组不符合整数的情况
                MergeSort.merge(array,l,mid,Math.min(r,len-1));
            }
        }
    }

    /**
     * 优化版
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sortOptimize(T[] array){
        assert array != null;
        int len = array.length;

        //优化: 对于小数组, 使用插入排序优化sz是分组的大小
        for( int sz = 0 ; sz < len ; sz += 16 ){
            //Math.min(i+16-1,len-1)防止数组越界,存在最后一个分组不符合整数的情况
            InsertionSort.sort(array,sz,Math.min(sz+16-1,len-1));
        }

        //分组循环排序,sz表示每组分组的个数,第一轮分组数组个数为1
        for (int sz = 16; sz < len ; sz += sz) {
            //对分组进行两两归并
            //由于每次归并的是两个分组个数,所以归并的间隔是 sz + sz = 2 * sz
            for (int i = 0; i < len ; i += 2*sz) {
                //i+sz 代表分组的下标最大值,由于下标从0开始,所以需要i+sz-1
                //用于归并的第二组起始点是i+sz,结束点:i + sz - 1 + sz
                //对array[i....i+sz-1] 和 array[i+sz.....i+sz+sz-1]进行归并
                int l = i;
                int mid = i+sz-1;
                int r = i+sz+sz-1;
                /**
                 * 优化:对于arr[mid] <= arr[mid+1]的情况,不进行merge
                 */
                if(array[mid].compareTo(array[mid+1]) > 0) {
                    //进行合并操作
                    MergeSort.merge(array, l, mid, Math.min(r, len - 1));
                }
            }
        }
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
        MergeSortBottomUp.sortOptimize(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }



}
