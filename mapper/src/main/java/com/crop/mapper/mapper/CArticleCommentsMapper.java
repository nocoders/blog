package com.crop.mapper.mapper;

import com.crop.mapper.model.CArticleComments;
import com.crop.mapper.model.CArticleCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CArticleCommentsMapper {
    long countByExample(CArticleCommentsExample example);

    int deleteByExample(CArticleCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CArticleComments record);

    int insertSelective(CArticleComments record);

    List<CArticleComments> selectByExample(CArticleCommentsExample example);

    CArticleComments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CArticleComments record, @Param("example") CArticleCommentsExample example);

    int updateByExample(@Param("record") CArticleComments record, @Param("example") CArticleCommentsExample example);

    int updateByPrimaryKeySelective(CArticleComments record);

    int updateByPrimaryKey(CArticleComments record);
}