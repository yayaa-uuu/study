package com.wx;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ch = scanner.nextLine();
        int n = scanner.nextInt();
        while (true) {
            if (n > 0 && n < 10) {
                break;
            } else {
                n = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            if (str.lastIndexOf(ch) == -1) {
                System.out.println("Not Found");
            } else {
                System.out.println(str.lastIndexOf(ch));
            }
        }
    }

}
