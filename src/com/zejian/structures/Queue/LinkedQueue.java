package com.zejian.structures.Queue;

import com.zejian.structures.LinkedList.singleLinked.Node;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zejian on 2016/11/28.
 * Blog : http://blog.csdn.net/javazejian/article/details/53375004 [原文地址,请尊重原创]
 * 链式队列的实现
 */
public class LinkedQueue<T> implements Queue<T> ,Serializable{
    private static final long serialVersionUID = 1406881264853111039L;
    /**
     * 指向队头和队尾的结点
     * front==null&&rear==null时,队列为空
     */
    private Node<T> front,rear;

    private int size;
    /**
     * 用于控制最大容量,默认128,offer方法使用
     */
    private int maxSize=128;

    public LinkedQueue(){
        //初始化队列
        this.front=this.rear=null;
    }

    @Override
    public int size() {
        return size;
    }

    public void setMaxSize(int maxSize){
        this.maxSize=maxSize;
    }

    @Override
    public boolean isEmpty() {
        return front==null&&rear==null;
    }

    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        Node<T> q=new Node<>(data,null);
        if (this.front==null) {//空队列插入
            this.front = q;
        } else {//非空队列,尾部插入
            this.rear.next=q;
        }
        this.rear=q;
        size++;
        return true;
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
        if (size>=maxSize)
            throw new IllegalArgumentException("The capacity of LinkedQueue has reached its maxSize:128");

        Node<T> q=new Node<>(data,null);
        if (this.front==null) {//空队列插入
            this.front = q;
        } else {//非空队列,尾部插入
            this.rear.next=q;
        }
        this.rear=q;
        size++;
        return false;
    }

    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    @Override
    public T peek() {
        return this.isEmpty()? null:this.front.data;
    }

    /**
     * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    @Override
    public T element() {
        if(isEmpty()){
            throw new NoSuchElementException("The LinkedQueue is empty");
        }
        return this.front.data;
    }

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    @Override
    public T poll() {
        if (this.isEmpty())
            return null;
        T x=this.front.data;
        this.front=this.front.next;
        if (this.front==null)
            this.rear=null;
        size--;
        return x;
    }
    
    /**
     * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    @Override
    public T remove() {
        if (isEmpty()){
            throw new NoSuchElementException("The LinkedQueue is empty");
        }
        T x=this.front.data;
        this.front=this.front.next;
        if (this.front==null)
            this.rear=null;
        size--;
        return x;
    }

    @Override
    public void clearQueue() {
        this.front= this.rear=null;
        size=0;
    }
}
