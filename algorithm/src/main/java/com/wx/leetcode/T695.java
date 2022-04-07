package com.wx.leetcode;

public class T695 {
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; i++) {
            for (int j = 0; j != grid[0].length; j++) {
                //判断该位置是否为陆地
                if (grid[i][j] != 0) {
                    ans = Math.max(ans,dfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    int dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] != 1) {       //终止条件
            return 0;
        }
        grid[x][y] = 0;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int ans = 1;
        for (int i = 0; i != 4; i++) {
            int mx = x + dx[i], my = y + dy[i];
            ans += dfs(grid, mx, my);
        }
        return ans;
    }
}
