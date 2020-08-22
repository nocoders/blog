package com.crop.web.service.impl;

import com.crop.mapper.dto.UserParam;
import com.crop.mapper.mapper.CUserMapper;
import com.crop.mapper.model.CUser;
import com.crop.mapper.model.CUserExample;
import com.crop.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CUserMapper cUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    @Override
    public CUser register(UserParam userParam) {
        CUserExample example = new CUserExample();
        example.createCriteria().andUserNameEqualTo(userParam.getUserName());
        long count = this.cUserMapper.countByExample(example);
        if (count > 0L) {
            return null;
        } else {
            CUser user = new CUser();
            String password = passwordEncoder.encode(userParam.getPassword());
            userParam.setPassword(password);
            BeanUtils.copyProperties(userParam, user);
            cUserMapper.insertSelective(user);
            return user;
        }
    }
}
