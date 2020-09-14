package com.crop.mapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端传递文章评论回复参数
 * @author linmeng
 * @version 1.0
 * @date 10/9/2020 下午4:29
 */
@Data
@ApiModel(value = "前端传递文章评论参数")
public class CommentReq {

    @ApiModelProperty(value="文章id",name="articleId")
    @NotNull(message = "文章id不能为空")
    private Long articleId;

    @ApiModelProperty(value="评论内容",name="content")
    @Length(max = 1000, message = "评论内容名长度最长为1000")
    @NotBlank(message = "评论内容不能为空")
    private String content;
}
