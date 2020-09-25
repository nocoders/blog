package com.crop.web.service;

import com.crop.web.dto.ArticleUpdateReq;
import com.crop.web.dto.ArticleDetail;
import com.crop.web.dto.ArticlePageParam;
import com.crop.web.dto.PageBean;
import com.crop.mbg.model.CArticle;
import com.crop.mbg.model.CUser;

import java.util.List;


public interface ArticleService {
    Long add(ArticleUpdateReq param, CUser user);

    ArticleDetail getDetailById(Long id);

    List<CArticle> pageList(PageBean<ArticlePageParam> pageBean);

    int update(ArticleUpdateReq param);
}
