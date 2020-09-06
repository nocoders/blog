package com.crop.web.service;

import com.crop.mapper.dto.ArticleUpdateParam;
import com.crop.mapper.dto.ArticleDetail;
import com.crop.mapper.dto.ArticlePageReq;
import com.crop.mapper.dto.PageBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;

import java.util.List;


public interface ArticleService {
    CArticle add(ArticleUpdateParam param, CUser user);

    ArticleDetail getDetailById(Long id);

    List<CArticle> pageList(PageBean<ArticlePageReq> pageBean);

    int update(ArticleUpdateParam param);
}
