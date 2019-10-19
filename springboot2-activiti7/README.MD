1、参考文档：
```$xslt
1、https://www.jianshu.com/p/783f9c588376
2、https://blog.csdn.net/jiaoshaoping/article/details/80748065
```
2、mySQL5以上版本不自动创建表, 在url后加上nullCatalogMeansCurrent=true
```
    url: jdbc:mysql://127.0.0.1:3306/activiti7?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&nullCatalogMeansCurrent=true

```
3、Activiti7目前必须使用Security, 未来会去掉; 所以目前还得添加表
```
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

