package com.crop.web.dao;

import com.crop.mbg.mapper.CArticleMapper;
import com.crop.mbg.model.CArticleLikes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeDao extends CArticleMapper {

    Integer batchInsert(List<CArticleLikes> articles);

}
