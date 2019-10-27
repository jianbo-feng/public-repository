create table test.user
(
    id bigint auto_increment comment '主键'
        primary key,
    age int null comment '年龄',
    password varchar(32) null comment '密码',
    sex int null comment '性别',
    username varchar(32) null comment '用户名'
)engine=MyISAM collate=utf8mb4_bin;

create table test1.user
(
    id bigint auto_increment comment '主键'
        primary key,
    age int null comment '年龄',
    password varchar(32) null comment '密码',
    sex int null comment '性别',
    username varchar(32) null comment '用户名'
)engine=MyISAM collate=utf8mb4_bin;

create table test2.user
(
    id bigint auto_increment comment '主键'
        primary key,
    age int null comment '年龄',
    password varchar(32) null comment '密码',
    sex int null comment '性别',
    username varchar(32) null comment '用户名'
)engine=MyISAM collate=utf8mb4_bin;

INSERT INTO test1.user (id, age, password, sex, username) VALUES (2, 22, 'admin', 1, 'admin');
INSERT INTO test2.user (id, age, password, sex, username) VALUES (3, 19, 'uuu', 2, 'user');
INSERT INTO test2.user (id, age, password, sex, username) VALUES (4, 18, 'bbbb', 1, 'zzzz');
