package com.crop.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 文件夹前端发送请求参数
 * @author linmeng
 * @version 1.0
 * @date 11/9/2020 下午4:32
 */
@Data
public class CollectionFolderReq {

    @ApiModelProperty(value="是否私人文件夹，0-否，1-是，默认0",name="isPrivate")
    private Integer isPrivate;

    @ApiModelProperty(value="文件夹名称",name="name")
    @Length(max = 32, message = "文件夹名称名长度最长为32")
    @NotBlank(message = "文件夹名称不能为空")
    private String name;

    @ApiModelProperty(value="文件夹描述",name="description")
    @Length(max = 64, message = "文件夹描述名长度最长为64")
    private String description;
}
