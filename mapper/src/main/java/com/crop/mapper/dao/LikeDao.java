package com.crop.mapper.dao;

import com.crop.mapper.mapper.CArticleMapper;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CArticleLikes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeDao extends CArticleMapper {

    Integer batchInsert(List<CArticleLikes> articles);

}
