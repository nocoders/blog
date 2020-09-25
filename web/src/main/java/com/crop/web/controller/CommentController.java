package com.crop.web.controller;

import com.crop.common.api.CommonResult;
import com.crop.web.dto.CommentReq;
import com.crop.web.dto.CommentReplyParam;
import com.crop.web.dto.IdBean;
import com.crop.mbg.model.CUser;
import com.crop.web.service.CommentService;
import com.crop.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 10/9/2020 下午4:11
 */
@RestController
@RequestMapping({"/article"})
@Slf4j
@Api(tags = {"CommentController"}, description = "文章评论接口")
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ApiOperation("文章评论接口,用于对文章评论")
    public CommonResult<IdBean> comment(@RequestBody @Validated CommentReq param){
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        if (user == null){
            return CommonResult.unauthorized(null);
        }

        Long commentId = commentService.comment(param,user);

        return commentId == null ? CommonResult.failed():CommonResult.success(new IdBean(commentId));
    }

    @PostMapping("/comment/reply")
    @ApiOperation("文章评论回复接口，用于回复评论或回复回复")
    public CommonResult<IdBean> commentReply(@RequestBody @Validated CommentReplyParam param){
        // 获取用户信息
        CUser user = userService.getUserFromRequest();
        Long replyId = commentService.reply(param,user);

        return  replyId == null ? CommonResult.failed():CommonResult.success(new IdBean(replyId));
    }

    @DeleteMapping("/comment/{id}")
    @ApiOperation("评论删除，根据评论id 删除评论和该评论下的回复")
    public CommonResult commentDelete(@PathVariable("id") Long id){
        commentService.commentDelete(id);

        return CommonResult.success();
    }

    @DeleteMapping("/comment/reply/{id}")
    @ApiOperation("评论删除，根据评论id 删除评论和该评论下的回复")
    public CommonResult commentReplyDelete(@PathVariable("id") Long id){

        Integer deleteCount = commentService.commentReplyDelete(id);

        return deleteCount>0 ? CommonResult.success():CommonResult.failed();
    }
}
