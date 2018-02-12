package com.zejian.structures.Sort.Sort_N_2;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 冒泡排序算法原理:比较两个相邻的元素，将值大的元素交换至右端。
 * 核心思想：依次比较相邻的两个数，将小数放在前面，大数放在后面。即在第一趟：首先比较第1个和第2个数，
 * 将小数放前，大数放后。然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，
 * 将小数放前，大数放后。重复第一趟步骤，直至全部排序完成。
 *
 *
 * 时间复杂度分析:
 * 最好的情况是只需扫描一趟,比较 N 次,没有数据移动,时间复杂度为O(N)
 * 最坏的情况是数据序列随机排列或者反序排列,需要 N-1 趟扫描 ,那么对于移动数据和比较次数都如下
 * 第一趟:比较 N - 1 次
 * 第二趟:比较 N - 2 次
 * 第三趟:比较 N - 3 次
 * .....
 * 第N-1趟:比较 1 次
 *
 * N-1趟的总次数: (N-1) + (N-2) + (N-3) + ... + 1 = (N-1)*(N-2)/2
 * 注意对于移动数据和比较次数都是(N-1)*(N-2)/2 ≈ N²
 * 所以最坏的情况下时间杂度为 O(N²)
 * 即冒泡排序的时间复杂度在O(N) ~ O(N²) 之间.
 * 同时冒泡排序算法是稳定的.
 */
public class BubbleSort {

    /**
     * 未优化版本
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort1(T[] array){

        assert array != null;

        for (int i = 0; i <array.length ; i++) {
            //这里限制边界需要注意,长度要-1,防止数组越界
            for (int j = 0; j < array.length - 1 - i ; j++) {
                if(array[j].compareTo(array[j+1]) > 0){
                    swap(array,j,j+1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化版,思路,如果在某轮比较中,再没有发生过交换,那么说明该数组元素已是
     * 有序,没有必要再进行循环操作.这里我们通过一个flag标志符记录.
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] array){

        assert array != null;
        boolean exchange = true;//标记是否发生元素交换
        for (int i = 0; i <array.length && exchange ; i++) {
            exchange = false;
            //这里限制边界需要注意,长度要-1,防止数组越界
            for (int j = 0; j < array.length - 1 - i ; j++) {
                if(array[j].compareTo(array[j+1]) > 0){
                    swap(array,j,j+1);
                    exchange = true;
                }
            }
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {

        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
        BubbleSort.sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();

//
//        // 测试排序算法辅助函数
//        int N = 20000;
//        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
//        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.SelectionSort", arr);

    }
}
