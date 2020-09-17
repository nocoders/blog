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
    /** 文章点赞数 */
    public static final String ARTICLE_LIKED_COUNT = "ARTICLE_LIKED_COUNT";
    /** 用户总点赞数 */
    public static final String USER_LIKED_COUNT = "USER_LIKED_COUNT";
    /** 用户收藏锁 */
    public static final String ARTICLE_USER_COMMENT_LOCK = "ARTICLE_USER_COMMENT_LOCK:%s:%s";
    /** 文章评论数量 */
    public static final String USER_ARTICLE_COMMENT_COUNT = "ARTICLE_COMMENT_COUNT:%s";
    /** 用户评论总数量 */
    public static final String USER_COMMENT_COUNT = "USER_COMMENT_COUNT";

}
