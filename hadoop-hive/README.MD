# SpringBoot 操作Hive
```
使用了Docker，在Docker中查找hadoop3,并安装。
Java驱动连接的Hive端口是10000，很可能Docker映射出来的不是10000，例如32771
http://localhost:5050/hive/*** 使用JDBC/DRUID操作hive
http://localhost:5050/hive2/*** 使用hiveJdbcTemplate操作hive
```
>查看库里面的表名称列表接口
```
http://localhost:5050/hive/table/list
```
>创建示例表：user_sample
```
http://localhost:5050/hive2/table/create
```
>查询库里面某个表的数据(例如查询表：user_sample)
```
http://localhost:5050/hive/table/select?tableName=user_sample
```
>查询库里面某个表的字段信息(例如查询表：user_sample)
```
http://localhost:5050/hive/table/describe?tableName=user_sample
```
>向表(user_sample)中插入数据【插入有点缓慢】
```
http://localhost:5050/hive2/table/insert
```