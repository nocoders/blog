package com.crop.web.service;

import com.crop.mapper.dto.UserReq;
import com.crop.mapper.model.CUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:43
 */
public interface UserService {
    CUser register(UserReq userReq);

    String login(String userName, String password);

    CUser getUserFromRequest();

    CUser getUserFromToken(String token);

    UserDetails loadUserByUsername(String username);

    CUser getUserById(Long userId);
}
