package cn.tnar.msf.cm4.msg.dto.controller;

import cn.tnar.msf.cm4.msg.dto.entity.StudyRequest;
import cn.tnar.msf.cm4.msg.dto.annotation.Idempotent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxli
 * @date 2021/7/13 9:47
 */
@RestController
public class AspectController {

    @Idempotent(expiredTime = 20L)
    @RequestMapping("/aspect")
    public String aspect(@RequestBody StudyRequest studyRequest) {

        return null;
    }
}
