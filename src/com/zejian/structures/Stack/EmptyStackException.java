package com.zejian.structures.Stack;

import java.io.Serializable;

/**
 * Created by zejian on 2016/11/27.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 自定义异常
 */
public class EmptyStackException extends RuntimeException implements Serializable{


    private static final long serialVersionUID = -8766038608920134747L;


    public EmptyStackException(){
        super();
    }

    public EmptyStackException(String msg){
        super(msg);
    }
}
