package com.wx.aspect;

import com.wx.entity.StudyRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wxli
 * @date 2021/7/13 0:17
 */
@Slf4j
@Aspect
@Component
public class IdempotentAspect {

    @Pointcut("@annotation(com.wx.annotation.Idempotent)")
    public void executeIdempotent() {
    }

    @Around("executeIdempotent()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(" 开始拦截 ");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(System.out::println);
        for (Object arg : args) {
            StudyRequest request = (StudyRequest) arg;
            System.out.println(request.getName());
            System.out.println(request.getAge());
        }
        return joinPoint.proceed();
    }
}
