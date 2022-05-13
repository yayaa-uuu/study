
```plantuml
@startmindmap
* 事务
**_ 原子性
***_ 事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用
**_ 一致性
***_  执行事务前后，数据保持一致
***_  例如转账业务中，无论事务是否成功，转账者和收款人的总额应该是不变的
**_ 隔离性
***_  并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发事务之间数据库是独立的；
**_ 持久性
***_  一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库发生故障也不应该对其有任何影响。
@endmindmap
```

```plantuml
@startmindmap
* 隔离级别
**_ 未提交读
***_ 允许读取未提交的数据更变，
**_ 提交读
***_ 允许读取并发事务已经提交的数据，可以阻止脏读，但幻读和不可重复读仍可能发生
**_ 可重复读
***_ 对同一字段多次读取的结果是一致的，除非数据被事务自己所修改
**_ 串行化
***_ 最高隔离级别，完全服从ACID的隔离级别，所有事务依次逐个执行，这样事务之间完全不可能产生干扰，也就是说，该级别可以防止脏读，不可重复读和幻读。
@endmindmap
```

```plantuml
@startmindmap
* 并发事务可能出现的问题
**_ 脏读
***_ A事务读取到B事务未提交的修改
**_ 不可重复读
***_ A事务多次读取，出现不同结果(B事务提交前后，对A事务造成影响)
**_ 幻读
***_ A事务多次读取。B事务插入数据。
@endmindmap
```

```plantuml
@startmindmap
* 预写日志(WAL)
**_ 在允许页缓存将页上的修改缓存起来的同时，保证数据库系统仍然具有持久性的语义
**_:在那些受操作影响的缓存页被同步到磁盘上之前，将所有操作持久化到磁盘上。
每个修改数据库状态的操作必须先写日志到磁盘上，然后才能修改相关页的内容;
**_ 当发生崩溃时，使系统可以从操作日志中重建内存中丢失的更改。
**_ 日志先行
**_ 追加的形式写入日志顺序I/O
@endmindmap
```


#### 各隔离级别下，并发事务问题对应

|  | 脏读 | 不可重复读 | 幻读 |
|--|--|--|--|
| 未提交读 | √ | √ | √ |
| 提交读 | x | √ | √ |
| 可重复读 | x | x | √ |
| 串行化 | x | x | x |




```plantuml
@startmindmap
* InnoDB下事务的实现原理
**_ redo_log(重做日志)保证事务的持久性
**_ undo_log(回滚日志)保证事务的原子性
**_ 锁机制、MVCC保证事务的隔离性(默认支持的隔离级别是 REPEATABLE-READ)
**_ 保证了持久性，原子性，隔离性之后一致性才能得到保障
@endmindmap
```


```plantuml
@startmindmap
* 树
**_ 二分搜索树 
***_ 特点 
****_ 一个节点的键大于其左子树中存储的任何键，且小于右子树中存储的任何键

**_ 2-3树 
**_ AVL树 
***_ 左右子树高度相差不超过1 
***_ 树高度logm n 
**_ B树
***_ 所有节点既放key也放val
***_ 叶子节点独立
***_ 对范围内的每个节点做二分查找，可能没有达到叶子节点检索就结束了。
**_ B+树
***_ 非叶子节点存key，叶子节点存key和val
***_ 叶子节点有一个next指针指向相邻的叶子节点
***_ 查找稳定，任何查找都是从根节点开始到叶子节点。



@endmindmap
```


```plantuml
@startmindmap
* 索引
**_ 作用
***_ 相当于目录
**_ 优点
***_ 使用索引可以大大加快检索的速度
***_ 创建唯一索引，可以保证数据库中每一行数据的唯一性
**_ 缺点
***_:创建索引和维护索引需要耗费许多时间。
当对表的数据进行增删改的时候，如果数据有索引，那么索引也需要动态修改，会降低sql执行效率。;
***_ 索引需要使用物理文件存储，也会耗费一定空间。






@endmindmap
```

```plantuml
@startmindmap
* 索引类型
**_ 主键索引
***_ 一个表只能有一个主键，且主键不能为NULL，不能重复
**_ 二级索引(辅助索引)
***_ 特点
****_ 叶子节点 Val 存主键值，索引列值。
***_ 唯一索引
****_ 属性列数据不能重复，允许 NULL ，一张表允许创建多个唯一索引
****_ 建立唯一索引的目的是该属性列的唯一性，而不是为了提高效率
***_ 普通索引
****_ 一张表允许创建多个普通索引，并允许数据重复和 NULL。
****_ 普通索引的唯一作用就是为了快速查询数据，
***_ 前缀索引
****_ 前缀索引只适用于字符串类型的数据。
****_ 前缀索引是对文本的前几个字符创建索引，相比普通索引建立的数据更小， 因为只取前几个字符。

***_ 全文索引
****_ 全文索引主要是为了检索大文本数据中的关键字的信息，是目前搜索引擎数据库使用的一种技术

@endmindmap
```



