package com.crop.web.service.impl;

import com.crop.common.api.ResultCode;
import com.crop.common.exception.ApiException;
import com.crop.mapper.dao.CollectionFolderDao;
import com.crop.mapper.dto.CollectionFolderReq;
import com.crop.mapper.mapper.CArticleCollectionsFolderMapper;
import com.crop.mapper.model.CArticleCollectionsFolder;
import com.crop.mapper.model.CArticleCollectionsFolderExample;
import com.crop.mapper.model.CUser;
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
}
