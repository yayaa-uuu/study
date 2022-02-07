package com.wx.strategy;

/**
 * @author wxli
 * @date 2021/7/20 21:22
 */
public class MallardDuck extends Duck{
    public MallardDuck() {
        quackBehavior=new Quack();
        flyBehavior=new FlyWithWings();
    }

    @Override
    public void display() {

    }
}
