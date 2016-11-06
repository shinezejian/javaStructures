package com.zejian.structures.LinkedList.singleLinked;

import com.zejian.structures.LinkedList.ILinkedList;

/**
 * Created by zejian on 2016/10/22.
 * 带头结点并含有尾指针的链表
 */
public class HeadSingleILinkedList<T> implements ILinkedList<T> {

    protected Node<T> headNode; //不带数据头结点
    protected Node<T> rear;//指向尾部的指针

    public HeadSingleILinkedList() {
        //初始化头结点与尾指针
        this.headNode =rear= new Node<>(null);
    }

    public HeadSingleILinkedList(Node<T> head) {
        this();
        this.headNode.next =rear.next= head;
        rear=rear.next;//更新末尾指针指向
    }

    /**
     * 传入一个数组,转换成链表
     * @param array
     */
    public HeadSingleILinkedList(T[] array)
    {
        this();
        if (array!=null && array.length>0)
        {
            this.headNode.next = new Node<T>(array[0]);
            rear=this.headNode.next;
            int i=1;
            while (i<array.length)
            {
                rear.next=new Node<T>(array[i++]);
                rear = rear.next;
            }
        }
    }

    /**
     * 通过传入的链表构造新的链表
     * @param list
     */
    public HeadSingleILinkedList(HeadSingleILinkedList<T> list) {
        this();
        if (list!=null && list.headNode.next!=null)
        {
            this.headNode.next = new Node(list.headNode.data);
            Node<T> p = list.headNode.next;
            rear = this.headNode.next;
            while (p!=null)
            {
                rear.next = new Node<T>(p.data);
                rear = rear.next;
                p = p.next;
            }
        }
    }


    /**
     * 判断链表是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.headNode.next==null;
    }

    @Override
    public int length() {
        int length=0;
        Node<T> currentNode=headNode.next;
        while (currentNode!=null){
            length++;
            currentNode=currentNode.next;
        }
        return length;
    }

    /**
     * 根据index索引获取值
     * @param index 下标值起始值为0
     * @return
     */
    @Override
    public T get(int index) {

        if(index>=0){
            int j=0;
            Node<T> pre=this.headNode.next;
            //找到对应索引的结点
            while (pre!=null&&j<index){
                pre=pre.next;
                j++;
            }

            if(pre!=null){
                return pre.data;
            }

        }
        return null;
    }

    /**
     * 根据索引替换对应结点的data
     * @param index 下标从0开始
     * @param data
     * @return 返回旧值
     */
    @Override
    public T set(int index, T data) {

        if(index>=0&&data!=null){
            Node<T> pre=this.headNode.next;
            int j=0;
            while (pre!=null&&j<index){
                pre=pre.next;
                j++;
            }

            if (pre!=null){
                T oldData=pre.data;
                pre.data=data;//设置新值
                return oldData;
            }

        }
        return null;
    }

    /**
     * 根据下标添加结点
     * 1.头部插入
     * 2.中间插入
     * 3.末尾插入
     * @param index 该值从0开始,如传4就代表插入在第5个位置
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {

        if (data==null){
            throw new NullPointerException("data can\'t be empty!");
        }

        if(index<0)
            throw new NullPointerException("index can\'t less than 0");

        //无需区分位置操作,中间/头部/尾部插入
        int j=0;
        Node<T> pre=this.headNode;
        while (pre.next!=null&&j<index){
            pre=pre.next;
            j++;
        }

        //将新插入的结点的后继指针指向pre.next
        Node<T> q=new Node<T>(data,pre.next);
        //更改指针指向
        pre.next=q;

        //如果是未指针
        if (pre==this.rear)
            this.rear=q;

        return true;
    }

    /**
     * 尾部插入
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        if (data==null)
            throw new NullPointerException("data can\'t be empty!");

        this.rear.next = new Node<T>(data);
        //更新末尾指针的指向
        this.rear = this.rear.next;
        return true;
    }

    /**
     * 根据索引删除结点
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        T old=null;

        //包含了头删除或中间删除或尾部删除的情况
        if (index>=0){

            Node<T> pre = this.headNode;
            int j = 0;
            //查找需要删除位置的前一个结点
            while (pre.next != null && j < index) {
                pre = pre.next;
                j++;
            }

            //获取到要删除的结点
            Node<T> r = pre.next;

            if (r != null) {
                //获取旧值
                old =r.data;
                //如果恰好是尾部结点,则更新rear的指向
                if (r==this.rear){
                    this.rear=pre;
                }
                //更改指针指向
                pre.next=r.next;
            }

        }
        return old;
    }

    /**
     * 根据data移除所有数据相同的结点
     * @param data
     * @return
     */
    @Override
    public boolean removeAll(T data) {

        boolean isRemove=false;

        if(data!=null){
            //用于记录要删除结点的前一个结点
            Node<T> front=this.headNode;
            //当前遍历的结点
            Node<T> pre=front.next;
            //查找所有数据相同的结点并删除
            while (pre!=null){
                if(data.equals(pre.data)){
                    //如果恰好是尾部结点,则更新rear的指向
                    if(data.equals(rear.data)){
                        this.rear=front;
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
        }
        return isRemove;
    }

    /**
     * 清空链表
     */
    @Override
    public void clear() {
        this.headNode.next=null;
        this.rear=this.headNode;
    }

    /**
     * 判断是否包含某个值
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {

        if(data!=null){
            Node<T> pre=this.headNode.next;
            while (pre!=null){
                if(data.equals(pre.data)){
                    return true;
                }
                pre=pre.next;
            }
        }
        return false;
    }

    /**
     * 从末尾连接两个链表
     * @param list
     */
    public void concat(HeadSingleILinkedList<T> list)
    {
        if (this.headNode.next==null) {
            this.headNode.next = list.headNode.next;
        } else {
            Node<T> pre=this.headNode.next;
            while (pre.next!=null)
                pre = pre.next;
           pre.next=list.headNode.next;
            //更新尾部指针指向
            this.rear=list.rear;
        }
    }

    @Override
    public String toString() {
        String str="(";
        Node<T> pre = this.headNode.next;
        while (pre!=null)
        {
            str += pre.data;
            pre = pre.next;
            if (pre!=null)
                str += ", ";
        }
        return str+")";
    }

    public static void main(String[] args){

        String[] letters={"A","B","C","D","E","F"};
        HeadSingleILinkedList<String> list=new HeadSingleILinkedList<>(letters);

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
        System.out.println("list:"+list.toString());
        System.out.println("list.add(Z)—>"+list.add("Z"));
        System.out.println("list:"+list.toString());


        System.out.println("list.contains(Z)->"+list.contains("Z"));
        System.out.println("list.set(4,P)-->"+list.set(4,"P"));
        System.out.println("list:"+list.toString());

        System.out.println("list.remove(Z)->"+list.removeAll("Z"));
        System.out.println("list.remove(4)-->"+list.remove(4));
        System.out.println("list:"+list.toString());
    }
}
