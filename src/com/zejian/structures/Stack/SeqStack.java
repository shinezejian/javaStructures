package com.zejian.structures.Stack;

import java.io.Serializable;

/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
 * 顺序栈的实现
 */
public class SeqStack<T> implements Stack<T>,Serializable {

    private static final long serialVersionUID = -5413303117698554397L;

    /**
     * 栈顶指针,-1代表空栈
     */
    private int top=-1;

    /**
     * 容量大小默认为10
     */
    private int capacity=10;

    /**
     * 存放元素的数组
     */
    private T[] array;

    private int size;

    public SeqStack(int capacity){
        array = (T[]) new Object[capacity];
    }

    public SeqStack(){
        array= (T[]) new Object[this.capacity];
    }

    public  int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return this.top==-1;
    }

    /**
     * 添加元素,从栈顶(数组尾部)插入
     * @param data
     */
    @Override
    public void push(T data) {
        //判断容量是否充足
        if(array.length==size)
            ensureCapacity(size*2+1);//扩容

        //从栈顶添加元素
        array[++top]=data;

        size++;
    }

    /**
     * 获取栈顶元素的值,不删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return array[top];
    }

    /**
     * 从栈顶(顺序表尾部)删除
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        size--;
        return array[top--];
    }

    /**
     * 扩容的方法
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity<size)
            return;

        T[] old = array;
        array = (T[]) new Object[capacity];
        //复制元素
        for (int i=0; i<size ; i++)
            array[i]=old[i];
    }

    public static void main(String[] args){
        SeqStack<String> s=new SeqStack<>();
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println("size->"+s.size());
        int l=s.size();//size 在减少,必须先记录
        for (int i=0;i<l;i++){
            System.out.println("s.pop->"+s.pop());
        }

        System.out.println("s.peek->"+s.peek());
    }
}
