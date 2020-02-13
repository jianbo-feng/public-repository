# SpringBoot2.2.1 集成MyBatis、Quartz、Postgresql
## 数据库准备
```
1、会自动创建到Postgres指定数据的public(schema)、
2、在PG库里面创建数据库：quartz, 再在quartz库里面创建schema：quartz，
    最后修改“tablePrefix: QRTZ_”为：tablePrefix: quartz.QRTZ_
3、需要根据脚本table_postgres.sql 创建表
```

## 动态定时任务与静态定时任务
```
动态定时任务：在项目运行过程中增删改定时任务
静态定时任务：通过配置文件或@Bean方式声明任务的作业信息或调度器信息

如果项目以动态定时任务为主，尽量使用动态定时任务，以便管理
```

## 集群说明
```
1、代码一致，最少需要存储的数据库(以及表)相同，以及相应的作业类全路径相同
2、需要springboot2-quartz-db和springboot2-quartz-db2项目一起做测试
```

## 项目访问路径
```
http://localhost:8084/quartz-db/
ex: http://localhost:8084/quartz-db/test/demo
```

## 可能出现的问题
```
个别定时任务需要查询数据库、以及集群(还存活的应用根据集群配置)需要访问数据库等，可能会出现还有未关闭的数据库连接
```

## 特别说明
### application.yml
```
spring:
  #配置数据源
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/quartz
    username: postgres
    password: postgres
  quartz:
    #持久化到数据库方式
    job-store-type: jdbc
    initialize-schema: always
```
### quartz.properties
```
#==============================================================
#Configure Main Scheduler Properties
#==============================================================
org.quartz.scheduler.instanceName = quartzScheduler
org.quartz.scheduler.instanceId = AUTO

org.quartz.scheduler.skipUpdateCheck = true

#==============================================================
#Configure JobStore
#==============================================================
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX
#org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
#org.quartz.jobStore.tablePrefix = QRTZ_
org.quartz.jobStore.tablePrefix = quartz6.QRTZ_
org.quartz.jobStore.isClustered = true
org.quartz.jobStore.clusterCheckinInterval = 2000
#org.quartz.jobStore.maxMisfiresToHandleAtATime = 1
#org.quartz.jobStore.misfireThreshold = 120000
#org.quartz.jobStore.txIsolationLevelSerializable = true
org.quartz.jobStore.selectWithLockSQL = SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE
org.quartz.jobStore.useProperties = true

#==============================================================
#Configure DataSource
#==============================================================
#org.quartz.jobStore.dataSource = myDS
#org.quartz.dataSource.myDS.driver = org.postgresql.Driver
#org.quartz.dataSource.myDS.URL = jdbc:postgresql://localhost:5432/quartz
#org.quartz.dataSource.myDS.user = postgres
#org.quartz.dataSource.myDS.password = postgres
#org.quartz.dataSource.myDS.maxConnections = 30

#==============================================================
#Configure ThreadPool
#==============================================================
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount = 20
org.quartz.threadPool.threadPriority = 5
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true

org.quartz.plugin.triggHistory.class = org.quartz.plugins.history.LoggingJobHistoryPlugin
org.quartz.plugin.shutdownhook.class = org.quartz.plugins.management.ShutdownHookPlugin
org.quartz.plugin.shutdownhook.cleanShutdown = true
```
