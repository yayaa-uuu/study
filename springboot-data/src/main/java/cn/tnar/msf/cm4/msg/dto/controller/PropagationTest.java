package cn.tnar.msf.cm4.msg.dto.controller;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wxli
 *
 * https://www.baeldung.com/spring-transactional-propagation-isolation
 *
 * spring中事务传播行为
 * 1.必须传播
 * 2.支持传播
 * 3.强制传播
 * 4.从不传播
 * 5.不支持传播
 * 6.直接新建一个事务
 * 7.嵌套传播
 *
 * @Transactional 将注解放在接口、类的定义上，或者直接放在方法上。
 * 它们根据优先级顺序相互覆盖；从低到高依次是：接口、超类、类、接口方法、超类方法、类方法。
 * @date 2021/7/21 16:22
 */
public class PropagationTest {
    /**
     * 必须传播
     *
     * Spring 检查是否存在活动事务，
     * 如果不存在，则创建一个新事务。
     * 否则，业务逻辑会附加到当前活动的事务中：
     *
     * 伪代码
     * if (isExistingTransaction()) {
     *     if (isValidateExistingTransaction()) {
     *         validateExisitingAndThrowExceptionIfNotValid();
     *     }
     *     return existing;
     * }
     * return createNewTransaction();
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredExample(String user) {
        // ...
    }

    /**
     * 支持传播
     *
     * Spring 首先检查是否存在活动事务。
     * 如果存在事务，则将使用现有事务。
     * 如果没有事务，则以非事务方式执行：
     *
     * 伪代码
     * if (isExistingTransaction()) {
     *     if (isValidateExistingTransaction()) {
     *         validateExisitingAndThrowExceptionIfNotValid();
     *     }
     *     return existing;
     * }
     * return emptyTransaction;
     *
     * @param user
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsExample(String user) {
        // ...
    }

    /**
     * 强制传播
     *
     * 如果存在活动事务，则将使用它。
     * 如果没有活动事务，则 Spring 会抛出异常：
     *
     * if (isExistingTransaction()) {
     *     if (isValidateExistingTransaction()) {
     *         validateExisitingAndThrowExceptionIfNotValid();
     *     }
     *     return existing;
     * }
     * throw IllegalTransactionStateException;
     *
     * @param user
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryExample(String user) {
        // ...
    }

    /**
     * 从不传播
     *
     * 如果有活动事务，Spring 会抛出异常：
     * if (isExistingTransaction()) {
     *     throw IllegalTransactionStateException;
     * }
     * return emptyTransaction;
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverExample(String user) {
        // ...
    }

    /**
     * 不支持传播
     *
     * 如果当前存在事务，首先Spring将其挂起，然后在没有事务的情况下执行业务逻辑：
     *
     * 该JtaTransactionManager中支持实时交易暂停外的开箱。
     * 其他人通过持有对现有引用的引用然后从线程上下文中清除它来模拟挂起
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedExample(String user) {
        // ...
    }

    /**
     * 创建一个新的
     *
     * Spring 将挂起当前事务（如果存在），然后创建一个新事务：
     *
     * if (isExistingTransaction()) {
     *     suspend(existing);
     *     try {
     *         return createNewTransaction();
     *     } catch (exception) {
     *         resumeAfterBeginException();
     *         throw exception;
     *     }
     * }
     * return createNewTransaction();
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewExample(String user) {
        // ...
    }

    /**
     *
     * 嵌套
     *
     * Spring 检查事务是否存在，如果存在，则标记一个保存点。
     * 这意味着如果我们的业务逻辑执行抛出异常，那么事务将回滚到这个保存点。
     * 如果没有活动事务，它的工作方式类似于REQUIRED。
     *
     * DataSourceTransactionManager支持这种开箱即用的传播。
     * JTATransactionManager 的一些实现也可能支持这一点。
     *
     * JpaTransactionManager接口支持嵌套只为JDBC连接。
     * 但是，如果我们将nestedTransactionAllowed标志设置为true，
     * 那么如果我们的 JDBC 驱动程序支持保存点，
     * 它也适用于 JPA 事务中的 JDBC 访问代码。
     */
    @Transactional(propagation = Propagation.NESTED)
    public void nestedExample(String user) {
        // ...
    }
}
