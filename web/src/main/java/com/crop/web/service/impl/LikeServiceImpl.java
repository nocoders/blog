package com.crop.web.service.impl;

import com.crop.common.api.RedisConstant;
import com.crop.common.api.RedisLockEntity;
import com.crop.common.exception.ApiException;
import com.crop.common.service.RedisService;
import com.crop.common.util.RedisKeyUtil;
import com.crop.web.dao.ArticleDao;
import com.crop.web.dto.LikeReq;
import com.crop.mbg.mapper.CArticleLikesMapper;
import com.crop.mbg.model.CArticle;
import com.crop.mbg.model.CUser;
import com.crop.web.service.LikeService;
import com.crop.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 点赞相关service
 * @author linmeng
 * @version 1.0
 * @date 2/9/2020 下午2:47
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CArticleLikesMapper likesMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    /**
     * 用户点赞，校验文章状态
     * 判断redis中有没有该文章该用户的点赞记录
     * 缓存没有的话，判断点赞 状态，点赞状态 相加小于0，或大于1，参数错误
     * 点赞，redis存储相关点赞数据，不持久化到数据库，直接
     * @param param
     * @author linmeng
     * @date 2/9/2020 下午3:04
     * @return com.crop.mapper.model.CArticleLikes
     */
    @Override
    public boolean like(LikeReq param, HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);
        CUser user = userService.getUserFromToken(token);
        Long userId, articleId = param.getArticleId();
        if (user == null || null == (userId = user.getId()) ){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        CArticle article = articleDao.selectByPrimaryKey(articleId);
        if (null == article || 1 == article.getStatus()){
            throw new ApiException("文章未发布，不能点赞。");
        }
        Long likedUserId = article.getUserId();
        // 从redis中根据被点赞用户id，文章id作为一个hash，点赞用户id为key
        String articleLikedKey = RedisKeyUtil.getArticleLikedKey(articleId);
        String articleLikeLockKey = RedisKeyUtil.getArticleUserLikeLockKey(articleId, userId);
        RedisLockEntity redisLockEntity = RedisLockEntity.builder().lockKey(articleLikeLockKey).token(token).build();
        if (redisService.lock(redisLockEntity)){
            Boolean likeExists = redisService.hHasKey(articleLikedKey, String.valueOf(userId));
            Integer afterLiked = param.getType();
            if (likeExists){
                Integer liked = (Integer) redisService.hGet(articleLikedKey, String.valueOf(userId));
                afterLiked = liked + afterLiked;
                if (afterLiked < 0 || afterLiked > 1){
                    redisService.unlockLua(redisLockEntity);
                    throw new ApiException("点赞类型错误");
                }
                redisService.hSet(articleLikedKey,String.valueOf(userId),afterLiked);
            }else {
                if (afterLiked != 1){
                    redisService.unlockLua(redisLockEntity);
                    throw new ApiException("点赞类型错误");
                }
                redisService.hSet(articleLikedKey,String.valueOf(userId),1);
            }
            // 文章点赞数量修改
            if (redisService.hHasKey(RedisConstant.ARTICLE_LIKED_COUNT,String.valueOf(articleId))){
                redisService.hIncr(RedisConstant.ARTICLE_LIKED_COUNT,String.valueOf(articleId),afterLiked.longValue());
            }else {
                redisService.hSet(RedisConstant.ARTICLE_LIKED_COUNT,String.valueOf(articleId),1);
            }
            // 用户点赞数量修改
            if (redisService.hHasKey(RedisConstant.USER_LIKED_COUNT,String.valueOf(likedUserId))){
                redisService.hIncr(RedisConstant.USER_LIKED_COUNT,String.valueOf(likedUserId),afterLiked.longValue());
            }else {
                redisService.hSet(RedisConstant.USER_LIKED_COUNT,String.valueOf(likedUserId),1);
            }

            redisService.unlockLua(redisLockEntity);
            return true;
        }else {
            return false;
        }
    }
}
