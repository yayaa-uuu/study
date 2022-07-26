package com.wx.homework;

import java.util.Scanner;

public class T1_1_21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strings = str.split(" ");
            String name = strings[0];
            String num1 = strings[1];
            String num2 = strings[2];
            System.out.print(name + " " + num1 + " " + num2 + " ");
            System.out.printf("%.3f", Double.parseDouble(num1) / Double.parseDouble(num2));
        }
    }
}
