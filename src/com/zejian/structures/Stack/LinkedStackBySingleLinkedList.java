package com.zejian.structures.Stack;

import com.zejian.structures.LinkedList.singleLinked.SingleILinkedList;

import java.io.Serializable;


/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian/article/details/53362993 [原文地址,请尊重原创]
 * 链式栈的实现(利用单链表即可)
 */
public class LinkedStackBySingleLinkedList<T> implements Stack<T> ,Serializable {

    private static final long serialVersionUID = 3409158027110650450L;


    private SingleILinkedList<T> linkedList;

    public LinkedStackBySingleLinkedList(){
        linkedList=new SingleILinkedList<>();
    }


    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * 栈顶插入(单链表尾部)
     * @param data
     */
    @Override
    public void push(T data) {
        linkedList.add(data);
    }

    /**
     * 获取元素,不删除
     * @return
     */
    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return linkedList.get(0);
    }

    /**
     * 栈顶移除
     * @return
     */
    @Override
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("Stack empty");
        return linkedList.remove(0);
    }
}
