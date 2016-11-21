package com.zejian.structures.LinkedList.MyCollection;

import java.util.*;


/**
 * Created by zejian on 2016/11/10.
 * Blog : http://blog.csdn.net/javazejian [请尊重原创,转载注明出处]
 */
public interface IList<T> {
    /**
     * list大小
     * @return
     */
    int size();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含data
     * @param data
     * @return
     */
    boolean contains(T data);


    /**
     * 清空数据
     */
    void clear();

    /**
     * 根据index获取数据
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 替换数据
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);

    /**
     * 尾部添加数据
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * 根据index添加数据
     * @param index
     * @param data
     */
    void add(int index, T data);

    /**
     * 移除数据
     * @param data
     * @return
     */
    boolean remove(T data);

    /**
     * 根据index删除数据
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 根据data获取下标
     * @param data
     * @return
     */
    int indexOf(T data);

    /**
     * 根据data获取最后一个下标
     * @param data
     * @return
     */
    int lastIndexOf(T data);




}
