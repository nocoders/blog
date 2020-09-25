package com.crop.web.service;

import com.crop.mbg.model.CUser;

public interface UserCacheService {

    CUser getUser(String username);

    void setUser(CUser user);

    void delUser(Long userId);
}
