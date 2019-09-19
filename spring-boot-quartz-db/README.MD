# SpringBoot2.1.7 集成MyBatis、Quartz、Postgresql
## 数据库准备
```
1、会自动创建到Postgres指定数据的public(schema)、
2、在PG库里面创建数据库：quartz, 再在quartz库里面创建schema：quartz，
    最后修改“tablePrefix: QRTZ_”为：tablePrefix: quartz.QRTZ_
```

## 特别说明
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
    properties:
      org:
        quartz:
          scheduler:
            instanceName: MyScheduler
            instanceId: AUTO
          jobStore:
            class: org.quartz.impl.jdbcjobstore.JobStoreTX
            driverDelegateClass: org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
            tablePrefix: QRTZ_
            isClustered: true
            clusterCheckinInterval: 10000
            useProperties: false
          threadPool:
            class: org.quartz.simpl.SimpleThreadPool
            threadCount: 10
            threadPriority: 5
            threadsInheritContextClassLoaderOfInitializingThread: true
```