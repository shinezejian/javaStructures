package com.zejian.structures.recursion;

import java.math.BigInteger;

/**
 * Created by zejian on 2016/12/11.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 斐波那契数列的实现
 */
public class Fibonacci  {

    /**
     * 斐波那契数列的实现
     * 0,1,1,2,3,5,8,13,21......
     * @param day
     */
    public long fibonacci(int day){

        if(day==0){ //F(0)=0
            return 0;
        }else if (day==1||day==2){//F(1)=1
            return 1;
        }else {
           return fibonacci(day-1)+fibonacci(day-2); //F(n)=F(n-1)+F(n-2)
        }
    }

    /**
     * 更为简洁的写法
     * @param day
     * @return
     */
    public long fib(int day) {
        return day== 0 ? 0 : (day== 1 || day==2 ? 1 : fib(day - 1) + fib(day - 2));
    }

//    //1,2,3,5,8,13,21......
//    BigInteger fib_i(int a, int b , int n)
//    {
//        if (n==3){
//            return BigInteger.valueOf(a).add(BigInteger.valueOf(b));
//        }else {
//            return fib_i(b ,a+b, n-1);
//        }
//    }

    //BigInteger可以防止数据异常
//BigInteger 任意大的整数，原则上是，只要你的计算机的内存足够大，可以有无限位的
// 递推实现方式（迭代的方式效率高，时间复杂度O(n)）
    public  BigInteger fibonacciN(int n){
        if (n == 1) {
            return new BigInteger("0");
        }
        //f(0)=0;
        BigInteger n1 = new BigInteger("0");
        //f(1)=1;
        BigInteger n2 = new BigInteger("1");
        //记录最终值f(n)
        BigInteger sn = new BigInteger("0");
        for (int i = 0; i < n - 1; i++) {
            sn = n1.add(n2);//相加
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    // 与上述相同的递推实现方式 ，使用long返回值，当n过大会造成数据溢出，计算结果可能是一个未知的负数，因此建议使用BigInteger
    public static long fibonacciNormal(int n){
        if(n <= 2){
            return 1;
        }
        long n1 = 1, n2 = 1, sn = 0;
        for(int i = 0; i < n - 2; i ++){
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    //测试
    public static void main(String[] args){
        Fibonacci fibonacci=new Fibonacci();
        long now =System.currentTimeMillis();
//        System.out.println("第11天动物数量为:"+ fibonacci.fib_i(1,1,50));
//        System.out.println("第11天动物数量为:"+ fibonacci.fib(50));//12586269025
        System.out.println("第11天动物数量为:"+ fibonacci.fibonacciNormal(100));//12586269025
//        System.out.println("第11天动物数量为:"+ fibonacci.fibonacci(10));
        System.out.println("执行第500天的时间为:"+(System.currentTimeMillis()-now));
    }
}
