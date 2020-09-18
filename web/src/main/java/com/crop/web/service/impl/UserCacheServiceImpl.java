package com.crop.web.service.impl;

import com.crop.common.service.impl.RedisServiceImpl;
import com.crop.mapper.model.CUser;
import com.crop.web.service.UserCacheService;
import com.crop.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用户缓存 service
 * @author linmeng
 * @version 1.0
 * @date 18/9/2020 上午9:09
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;

    @Autowired
    private RedisServiceImpl redisService;

    private UserService userService;

    @Override
    public CUser getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + username;

        return (CUser) redisService.get(key);
    }

    @Override
    public void setUser(CUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUserName();
        redisService.set(key,user,REDIS_EXPIRE);
    }

    @Override
    public void delUser(Long userId) {
        CUser user = userService.getUserById(userId);
        if (user!=null){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUserName();
            redisService.del(key);
        }
    }
}
