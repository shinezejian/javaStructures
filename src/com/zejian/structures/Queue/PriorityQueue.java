package com.zejian.structures.Queue;

import com.zejian.structures.LinkedList.MyCollection.SortMyLinkedList;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Created by zejian on 2016/11/30.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 优先队列的简单实现,采用排序双链表,T必须实现Comparable接口
 */
public class PriorityQueue<T extends Comparable<? extends T>> implements Queue<T> ,Serializable {

    private static final long serialVersionUID = 8050142086009260625L;

    private SortMyLinkedList<T> list;//排序循环双链表

    private boolean asc;//true表示升序,false表示降序

    /**
     * 用于控制最大容量,默认128,offer方法使用
     */
    private int maxSize=128;
    /**
     * 初始化队列
     * @param asc
     */
    public PriorityQueue(boolean asc){
        this.list=new SortMyLinkedList<>();
        this.asc=asc;//默认升序
    }


    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * data 入队,添加成功返回true,否则返回false
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        return list.add(data);
    }

    /**
     * offer 方法可插入一个元素,这与add 方法不同，
     * 该方法只能通过抛出未经检查的异常使添加元素失败。
     * 而不是出现异常的情况，例如在容量固定（有界）的队列中
     * NullPointerException:data==null时抛出
     * IllegalArgumentException:队满,使用该方法可以使Queue的容量固定
     * @param data
     * @return
     */
    @Override
    public boolean offer(T data) {
        if (data==null)
            throw new NullPointerException("The data can\'t be null");
        if (list.size()>=maxSize)
            throw new IllegalArgumentException("The capacity of PriorityQueue has reached its maxSize:128");

        return add(data);
    }

    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty()){
            return null;
        }
        return this.asc ? this.list.get(0):this.list.get(size()-1);
    }

    /**
     * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    @Override
    public T element() {
        if(isEmpty()){
            throw new NoSuchElementException("The PriorityQueue is empty");
        }
        return peek();
    }

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    @Override
    public T poll() {
        if(isEmpty()){
            return null;
        }
        return this.asc ? this.list.remove(0): this.list.remove(list.size()-1);
    }

    /**
     * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    @Override
    public T remove() {
        if (isEmpty()){
            throw new NoSuchElementException("The PriorityQueue is empty");
        }
        return poll();
    }

    @Override
    public void clearQueue() {
        this.list.clear();
    }

    //测试
    public static void main(String[] args){
        PriorityQueue<Process> priorityQueue=new PriorityQueue<>(false);

        System.out.println("初始化队列");
        priorityQueue.add(new Process("进程1",10));
        priorityQueue.add(new Process("进程2",1));
        priorityQueue.add(new Process("进程3",8));
        priorityQueue.add(new Process("进程4",3));
        priorityQueue.add(new Process("进程5"));
        System.out.println("队列中的进程执行优先级:");
        while (!priorityQueue.isEmpty()){
            System.out.println("process:"+priorityQueue.poll().toString());
        }

    }

}
