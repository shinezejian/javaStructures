package com.zejian.structures.LinkedList.doubleLinked;

import com.zejian.structures.LinkedList.ILinkedList;

/**
 * Created by zejian on 2016/10/24.
 * 循环双链表,带空头结点(不含数据),循环链表可以不需要尾部指针
 */
public class LoopHeadDILinkedList<T> implements ILinkedList<T> {

    public DNode<T> head; //不带数据的头结点
 //   protected DNode<T> tail; //指向尾部的指针

    public LoopHeadDILinkedList(){
        this.head = new DNode<>();//初始化头结点
        this.head.next=head;
        this.head.prev=head;

    }


    /**
     * 传入一个数组,转换成链表
     * @param array
     */
    public LoopHeadDILinkedList(T[] array)
    {
        this();
        if (array!=null && array.length>0)
        {
            DNode<T> p= new DNode<>(array[0]);
            head.next=p;
            head.prev=p;
            p.prev=head;
            p.next=head;

            int i=1;
            while (i<array.length)
            {
                p.next=new DNode<>(array[i++],p,head);
                head.prev=p.next;
                p=p.next;
            }
        }
    }



    @Override
    public boolean isEmpty() {
        return this.head.next==head;//循环双链表的后继指针指向自己说明是空链表
    }

    @Override
    public int length() {

        int length=0;
        DNode<T> p=this.head.next;
        while (p!=this.head){
            length++;
            p=p.next;
        }
        return length;
    }

    @Override
    public T get(int index) {

        if (index>=0)
        {
            int j=0;
            DNode<T> p=this.head.next;
            while (p!=head && j<index)
            {
                j++;
                p=p.next;
            }
            if (p!=head)
                return p.data;
        }
        return null;
    }

    /**
     * 根据index修改data
     * @param index
     * @param data
     * @return 返回被替换的data
     */
    @Override
    public T set(int index, T data) {

        if (data==null){
            return null;
        }

        if(index>=0){
            int j=0;
            DNode<T> p=this.head.next;

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

    /**
     * 根据index添加
     * 循环链表中无论是prev还是next都不存在空的情况,因此添加时
     * 无论是头部还是尾部还是中,都视为一种情况对待
     * @param index
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {
        int size=length();
        if(data==null||index<0||index>=size)
            return false;

        int j=0;
        DNode<T> p=this.head;
        //寻找插入点的位置
        while (p.next!=head&&j<index){
            p=p.next;
            j++;
        }

        //创建新结点,如果index=3,那么插入的位置就是第4个位置
        DNode<T> q=new DNode<>(data,p,p.next);
        p.next=q;
        p.next.prev=q;

        return true;
    }

    /**
     * 尾部添加
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {

        if(data==null)
            return false;
        //创建新结点,让前继指针指向head.pre,后继指针指向head
        DNode<T> p=new DNode<>(data,head.prev,head);
        //更新tail后继指针的指向
        this.head.prev.next=p;
        this.head.prev=p;

        return true;
    }

    @Override
    public T remove(int index) {
        T old = null;
        int size=length();

        if (index<0||index>=size)
            return old;

        int j=0;
        DNode<T> p=this.head.next;

        while (p!=head && j<index)
        {
            j++;
            p = p.next;
        }

        if (p!=head)
        {
            old = p.data;
            p.prev.next = p.next;
            p.next.prev = p.prev;
        }

        return old;
    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;

        if(data==null){
            return isRemove;
        }

        DNode<T> p=this.head.next;
        while (p!=head){
            if(data.equals(p.data)){
                p.prev.next=p.next;
                p.next.prev=p.prev;
                isRemove=true;
            }
            p=p.next;
        }

        return isRemove;
    }

    @Override
    public void clear() {
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public boolean contains(T data) {

        if (data==null){
           return false;
        }

        DNode<T> p=this.head.next;

        while (p!=head){
            if(data.equals(p.data)){
                return false;
            }

            p=p.next;
        }

        return false;
    }

    @Override
    public String toString()
    {
        String str="(";
        DNode<T> p = this.head.next;
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

        String[] letters={"A","B","C","D","Z","E","F"};
        LoopHeadDILinkedList<String> list=new LoopHeadDILinkedList<>(letters);

        System.out.println("init list-->"+list.toString());

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
        System.out.println("list:"+list.toString());
        System.out.println("list.add(Z)—>"+list.add("Z"));
        System.out.println("list:"+list.toString());


        System.out.println("list.contains(Z)->"+list.contains("Z"));
        System.out.println("list.set(4,P)-->"+list.set(4,"P"));
        System.out.println("list:"+list.toString());


        System.out.println("list.remove(3)-->"+list.remove(3));
        System.out.println("list.remove(Z)->"+list.removeAll("Z"));
        System.out.println("list:"+list.toString());
    }
}
