package com.crop.mapper.dao;

import com.crop.mapper.dto.ArticleBean;
import com.crop.mapper.mapper.CArticleMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 25/8/2020 下午3:48
 */
@Mapper
public interface ArticleDao extends CArticleMapper {

    ArticleBean getArticleDetailById(Long id);
}
