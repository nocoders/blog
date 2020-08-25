package com.crop.web.service.impl;

import com.crop.common.exception.Asserts;
import com.crop.mapper.dao.UserDao;
import com.crop.mapper.dto.UserParam;
import com.crop.mapper.mapper.CUserMapper;
import com.crop.mapper.model.CUser;
import com.crop.mapper.model.CUserExample;
import com.crop.security.util.JwtTokenUtil;
import com.crop.web.bo.CUserDetails;
import com.crop.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public CUser register(UserParam userParam) {
        CUserExample example = new CUserExample();
        example.createCriteria().andUserNameEqualTo(userParam.getUserName());
        long count = userDao.countByExample(example);
        if (count > 0L) {
            return null;
        } else {
            CUser user = new CUser();
            String password = passwordEncoder.encode(userParam.getPassword());
            userParam.setPassword(password);
            BeanUtils.copyProperties(userParam, user);
            userDao.insertSelective(user);
            return user;
        }
    }

    /**
     * 用户 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @author linmeng
     * @date 22/8/2020 下午8:41
     * @return java.lang.String 返回登录token
     */
    @Override
    public String login(String userName, String password) {
        CUserExample userExample = new CUserExample();
        userExample.createCriteria().andUserNameEqualTo(userName).andStatusEqualTo(true);
        List<CUser> cUsers = userDao.selectByExample(userExample);
        if (CollectionUtils.isEmpty(cUsers)){
            Asserts.fail("用户名不正确");
        }
        CUser cUser = cUsers.get(0);
        if (!cUser.getStatus()){
            Asserts.fail("该用户已经被禁用");
        }
        if (!passwordEncoder.matches(password,cUser.getPassword())){
            Asserts.fail("密码不正确");
        }
        // token生成

        return jwtTokenUtil.generatorToken(new CUserDetails(cUser));
    }
}
