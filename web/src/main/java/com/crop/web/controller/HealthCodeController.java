package com.crop.web.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 2022年1月14日 17:15
 */
@RestController
@RequestMapping("/healthCode")
@Api(value = "健康码相关接口", tags = "设备端模块接口")
public class HealthCodeController {
}
