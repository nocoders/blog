drop database if exists blog;

create database blog default charset utf8mb4 collate utf8_general_ci;

use blog;

-- drop table if exists c_user;

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

-- drop table if exists c_article;

/*==============================================================*/
/* Table: c_acticle                                             */
/*==============================================================*/
create table c_article
(
    id                   bigint(20) not null auto_increment comment '主键id',
    user_id              bigint(20) not null comment '用户id',
    title                varchar(64) not null default '' comment '文章标题',
    description          varchar(128) not null default '' comment '文章描述',
    is_original          tinyint(1) default 1 comment '是否原创，1-原创，0-转载',
    status               tinyint(1) default 1 comment '状态，1-草稿，0-发布',
    views                int(10) default 0 comment '浏览量',
    comments             int(10) default 0 comment '评论数量',
    likes                int(10) default 0 comment '点赞数量',
    `collections`        int(10) DEFAULT 0 COMMENT '收藏量',
    deleted              tinyint(1) default 0 comment '是否删除，0-未删除，1-已删除',
    create_time          timestamp default CURRENT_TIMESTAMP,
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- drop table if exists c_article_content;

/*==============================================================*/
/* Table: c_article_content                                     */
/*==============================================================*/
create table c_article_content
(
    id                   bigint(20) not null auto_increment,
    article_id           bigint(20) not null comment '文章id',
    content              longtext not null comment '文章内容',
    create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章内容表';

-- drop table if exists c_article_comments;
/*==============================================================*/
/* Table: c_article_comments 文章评论表                                     */
/*==============================================================*/
create table c_article_comments
(
    id                   bigint(20) not null auto_increment,
    article_id           bigint(20) not null comment '文章id',
    article_type         varchar(10) not null comment '文章类型',
    content              varchar(1000) not null comment '评论内容',
    from_uid             bigint(20) not null comment '评论用户id',
    create_time          timestamp default CURRENT_TIMESTAMP,
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章评论表';

drop table if exists c_article_comment_reply;

/*==============================================================*/
/* Table: c_article_comment_reply                               */
/*==============================================================*/
create table c_article_comment_reply
(
    id                   bigint(20) not null auto_increment,
    comment_id           bigint(20) not null comment '根评论id',
    reply_id             bigint(20) not null comment '回复id，针对该评论回复的上一条评论或回复',
    reply_type           tinyint(1) not null comment '回复类型，0：针对评论回复，1：针对回复回复',
    from_uid             bigint(20) not null comment '回复用户id',
    to_uid               bigint(20) not null comment '目标用户id',
    create_time          timestamp default CURRENT_TIMESTAMP,
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章评论回复表';

-- drop table if exists c_article_likes;

/*==============================================================*/
/* Table: c_article_likes                                       */
/*==============================================================*/
create table c_article_likes
(
    id                   bigint(20) not null auto_increment,
    article_id           bigint(20) not null comment '文章id',
    user_id              bigint(20) not null comment '点赞用户id',
    username             varchar(32) not null comment '点赞用户名名称称',
    status               tinyint(1) not null default 0 comment '点赞状态，1：点赞，0：点赞后又取消',
    create_time           timestamp default CURRENT_TIMESTAMP,
    update_time           timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章点赞表';

-- drop table if exists c_article_collections;

/*==============================================================*/
/* Table: c_article_collections                                 */
/*==============================================================*/
create table c_article_collections
(
    id                   bigint(20) not null auto_increment,
    article_id           bigint(20) not null comment '文章id',
    article_url          varchar(128) not null comment '收藏文章url',
    article_name         varchar(64) not null comment '文章名称',
    user_id              bigint(20) not null comment '收藏用户id',
    create_time          timestamp default CURRENT_TIMESTAMP,
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章收藏记录表';