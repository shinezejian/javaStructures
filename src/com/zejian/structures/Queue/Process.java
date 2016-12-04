package com.zejian.structures.Queue;

/**
 * Created by zejian on 2016/12/3.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 模拟进程
 */
public class Process implements Comparable<Process> {

    private String name;//进程名称

    private int priority;//进程优先级默认5,范围1~10

    public Process(String name,int priority){
        this.name=name;
        if(priority>=1&&priority<=10){
            this.priority=priority;
        }else{
            throw new IllegalArgumentException("priority must between 1 and 10");
        }

    }

    public Process(String name){
        this(name,5);
    }

    /**
     * 优先级比较
     * @param o
     * @return
     */
    @Override
    public int compareTo(Process o) {
        return this.priority-o.priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
