package com.crop.mapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户点赞 前端传递
 * @author linmeng
 * @version 1.0
 * @date 2/9/2020 下午2:49
 */
@Data
@ApiModel(description = "前端传递用户点赞参数")
public class LikeReq {

    @ApiModelProperty(value = "文章id",required = true)
    @NotNull(message = "点赞文章id不能为空")
    private Long articleId;

    @ApiModelProperty(value = "点赞类型，1：点赞，-1：取消点赞")
    private Integer type = 1;
}