```plantuml
@startuml
title 聚簇索引
start
if (主键索引) then(yes)

else if (检查是否有唯一索引) then(yes)
else (no)
   : InnoDB会隐式定义一个自增主键 ;
endif
:作为聚簇索引;
end
@enduml
```

```plantuml
@startmindmap
* 聚簇索引和非聚簇索引
**_ 聚簇索引
***_ 特点
****_: 索引结构和数据一起存放的索引。主键索引属于聚簇索引
对于InnoDB引擎来说，该表的索引(B+树)的每个非叶子节点存储索引，叶子节点存储索引和索引对应的数据;
***_ 优点
****_ 聚集索引的查询数据非常快，因为整个B+树本身就是一颗多叉平衡树，叶子节点也都是有序的，定位到索引的节点，就相当于定位到了数据。
***_ 缺点
****_ 依赖于有序的数据 
****_ 更新代价大 

**_ 非聚簇索引
***_ 特点
****_ 非聚集索引即索引结构和数据分开存放的索引。
***_ 优点
****_ 更新代价比聚集索引要小 
***_ 缺点
****_ 跟聚集索引一样，非聚集索引也依赖于有序的数据
****_ 可能会二次查询(回表)

@endmindmap
```

```plantuml
@startmindmap
* 高性能索引策略 
**_ 最左匹配 
***_ index(a,b)
***_ select a,b from table where a=?  
***_ select a,b from table where a=? ,b = ?    
***_ select a,b from table where b = ?     失效 

**_ 覆盖索引 
***_ index(a,name)
***_ select name from table where a = ? 
***_ select name,b from table where a = ?  回表 

@endmindmap
```




#### 存储引擎索引区别 

|  | 锁 | 事务 | 外键 | 崩溃后恢复 |MVCC(行锁升级)|
|--|--|--|--|--|--|
| InnoDB | 行锁(默认)、表锁 | √ | √ | √ | √ | √ |
| MyISAM | 表锁 | x | x | x | x | x |



```plantuml
@startmindmap
title InnoDB 锁算法

* 锁算法
**_ Record lock 
***_ 记录锁，单个行记录上的锁 
**_ Gap lock 
***_ 间隙锁，锁定一个范围，不包括记录本身 
**_ Next-key lock 
***_ record+gap 临键锁，锁定一个范围，包含记录本身 
@endmindmap
```



```plantuml
@startmindmap
title InnoDB MVCC

* MVCC
**_ 工作原理
***_ 使用某个时间点存在的数据快照

**_ 实现原理 
***_ row_id 
****_ 行ID(唯一键)    
***_ transaction_id
****_ 事务ID
***_ roll_pointer
****_ 回滚指针，指向上一个旧版本
@endmindmap
```


```plantuml
@startuml
title 跨不同事务处理行的多个版本的序列图


TransactionA -> row : a write
participant InnoDB_engine as InnoDB_engine

TransactionA -> undo_log : undo record with txn ID A 
undo_log -> TransactionA : Rollback pointer
TransactionA -> redo_log : redo log record

loop compare transaction IDs
InnoDB_engine <- TransactionB : records row
InnoDB_engine -> TransactionB : apply undo records till correct txn ID
end





@enduml
```







```plantuml
@startmindmap
title InnoDB下日志文件
* log
**_ bin log
***_ 主从，数据恢复
**_ undo log
***_ 数据库原子性保证
***_ 记录数据的逻辑变化
***_ 例每条INSERT 都会对应一条DELETE,每条UPDATE 都会对应一条相反的UPDATE
**_ redo log 
***_ 数据库持久性保证 
***_ 可发生在事务提交前后，根据刷盘策略决定
***_ 数据库后台线程每秒都会对该日志缓存进行刷盘动作
@endmindmap
```

```plantuml
@startmindmap
title binlog 刷盘策略
* sync_binlog
**_ 0：不去强制要求，由系统自行判断何时写入磁盘；
**_ 1：每次commit的时候都要将binlog写入磁盘；
**_ N：每N个事务，才会将binlog写入磁盘。

@endmindmap
```

```plantuml
@startmindmap
title redo_log 刷盘策略
* innodb_flush_log_at_trx_commit
**_ 0(延迟写) 提交事务不写磁盘 写入过程在master thread 中进行
**_ 1(实时写，实时刷) 提交事务调用fsync
**_ 2(实时写，延迟刷) 提交事务时不写磁盘  写入文件系统缓存

@endmindmap
```


<!-- @import "../image/06.png" -->
<!-- @import "../image/07.png" -->
<!-- @import "../image/09.png" -->


#### 参考
[高性能mysql第三版](C:\Users\yy\Desktop\资料\mysql\高性能mysql第三版.pdf)
[高性能mysql第四版](C:\Users\yy\Desktop\资料\mysql\高性能mysql第三版.pdf)
[MySQL是怎样运行的：从根儿上理解MySQL](C:\Users\yy\Desktop\资料\mysql\MySQL是怎样运行的：从根儿上理解MySQL.pdf)
[数据库系统内幕.pdf](C:\Users\yy\Desktop\资料\mysql\数据库系统内幕.pdf)