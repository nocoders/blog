package com.crop.mapper.mapper;

import com.crop.mapper.dto.ArticleBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CArticleMapper {
    long countByExample(CArticleExample example);

    int deleteByExample(CArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticle record);

    int insertSelective(CArticle record);

    List<CArticle> selectByExample(CArticleExample example);

    CArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticle record, @Param("example") CArticleExample example);

    int updateByExample(@Param("record") CArticle record, @Param("example") CArticleExample example);

    int updateByPrimaryKeySelective(CArticle record);

    int updateByPrimaryKey(CArticle record);
}