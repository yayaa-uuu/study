package com.wx.datastructure.stack;

import java.util.Iterator;

public class ArrayStack<T> implements Stack<T>, Iterable<T> {
    private T[] a;
    private int N;

    public ArrayStack(T[] a) {
        a = (T[]) new Object[1];
    }

    @Override
    public void push(T value) {
        if (N == a.length) resize(2 * a.length);
        a[N++] = value;
    }

    private void resize(int max) {
        //将原来的数组元素到新数组中。
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    @Override
    public T pop() {
        T t = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(N / 2);
        return t;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int i;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {
        }
    }
}
