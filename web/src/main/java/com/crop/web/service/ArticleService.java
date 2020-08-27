package com.crop.web.service;

import com.crop.mapper.dto.ArticleAddParam;
import com.crop.mapper.dto.ArticleBean;
import com.crop.mapper.dto.ArticlePageReq;
import com.crop.mapper.dto.PageBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;

import java.util.List;


public interface ArticleService {
    CArticle add(ArticleAddParam param, CUser user);

    ArticleBean getDetailById(Long id);

    List<CArticle> pageList(PageBean<ArticlePageReq> pageBean);
}
