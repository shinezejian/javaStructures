package com.zejian.structures.Tree.BinaryTree;

import java.io.Serializable;

/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 自定义异常
 */
public class EmptyTreeException extends RuntimeException implements Serializable{


    private static final long serialVersionUID = -8766038608920134747L;


    public EmptyTreeException(){
        super();
    }

    public EmptyTreeException(String msg){
        super(msg);
    }
}
