package com.zejian.structures.Tree.BinaryTree;

import java.io.Serializable;

/**
 * Created by zejian on 2016/12/14.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 二叉树结点
 */
public class BinaryNode<T extends Comparable> implements Serializable{
    private static final long serialVersionUID = -6477238039299912313L;

    public BinaryNode<T> left;//左结点

    public BinaryNode<T> right;//右结点

    public T data;

    public BinaryNode(T data,BinaryNode left,BinaryNode right){
        this.data=data;
        this.left=left;
        this.right=right;
    }

    public BinaryNode(T data){
        this(data,null,null);

    }

    /**
     * 判断是否为叶子结点
     * @return
     */
    public boolean isLeaf(){
        return this.left==null&&this.right==null;
    }

}
