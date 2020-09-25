package com.crop.web.controller;

import com.alibaba.fastjson.JSON;
import com.crop.common.api.CommonResult;
import com.crop.web.dto.CollectionFolderReq;
import com.crop.web.dto.CollectorReq;
import com.crop.web.dto.IdBean;
import com.crop.mbg.model.CArticleCollectionsFolder;
import com.crop.mbg.model.CUser;
import com.crop.web.service.CollectionService;
import com.crop.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收藏 controller
 * @author linmeng
 * @version 1.0
 * @date 11/9/2020 下午3:58
 */
@RestController
@RequestMapping({"/collection"})
@Slf4j
@Api(tags = {"CollectionController"}, description = "收藏接口")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    @PostMapping({"/folder/create"})
    @ApiOperation("收藏文件夹创建")
    public CommonResult<IdBean> folderCreate(@RequestBody @Validated CollectionFolderReq req){
        log.info("前端传递文件夹创建参数:"+ JSON.toJSONString(req));
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        Long folderId = collectionService.folderCreate(req,user);

        return folderId == null ? CommonResult.failed():CommonResult.success(new IdBean(folderId));
    }

    @PostMapping({"/folder/list"})
    @ApiOperation("查询登录用户文件夹集合")
    public CommonResult<List<CArticleCollectionsFolder>> folderList(){
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        List<CArticleCollectionsFolder> list = collectionService.folderList(user);

        return list.isEmpty() ? CommonResult.failed():CommonResult.success(list);
    }

    @PostMapping({"/collect"})
    @ApiOperation("文章收藏")
    public CommonResult collect(@RequestBody @Validated CollectorReq req){
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }
        Long collectionId =collectionService.collect(req,user.getId());

        return collectionId == null ?  CommonResult.failed():CommonResult.success(new IdBean(collectionId));
    }

}
