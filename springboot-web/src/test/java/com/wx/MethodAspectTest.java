package com.wx;

import com.wx.annotation.MethodAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * https://stackoverflow.com/questions/56763066/how-to-test-an-aspect-with-springboottest
 */
@SpringBootTest
@Import(AnnotationAwareAspectJAutoProxyCreator.class) // activate aspect
public class MethodAspectTest {
    @MethodAnnotation
    @Test
    public void test(){
        System.out.println(123);
    }
}
