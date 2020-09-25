package com.crop.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 *
 * @author linmeng
 * @version 1.0
 * @date 27/8/2020 上午9:17
 */
@Getter
@Setter
@ApiModel(value = "文章分页请求参数")
public class ArticlePageParam {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "是否原创，1-原创，0-转载")
    @Size(max = 1,message = "是否原创取值范围在0到1之间")
    private Byte isOriginal;

    @ApiModelProperty(value = "状态，1-草稿，0-发布")
    @Size(max = 1,message = "状态取值范围在0到1之间")
    private Byte status;

    @ApiModelProperty(value = "用户id,无需前端传递")
    private Long userId;
}
