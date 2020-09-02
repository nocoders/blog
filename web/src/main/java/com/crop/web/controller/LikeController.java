package com.crop.web.controller;

import com.crop.common.api.CommonResult;
import com.crop.common.api.FailMessage;
import com.crop.mapper.dto.LikeParam;
import com.crop.mapper.model.CArticleLikes;
import com.crop.web.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞相关接口
 * @author linmeng
 * @version 1.0
 * @date 28/8/2020 下午5:17
 */
@RestController
@RequestMapping({"/like"})
@Slf4j
@Api(tags = {"LikeController"}, description = "点赞相关接口")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping({"/like"})
    @ApiOperation("用户点赞")
    public CommonResult<CArticleLikes> register(@RequestBody @Validated LikeParam param) {
        CArticleLikes like = likeService.like(param);

        return like == null ? CommonResult.failed(FailMessage.DUPLICATE_USERNAME.getMessage()) : CommonResult.success(like);
    }

}
