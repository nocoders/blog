package com.crop.web.service;

import com.crop.mapper.dto.ArticleUpdateReq;
import com.crop.mapper.dto.ArticleDetail;
import com.crop.mapper.dto.ArticlePageParam;
import com.crop.mapper.dto.PageBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;

import java.util.List;


public interface ArticleService {
    Long add(ArticleUpdateReq param, CUser user);

    ArticleDetail getDetailById(Long id);

    List<CArticle> pageList(PageBean<ArticlePageParam> pageBean);

    int update(ArticleUpdateReq param);
}
