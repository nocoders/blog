package com.crop.web.service;

import com.crop.mapper.dto.UserParam;
import com.crop.mapper.model.CUser;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:43
 */
public interface UserService {
    CUser register(UserParam userParam);

    String login(String userName, String password);
}
