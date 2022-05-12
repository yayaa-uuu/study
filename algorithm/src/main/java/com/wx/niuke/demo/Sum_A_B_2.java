package com.wx.niuke.demo;

import java.util.Scanner;

/**
 * 链接：https://ac.nowcoder.com/acm/contest/5657/B
 * 来源：牛客网
 *
 * 计算a+b
 * 打开以下链接可以查看正确的代码
 * https://ac.nowcoder.com/acm/contest/5657#question
 *
 * 数据范围：数据组数满足 1 \le t \le 100 \1≤t≤100  ，数据大小满足 1 \le a,b \le 1000 \1≤a,b≤1000
 * 输入描述:
 * 输入第一行包括一个数据组数t(1 <= t <= 100)
 * 接下来每行包括两个正整数a,b(1 <= a, b <= 1000)
 * 输出描述:
 * 输出a+b的结果
 * 示例1
 * 输入
 * 复制
 * 2
 * 1 5
 * 10 20
 * 输出
 * 复制
 * 6
 * 30
 */
public class Sum_A_B_2 {
    public static void main(String[] args) {
        //输入
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int curr = 0;
        while (curr < t) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
            curr++;
        }
    }
}
