package com.crop.blog.service.impl;

import com.crop.blog.model.User;
import com.crop.blog.mapper.UserMapper;
import com.crop.blog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author linmeng
 * @since 2020-08-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
