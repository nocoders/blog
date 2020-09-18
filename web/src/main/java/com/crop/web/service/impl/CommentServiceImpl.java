package com.crop.web.service.impl;

import com.crop.common.api.RedisConstant;
import com.crop.common.exception.ApiException;
import com.crop.common.service.impl.RedisServiceImpl;
import com.crop.common.util.RedisKeyUtil;
import com.crop.mapper.dao.ArticleDao;
import com.crop.mapper.dto.CommentReq;
import com.crop.mapper.dto.CommentReplyParam;
import com.crop.mapper.mapper.CArticleCommentReplyMapper;
import com.crop.mapper.mapper.CArticleCommentsMapper;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CArticleCommentReply;
import com.crop.mapper.model.CArticleComments;
import com.crop.mapper.model.CUser;
import com.crop.web.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 评论回复相关业务逻辑处理
 *
 * @author linmeng
 * @version 1.0
 * @date 10/9/2020 下午4:47
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CArticleCommentsMapper commentsMapper;
    
    @Autowired
    private CArticleCommentReplyMapper replyMapper;

    @Autowired
    private RedisServiceImpl redisService;

    /**
     * 对文章进行评论
     * @param param 前端传递评论参数
     * @param user 当前登录用户
     * @author linmeng
     * @date 10/9/2020 下午4:50
     * @return java.lang.Long
     */
    @Override
    public Long comment(CommentReq param, CUser user) {
        // 检查文章是否发布
        Long articleId = param.getArticleId();
        CArticle article = articleDao.selectByPrimaryKey(articleId);
        if (null == article || 1 == article.getStatus()){
            throw new ApiException("文章未发布，不能点赞。");
        }
        Long userId = article.getUserId();
        commentCountIncrement(userId,articleId);
        
        CArticleComments comments = new CArticleComments();
        comments.setArticleId(articleId);
        comments.setFromUid(user.getId());
        comments.setContent(param.getContent());
        commentsMapper.insertSelective(comments);

        return comments.getId();
    }

    /**
     * redis中统计文章点赞的数量
     * @param userId
     * @param articleId
     * @author linmeng
     * @date 17/9/2020 上午9:55
     * @return void
     */
    public void commentCountIncrement(Long userId,Long articleId){
        String userArticleCommentKey = RedisKeyUtil.getUserArticleCommentKey(userId);
        if (redisService.hHasKey(userArticleCommentKey,String.valueOf(articleId))){
            redisService.hIncr(userArticleCommentKey,String.valueOf(articleId),1L);
        }else {
            redisService.hSet(userArticleCommentKey,String.valueOf(articleId),1);
        }
        if (redisService.hHasKey(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId))){
            redisService.hIncr(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId),1L);
        }else {
            redisService.hSet(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId),1);
        }
    }

    /**
     * 对评论进行回复或对回复进行回复
     * @param param 回复信息
     * @param user 登录用户
     * @author linmeng
     * @date 11/9/2020 上午9:48
     * @return java.lang.Long
     */
    @Override
    public Long reply(CommentReplyParam param, CUser user) {
        if (param.getReplyType() == 0){
            if (!param.getCommentId().equals(param.getReplyId())){
                throw new ApiException("参数错误");
            }
        }
        CArticleComments originalComment = commentsMapper.selectByPrimaryKey(param.getCommentId());
        if (Objects.isNull(originalComment)){
            throw new ApiException("找不到原始评论");
        }
        param.setToUid(originalComment.getFromUid());
        if (param.getReplyType() == 1){
            CArticleCommentReply commentReply = replyMapper.selectByPrimaryKey(param.getReplyId());
            if (Objects.isNull(commentReply) || !commentReply.getFromUid().equals(param.getToUid())){
                throw new ApiException("找不到回复评论或被回复用户id错误");
            }
            param.setToUid(commentReply.getFromUid());
        }
        commentCountIncrement(originalComment.getArticleId(),user.getId());
        CArticleCommentReply cArticleCommentReply = new CArticleCommentReply();
        BeanUtils.copyProperties(param,cArticleCommentReply);
        cArticleCommentReply.setFromUid(user.getId());
        replyMapper.insertSelective(cArticleCommentReply);

        return cArticleCommentReply.getId();
    }
}
