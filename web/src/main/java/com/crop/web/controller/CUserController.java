package com.crop.web.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.crop.common.api.CommonResult;
import com.crop.common.api.ResultCode;
import com.crop.web.dto.UserReq;
import com.crop.mbg.model.CUser;
import com.crop.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.HashMap;

import static com.crop.common.api.CommonResult.success;
import static com.crop.common.api.CommonResult.validateFailed;

/**
 * 用户登录相关接口
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:42
 */
@RestController
@RequestMapping({"/user"})
@Api(tags = {"UserController"}, description = "普通用户相关接口")
public class CUserController {
    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping({"/register"})
    @ApiOperation("用户注册")
    public CommonResult<CUser> register(@RequestBody @Validated UserReq userReq) {
        CUser cUser = userService.register(userReq);
        return cUser == null ? CommonResult.failed(ResultCode.UNAUTHORIZED) : CommonResult.success(cUser);
    }

    @PostMapping({"/login"})
    @ApiOperation("用户登录")
    public CommonResult login(@RequestBody @Validated UserReq userReq){
        String  token = userService.login(userReq.getUserName(), userReq.getPassword());
        if (StringUtils.isBlank(token)){
            return validateFailed("用户名或密码错误");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return success(tokenMap);
    }

    @GetMapping("/info")
    @ApiOperation("获取当前登录用户信息")
    public CommonResult checkLogin(){

        return success(userService.getUserFromRequest());
    }
}
