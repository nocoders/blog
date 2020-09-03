package com.crop.mapper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "com.crop.mapper.model.CArticleCollections",description="")
@Data
public class CArticleCollections implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="文章id",name="articleId")
    private Long articleId;

    @ApiModelProperty(value="收藏文章url",name="articleUrl")
    @Length(max = 128, message = "收藏文章url名长度最长为128")
    private String articleUrl;

    @ApiModelProperty(value="文章名称",name="articleName")
    @Length(max = 64, message = "文章名称名长度最长为64")
    private String articleName;

    @ApiModelProperty(value="收藏用户id",name="userId")
    private Long userId;

    @ApiModelProperty(value="",name="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value="",name="updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", articleUrl=").append(articleUrl);
        sb.append(", articleName=").append(articleName);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}