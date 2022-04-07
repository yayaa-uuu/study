package com.wx.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class T695_2 {
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;

        for (int i = 0; i != grid.length; i++) {
            for (int j = 0; j != grid[0].length; j++) {
                int curr = 0;
                Queue<Integer> queuei = new LinkedList<>();
                Queue<Integer> queuej = new LinkedList<>();
                queuei.offer(i);
                queuej.offer(j);
                while (!queuei.isEmpty()) {
                    //取出当前位置值
                    int cur_i = queuei.poll();
                    int cur_j = queuej.poll();
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++curr;
                    grid[cur_i][cur_j] = 0;     //将当前位置修改置为0
                    //当前位置 上下左右移动
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != di.length; ++index) {
                        int index_i = di[index] + cur_i;
                        int index_j = dj[index] + cur_j;
                        queuei.offer(index_i);
                        queuej.offer(index_j);
                    }
                }
                ans = Math.max(ans, curr);
            }
        }
        return ans;
    }

}
