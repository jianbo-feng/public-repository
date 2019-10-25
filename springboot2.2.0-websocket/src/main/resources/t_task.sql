CREATE TABLE `t_task` (
  `task_id` varchar(255) NOT NULL COMMENT '任务ID：唯一标识',
  `task_name` text NOT NULL COMMENT '任务名称',
  `task_desc` text COMMENT '任务描述',
  `task_cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` text COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `task_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `task_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `business_key` text COMMENT '业务配置信息',
  `record_time` datetime DEFAULT NULL COMMENT '信息记录时间',
  `record_flag` int(11) DEFAULT NULL COMMENT '信息标识(1,正常;0,删除;)',
  `creator_id` varchar(255) DEFAULT NULL COMMENT '信息创建者ID',
  `updator_id` varchar(255) DEFAULT NULL COMMENT '信息更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `repeat_count` int(11) DEFAULT NULL COMMENT '执行次数(若为null,则表示无限次数；0：执行一次之后不再执行)',
  `start_at` datetime DEFAULT NULL COMMENT '任务启动时间',
  PRIMARY KEY (`task_id`)
)

INSERT INTO `t_task` VALUES ('1', '测试任务', '测试任务', '0 0/2 * * * ?', NULL, NULL, 'test_group', NULL, '2019-10-25 20:28:24', 1, 'admin', NULL, NULL, 0, NULL);
