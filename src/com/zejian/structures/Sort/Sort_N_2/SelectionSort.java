package com.zejian.structures.Sort.Sort_N_2;

import com.zejian.structures.Sort.SortTestHelper;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 选择排序算法核心思想:首先,在数组中找到最小的那个元素,将其与数组中第一个元素交换位置
 * (如果恰好第一个位置就是最小元素那么它将与自己交换位置).第一轮结束,接着在剩余的元素中
 * 寻找最小的那个元素,再与第二个位置的元素交换位置,第二轮结束.如此重复,直到数组排序完成.
 *
 * 时间复杂度分析:对于长度为N的数组,选择排序大概需要 N²/2 次比较和 N 次交换
 * 第一次比较需要 : N-1 次
 * 第二次比较需要 : N-2 次
 * 第三次比较需要 : N-3 次
 * .....
 * 第N-1次比较需要 : 1 次
 *
 * 那么对于长度为N的数组进行排序,大约需要
 *  (N-1) + (N-2) + (N-3) + ... + 1 = N * (N-1) / 2
 * 所以选择排序总时间复杂度为
 *  N * (N-1) / 2 + N ≈ N²/2
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {
        assert array != null;

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int k = i + 1; k < array.length; k++) {
                //找到最小的元素的索引并赋值给minIndex
                if (array[minIndex] > array[k]) {
                    minIndex = k;
                }
            }
            //交换位置
            swap(array, i, minIndex);
        }
    }

    /**
     * 比较实现了Comparable的数据类型
     * @param array
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        assert array != null;

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int k = i + 1; k < array.length; k++) {
                //找到最小的元素的索引并赋值给minIndex
                if (array[minIndex].compareTo(array[k]) > 0) {
                    minIndex = k;
                }
            }
            //交换位置
            swap(array, i, minIndex);
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {

//        int[] arr = {10,9,8,7,6,5,4,3,2,1};
//        SelectionSort.selectionSort(arr);
//        for( int i = 0 ; i < arr.length ; i ++ ){
//            System.out.print(arr[i]);
//            System.out.print(' ');
//        }
//        System.out.println();


        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.SelectionSort", arr);

    }

}
