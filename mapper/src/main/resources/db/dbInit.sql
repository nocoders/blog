drop database if exists blog;

create database blog default charset utf8 collate utf8_general_ci;

use blog;

drop table if exists c_user;

/*==============================================================*/
/* Table: c_user                                                */
/*==============================================================*/
create table c_user
(
   id                   bigint not null auto_increment,
   user_name            varchar(32) not null default '' comment '用户名称',
   password             varchar(64) not null default '' comment '密码',
   telephone            varchar(11) default '' comment '手机号码',
   email                varchar(32) default '' comment '邮箱',
   nick_name            varchar(64) not null default '' comment '昵称',
   icon_url             varchar(128) default '' comment '头像url',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   status               tinyint(1) default 1 comment '状态：1-启用，0-禁用',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';