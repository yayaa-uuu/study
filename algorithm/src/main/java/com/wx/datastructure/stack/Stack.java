package com.wx.datastructure.stack;

public interface Stack<T> {
    void push(T value);
    T pop();
    boolean isEmpty();
    int size();
}
