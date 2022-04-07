package com.wx.datastructure.stack.day;

import com.wx.datastructure.stack.Stack;

public class ArrayStack implements Stack {

    private int N;
    private Object[] a = new Object[N];

    @Override
    public void push(Object value) {
        if (N == a.length) resize(2 * a.length);
        a[N++] = value;
    }

    private void resize(int max) {
        Object[] temp = new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Object pop() {
        Object object = a[--N];
        if (N > 0 && a.length == N / 4) resize(N / 2);
        return object;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }
}
