package com.wx;

import java.util.Optional;

/**
 * @author wxli
 * @date 2021/7/21 10:23
 */
public class OptionalTest {
    public static void main(String[] args) {
        Student student = new Student();
        String name = Optional.ofNullable(student)
                .map(Student::getName)
                .get();
        System.out.println(name);
    }
}
