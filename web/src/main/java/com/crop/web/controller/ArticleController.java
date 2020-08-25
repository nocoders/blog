package com.crop.web.controller;

import com.crop.common.api.CommonResult;
import com.crop.common.api.FailMessage;
import com.crop.mapper.dto.ArticleAddParam;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;
import com.crop.web.service.ArticleService;
import com.crop.web.service.impl.WebServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:42
 */
@RestController
@RequestMapping({"/article"})
@Api(tags = {"ArticleController"}, description = "文章相关接口")
public class ArticleController {

    @Autowired
    private WebServiceImpl webService;

    @Autowired
    private ArticleService articleService;
    @PostMapping({"/add"})
    @ApiOperation("文章添加")
    public CommonResult<CArticle> register(@RequestBody @Validated ArticleAddParam param, HttpServletRequest request) {

        // 获取用户信息
        CUser user = webService.getUserFromRequest(request);
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        CArticle article = articleService.add(param,user);
        return article == null ? CommonResult.failed(FailMessage.DUPLICATE_USERNAME.getMessage()) : CommonResult.success(article);
    }
}
