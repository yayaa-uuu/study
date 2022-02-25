package com.wx;

/**
 * 用辗转相除法求几个数的最大公约数，可以先求出其中任意两个数的最大公约数，
 * 再求这个最大公约数与第三个数的最大公约数，依次求下去，直到最后一个数为止。
 * 最后所得的那个最大公约数，就是所有这些数的最大公约数。
 *
 *
 * 计算两个非负整数的最大公约数
 * p，q，如果q是0，那么最大公约数是q。
 * 否则，将p除以q得到余数r，p和q的最大公约数即为q和r的最大公约数
 */
public class GreatestCommonDivisorTest {
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        System.out.println(gcd(24, 12));
        System.out.println(gcd(24, 60));
    }
}
