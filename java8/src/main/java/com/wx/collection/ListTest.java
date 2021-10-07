package com.wx.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 不要在 foreach 循环里进行元素的 remove/add 操作。
 * remove 元素请使用 Iterator
 * 方式，如果并发操作，需要对 Iterator 对象加锁。
 * @author wxli
 * @date 2021/9/28 19:49
 */
public class ListTest {

    @Test
    public void remove(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String item=iterator.next();
            if (item.equals("2")) {
                iterator.remove();
            }
        }
        System.out.println(list.toString());

    }
    @Test
    public void remove2(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if (item.equals("2")) {
                list.remove(item);
            }
        }
        System.out.println(list.toString());

    }
}
