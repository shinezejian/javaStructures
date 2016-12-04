package com.zejian.structures.LinkedList.MyCollection;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zejian on 2016/11/10.
 * Blog : http://blog.csdn.net/javazejian [请尊重原创,转载注明出处]
 * 改良的双链表表(带头结点和尾结点)类似java集合类LinkedList
 */
public class MylinkeList<T> implements Serializable,IList<T>, Iterable<T>{

    private static final long serialVersionUID = 8683452581122892300L;

    /**
     * 链表size,优化计算过程,无需遍历链表
     */
    protected int size = 0;

    /**
     * 修改的记录符
     */
    protected int modCount=0;

    /**
     * 头部指向结点,不带数据,排除特殊情况,优化代码量
     */
    protected Node<T> first;

    /**
     * 尾部指向结点,不带数据,排除特殊情况,优化代码量
     */
    protected Node<T> last;

    /**
     * 初始化链表
     */
    public MylinkeList() {
        first=new Node<>(null,null,null);
        last=new Node<>(first,null,null);
        first.next=last;
        size=0;
        modCount++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data)!=-1;
    }

    /**
     * 清空数据,GC更容易回收
     * Blog : http://blog.csdn.net/javazejian
     */
    @Override
    public void clear() {
        for (Node<T> x = first.next; x != null; ) {
            Node<T> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        //初始化链表
        first=new Node<>(null,null,null);
        last=new Node<>(first,null,null);
        first.next=last;
        size = 0;
        modCount++;
    }

    /**
     * 根据index查询数据
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    @Override
    public T set(int index, T data) {
        //检测下标是否越界
        checkElementIndex(index);
        Node<T> x = getNode(index);
        T oldVal = x.data;
        x.data = data;
        return oldVal;
    }

    /**
     * 尾部添加
     * @param data
     * @return
     */
    @Override
    public boolean add(T data) {
        linkLast(data);
        return true;
    }

    @Override
    public void add(int index, T data) {
        checkElementIndex(index);

        if (index == size)//直接尾部添加
            linkLast(data);
        else
            linkBefore(data, getNode(index));//查找到插入结点并在其前插入
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = first; x != null; x = x.next) {
                if (data.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        //移除
        return  unlink(getNode(index));
    }

    /**
     * 根据值查下标
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     * @return
     */
    @Override
    public int indexOf(T data) {
        int index = 0;
        if (data == null) {
            //注意起始结点
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = first.next; x != null; x = x.next) {
                if (data.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * 根据data查询最后一个下标
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     * @return
     */
    @Override
    public int lastIndexOf(T data) {
        int index = size;
        if (data == null) {
            for (Node<T> x = last.prev; x != null; x = x.prev) {
                index--;
                if (x.data == null)
                    return index;
            }
        } else {
            for (Node<T> x = last.prev; x != null; x = x.prev) {
                index--;
                if (data.equals(x.data))
                    return index;
            }
        }
        return -1;
    }

    /**
     * 删除x结点
     * Blog : http://blog.csdn.net/javazejian
     * @param x
     * @return
     */
    T unlink(Node<T> x) {
        // assert x != null;
        x.next.prev=x.prev;
        x.prev.next=x.next;
        size--;
        modCount++;
        return  x.data;
    }


    /**
     * 在succ结点前插入
     * Blog : http://blog.csdn.net/javazejian
     */
    void linkBefore(T T, Node<T> succ) {
        // assert succ != null;
        final Node<T> newNode = new Node<>(succ.prev, T, succ);
        succ.prev.next=newNode;
        succ.prev = newNode;
        size++;
        modCount++;
    }

    /**
     * 链表头部添加,由于拥有头结点和尾结点,无需判断插入情况
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     */
    private void linkFirst(T data) {
        //头结点的下一个结点
        final Node<T> f = first.next;
        final Node<T> newNode = new Node<>(first, data, f);
        f.prev=newNode;
        first.next = newNode;
        size++;
        modCount++;
    }


    /**
     * 链表尾部添加,由于拥有头结点和尾结点,无需判断插入情况
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     */
    void linkLast(T data) {
        //尾部结点的前一个结点
        final Node<T> l = last.prev;
        final Node<T> newNode = new Node<>(l, data, last);
        l.next = newNode;
        last.prev=newNode;
        size++;
        //记录修改
        modCount++;
    }



    /**
     * 优化结点查询,根据情况而定查询起点
     * Blog : http://blog.csdn.net/javazejian
     * @param index
     * @return
     */
    Node<T> getNode(int index) {
        //如果index小于size的一半,则从头结点开始查找,否则从尾部开始查找(右移2位相当除以2)
        if (index < (size >> 1)) {
            Node<T> x = first.next;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last.prev;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * 判断index是否越界
     * Blog : http://blog.csdn.net/javazejian
     * @param index
     */
    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }


    /**
     * 迭代器,支持变量过程删除结点
     * Blog : http://blog.csdn.net/javazejian
     */
    private class Itr implements Iterator<T> {
        /**
         * 指向下一个结点的下标
         */
        int cursor = 0;

        /**
         * 当前需要返回结点的下标
         */
        int lastRet = -1;

        /**
         *用于判断是否集合被修改
         */
        int expectedModCount = modCount;

        /**
         * 是否还有下一个结点
         * @return
         */
        public boolean hasNext() {
            return cursor != size();
        }

        /**
         * 获取当前结点的值
         * @return
         */
        public T next() {
            checkForComodification();
            try {
                int i = cursor;
                T next = get(i);
                lastRet = i;//指向当前结点
                cursor = i + 1;//更新,指向下一个还未访问的结点
                return next;
            } catch (IndexOutOfBoundsException T) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MylinkeList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;//回撤一位
                lastRet = -1;//复原
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException T) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * 检测是否集合已变更
         * 快速失败机制
         */
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    public ListIterator<T> listIterator(int index) {
        checkElementIndex(index);
        return new ListItr(index);
    }

    /**
     * 含前后指向的迭代器,支持变量过程添加元素,删除元素
     * Blog : http://blog.csdn.net/javazejian
     */
    private class ListItr implements ListIterator<T> {
        private Node<T> lastReturned;//指向当前正在被访问的结点
        private Node<T> next;//还未被访问的结点
        private int nextIndex;//还未被访问的结点下标
        private int expectedModCount = modCount;//用于判断集合是否被修改

        /**
         * 结点指向传入值index的结点
         * @param index
         */
        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : getNode(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        /**
         * 获取结点数据
         * @return
         */
        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;//当前正在被访问的结点
            next = next.next;//更新至还未被访问的结点
            nextIndex++;//更新至还未被访问结点的下标
            return lastReturned.data;
        }

        /**
         * 是否有前驱结点
         * @return
         */
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        /**
         * 功能与next()一样,但previous()是往前遍历
         * @return
         */
        public T previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last.prev : next.prev;
            nextIndex--;
            return lastReturned.data;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        /**
         * 移除操作
         */
        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<T> lastNext = lastReturned.next;
            unlink(lastReturned);
            //如果next还未更新,则直接执行lastNext
            if (next == lastReturned)
                next = lastNext;
            else
                //如果next已更新,那么nextIndex必定已执行了nextIndex++操作,此时由于删除结点
                //所以必须执行nextIndex--,才能使nextIndex与next相对应
                nextIndex--;

            //复原
            lastReturned = null;
            expectedModCount++;
        }

        public void set(T T) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.data = T;
        }

        public void add(T T) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(T);
            else
                linkBefore(T, next);
            nextIndex++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    /**
     * 双向结点类
     * Blog : http://blog.csdn.net/javazejian
     * @param <T>
     */
    protected static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    //测试
    public static void main(String[] args){
        System.out.println("------init-------");
        MylinkeList<Integer> mylinkeList=new MylinkeList<>();
        mylinkeList.add(2);
        mylinkeList.add(10);
        mylinkeList.add(1);
        mylinkeList.add(9);
        mylinkeList.add(20);
        mylinkeList.add(555);

        print(mylinkeList);
        System.out.println("------remove(2)-------");
        mylinkeList.remove(2);
        print(mylinkeList);
        System.out.println("------indexOf(10)&set(0,0)-------");
        System.out.println("index-->"+mylinkeList.indexOf(10));
        mylinkeList.set(0,0);
        print(mylinkeList);

        System.out.println("-------------iterator--------------");
        Iterator<Integer> iterator=mylinkeList.iterator();
        while (iterator.hasNext()){
            System.out.println("iterator.next-->"+iterator.next());
        }

        System.out.println("-------------iteratorList--------------");
        ListIterator<Integer> iteratorList=mylinkeList.listIterator(0);
        iteratorList.add(88);
        while (iteratorList.hasNext()){
            System.out.println("iteratorList.next-->"+iteratorList.next());
        }
        iteratorList.add(100);
        System.out.println("-------------iteratorList1.add--------------");
        //使用完后必须重新new
        ListIterator<Integer> iteratorList1=mylinkeList.listIterator(0);
        while (iteratorList1.hasNext()){
            int i=iteratorList1.next();
            if(i==555){
                System.out.println("i==555");
                iteratorList1.remove();
            }else {
                System.out.println("iteratorList.next-->" +i);
            }
        }


        System.out.println("-------------foreach--------------");
        for(Integer data : mylinkeList){
            System.out.println("data-->"+data);
        }

        System.out.println("-------------iterator--------------");
        //抛异常:java.util.ConcurrentModificationException
        //在迭代时删除元素必须使用iterator自身的删除方法,使用mylinkeList的
        //删除方法将会触发快速失败机制
        Iterator<Integer> it = mylinkeList.iterator();
        while (it.hasNext()) {
            mylinkeList.remove(new Integer(100));
            Integer value = it.next();
            if (value==100) {
                System.out.println("该集合含100!");
            }else {
                System.out.println("该集合不含100!");
            }
        }
    }

    public static void print(MylinkeList mylinkeList){
        for (int i=0;i<mylinkeList.size();i++) {
            System.out.println("i->"+mylinkeList.get(i));
        }
    }


}
