package com.crop.web.service.impl;

import com.crop.common.exception.Asserts;
import com.crop.mapper.dao.UserDao;
import com.crop.mapper.dto.UserParam;
import com.crop.mapper.model.CUser;
import com.crop.mapper.model.CUserExample;
import com.crop.security.util.JwtTokenUtil;
import com.crop.mapper.dto.CUserDetails;
import com.crop.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil tokenUtil;

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
        userExample.createCriteria().andUserNameEqualTo(userName).andStatusEqualTo((byte) 1);
        List<CUser> cUsers = userDao.selectByExample(userExample);
        if (CollectionUtils.isEmpty(cUsers)){
            Asserts.fail("用户名不正确");
        }
        CUser cUser = cUsers.get(0);
        if (cUser.getStatus()==0){
            Asserts.fail("该用户已经被禁用");
        }
        if (!passwordEncoder.matches(password,cUser.getPassword())){
            Asserts.fail("密码不正确");
        }
        // token生成

        return jwtTokenUtil.generatorToken(new CUserDetails(cUser));
    }

    /**
     * 从request里面拿到token，从token中拿到username，根据username去数据库查询用户信息
     * @param request 前端请求
     * @author linmeng
     * @date 25/8/2020 上午11:29
     * @return com.crop.mapper.model.CUser
     */
    @Override
    public CUser getUserFromRequest(HttpServletRequest request){
        String header = request.getHeader(this.tokenHeader);
        if (StringUtils.isNotBlank(header) && header.startsWith(this.tokenHead)){
            String username = tokenUtil.getUserNameFromToken(header.substring(this.tokenHead.length()));
            if (StringUtils.isNotBlank(username)){
                return userDao.getUserByUserName(username);
            }
        }

        return null;
    }
}
