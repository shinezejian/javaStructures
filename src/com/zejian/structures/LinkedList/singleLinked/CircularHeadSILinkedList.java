package com.zejian.structures.LinkedList.singleLinked;

import com.zejian.structures.LinkedList.ILinkedList;


/**
 * Created by zejian on 2016/10/25.
 * 循环单链表
 */
public class CircularHeadSILinkedList<T> implements ILinkedList<T> {

    protected Node<T> head; //不带数据头结点
    protected Node<T> tail;//指向尾部的指针


    public CircularHeadSILinkedList() {
        //初始化头结点与尾指针
        this.head= new Node<T>(null);
        this.head.next=head;
        this.tail=head;
    }


    public CircularHeadSILinkedList(T[] array)
    {
        this();
        if (array!=null && array.length>0)
        {
            this.head.next = new Node<>(array[0],head);
            tail=this.head.next;
            int i=1;
            while (i<array.length)
            {
                tail.next=new Node<>(array[i++]);
                tail.next.next=head;
                tail = tail.next;
            }
        }
    }


    @Override
    public boolean isEmpty() {
        return this.head.next==head;
    }

    @Override
    public int length() {

        int length=0;
        Node<T> p=this.head.next;
        while (p!=head){
            p=p.next;
            length++;
        }

        return length;
    }

    @Override
    public T get(int index) {

        if (index>=0)
        {
            int j=0;
            Node<T> pre=this.head.next;
            while (pre!=null && j<index)
            {
                j++;
                pre=pre.next;
            }
            if (pre!=null)
                return pre.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {

        if (data==null){
            return null;
        }

        if(index>=0){
            int j=0;
            Node<T> p=this.head.next;

            while (p!=head&&j<index){
                j++;
                p=p.next;
            }

            //如果不是头结点
            if(p!=head){
                T old = p.data;
                p.data=data;

                return old;
            }
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        int size=length();
        if(data==null||index<0||index>=size)
            return false;

        int j=0;
        Node<T> p=this.head;
        //寻找插入点的位置的前一个结点
        while (p.next!=head&&j<index){
            p=p.next;
            j++;
        }

        //创建新结点,如果index=3,那么插入的位置就是第4个位置
        Node<T> q=new Node<>(data,p.next);
        p.next=q;
        //更新尾部指向
        if(p==tail){
            this.tail=q;
        }
        return true;
    }

    @Override
    public boolean add(T data) {
        if(data==null){
            return false;
        }

        Node<T> q=new Node<>(data,this.tail.next);
        this.tail.next=q;
        //更新尾部指向
        this.tail=q;

        return true;
    }

    @Override
    public T remove(int index) {
        int size=length();
        if(index<0||index>=size||isEmpty()){
            return null;
        }

        int j=0;
        Node<T> p=this.head.next;

        while (p!=head&&j<index){
            j++;
            p=p.next;
        }

        if(p!=head){
            T old =p.next.data;

            if(tail==p.next){
                tail=p;
            }
            p.next=p.next.next;

            return old;
        }

        return null;
    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;
        if(data==null){
            return isRemove;
        }

        //用于记录要删除结点的前一个结点
        Node<T> front=this.head;
        //当前遍历的结点
        Node<T> pre=front.next;
        //查找所有数据相同的结点并删除
        while (pre!=head){
            if(data.equals(pre.data)){
                //如果恰好是尾部结点,则更新rear的指向
                if(data.equals(tail.data)){
                    this.tail=front;
                }
                //相等则删除pre并更改指针指向
                front.next=pre.next;
                pre =front.next;
                isRemove=true;
            }else {
                front=pre;
                pre=pre.next;
            }
        }


        return isRemove;
    }

    @Override
    public void clear() {
        this.head.next=head;
        this.tail=head;
    }

    @Override
    public boolean contains(T data) {
        if (data==null){
            return false;
        }

        Node<T> p=this.head.next;

        while (p!=head){
            if(data.equals(p.data)){
                return true;
            }

            p=p.next;
        }

        return false;
    }

    @Override
    public String toString()
    {
        String str="(";
        Node<T> p = this.head.next;
        while (p!=head)
        {
            str += p.data.toString();
            p = p.next;
            if (p!=head)
                str += ", ";
        }
        return str+")";
    }

    public static void main(String[] args){

        String[] letters={"A","B","C","D","E","F"};
        CircularHeadSILinkedList<String> list=new CircularHeadSILinkedList<>(letters);

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
        System.out.println("list:"+list.toString());
        System.out.println("list.add(Z)—>"+list.add("Z"));
        System.out.println("list:"+list.toString());


        System.out.println("list.contains(Z)->"+list.contains("Z"));
        System.out.println("list.set(4,P)-->"+list.set(4,"P"));
        System.out.println("list:"+list.toString());

        System.out.println("list.removeAll(Z)->"+list.removeAll("Z"));
        System.out.println("list.remove(4)-->"+list.remove(4));
        System.out.println("list:"+list.toString());
    }
}
