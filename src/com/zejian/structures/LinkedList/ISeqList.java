package com.zejian.structures.LinkedList;

/**
 * Created by zejian on 2016/10/30.
 * 顺序表顶级接口
 */
public interface ISeqList<T> {

    /**
     * 判断链表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 链表长度
     * @return
     */
    int length();

    /**
     * 获取元素
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 设置某个结点的的值
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);

    /**
     * 根据index添加结点
     * @param index
     * @param data
     * @return
     */
    boolean add(int index, T data);

    /**
     * 添加结点
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * 根据index移除结点
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 根据data移除结点
     * @param data
     * @return
     */
    boolean remove(T data);

    /**
     * 根据data移除所有结点
     * @param data
     * @return
     */
    boolean removeAll(T data);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 是否包含data结点
     * @param data
     * @return
     */
    boolean contains(T data);

    /**
     * 根据值查询下标
     * @param data
     * @return
     */
    int indexOf(T data);

    /**
     * 根据data值查询最后一个出现在顺序表中的下标
     * @param data
     * @return
     */
    int lastIndexOf(T data);

    /**
     * 输出格式
     * @return
     */
    String toString();
}
