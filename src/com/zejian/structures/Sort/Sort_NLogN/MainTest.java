package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.SortTestHelper;

import java.util.Arrays;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 测试归并排序,普通快速排序 双路快速排序 三路快速排序的性能效率
 *
 */
public class MainTest {

    public static void main(String[] args) {

        int N = 200000;

        // 测试1 一般测试
        System.out.println("测试随机无序的数组, size = " + N + " , random range [0, " + N + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort","sort",arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort2Ways","quickSort2Ways",arr3);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort3Ways","quickSort3Ways",arr4);

        System.out.println();


        // 测试2 有序性更强的测试
        System.out.println("测试存在大量重复元素的数组排序, size = " + N + " , random range [0,3]");

        arr1 = SortTestHelper.generateRandomArray(N, 0, 3);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort","sort",arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort2Ways","quickSort2Ways",arr3);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort3Ways","quickSort3Ways",arr4);

        System.out.println();


        // 测试3 测试近乎有序的数组
        int swapTimes = 100;
        System.out.println("测试近乎有序的数组的数组排序, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort","sort",arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort2Ways","quickSort2Ways",arr3);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.QuickSort3Ways","quickSort3Ways",arr4);

    }
}
