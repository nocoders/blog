package com.crop.web.service;

import com.crop.mapper.dto.LikeReq;

import javax.servlet.http.HttpServletRequest;

/**
 * 点赞相关service
 * @author linmeng
 * @date 2/9/2020 下午2:47
 */
public interface LikeService {
    boolean like(LikeReq param, HttpServletRequest request);
}
