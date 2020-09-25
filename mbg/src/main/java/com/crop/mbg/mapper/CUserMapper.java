package com.crop.mbg.mapper;

import com.crop.mbg.model.CUser;
import com.crop.mbg.model.CUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CUserMapper {
    long countByExample(CUserExample example);

    int deleteByExample(CUserExample example);

    /**
     * 
     * @param id 删除数据主键id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录（不建议使用）
     * @param record 删除数据主键id
     */
    int insert(CUser record);

    /**
     * 插入数据库记录（建议使用）
     * @param record 删除数据主键id
     */
    int insertSelective(CUser record);

    List<CUser> selectByExample(CUserExample example);

    /**
     * 根据主键id查询
     * @param id 删除数据主键id
     */
    CUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CUser record, @Param("example") CUserExample example);

    int updateByExample(@Param("record") CUser record, @Param("example") CUserExample example);

    /**
     * 修改数据(推荐使用)
     * @param record 删除数据主键id
     */
    int updateByPrimaryKeySelective(CUser record);

    /**
     * 修改数据
     * @param record 删除数据主键id
     */
    int updateByPrimaryKey(CUser record);
}