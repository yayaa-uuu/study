package com.wx;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.ArrayList;
import java.util.List;

public class HamcrestTest {
    private List<String> values;

    @Before
    public void setUpList() {
        values = new ArrayList<>();
        values.add("x");
        values.add("y");
        values.add("z");
    }

    @Test
    public void testWithoutHamcrest() {
//        Assert.assertTrue(values.contains("one")
//                || values.contains("two")
//                || values.contains("three"));
        Assert.assertThat(values, JUnitMatchers.hasItem(CoreMatchers.anyOf(CoreMatchers.equalTo("one"),
                CoreMatchers.equalTo("two")
                , CoreMatchers.equalTo("three"))));
    }
}
