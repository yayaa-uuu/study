package com.wx.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/Java%20%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E5%AE%9E%E6%88%98-%E5%AE%8C/07%20%20%E6%A1%88%E4%BE%8B%E5%88%86%E6%9E%90%EF%BC%9A%E6%97%A0%E5%A4%84%E4%B8%8D%E5%9C%A8%E7%9A%84%E7%BC%93%E5%AD%98%EF%BC%8C%E9%AB%98%E5%B9%B6%E5%8F%91%E7%B3%BB%E7%BB%9F%E7%9A%84%E6%B3%95%E5%AE%9D.md
 * <p>
 * accessOrder   true 按照对象的访问顺序进行排序
 * false 按照对象的插入顺序进行排序
 */
public class LRU extends LinkedHashMap {
    int capacity;

    public LRU(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}