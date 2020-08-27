package com.crop.mapper.dto;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.crop.mapper.model.CArticle;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 文章详情
 * @author linmeng
 * @version 1.0
 * @date 25/8/2020 下午3:41
 */
@Data
@ApiModel(value = "文章详情",description = "文章详情")
public class ArticleBean extends CArticle {

    @ApiModelProperty(value = "文章内容")
    private String content;
}
