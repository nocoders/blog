package com.crop.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 收藏传递参数
 * @author linmeng
 * @version 1.0
 * @date 14/9/2020 下午2:40
 */
@Data
public class CollectorReq {

    @ApiModelProperty(value="文章id",name="articleId")
    @NotNull(message = "文章id不能为空")
    private Long articleId;

    @ApiModelProperty(value="文章名称",name="articleName")
    @Length(max = 64, message = "文章名称名长度最长为64")
    @NotBlank(message = "文章名称不能为空")
    private String articleName;

    @ApiModelProperty(value="收藏类型，0-博客，暂定只有一种类型",name="type")
    @NotNull(message = "收藏类型不能为空")
    private Integer type;

    @ApiModelProperty(value="收藏文件夹id",name="folderId")
    @NotNull(message = "收藏文件夹id不能为空")
    private Long folderId;
}
