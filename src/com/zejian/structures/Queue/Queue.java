package com.zejian.structures.Queue;

/**
 * Created by zejian on 2016/11/28.
 * Blog :http://blog.csdn.net/javazejian/article/details/53375004 [原文地址,请尊重原创]
 * 队列抽象数据类型
 */
public interface Queue<T> {

    /**
     * 返回队列长度
     * @return
     */
    int size();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * offer 方法可插入一个元素,这与add 方法不同，
     * 该方法只能通过抛出未经检查的异常使添加元素失败。
     * 而不是出现异常的情况，例如在容量固定（有界）的队列中
     * NullPointerException:data==null时抛出
     * @param data
     * @return
     */
    boolean offer(T data);

    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    T peek();

    /**
     * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    T element();

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    T poll();

    /**
     * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    T remove();


    /**
     * 清空队列
     */
    void clearQueue();

}
