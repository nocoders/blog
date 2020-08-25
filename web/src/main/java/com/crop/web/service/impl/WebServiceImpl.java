package com.crop.web.service.impl;

import com.crop.mapper.dao.UserDao;
import com.crop.mapper.model.CUser;
import com.crop.security.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * web相关的公共方法
 * @author linmeng
 * @version 1.0
 * @date 25/8/2020 上午11:16
 */
@Component
public class WebServiceImpl {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil tokenUtil;
    /**
     * 从request里面拿到token，从token中拿到username，根据username去数据库查询用户信息
     * @param request 前端请求
     * @author linmeng
     * @date 25/8/2020 上午11:29
     * @return com.crop.mapper.model.CUser
     */
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
