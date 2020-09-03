package com.crop.mapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户点赞 前端传递
 * @author linmeng
 * @version 1.0
 * @date 2/9/2020 下午2:49
 */
@Data
@ApiModel(description = "前端传递用户点赞参数")
public class LikeParam {

    @ApiModelProperty(value = "点赞用户id",required = true)
    @NotNull(message = "点赞用户id不能为空")
    private Long userId;


    @ApiModelProperty(value = "点赞用户名称",required = true)
    @NotBlank(message = "点赞用户名称不能为空")
    @Max(value = 32,message = "点赞用户名称不能为空 ")
    private Long username;

    @ApiModelProperty(value = "文章id",required = true)
    @NotNull(message = "点赞文章id不能为空")
    private Long articleId;

    @ApiModelProperty(value = "点赞类型，0：点赞，1：取消点赞")
    private Integer type = 0;
}
