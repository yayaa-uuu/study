package com.wx.leetcode;

import java.util.HashMap;
import java.util.Map;

public class T387 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int j = 0; j < s.length(); ++j) {
            if (map.get(s.charAt(j)) == 1) {
                return j;
            }
        }
        return -1;
    }
}
