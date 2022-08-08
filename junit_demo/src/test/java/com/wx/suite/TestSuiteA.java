package com.wx.suite;

import com.wx.testcase.TestCaseA;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = TestCaseA.class)
public class TestSuiteA {
}
