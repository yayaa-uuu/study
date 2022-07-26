package com.wx.homework;

import java.util.Scanner;

/**
 * 编写一个程序，如果double 类型的变量x，y 都严格位于0 和 1 之间则打印 ture 否者打印false
 */
public class T1_1_5 {
    public static void main(String[] args) {
        double x, y;
        Scanner scanner = new Scanner(System.in);
        x = scanner.nextDouble();
        y = scanner.nextDouble();
        if (x > 0 && x < 1 && y > 0 && y < 1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
