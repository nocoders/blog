package com.crop.web.service;

import com.crop.mapper.dto.LikeParam;
import com.crop.mapper.model.CArticleLikes;

/**
 * 点赞相关service
 * @author linmeng
 * @date 2/9/2020 下午2:47
 */
public interface LikeService {
    CArticleLikes like(LikeParam param);
}