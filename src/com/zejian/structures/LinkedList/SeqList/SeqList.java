package com.zejian.structures.LinkedList.SeqList;

import com.zejian.structures.LinkedList.ISeqList;

/**
 * Created by zejian on 2016/10/29.
 * 顺序表
 */
public class SeqList<T> implements ISeqList<T>{

    private Object[] table;                      //数组声明,用于存储元素
    private int length;                          //顺序表的大小

    public SeqList(int capacity)
    {
        //申请数组存储空间,元素初始化为null
        this.table = new Object[Math.abs(capacity)];
        this.length = 0;
    }

    /**
     * 默认大小为64
     */
    public SeqList()
    {
        this(64);
    }

    /**
     * 传入一个数组初始化顺序表
     * @param array
     */
    public SeqList(T[] array){

        if (array==null){
            throw new NullPointerException("array can\'t be empty!");
        }
        //创建对应容量的数组
        this.table = new Object[array.length];

        for (int i=0;i<array.length;i++){
            this.table[i]=array[i];
        }

        this.length=array.length;

    }

    /**
     * 判断顺序表是否为空
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return this.length==0;
    }

    /**
     * 计算顺序表的大小
     * @return
     */
    @Override
    public int length()
    {
        return this.length;
    }


    /**
     * 获取元素
     * @param index
     * @return
     */
    @Override
    public T get(int index){
        if (index>=0 && index<this.length)
            return (T) this.table[index];
        return null;
    }

    /**
     * 设置某个结点的的值
     * @param index
     * @param data
     * @return
     */
    @Override
    public T set(int index, T data){
        if (index>=0 && index<this.length&& data!=null)
        {
            T old = (T)this.table[index];
            this.table[index] = data;
            return old;
        }
        return null;
    }

    /**
     * 根据index插入元素
     * @param index 插入位置的下标,0作为起始值
     * @param data 插入的数据
     * @return
     */
    @Override
    public boolean add(int index, T data)
    {
        if (data==null)
            return false;

        //插入下标的容错判断,插入在最前面
        if (index<0)
            index=0;

        //插入下标的容错判断,插入在最后面
        if (index>this.length)
            index = this.length;

        //判断内部数组是否已满
        if (this.length==table.length)
        {
            //把原数组赋值给临时数组
            Object[] temp = this.table;

            //对原来的数组进行成倍拓容,并把原数组的元素复制到新数组
            this.table = new Object[temp.length*2];

            //先把原数组下标从0到index-1(即插入位置的前一个位置)复制到新数组
            for (int i=0; i<index; i++) {
                this.table[i] = temp[i];
            }
        }

        //从原数组的最后一个元素开始直到index位置,都往后一个位置
        // 最终腾出来的位置就是新插入元素的位置了
        for (int j=this.length-1; j>=index; j--) {
            this.table[j + 1] = this.table[j];
        }
        //插入新值
        this.table[index] = data;
        //长度加一
        this.length++;
        //插入成功
        return true;
    }

    /**
     * 在尾部插入元素
     * @param data
     * @return
     */
    @Override
    public boolean add(T data)
    {
        return add(this.length, data);
    }

    /**
     * 根据index删除元素
     * @param index 需要删除元素的下标
     * @return
     */
    @Override
    public T remove(int index)
    {
        if (this.length!=0 && index>=0 && index<this.length)
        {
            //记录删除元素的值并返回
            T old = (T)this.table[index];

            //从被删除的元素位置开,其后的元素都依次往前移动
            for (int j=index; j<this.length-1; j++) {
                this.table[j] = this.table[j + 1];
            }
            //设置数组元素对象为空
            this.table[this.length-1]=null;
            //顺序表长度减1
            this.length--;
            return old;
        }
        return null;
    }

    /**
     * 根据data删除某个数据
     * @param data
     * @return
     */
    @Override
    public boolean remove(T data) {
        if (this.length!=0 && data!=null)
            return this.remove(this.indexOf(data))!=null;
        return false;
    }

    @Override
    public boolean removeAll(T data) {
        boolean done=false;
        if (this.length!=0 && data!=null)
        {
            int i=0;
            while (i<this.length)
                //找出数据相同的选项
                if (data.equals(this.table[i]))
                {
                    this.remove(i);//根据下标删除
                    done = true;
                }
                else
                    i++;//继续查找
        }
        return done;
    }

    /**
     * 清空顺序表
     */
    @Override
    public void clear()
    {
        this.length=0;
    }

    /**
     * 判断两个顺序表是否相等
     * @param obj
     * @return
     */
    public boolean equals(Object obj)
    {
        //如果内存地址相当,那么两个顺序肯定相等
        if (this==obj)
            return true;

        //判断是否属于同种类型对象
        if (obj instanceof SeqList)
        {
            //强制转换成顺序表
            SeqList<T> list = (SeqList<T>)obj;
            for (int i=0; i<this.length(); i++)
                //比较每个值是否相当
                if (!(this.get(i).equals(list.get(i))))
                    return false;
            return true;
        }
        return false;
    }


    /**
     * 根据数据查询下标
     * @param data
     * @return
     */
    @Override
    public int indexOf(T data)
    {
        if (data!=null)
            for (int i=0; i<this.length; i++) {
                //相当则返回下标
                if (this.table[i].equals(data))
                    return i;
            }
        return -1;
    }

    /**
     * 根据data查询最后一个出现在顺序表中的下标
     * @param data
     * @return
     */
    @Override
    public int lastIndexOf(T data)
    {
        if (data!=null)
            for (int i=this.length-1; i>=0; i--)
                if (data.equals(this.table[i]))
                    return i;
        return -1;
    }

    /**
     * 查询是否包含某个数据
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data)
    {
        return this.indexOf(data)>=0;
    }

    @Override
    public String toString()
    {
        String str="(";
        if (this.length!=0)
        {
            for (int i=0; i<this.length-1; i++)
                str += this.table[i].toString()+", ";
            str += this.table[this.length-1].toString();
        }
        return str+") ";
    }
}
