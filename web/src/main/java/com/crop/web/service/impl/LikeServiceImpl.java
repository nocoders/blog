package com.crop.web.service.impl;

import com.crop.common.api.RedisLockEntity;
import com.crop.common.exception.ApiException;
import com.crop.common.service.impl.RedisServiceImpl;
import com.crop.common.util.RedisKeyUtil;
import com.crop.mapper.dao.ArticleDao;
import com.crop.mapper.dto.LikeReq;
import com.crop.mapper.mapper.CArticleLikesMapper;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;
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
    private RedisServiceImpl redisService;

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    /**
     * 用户点赞，校验文章状态
     * 判断redis中有没有该文章该用户的点赞记录
     * 缓存没有的话，判断点赞 状态，点赞状态 相加小于0，或大于1，参数错误
     * @param param
     * @author linmeng
     * @date 2/9/2020 下午3:04
     * @return com.crop.mapper.model.CArticleLikes
     */
    @Override
    public boolean like(LikeReq param, HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);
        CUser user = userService.getUserFromToken(token);
        if (user == null || null == user.getId()){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        CArticle article = articleDao.selectByPrimaryKey(param.getArticleId());
        if (null == article || 0 == article.getStatus()){
            throw new ApiException("文章未发布，不能点赞。");
        }
        // 从redis中根据文章id，用户id
        String articleLikedKey = RedisKeyUtil.getArticleLikedKey(param.getArticleId());
        String articleLikeLockKey = RedisKeyUtil.getArticleUserLikeLockKey(param.getArticleId(), user.getId());
        RedisLockEntity redisLockEntity = RedisLockEntity.builder().lockKey(articleLikeLockKey).token(token).build();
        if (redisService.lock(redisLockEntity)){
            Boolean likeExists = redisService.hHasKey(articleLikedKey, String.valueOf(user.getId()));
            if (likeExists){
                Integer liked = (Integer) redisService.hGet(articleLikedKey, String.valueOf(user.getId()));
                Integer afterLiked = liked + param.getType();
                if (afterLiked < 0 || afterLiked > 1){
                    redisService.unlockLua(redisLockEntity);
                    throw new ApiException("点赞类型错误");
                }
                redisService.hSet(articleLikedKey,String.valueOf(user.getId()),afterLiked);
                return true;
            }else {
                if (param.getType() != 1){
                    redisService.unlockLua(redisLockEntity);
                    throw new ApiException("点赞类型错误");
                }
                redisService.hSet(articleLikedKey,String.valueOf(user.getId()),1);
            }
            redisService.unlockLua(redisLockEntity);
            return true;
        }else {
            return false;
        }
    }
}
