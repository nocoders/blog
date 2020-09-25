package com.crop.web.dao;

import com.crop.mbg.mapper.CUserMapper;
import com.crop.mbg.model.CUser;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 24/8/2020 下午5:16
 */
@Mapper
public interface UserDao extends CUserMapper {

    CUser getUserByUserName(String username);
}
