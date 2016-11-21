package com.zejian.structures.LinkedList.MyCollection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zejian on 2016/11/20.
 * Blog : http://blog.csdn.net/javazejian [请尊重原创,转载注明出处]
 */
public class FailFastTest {

    public static void main(String[] args){
        ArrayList<Integer> list =new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            Integer value= (Integer) iterator.next();
            if(value==20){
                iterator.remove();//调用iterator的方法移除第2个元素
            }else {
                System.out.println("value-->"+value);
            }
        }
    }

}
