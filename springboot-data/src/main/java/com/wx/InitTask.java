package com.wx;

import com.wx.service.ThirdCarInRelationService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxli
 * @date 2021/7/6 17:53
 */
@Component
public class InitTask {
    public final static Map<String, ThirdCarInRelationService> MAP =new HashMap<>();

    private final ThirdCarInRelationService tThirdAdmissionRelationService;

    public InitTask(ThirdCarInRelationService tThirdAdmissionRelationService) {
        this.tThirdAdmissionRelationService = tThirdAdmissionRelationService;
    }
    @PostConstruct
    public void init(){
        MAP.put("a",tThirdAdmissionRelationService);
    }
}
