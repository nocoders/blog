package com.crop.web.controller;

import com.alibaba.fastjson.JSON;
import com.crop.common.api.CommonResult;
import com.crop.common.api.FailMessage;
import com.crop.mapper.dto.ArticleUpdateParam;
import com.crop.mapper.dto.ArticleDetail;
import com.crop.mapper.dto.ArticlePageReq;
import com.crop.mapper.dto.PageBean;
import com.crop.mapper.model.CArticle;
import com.crop.mapper.model.CUser;
import com.crop.web.service.ArticleService;
import com.crop.web.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation("文章添加")
    public CommonResult<CArticle> add(@RequestBody @Validated ArticleUpdateParam param) {

        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        CArticle article = articleService.add(param,user);
        return article == null ? CommonResult.failed(FailMessage.DUPLICATE_USERNAME.getMessage()) : CommonResult.success(article);
    }

    @GetMapping({"/{id}"})
    @ApiOperation("文章详情")
    public CommonResult<ArticleDetail> details(@PathVariable(value = "id") Long id){
        log.info("前端传递文章id为：{}",id);

        ArticleDetail details = articleService.getDetailById(id);

        return details ==null ? CommonResult.failed():CommonResult.success(details);
    }

    @PostMapping({"/page"})
    @ApiOperation("文章分页")
    public CommonResult<PageInfo<CArticle>> pageList(@RequestBody @Validated PageBean<ArticlePageReq> pageBean){
        log.info("前端传递文章分页参数："+ JSON.toJSONString(pageBean));
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        pageBean.getParam().setUserId(user.getId());
        PageInfo<CArticle> pageInfo = new PageInfo<>(articleService.pageList(pageBean));

        return CommonResult.success(pageInfo);
    }

    @PutMapping
    @ApiOperation("文章修改")
    public CommonResult update(@RequestBody ArticleUpdateParam param){
        log.info("前端传递文章修改参数："+ JSON.toJSONString(param));

        if (null == param.getId() || null == param.getUserId()){
            return CommonResult.failed(FailMessage.ID_NOT_NULL_WHEN_UPDATE.getMessage());
        }

        return articleService.update(param)>0 ? CommonResult.success() : CommonResult.failed();
    }
}
