package com.crop.common.util;

import com.crop.common.api.RedisConstant;

/**
 * @author linmeng
 * @version 1.0
 * @date 4/9/2020 上午9:59
 */
public class RedisKeyUtil {

    /**
     * 获取文章点赞redis key
     * @param articleId
     * @author linmeng
     * @date 4/9/2020 上午10:16
     * @return java.lang.String
     */
    public static String getArticleLikedKey(Long articleId){

        return String.format(RedisConstant.ARTICLE_USER_LIKED,articleId);
    }

    /**
     * 获取文章用户点赞加锁key
     * @param articleId
     * @param userId
     * @author linmeng
     * @date 6/9/2020 下午8:37
     * @return java.lang.String
     */
    public static String getArticleUserLikeLockKey(Long articleId,Long userId){

        return String.format(RedisConstant.ARTICLE_USER_LIKED_LOCK,articleId,userId);
    }

    /**
     * 获取文章点赞加锁 key
     * @param articleId
     * @author linmeng
     * @date 9/9/2020 下午2:43
     * @return java.lang.String
     */
    public static String getArticleLikeLockKey(Long articleId){

        return String.format(RedisConstant.ARTICLE_LIKED_LOCK,articleId);
    }
}
