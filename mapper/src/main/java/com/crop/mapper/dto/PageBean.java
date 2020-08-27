package com.crop.mapper.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author linmeng
 * @version 1.0
 * @date 27/8/2020 上午9:08
 */
@Data
@ApiModel(value = "分页请求参数")
public class PageBean<T> {


    @ApiModelProperty(value = "查询条件")
    @NotNull(message = "查询条件不能为空，无查询条件时也需要传入对象并将各字段置空")
    T param;

    @Min(value = 1,message = "分页页码最小为1")
    @ApiModelProperty(value = "分页页码最小为1",required = true)
    @NotNull(message = "分页页码必传")
    Integer pageNum;

    @Min(value = 1,message = "每页数量最小为10")
    @NotNull(message = "每页数量必传")
    @ApiModelProperty(value = "每页数量最小为10",required = true)
    Integer pageSize;
}
