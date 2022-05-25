package com.wx;

import com.wx.aspect.DemoComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class DemoComponentFailTest {
    @Autowired
    DemoComponent demoComponent;

    @Test
    public void test() {
        demoComponent.sayHello();
    }
}