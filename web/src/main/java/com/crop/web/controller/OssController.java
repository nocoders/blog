package com.crop.web.controller;

import com.crop.common.api.CommonResult;
import com.crop.web.dto.OssCallbackResult;
import com.crop.web.dto.OssPolicyResult;
import com.crop.web.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss 对象存储 管理 Controller
 * @author linmeng
 * @version 1.0
 * @date 2021年2月5日 09:17
 */
@RestController
@Api(tags = "OssController",description = "OSS管理")
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;


    @ApiOperation(value = "oss上传签名生成")
    @GetMapping(value = "/policy")
    public CommonResult<OssPolicyResult> policy(){
        OssPolicyResult res = ossService.policy();
        return CommonResult.success(res);
    }

    @ApiOperation(value = "oss上传成功联调")
    @PostMapping(value = "callback")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request){
        OssCallbackResult result = ossService.callback(request);
        return CommonResult.success(result);
    }

}
