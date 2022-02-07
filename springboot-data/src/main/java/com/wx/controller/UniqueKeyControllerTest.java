package com.wx.controller;

import com.wx.entity.ThirdCarInRelation;
import com.wx.service.ThirdCarInRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxli
 * @date 2021/7/8 20:49
 */
@RestController
public class UniqueKeyControllerTest {

    @Autowired
    ThirdCarInRelationService thirdCarInRelationService;
    /**
     * 向一张带有唯一键的表重复插入数据，mybatis plus   save（）方法返回值会报错还是会抛异常
     *
     * 结论：save（）方法会抛出异常，
     */
    @RequestMapping("/uniqueKey")
    public void uniqueKey(){
        ThirdCarInRelation thirdCarInRelation = new ThirdCarInRelation();
        thirdCarInRelation.setParkCode("1");
        thirdCarInRelation.setSerialno("123");
        thirdCarInRelation.setRemoteId("1");
        thirdCarInRelation.setCreateTime(0L);
        thirdCarInRelation.setUpdateTime(0L);

        System.out.println(thirdCarInRelationService.save(thirdCarInRelation));
    }
}
