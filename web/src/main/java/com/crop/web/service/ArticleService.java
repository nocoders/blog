package com.crop.web.service;

import com.crop.mapper.dto.ArticleAddParam;
import com.crop.mapper.dto.ArticleDetails;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;


public interface ArticleService {
    CArticle add(ArticleAddParam param, CUser user);

    ArticleDetails getDetailById(Long id);
}
