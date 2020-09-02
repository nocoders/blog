package com.crop.web.service.impl;

import com.crop.mapper.dto.LikeParam;
import com.crop.mapper.model.CArticleLikes;
import com.crop.web.service.LikeService;
import org.springframework.stereotype.Service;

/**
 * 点赞相关service
 * @author linmeng
 * @version 1.0
 * @date 2/9/2020 下午2:47
 */
@Service
public class LikeServiceImpl implements LikeService {

    /**
     * 用户点赞 信息
     * @param param
     * @author linmeng
     * @date 2/9/2020 下午3:04
     * @return com.crop.mapper.model.CArticleLikes
     */
    @Override
    public CArticleLikes like(LikeParam param) {
        return null;
    }
}
