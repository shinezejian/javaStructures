package com.zejian.structures.Sort.Sort_NLogN;

import com.zejian.structures.Sort.SortTestHelper;
import com.zejian.structures.Sort.Sort_N_2.InsertionSort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by zejian on 2018/2/12.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 归并排序算法,采用的是分而治之的策略.其核心思想是先把要排序的数组元素不断得进行一分为二,
 * 直到每个分组只有一个元素时,由于该分组只有一个元素就本身就是有序的数组,然后再将有序的数组
 * 两两合并为新的有序数组,合并完成后如果还有分组就重复前面的操作将有序数组再次合并为新的有
 * 序数组,直到合并为一个数组为止.最终数组就是有序数组.如下演示:
 *  8 5 1 2 7 6 3 4 其中l=0 r=7 , mid=(l+r)/2 =3
 * 根据下标3分为两组: [8 5 1 2] [7 6 3 4]
 *
 * 注意:先处理左边分组(继续分组操作直到分组的元素个数为1)
 * [8 5 1 2] ==> L=0 R=3 mid=(l+r)/2=1
 * [8 5] [1 2]
 * [8 5] ==> L=0 R=1 mid=0;
 * [8] [5] ==> L=0 R=0 L>=R 分组结束
 * 对有序数组[8] [5]进行归并[5 8]
 * [1 2] ==> L=2 R=3 mid=2
 * [1] [2] ==> L=2 R=2 L>=R 分组结束
 * 对有序数组[1] [2]进行归并[1 2]
 * 再对有序数组[5 8] [1 2]进行合并[1 2 5 8]
 * 左边最终处理结果:[1 2 5 8]
 *
 * 再处理右边分组
 * [7 6 3 4] ==> L=4 R=7 mid=5
 * [7 6] [3 4]
 * [7 6] ==> L=4 R=5 mid=4
 * [7] [6] ==> L=4 R=4 L>=R分组结束
 * 合并有序数组[7] [6] => [6 7]
 * 同理: [3] [4] => [3 4]
 * 合并有序数组[6 7] [3 4] ==> [3 4 6 7]
 * 右边处理完成
 *
 * 最终合并有序数组[1 2 5 8] 和 [3 4 6 7]
 * [1 2 3 4 5 6 7 8]
 *
 *
 * 归并排序时间复杂度分析:
 * 对于数组进行分组直到数组元素个数为1,采用的是类似与二叉树查找的操作,因此假设数组元素个数为N,
 * 那么N个元素分组后最多有 ㏒N 层.由于每层分组后最终都需要对数组执行归并操作,而每层处理的归并
 * 操作(该操作就是让数组中元素交换位置)总元素是是固定的为N.
 * 因此归并排序的时间复杂度为 N * ㏒N
 *
 *             [8 5 1 2 7 6 3 4]
 *  1层       [8 5 1 2] [7 6 3 4]
 *  2层      [8 5] [1 2] [7 6] [3 4]
 *  3层   [8] [5] [1] [2] [7] [6] [3] [4]
 *                  执行归并操作
 *         [5 8]  [1 2]   [6 7]   [3 4]    每次归并操作元素个数为N
 *          [1 2 5 8]       [3 4 6 7]
 *              [1 2 3 4 5 6 7 8]
 *
 *
 *  归并排序算法和快速排序算法是java.util.Arrays中使用的排序算。
 *  对于一般的基本数据类型，Arrays.sort函数使用双轴快速排序算法，
 *  而对于对象类型使用归并排序（准确的说使用的是TimSort排序算法，
 *  它是归并排序的优化版本）。这样做的原因有两点
 *  第一个原因，归并排序是稳定的而快速排序不是稳定的。
 *  第二个原因，对于基本数据类型，排序的稳定性意义不大，
 *  但对于复合数据类型（比如对象）排序的稳定性就能帮助我们保持排序结果的某些性质。
 *
 */
public class MergeSort {

    /**
     * 未优化版归并排序
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] array){
        assert array != null;
        int n = array.length;
        mergeSort(array,0,n-1);
    }

    /**
     * 优化版归并排序
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void sortOptimize(T[] array){
        assert array != null;
        int n = array.length;
        mergeSortOptimize(array,0,n-1);
    }


    /**
     * 归并排序算法优化版
     */
    private static <T extends Comparable<T>> void mergeSortOptimize(T[] array ,int l , int r){

        // 优化: 对于小规模数组, 使用插入排序,在较小的数据范围内,数据近乎有序
        // 可能性非常,采用插入排序能提高效率
        if( r - l <= 15 ){
            InsertionSort.sort(array, l, r);
            return;
        }

        //计算分组间距
        int mid = l + (r - l)/2;

        //递归分组
        mergeSort(array,l,mid);
        mergeSort(array,mid+1,r);

        /**
         * 优化: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
         * 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
         */
        if( array[mid].compareTo(array[mid+1]) > 0 ) {
            //两边都分组完成即分组后数组元素只有一个后开始合并
            merge(array, l, mid, r);
        }
    }

    /**
     * 归并排序算法(未优化版)
     * @param array
     * @param l
     * @param r
     * @param <T>
     */
    private static <T extends Comparable<T>> void mergeSort(T[] array ,int l , int r){
        //递归结束条件(分组后数组只有一个元素时不再分组)
        if(l>=r){
            return;
        }

        //计算分组间距
        int mid = l + (r - l)/2;

        //递归分组
        mergeSort(array,l,mid);
        mergeSort(array,mid+1,r);
        //两边都分组完成即分组后数组元素只有一个后开始合并
        merge(array, l, mid, r);
    }

    /**
     * 合并函数
     * @param array
     * @param l
     * @param mid
     * @param r
     * @param <T>
     */
    public static <T extends Comparable<T>> void merge(T[] array , int l , int mid , int r){
        int len = r-l+1;
        //创建临时数据辅助归并
        T[] temp = (T[]) Array.newInstance(array.getClass().getComponentType(),len);
        //把需要归并的元素全赋值给temp;
        for(int i=l ; i <= r ; i++){
            temp[i-l] = array[i];
        }

        int i = l;
        int j = mid+1;
        //执行归并操作(O(N)级别)
        for(int k=l ; k <= r ; k++){
            if(i > mid){ //说明左边的元素已全部归并完成
                array[k] = temp[j-l];
                j++;
            }else if(j > r){ //说明右边的元素已全部归并完成
                array[k] = temp[i-l];
                i++;
            }else if(temp[i-l].compareTo(temp[j-l]) > 0){//左边的元素大于右边
                array[k] = temp[j-l];
                j++;
            }else if(temp[i-l].compareTo(temp[j-l]) < 0){ //右边的元素大于左边
                array[k] = temp[i-l];
                i++;
            }
        }
    }

    public static void main(String[] args) {
//
//        Integer[] arr = {10,9,8,7,6,5,4,3,2,1};
//        MergeSort.sort(arr);
//        for( int i = 0 ; i < arr.length ; i ++ ){
//            System.out.print(arr[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        //对于近乎有序的数组,优化后的归并排序效率更高
        int N = 200000;
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(N, 4);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sort", arr1);
        SortTestHelper.testSort("com.zejian.structures.Sort.Sort_NLogN.MergeSort","sortOptimize",arr2);

    }


}
