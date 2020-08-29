package com.crop.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
}
