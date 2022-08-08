package com.wx;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(60, calculator.add(10, 50), 0);
    }
}
