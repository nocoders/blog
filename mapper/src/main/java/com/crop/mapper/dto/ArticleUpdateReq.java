package com.crop.mapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章添加 参数类
 * @author linmeng
 * @version 1.0
 * @date 24/8/2020 下午2:59
 */
@Data
@ApiModel(value = "文章添加修改时参数",description = "文章添加修改时参数")
public class ArticleUpdateReq {

    @ApiModelProperty(value = "文章id,添加时不传递，修改时传递")
    private Long id;

    @ApiModelProperty(value = "用户id,添加时不传递，修改时传递")
    private Long userId;

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空")
    @Length(max = 64,message = "文章标题最大长度不能超过64")
    private String title;

    @ApiModelProperty(value = "文章描述")
    @NotBlank(message = "文章描述不能为空")
    @Length(max = 128,message = "文章描述最大长度不能超过128")
    private String description;

    @ApiModelProperty(value = "是否原创，1-原创，0-转载")
    @NotNull(message = "是否原创不能为空，必须为原创或转载")
    private Boolean isOriginal;

    @ApiModelProperty(value = "状态，1-草稿，0-发布")
    @NotNull(message = "状态不能为空，必须为草稿或发布")
    private Boolean status;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message = "文章内容不能为空")
    private String content;
}
