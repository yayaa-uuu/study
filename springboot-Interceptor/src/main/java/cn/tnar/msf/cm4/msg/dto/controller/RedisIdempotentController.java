package cn.tnar.msf.cm4.msg.dto.controller;

import cn.tnar.msf.cm4.msg.dto.annotation.Idempotent;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wxli
 * @date 2021/7/12 17:07
 */
@RestController
public class RedisIdempotentController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存
     * @param dto
     */
    @RequestMapping("/set")
    public void set(@RequestBody IdempotentDTO dto){
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(dto.getStr(),dto.getTimestamp().toString(),dto.getTimestamp(),TimeUnit.SECONDS);
        //过期时间可以适当设置久一点,方便验证
    }

    /**
     * 获取缓存
     * @param str
     */
    @RequestMapping("/get")
    public void get(@RequestParam String str) {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        Object o = stringObjectValueOperations.get(str);
    }

    @Idempotent
    @RequestMapping("/aspectTest")
    public void aspectTest(){
        System.out.println("nihao ");
    }




    @Data
    private static class IdempotentDTO{
        private Long timestamp;
        private String str;
    }

}
