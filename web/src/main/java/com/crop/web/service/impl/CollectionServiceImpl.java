package com.crop.web.service.impl;

import com.crop.common.api.RedisConstant;
import com.crop.common.api.ResultCode;
import com.crop.common.exception.ApiException;
import com.crop.common.service.RedisService;
import com.crop.common.util.RedisKeyUtil;
import com.crop.mbg.model.*;
import com.crop.web.dao.ArticleDao;
import com.crop.web.dao.CollectionDao;
import com.crop.web.dao.CollectionFolderDao;
import com.crop.web.dto.CollectionFolderReq;
import com.crop.web.dto.CollectorReq;
import com.crop.web.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 收藏 业务逻辑处理类
 * @author linmeng
 * @version 1.0
 * @date 11/9/2020 下午4:50
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionFolderDao collectionFolderDao;

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisService redisService;
    /**
     * 收藏文件夹创建
     * @param req 前端传递参数
     * @param user 用户信息
     * @author linmeng
     * @date 11/9/2020 下午4:55
     * @return java.lang.Long
     */
    @Override
    public Long folderCreate(CollectionFolderReq req, CUser user) {
        CArticleCollectionsFolderExample example = new CArticleCollectionsFolderExample();
        example.createCriteria().andNameEqualTo(req.getName());
        List<CArticleCollectionsFolder> collectionsFolders = collectionFolderDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(collectionsFolders)){
            throw new ApiException(ResultCode.BAD_REQUEST);
        }
        CArticleCollectionsFolder folder = new CArticleCollectionsFolder();
        BeanUtils.copyProperties(req,folder);
        folder.setUserId(user.getId());
        collectionFolderDao.insertSelective(folder);

        return folder.getId();
    }

    /**
     * 根据用户信息查询收藏文件夹
     * @param user
     * @author linmeng
     * @date 14/9/2020 上午11:04
     * @return java.util.List<com.crop.mapper.model.CArticleCollectionsFolder>
     */
    @Override
    public List<CArticleCollectionsFolder> folderList(CUser user) {
        CArticleCollectionsFolderExample example = new CArticleCollectionsFolderExample();
        example.createCriteria().andUserIdEqualTo(user.getId());

        return collectionFolderDao.selectByExample(example);
    }

    /**
     * 文章收藏
     * @param req
     * @author linmeng
     * @date 14/9/2020 下午2:59
     * @return java.lang.Long
     */
    @Override
    public Long collect(CollectorReq req,Long userId) {
        // 检查文章，收藏文件夹是否存在
        CArticle article = articleDao.selectByPrimaryKey(req.getArticleId());
        if (article==null || article.getDeleted()!=0){
            throw new ApiException(ResultCode.BAD_REQUEST);
        }
        CArticleCollectionsFolder collectionsFolder = collectionFolderDao.selectByPrimaryKey(req.getFolderId());
        if (collectionsFolder==null){
            throw new ApiException(ResultCode.BAD_REQUEST);
        }
        CArticleCollections articleCollections = new CArticleCollections();
        BeanUtils.copyProperties(req,articleCollections);
        articleCollections.setUserId(userId);
        collectionDao.insertSelective(articleCollections);
        Long authorId = article.getUserId();
        String userArticleCollectKey = RedisKeyUtil.getUserArticleCollectKey(authorId);
        String articleId = String.valueOf(article.getId());
        if (redisService.hHasKey(userArticleCollectKey, articleId)){
            redisService.hIncr(userArticleCollectKey, articleId,1L);
        }else {
            redisService.hSet(userArticleCollectKey, articleId,1L);
        }
        if (redisService.hHasKey(RedisConstant.USER_COLLECTION_COUNT, String.valueOf(authorId))){
            redisService.hIncr(RedisConstant.USER_COLLECTION_COUNT, articleId,1L);
        }else {
            redisService.hSet(RedisConstant.USER_COLLECTION_COUNT, articleId,1L);
        }

        return articleCollections.getId();
    }

    @Override
    public void collectDelete(Long id) {

    }
}
