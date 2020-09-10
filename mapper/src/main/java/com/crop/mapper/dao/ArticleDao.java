package com.crop.mapper.dao;

import com.crop.mapper.dto.ArticleDetail;
import com.crop.mapper.mapper.CArticleMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 25/8/2020 下午3:48
 */
@Mapper
public interface ArticleDao extends CArticleMapper {

    ArticleDetail getArticleDetailById(Long id);

    List<Long> getDistributeArticleIds();
}
