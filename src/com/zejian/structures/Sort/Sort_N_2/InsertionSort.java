package com.zejian.structures.Sort.Sort_N_2;

import com.zejian.structures.Sort.SortTestHelper;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 插入排序算法核心思想:
 * 检查第i个元素，如果在它的左边的元素比它大，进行交换，然后继续下去与前一个元素比较，
 * 直到这个元素的左边元素比它还要小，就停止。插入排序法主要的回圈有两个变数：i和j，
 * 每一次执行这个回圈，就会将第i个数字放到左边恰当的位置去。
 *
 * 分成两组
 * 一组A已排序,一组B未排序,不断从B组中取出元素与A组元素比较,并交互位置.
 * 初始化时,第一组A只有一个元素,即第一个元素
 * 未排序组B,则为从第二个元素到最后一个元素. N-1个元素
 * 每轮循环比较后,A组元素总会增加已排序的元素,而B组总会减少1个未排序的元素
 *
 * 时间复杂度分析:
 * 在最坏情况下，数组完全逆序，插入第2个元素时要考察前1个元素，插入第3个元素时，
 * 要考虑前2个元素，……，插入第N个元素，要考虑前 N - 1 个元素。因此，最坏情况下的比较次数是
 * 1 + 2 + 3 + ... + (N - 1) = N * (N-1) / 2 ≈ N²
 * 所以最坏情况下的复杂度为 O(N²)
 * 最好情况下，数组已经是有序的，只需要N-1次比较和0次交换
 * 因此最好情况下，插入排序的时间复杂度为O(N)。
 *
 * 注意插入排序与选择排序的不同是,插入排序每次发现数组有序会提前结束,
 * 而选择排序每次总会从头到尾找出最小的元素,因此一般情况下,插入排序的效率
 * 总是高于选择排序.
 *
 * 尤其在数组有序的情况下,插入排序算法的效率十分高,达到O(N)级别.
 *
 */
public class InsertionSort {

    /**
     * 插入排序算法,未优化版,每次比较都进行swap
     * @param array
     */
    public static <T extends Comparable<T>> void sort1(T[] array){
        assert array != null;
        //从第2个元素开始
        for (int i = 1; i < array.length ; i++) {
            //元素进行比较排序
            for (int j = i; j > 0 && array[j].compareTo(array[j-1]) < 0 ; j--) {
                 swap(array,j,j-1);
            }
        }
    }

    /**
     * 插入排序算法,优化版,在确定交换的元素后再进行交换位置
     * 每次比较时先不交换,在最后确定最终交换位置时再执行交换.
     * @param array
     */
    public static <T extends Comparable<T>> void sort(T[] array){
        assert array != null;
        //从第2个元素开始
        for (int i = 1; i < array.length ; i++) {
            T e = array[i];//先记录要比较的元素,确定其交换的位置后再移动到对应位置
            int j;//记录j,最后交换时使用
            //普通写法
//            for (j = i; j > 0 ; j--) {
//                if(array[j-1].compareTo(e) > 0){
//                    array[j] = array[j-1];
//                }
//            }
            //更优雅的写法,(array[j-1].compareTo(e) > 0)这个比较确保了发现前面没有更大元素就停止,这点与选择排序不同.
            for (j = i; j > 0 && array[j-1].compareTo(e) > 0; j--) {
              array[j] = array[j-1];
            }
            array[j] = e;
        }
    }


//    public static <T extends Comparable<T>> void sort(T[] array,int l , int r){
//        assert array != null;
//
//        //从第2个元素开始
//        for (int i = l+1; i <=r ; i++) {
//            T e = array[i];//先记录要比较的元素,确定其交换的位置后再移动到对应位置
//            int j;//记录j,最后交换时使用
//            //更优雅的写法
//            //(array[j-1].compareTo(e) > 0)这个比较确保了发现前面没有更大元素就停止,这点与选择排序不同.
//            for (j = i; j > l && array[j-1].compareTo(e) > 0; j--) {
//                array[j] = array[j-1];
//            }
//            array[j] = e;
//        }
//    }


    // 对arr[l...r]的区间使用InsertionSort排序
    public static <T extends Comparable<T>> void sort(T[] arr, int l, int r){

        for( int i = l + 1 ; i <= r ; i ++ ){
            T e = arr[i];
            int j = i;
            for( ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

//        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//        InsertionSort.sort2(arr);
//        for( int i = 0 ; i < arr.length ; i ++ ){
//            System.out.print(arr[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.InsertionSort", arr);


    }
}
