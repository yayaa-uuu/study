package com.wx.controller;

import com.wx.entity.ThirdCarInRelation;
import com.wx.service.ThirdCarInRelationService;
import com.wx.util.TransactionManagerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wx.InitTask.MAP;

/**
 * @author wxli
 * @date 2021/7/6 17:16
 * <p>
 * 由   test  test2  可知，只要是获取到对应对象，
 * 然后对应的对象方法上有 @Transactional 注解，那么spring可以使用事务
 * 发生对应异常会回滚，默认runtimeException  ,  error   ,
 * 如数据库表有主键冲突，唯一键。那么会发生sqlException，得设置Transactional 里面的属性。
 *
 * 事务性质（ACID）
 * 原子性
 * 一致性
 * 持久性
 * 隔离性
 *
 * 隔离级别
 * 未提交读     两个事务同时执行，A事务没有提交，B事务读取了A事务没有提交的数据
 * 提交读       只能读取提交了的数据。  A事务（未提交）name=xiaoli
 *                                 B事务读取1次   name=xiaoli     A事务（提交） name=xiaozhang
 *                                 B事务读取1次   name=xiaozhang        不可重复度
 * 可重复度             按时间创建事务    A事务只会读取到那个时间的快照。
 * 串行化      加锁，独占
 *
 * 各种隔离级别下会出现的问题
 * 脏读             读取到了未提交的数据
 * 不可重复度        一个事务中多次读取数据不一致
 * 幻读             A事务修改全表， B事务提交一条数据。
 *                 A操作完毕之后发现还有一条数据没有被修改。
 */
@Slf4j
@RestController
public class TransactionalControllerTest {

    @Autowired
    ThirdCarInRelationService thirdCarInRelationService;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    PlatformTransactionManager platformTransactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    @RequestMapping("test")
    public void test() {
        thirdCarInRelationService.insert();
    }

    @RequestMapping("test2")
    public void test2() {
        MAP.get("a").insert();
    }

    /**
     * 不带返回值
     */
    @RequestMapping("test3")
    public void test3() {
        ThirdCarInRelation thirdCarInRelation = new ThirdCarInRelation();
        transactionTemplate.setTransactionManager(platformTransactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                thirdCarInRelation.setParkCode("123");
                thirdCarInRelation.setSerialno("321");
                thirdCarInRelation.setRemoteId("999");
                thirdCarInRelation.setCreateTime(0L);
                thirdCarInRelation.setUpdateTime(0L);
                thirdCarInRelationService.save(thirdCarInRelation);
                throw new RuntimeException("回滚");
            }
        });
    }

    /**
     * 可带返回值
     */
    @RequestMapping("test4")
    public void test4() {
        Long id = transactionTemplate.execute(status -> {
            ThirdCarInRelation thirdCarInRelation = new ThirdCarInRelation();
            thirdCarInRelation.setParkCode("123");
            thirdCarInRelation.setSerialno("3244");
            thirdCarInRelation.setRemoteId("99");
            thirdCarInRelation.setCreateTime(0L);
            thirdCarInRelation.setUpdateTime(0L);
            thirdCarInRelationService.save(thirdCarInRelation);
//            status.setRollbackOnly();     //手动回滚
            return thirdCarInRelation.getId();
        });

    }

    /**
     * 手动事务
     */
    @RequestMapping("test5")
    public void test5() {
        TransactionStatus transactionStatus = TransactionManagerUtil.getTransactionStatus(dataSourceTransactionManager);
        //监控  transactionStatus 对象     debug
        log.info("transactionStatus : {}", transactionStatus);
        try {
            Object savepoint = transactionStatus.createSavepoint();
            transactionStatus.rollbackToSavepoint(savepoint);
            log.info("transactionStatus : {}", transactionStatus);


            ThirdCarInRelation thirdCarInRelation = new ThirdCarInRelation();
            thirdCarInRelation.setParkCode("123");
            thirdCarInRelation.setSerialno("3244");
            thirdCarInRelation.setRemoteId("99");
            thirdCarInRelation.setCreateTime(0L);
            thirdCarInRelation.setUpdateTime(0L);
            thirdCarInRelationService.save(thirdCarInRelation);
            log.info("transactionStatus : {}", transactionStatus);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}
