package com.crop.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.crop.mapper.dto.ArticleAddParam;
import com.crop.mapper.mapper.CArticleContentMapper;
import com.crop.mapper.mapper.CArticleMapper;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CArticleContent;
import com.crop.mapper.model.CUser;
import com.crop.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章 service
 *
 * @author linmeng
 * @version 1.0
 * @date 24/8/2020 下午2:52
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private CArticleMapper articleMapper;

    @Autowired
    private CArticleContentMapper cArticleContentMapper;


    /**
     * 文章添加，添加时
     * @param param 前端传递需要添加文章
     * @author linmeng
     * @date 24/8/2020 下午3:29
     * @return com.crop.mapper.model.CArticle
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CArticle add(ArticleAddParam param, CUser user) {

        CArticle article = new CArticle();
        BeanUtil.copyProperties(param,article);
        article.setUserId(user.getId());
        articleMapper.insertSelective(article);
        CArticleContent articleContent = new CArticleContent(article.getId(), param.getContent());
        cArticleContentMapper.insertSelective(articleContent);

        return article;
    }
}
