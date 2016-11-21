package com.zejian.structures.LinkedList.MyCollection;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by zejian on 2016/11/8.
 * Blog : http://blog.csdn.net/javazejian [请尊重原创,转载注明出处]
 * 改良的顺序表类似java集合类ArrayList
 */
public class MyArrayList<T>  implements Serializable,IList<T>,Iterable<T>{

    private static final long serialVersionUID = 8683452581122892389L;

    /**
     * 默认大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空值数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};


    private int size;

    /**
     * 记录修改次数,适用于快速失败机制
     */
    private int modCount;

    /**
     * 存储数据的数组
     */
    private T[] elementData;


    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = (T[]) EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 扩容的方法
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity<size)
            return;

        modCount++;//记录元素变化

        T[] old = elementData;
        elementData = (T[]) new Object[capacity];
        //复制元素
        for (int i=0; i<size() ; i++)
            elementData[i]=old[i];
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * 清空数据
     */
    @Override
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    @Override
    public T get(int index) {
        //检测下标
        rangeCheck(index);

        return elementData[index];
    }

    @Override
    public T set(int index, T data) {
        //检测下标
        rangeCheck(index);

        T old=elementData[index];
        elementData[index]=data;
        return old;
    }

    @Override
    public boolean add(T data) {
        add(size(),data);
        return true;
    }

    /**
     * 添加
     * Blog : http://blog.csdn.net/javazejian
     * @param index
     * @param data
     */
    @Override
    public void add(int index, T data) {
        //判断容量是否充足
        if(elementData.length==size())
            ensureCapacity(size()*2+1);//扩容
        //根据index找到需要插入的位置
        for (int i=size; i>index; i--)
            elementData[i]=elementData[i-1];

        //赋值
        elementData[index]=data;
        size++;
        //记录变化
        modCount++;
    }

    /**
     * 根据data查询下标
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     * @return
     */
    @Override
    public int indexOf(T data) {
        if (data == null) {
            //查找null的下标
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            //查找有数据的下标
            for (int i = 0; i < size; i++)
                if (data.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 根据data查找最后一个的index
     * Blog : http://blog.csdn.net/javazejian
     * @param data
     * @return
     */
    @Override
    public int lastIndexOf(T data) {
        //倒序查找即可
        if (data == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (data.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean remove(T data) {
        if (data == null) {
            throw new NullPointerException("data can\'t be empty");
        } else {
            for (int index = 0; index < size; index++)
                if (data.equals(elementData[index])) {
                    this.remove(indexOf(data));
                    return true;
                }
        }
        return false;
    }

    /**
     * 根据下标移除元素
     * Blog : http://blog.csdn.net/javazejian
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {

        rangeCheck(index);
        modCount++;

        T oldValue = elementData[index];

        for (int i=index;i<size()-1;i++){
            elementData[i]=elementData[i+1];
        }

        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }


    /**
     * 检测下标
     * @param index
     */
    private void rangeCheck(int index) {
        if (index<0||index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }


    @Override
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    /**
     * 提供从指定index开始遍历的迭代器
     * Blog : http://blog.csdn.net/javazejian
     * @param index
     * @return
     */
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }



    /**
     * 提供从0开始遍历的迭代器
     * Blog : http://blog.csdn.net/javazejian
     * @return
     */
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    /**
     * 返回迭代器
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    /**
     * 迭代器-Itr
     * Blog : http://blog.csdn.net/javazejian
     */
    private class Itr implements Iterator<T> {
        /**
         * 表示将要访问的下一个元素的下标
         * index of next element to return
         */
        int cursor;

        /**
         * 当前正在访问的元素下标,如果没有则返回-1
         * index of last element returned; -1 if no such
         */
        int lastRet = -1;
        /**
         * 修改标识符,用于判断集合是否被修改
         */
        int expectedModCount = modCount;

        /**
         * 先判断是否还有下一个元素
         * @return
         */
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            //检测集合是否已被修改
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            //获取当前集合
            Object[] elementData = MyArrayList.this.elementData;

            if (i >= elementData.length)
                throw new ConcurrentModificationException();

            cursor = i + 1;//加一,移动到下一个要访问的下标

            return (T) elementData[lastRet = i];
        }

        /**
         * 使用迭代器的方法移除元素
         */
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            //检测集合是否已被改变
            checkForComodification();

            try {
                //移除当前操作的元素
                MyArrayList.this.remove(lastRet);

                //修改当前下标指向
                cursor = lastRet;
                //复原
                lastRet = -1;
                //更新标识符,防止抛出异常
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }


        /**
         * 检测modCount标识符
         */
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    /**
     * Blog : http://blog.csdn.net/javazejian
     * 可以前移指向的迭代器-ListItr
     */
    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) elementData[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T data) {
            checkForComodification();

            try {
                int i = cursor;
                MyArrayList.this.add(i, data);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }



public static void main(String[] args){
   MyArrayList<Integer> myArrayList=new MyArrayList<>();
    myArrayList.add(2);
    myArrayList.add(10);
    myArrayList.add(1);
    myArrayList.add(9);

    print(myArrayList);
    System.out.println("-------------");
    myArrayList.remove(2);
    print(myArrayList);
    System.out.println("-------------");
    System.out.println("index-->"+myArrayList.indexOf(10));
    myArrayList.set(0,0);
    print(myArrayList);

    System.out.println("-------------iterator--------------");
    Iterator iterator=myArrayList.iterator();
    while (iterator.hasNext()){
        System.out.println("iterator.next-->"+iterator.next());
    }

    System.out.println("-------------foreach--------------");
    for(Integer data : myArrayList){
        System.out.println("data-->"+data);
    }
    
}

    public static void print(MyArrayList myArrayList){
        for (int i=0;i<myArrayList.size();i++) {
            System.out.println("i->"+myArrayList.get(i));
        }
    }

}
