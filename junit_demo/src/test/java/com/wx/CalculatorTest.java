package com.wx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(60, calculator.add(10, 50), 0);
    }
}
