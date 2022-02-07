package com.wx;

import com.wx.annotation.Idempotent;
import lombok.Setter;

/**
 * @author wxli
 * @date 2021/7/12 18:02
 */
@Idempotent
public class Parent {

    @Setter
    private String name;

    @Idempotent
    public void test(){

    }
}
