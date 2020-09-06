package com.crop.web.service;

import com.crop.mapper.dto.UserParam;
import com.crop.mapper.model.CUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:43
 */
public interface UserService {
    CUser register(UserParam userParam);

    String login(String userName, String password);

    CUser getUserFromRequest(HttpServletRequest request);

    CUser getUserFromToken(String token);

    UserDetails loadUserByUsername(String username);
}
