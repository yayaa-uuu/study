package com.wx.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wxli
 * @date 2021/7/13 10:40
 */
@Aspect
@Component
@Slf4j
public class MethodAspect {

    @Pointcut("@annotation(com.wx.annotation.MethodAnnotation)")
    public void methodAspect() {

    }
    @Before("methodAspect()")
    public void start(){
        log.info("start  通知");
    }
    @After("methodAspect()")
    public void end(){
        log.info("end  通知");
    }

    @AfterReturning("methodAspect()")
    public void returnNotify(){
        log.info("return  notify");
    }
    @AfterThrowing("methodAspect()")
    public void exception(){
        log.info("exception  notify");


    }
}
