package com.crop.web.controller;

import com.alibaba.fastjson.JSON;
import com.crop.common.api.CommonResult;
import com.crop.common.api.FailMessage;
import com.crop.mapper.dto.ArticleAddParam;
import com.crop.mapper.dto.ArticleBean;
import com.crop.mapper.dto.ArticlePageReq;
import com.crop.mapper.dto.PageBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;
import com.crop.web.service.ArticleService;
import com.crop.web.service.impl.WebServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:42
 */
@RestController
@RequestMapping({"/article"})
@Slf4j
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

    @GetMapping({"/detail/{id}"})
    @ApiOperation("文章添加")
    public CommonResult<ArticleBean> details(@PathVariable(value = "id") Long id){
        log.info("前端传递文章id为：{}",id);

        ArticleBean details = articleService.getDetailById(id);

        return details ==null ? CommonResult.failed():CommonResult.success(details);
    }

    @PostMapping({"/page"})
    @ApiOperation("文章分页")
    public CommonResult<Page<ArticleBean>> pageList(@RequestBody @Validated PageBean<ArticlePageReq> pageBean, HttpServletRequest request){
        log.info("前端传递 文章分页参数："+ JSON.toJSONString(pageBean));
        // 获取用户信息
        CUser user = webService.getUserFromRequest(request);
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        pageBean.getParam().setUserId(user.getId());
        List<CArticle> list = articleService.pageList(pageBean);

        return null;
    }
}
