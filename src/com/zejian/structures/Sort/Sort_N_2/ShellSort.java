package com.zejian.structures.Sort.Sort_N_2;

import com.zejian.structures.Sort.SortTestHelper;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 希尔排序核心思想:希尔排序是一种基于插入排序优化的排序算法.其思想是利用分组的方式,对每个
 * 小分组进行插入排序,而这个分组的区间长度为h,而由h区间分组而成的小组是相互独立的,我们在
 * 小组内部执行插入排序操作使用该小组有序.然后使h的值变小,再次执行上述操作,直到h=1为止.
 * 至于h该如何取值,是一个数学的难题,这里我们使用推荐的增量序列
 * increment sequence: 1, 4, 13, 40, 121, 364, 1093...
 * 即 h = 3*h + 1
 *
 * 例如我们需要对以下数组进行排序
 * 8 9 2 7 3 5 4 6
 * 假设我们的h=4 , 那么分组将会是[8 3] [9 5] [2 4] [7 6]
 * 然后对每个小组进行单独的插入排序,即排序后如下
 * [3 8] [5 9] [2 4] [6 7]
 * 再第1轮完成后合并 3 8 5 9 2 4 6 7
 * 然后将h再减少, h = h / 3 再进行如上操作
 * 显然 h = h / 3 = 1 此时h已为1,无法更小了,那么希尔排序的工作就基本完成,
 * 同时当h=1时也意味着该排序完全变为插入排序算法,也就是说,当h=1时,我们只需要对
 * 刚才经过希尔操作后数组进行最后一次插入排序就可以使用数组完全有序了
 * 3 8 5 9 2 4 6 7
 * 插入排序后(h=1)
 * 2 3 4 5 6 7 8 9
 *
 * 之所以能这样操作,是因为无序数组经过希尔操作后已变得几乎有序,此时当h=1时变为插入排序的
 * 排序效率也非常高.当排序数组的数据规模十分大时,希尔排序的更能体现出优势.
 * 之所以需要希尔排序算法,是因为对于大规模乱序的数组插入排序的效率会显得很低,因为它只会交
 * 换相邻的元素,元素只能一点一点从数组的一端移动到另外一端.如对10000个数据排序恰好最小的
 * 元素在数组的末尾,那么操作时只能从尾部一点点向前移动,即经常N-1次操作.如果在此之前对数组
 * 进行希尔操作,直到h=1时,数组将会变得近乎有序,那么当h=1时执行插入排序的效率就非常高了.
 *
 * 希尔排序算法的时间复杂小于O(N^2),达到O(N^3/2)或O(N^4/3)等...,已突破N^2的瓶颈
 */
public class ShellSort {

    /**
     * 希尔排序算法
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] array){
        assert array != null;
        int h = 1;
        int N = array.length;
        //根据增量序列取值: 1, 4, 13, 40, 121, 364, 1093...
        //h为分组的区间
        while (h < N/3){
            h = 3*h + 1;
        }
        //最后1轮即h=1时,对整个数组执行插入排序算法
        while (h>=1){
            //对分组进行插入排序
            for (int i = h; i < N ; i++) {
                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                T e = array[i];
                int j = i;
                for (; j >= h && e.compareTo(array[j-h]) < 0; j -= h) {
                    array[j] = array[j-h];
                }
                //注意j的值已减去h,这里无需执行j-h操作
                array[j] = e;
            }

            h /= 3;

        }

    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {

//        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//        ShellSort.sort(arr);
//        for( int i = 0 ; i < arr.length ; i ++ ){
//            System.out.print(arr[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_N_2.ShellSort", arr);


    }

}
