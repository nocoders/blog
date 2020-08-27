package com.crop.web.controller;

import com.crop.common.api.CommonResult;
import static com.crop.common.api.CommonResult.*;
import com.crop.common.api.FailMessage;
import com.crop.mapper.dto.UserParam;
import com.crop.mapper.model.CUser;
import com.crop.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public CommonResult<CUser> register(@RequestBody @Validated UserParam userParam) {
        CUser cUser = userService.register(userParam);
        return cUser == null ? CommonResult.failed(FailMessage.DUPLICATE_USERNAME.getMessage()) : CommonResult.success(cUser);
    }

    @PostMapping({"/login"})
    @ApiOperation("用户登录")
    public CommonResult login(@RequestBody @Validated UserParam userParam){
        String  token = userService.login(userParam.getUserName(),userParam.getPassword());
        if (StringUtils.isBlank(token)){
            return validateFailed("用户名或密码错误");
        }
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return success(tokenMap);
    }
}
