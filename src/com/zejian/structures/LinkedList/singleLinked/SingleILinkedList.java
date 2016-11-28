package com.zejian.structures.LinkedList.singleLinked;

import com.zejian.structures.LinkedList.ILinkedList;


/**
 * Created by zejian on 2016/10/21.
 *  单项链表的实现,不含独立头结点,不含尾部指针
 */
public class SingleILinkedList<T> implements ILinkedList<T> {
    protected Node<T> head; //带数据头结点

    public SingleILinkedList(Node<T> head) {
        this.head = head;
    }


    public SingleILinkedList() {

    }

    /**
     * 传入一个数组,转换成链表
     * @param array
     */
    public SingleILinkedList(T[] array)
    {
        this.head = null;
        if (array!=null && array.length>0)
        {
            this.head = new Node<T>(array[0]);
            Node<T> rear=this.head;
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
    public SingleILinkedList(SingleILinkedList<T> list) {
        this.head = null;
        if (list!=null && list.head!=null)
        {
            this.head = new Node<>(list.head.data);
            Node<T> p = list.head.next;
            Node<T> rear = this.head;
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
        return this.head==null;
    }

    @Override
    public int length() {
        int length=0;
        Node<T> p=head;
        while (p!=null){
            length++;
            p=p.next;
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

        if(this.head!=null&&index>=0){
            int count=0;
            Node<T> p=this.head;
            //找到对应索引的结点
            while (p!=null&&count<index){
                p=p.next;
                count++;
            }

            if(p!=null){
                return p.data;
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

        if(this.head!=null&&index>=0&&data!=null){
            Node<T> pre=this.head;
            int count=0;
            while (pre!=null&&count<index){
                pre=pre.next;
                count++;
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
     * @param index 下标值从0开始
     * @param data
     * @return
     */
    @Override
    public boolean add(int index, T data) {

        if (data==null){
            return false;
        }
        //在头部插入
        if (this.head==null||index<=1){
            this.head = new Node<T>(data, this.head);
        }else {
            //在尾部或中间插入
            int count=0;
            Node<T> front=this.head;
            while (front.next!=null&&count<index-1){
                front=front.next;
                count++;
            }
            //尾部添加和中间插入属于同种情况,毕竟当front为尾部结点时front.next=null
            front.next=new Node<T>(data,front.next);
        }
        return true;
    }

    /**
     * 默认尾部插入
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        return add(Integer.MAX_VALUE,data);
    }

    /**
     * 根据索引删除结点
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {

        T old=null;

        if (this.head!=null&&index>=0){

            //直接删除的是头结点
            if(index==0){
                old=this.head.data;
                this.head=this.head.next;
            }else {

                Node<T> front = this.head;
                int count = 0;
                //查找需要删除结点的前一个结点
                while (front.next != null && count < index - 1) {
                    front = front.next;
                    count++;
                }

                if ( front.next!= null) {
                    //获取旧值
                    old =front.next.data;
                    //更改指针指向
                    front.next=front.next.next;
                }
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

        if(this.head!=null&&data!=null){

            //如果移除的是头结点
            if(data.equals(this.head.data)){
                this.head=this.head.next;
                isRemove=true;
            }else {

                Node<T> front=this.head;
                Node<T> pre=front.next;
                //查找所有数据相同的结点并删除
                while (pre!=null){

                    if(data.equals(pre.data)){
                        //更改指针指向
                        front.next=pre.next;
                        pre =front.next;
                        isRemove=true;
                    }else {
                        front=pre;
                        pre=pre.next;
                    }
                }
            }
        }else {//data=null || this.head=null
            isRemove=false;
        }
        return isRemove;
    }

    /**
     * 清空链表
     */
    @Override
    public void clear() {
        this.head=null;
    }

    /**
     * 判断是否包含某个值
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data) {

        if(this.head!=null&&data!=null){

            Node<T> pre=this.head;
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
    public void concat(SingleILinkedList<T> list)
    {
        if (this.head==null)
            this.head = list.head;
        else
        {
            Node<T> pre=this.head;
            while (pre.next!=null)
                pre = pre.next;
            pre.next=list.head;
        }
    }

    @Override
    public String toString() {
        String str="(";
        Node<T> pre = this.head;
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
        SingleILinkedList<String> list=new SingleILinkedList<>(letters);

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
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
