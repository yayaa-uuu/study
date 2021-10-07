package cn.tnar.msf.cm4.msg.dto.controller;

import cn.tnar.msf.cm4.msg.dto.entity.ThirdCarInRelation;
import cn.tnar.msf.cm4.msg.dto.service.ThirdCarInRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 服务远程调用接口，事务能否回滚三方保存的数据。
 * 结论不可以，
 *
 * @author wxli
 * @date 2021/7/27 22:31
 */
@RestController
public class RestTest {
    @Autowired
    ThirdCarInRelationService thirdCarInRelationService;

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/restInsert")
    public void restInsert() {
        ThirdCarInRelation record1 = new ThirdCarInRelation();
        record1.setParkCode("1");
        record1.setSerialno("2");
        record1.setRemoteId("2");
        record1.setCreateTime(0L);
        record1.setUpdateTime(0L);
        thirdCarInRelationService.save(record1);
    }

    @Transactional(isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class,
            propagation = Propagation.REQUIRED
    )
    @RequestMapping("/rest")
    public void rest() throws InterruptedException {
        ThirdCarInRelation record1 = new ThirdCarInRelation();
        record1.setParkCode("1");
        record1.setSerialno("3");
        record1.setRemoteId("3");
        record1.setCreateTime(0L);
        record1.setUpdateTime(0L);
        thirdCarInRelationService.save(record1);
        restTemplate.getForObject("http://localhost:8080/restInsert", String.class);
        Thread.sleep(10000);
    }

}
