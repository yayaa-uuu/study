package com.wx.fun;

import java.util.Optional;

/**
 * @author wxli
 * @date 2021/7/21 10:23
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer integer = 200;

        Optional.of(integer).ifPresent(System.out::println);

        System.out.println(Optional.of(integer).filter(i -> i > 2000).get());

        System.out.println(Optional.of(integer).filter(i -> i > 2000).orElse(100));

        System.out.println(Optional.of(integer).filter(i -> i > 2000).orElseThrow(RuntimeException::new));

        System.out.println(Optional.of(integer).filter(i -> i > 2000).orElseGet(() -> 300));

        System.out.println(Optional.of(integer).isPresent());

        Optional.of(integer).map(integer1 -> integer - 100).get();

    }
}
