package cn.tnar.msf.cm4.msg.dto.aspect;

import cn.tnar.msf.cm4.msg.dto.annotation.Idempotent;
import cn.tnar.msf.cm4.msg.dto.entity.StudyRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 首先 创建一个切面，  其次创建切点，       --->       配置了切点的方法或者类都会被通知
 * 切点可以使用切点表达式，或者直接配置注解
 * 将切点织入通知位置       ，
 * 通过反射获取方法上面的参数，
 * 通过代理可以将参数对象获取。
 * 查缓存   --->   没有   ---> 设置缓存
 *
 * @author wxli
 * @date 2021/7/13 0:17
 */
@Slf4j
@Aspect
@Component
public class IdempotentAspect {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     *             if (arg.getClass().equals(StudyRequest.class)) {
     *                 System.out.println(arg);
     *                String name = ((StudyRequest) arg).getName();
     *                 System.out.println(name);
     *             }
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(cn.tnar.msf.cm4.msg.dto.annotation.Idempotent)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(" 开始拦截 ");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Idempotent annotation = method.getAnnotation(Idempotent.class);
        long time = annotation.expiredTime();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {

            if (arg instanceof StudyRequest) {
                String name = ((StudyRequest) arg).getName();
                ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
                if (Objects.isNull(stringObjectValueOperations.get(name))) {
                    stringObjectValueOperations.set(name, name, time, TimeUnit.SECONDS);
                    return joinPoint.proceed();
                } else {
                    throw new Exception("Idempotent hits, key=" + name);
                }
            }
        }
        return joinPoint.proceed();
    }
}