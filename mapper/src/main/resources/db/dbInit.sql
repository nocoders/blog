drop database if exists blog;

create database blog default charset utf8 collate utf8_general_ci;

use blog;

drop table if exists c_user;

/*==============================================================*/
/* Table: c_user                                                */
/*==============================================================*/
create table c_user
(
   id                   bigint(20) UNSIGNED not null AUTO_INCREMENT COMMENT '主键',
   user_name            varchar(32) DEFAULT '' COMMENT '用户名称',
   `password`           varchar(64) DEFAULT '' COMMENT '用户密码',
   telephone            varchar(11) DEFAULT '' COMMENT '用户手机号码',
   email                varchar(32) DEFAULT '' COMMENT '用户邮箱',
   nick_name            varchar(64) DEFAULT '' COMMENT '用户昵称',
   create_time          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   update_time         	timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';