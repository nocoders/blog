package com.crop.mapper.dto;

import com.crop.mapper.model.CArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章详情
 * @author linmeng
 * @version 1.0
 * @date 25/8/2020 下午3:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "文章详情")
public class ArticleDetail extends CArticle {

    @ApiModelProperty(value = "文章内容")
    private String content;
}
