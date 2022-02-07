package com.wx.util;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author wxli
 * @date 2021/7/6 11:00
 */
public class TransactionManagerUtil {
    /**
     * 开启事务
     *
     * @param dataSourceTransactionManager
     * @return
     */
    public static TransactionStatus getTransactionStatus(DataSourceTransactionManager dataSourceTransactionManager) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        /**
         *  事物隔离级别，开启新事务，这样会比较安全些。
         */
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        /**
         * 获得事务状态
         */
        return dataSourceTransactionManager.getTransaction(def);
    }

    /**
     * 开启事务
     *
     * @param dataSourceTransactionManager
     * @return
     */
    public static TransactionStatus getTransactionStatus(DataSourceTransactionManager dataSourceTransactionManager, int transactionDefinition) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        /**
         *  事物隔离级别，开启新事务，这样会比较安全些。
         */
        def.setPropagationBehavior(transactionDefinition);
        /**
         * 获得事务状态
         */
        return dataSourceTransactionManager.getTransaction(def);
    }

    /**
     * 手动事务提交
     *
     * @param dataSourceTransactionManager
     */
    public static void commit(DataSourceTransactionManager dataSourceTransactionManager, TransactionStatus status) {
        dataSourceTransactionManager.commit(status);
    }

    /**
     * 手动事务回滚
     *
     * @param dataSourceTransactionManager
     */
    public static void rollback(DataSourceTransactionManager dataSourceTransactionManager, TransactionStatus status) {
        dataSourceTransactionManager.rollback(status);
    }
}
