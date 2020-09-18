package com.crop.web.service.impl;

import com.crop.common.exception.Asserts;
import com.crop.mapper.dao.UserDao;
import com.crop.mapper.dto.UserReq;
import com.crop.mapper.model.CUser;
import com.crop.mapper.model.CUserExample;
import com.crop.security.util.JwtTokenUtil;
import com.crop.mapper.dto.UserDetail;
import com.crop.web.service.UserCacheService;
import com.crop.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public CUser register(UserReq userReq) {
        CUserExample example = new CUserExample();
        example.createCriteria().andUserNameEqualTo(userReq.getUserName());
        long count = userDao.countByExample(example);
        if (count > 0L) {
            return null;
        } else {
            CUser user = new CUser();
            String password = passwordEncoder.encode(userReq.getPassword());
            userReq.setPassword(password);
            BeanUtils.copyProperties(userReq, user);
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

        return jwtTokenUtil.generatorToken(new UserDetail(cUser));
    }

    /**
     * 从request里面拿到token，从token中拿到username，根据username去数据库查询用户信息
     * @author linmeng
     * @date 25/8/2020 上午11:29
     * @return com.crop.mapper.model.CUser
     */
    @Override
    public CUser getUserFromRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader(this.tokenHeader);
        if (StringUtils.isNotBlank(header) && header.startsWith(this.tokenHead)){
            String token = header.substring(this.tokenHead.length());
            if (tokenUtil.isTokenExpired(token)){
                return null;
            }
            String username = tokenUtil.getUserNameFromToken(token);
            //
            if (StringUtils.isNotBlank(username)){
                CUser user = userCacheService.getUser(username);
                if (user!=null){
                    return user;
                }
                user = userDao.getUserByUserName(username);
                if (user!=null){
                    userCacheService.setUser(user);
                }
                return user;
            }
        }

        return null;
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @author linmeng
     * @date 6/9/2020 上午10:38
     * @return com.crop.mapper.model.CUser
     */
    @Override
    public CUser getUserFromToken(String token) {
        if (StringUtils.isNotBlank(token) && token.startsWith(this.tokenHead)){
            String username = tokenUtil.getUserNameFromToken(token.substring(this.tokenHead.length()));
            if (StringUtils.isNotBlank(username)){

                return userDao.getUserByUserName(username);
            }
        }

        return null;
    }

    /**
     * 根据用户名称去数据库获取用户信息
     * @param username
     * @author linmeng
     * @date 3/9/2020 上午10:40
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        CUser user = userDao.getUserByUserName(username);
        if (user != null){
            return new UserDetail(user);
        }

        return null;
    }

    @Override
    public CUser getUserById(Long userId) {

        return userDao.selectByPrimaryKey(userId);
    }
}
