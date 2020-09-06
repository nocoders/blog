package com.crop.common.api;

/**
 * redis 定义相关常量
 * @author linmeng
 * @version 1.0
 * @date 4/9/2020 上午10:00
 */
public class RedisConstant {

    /** 用户点赞文章记录，0：用户取消点赞，1：用户点赞*/
    public static final String ARTICLE_USER_LIKED = "ARTICLE_USER_LIKED:%s";
    /**  用户点赞锁  */
    public static final String ARTICLE_USER_LIKED_LOCK = "ARTICLE_USER_LIKED_LOCK:%s:%s";
}
