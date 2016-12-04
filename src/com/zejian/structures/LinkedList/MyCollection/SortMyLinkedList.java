package com.zejian.structures.LinkedList.MyCollection;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by zejian on 2016/12/3.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 排序list的简单实现
 */
public class SortMyLinkedList<T extends Comparable<? extends T>> extends MylinkeList<T> implements Serializable {

    private static final long serialVersionUID = -4783131709270334156L;

    @Override
    public boolean add(T data) {
        if(data==null)
            throw new NullPointerException("data can\'t be null");

        Comparable cmp =data;//这里需要转一下类型,否则idea编辑器上检验不通过.

        if(this.isEmpty() || cmp.compareTo(this.last.prev.data) > 0){
             return super.add(data);//直接尾部添加,last不带数据的尾结点
        }

        Node<T> p=this.first.next;
        //查找插入点
        while (p!=null&&cmp.compareTo(p.data)>0)
            p=p.next;

        Node<T> q=new Node<>(p.prev,data,p);
        p.prev.next=q;
        p.prev=q;

        size++;
        //记录修改
        modCount++;

        return true;
    }

    /**
     * 不根据下标插入,只根据比较大小插入
     * @param index
     * @param data
     */
    @Override
    public void add(int index, T data) {
        this.add(data);
    }


    /**
     * 未实现
     * @param index
     * @return
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    /**
     * 未实现
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    //测试
    public static void main(String[] args){
        SortMyLinkedList<Integer> list=new SortMyLinkedList<>();
        list.add(50);
        list.add(40);
        list.add(80);
        list.add(20);
        print(list);
    }

    public static void print(SortMyLinkedList mylinkeList){
        for (int i=0;i<mylinkeList.size();i++) {
            System.out.println("i->"+mylinkeList.get(i));
        }
    }
}
