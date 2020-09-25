package com.crop.web.service.impl;

import com.crop.common.api.RedisConstant;
import com.crop.common.exception.ApiException;
import com.crop.common.service.impl.RedisServiceImpl;
import com.crop.common.util.RedisKeyUtil;
import com.crop.web.dao.ArticleDao;
import com.crop.web.dao.ReplyDao;
import com.crop.web.dto.CommentReq;
import com.crop.web.dto.CommentReplyParam;
import com.crop.mbg.mapper.CArticleCommentsMapper;
import com.crop.mbg.model.*;
import com.crop.web.service.CommentService;
import com.crop.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ReplyDao replyDao;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private UserService userService;

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
        commentCountModify(userId,articleId,1L,true);
        
        CArticleComments comments = new CArticleComments();
        comments.setArticleId(articleId);
        comments.setFromUid(user.getId());
        comments.setContent(param.getContent());
        commentsMapper.insertSelective(comments);

        return comments.getId();
    }

    /**
     * redis中统计文章评论的数量
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

    public void commentCountModify(Long userId,Long articleId,Long modifyCount,boolean isIncrement){
        String userArticleCommentKey = RedisKeyUtil.getUserArticleCommentKey(userId);
        if (isIncrement){
            if (redisService.hHasKey(userArticleCommentKey,String.valueOf(articleId))){
                redisService.hIncr(userArticleCommentKey,String.valueOf(articleId),modifyCount);
            }else {
                redisService.hSet(userArticleCommentKey,String.valueOf(articleId),1);
            }
            if (redisService.hHasKey(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId))){
                redisService.hIncr(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId),1L);
            }else {
                redisService.hSet(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId),modifyCount);
            }
        }else {
            redisService.hDecr(userArticleCommentKey,String.valueOf(articleId),modifyCount);
            redisService.hDecr(RedisConstant.USER_COMMENT_COUNT,String.valueOf(userId),1L);

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
        CArticleComments originalComment = commentsMapper.selectByPrimaryKey(param.getCommentId());
        if (Objects.isNull(originalComment)){
            throw new ApiException("找不到原始评论");
        }
        param.setToUid(originalComment.getFromUid());
        if (param.getReplyType() == 1){
            CArticleCommentReply commentReply = replyDao.selectByPrimaryKey(param.getReplyId());
            if (Objects.isNull(commentReply)){
                throw new ApiException("找不到回复评论");
            }
            param.setToUid(commentReply.getFromUid());
        }
        commentCountModify(originalComment.getArticleId(),user.getId(),1L,true);
        CArticleCommentReply cArticleCommentReply = new CArticleCommentReply();
        BeanUtils.copyProperties(param,cArticleCommentReply);
        cArticleCommentReply.setFromUid(user.getId());
        replyDao.insertSelective(cArticleCommentReply);

        return cArticleCommentReply.getId();
    }

    /**
     * 根据评论id删除相关评论回复，第一，登录用户是作者 可删除，第二，登录用户是该条评论的主人可删除
     * @param id
     * @author linmeng
     * @date 22/9/2020 上午10:11
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void commentDelete(Long id) {

        CArticleComments comments = commentsMapper.selectByPrimaryKey(id);
        if (comments==null){
            throw new ApiException("找不到评论信息");
        }
        CUser user = userService.getUserFromRequest();
        CArticle article = articleDao.selectByPrimaryKey(comments.getArticleId());
        if (article==null){
            throw new ApiException("文章未发布或已删除");
        }
        if (!user.getId().equals(comments.getFromUid()) && !user.getId().equals(article.getUserId())){
            throw new ApiException("用户无权限删除该评论");
        }
        int commentDeleteCount = commentsMapper.deleteByPrimaryKey(id);
        CArticleCommentReplyExample replyExample = new CArticleCommentReplyExample();
        replyExample.createCriteria().andCommentIdEqualTo(id);
        int replyDeleteCount = replyDao.deleteByExample(replyExample);
        commentCountModify(article.getId(),user.getId(), (long) (commentDeleteCount + replyDeleteCount),false);
    }

    /**
     * 根据传递的回复id删除下面的所有回复
     *  登录用户若为当前文章作者或登录用户是发表回复用户的话,可任意删除。若，
     * @param id
     * @author linmeng
     * @date 22/9/2020 下午5:03
     * @return void
     */
    @Override
    public Integer commentReplyDelete(Long id) {
        // 检查传入回复信息是否存在
        CArticleCommentReply commentReply = replyDao.selectByPrimaryKey(id);
        if (commentReply==null){
            throw new ApiException("传递回复id不存在或已删除");
        }
        CArticleComments comments = commentsMapper.selectByPrimaryKey(commentReply.getCommentId());
        if (comments==null){
            throw new ApiException("回复id所在评论不存在");
        }
        CArticle article = articleDao.selectByPrimaryKey(comments.getArticleId());
        if (article==null){
            throw new ApiException("回复所在文章不存在");
        }
        // 检查登录用户是否为当前用户或该条回复作者
        CUser user = userService.getUserFromRequest();
        if (!user.getId().equals(commentReply.getFromUid()) && !user.getId().equals(article.getUserId())){
            throw new ApiException("用户无权限删除该回复");
        }
        String replyIdStr = replyDao.getReplyIdTree(id);
        int deleteCount = replyDao.batchDelete(replyIdStr.substring(1));
        commentCountModify(article.getId(),user.getId(), (long) deleteCount,false);

        return deleteCount;
    }
}
