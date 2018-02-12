package com.zejian.structures.Sort.Sort_N_2;

import com.zejian.structures.Sort.SortTestHelper;

import java.util.Arrays;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 测试插入排序和选择排序的性能
 * 优化后，插入排序比选择排序性能略好
 * 对于有序性强的数组，插入排序远远优于选择排序
 *
 */
public class MainTest {

    public static void main(String[] args) {

        int N = 20000;

        // 测试1 一般测试
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.SelectionSort", arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.InsertionSort", arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.ShellSort", arr3);

        System.out.println();


        // 测试2 有序性更强的测试
        System.out.println("Test for more ordered random array, size = " + N + " , random range [0,3]");

        arr1 = SortTestHelper.generateRandomArray(N, 0, 3);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.SelectionSort", arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.InsertionSort", arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.ShellSort", arr3);

        System.out.println();


        // 测试3 测试近乎有序的数组
        int swapTimes = 100;
        System.out.println("测试近乎有序的数组, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.SelectionSort", arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.InsertionSort", arr2);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.ShellSort", arr3);

        return;
    }
}
