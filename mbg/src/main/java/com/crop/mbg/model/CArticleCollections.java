package com.crop.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "com.crop.mbg.model.CArticleCollections",description="")
@Data
public class CArticleCollections implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="文章id",name="articleId")
    private Long articleId;

    @ApiModelProperty(value="文章名称",name="articleName")
    @Length(max = 64, message = "文章名称名长度最长为64")
    private String articleName;

    @ApiModelProperty(value="作者",name="author")
    @Length(max = 64, message = "作者名长度最长为64")
    private String author;

    @ApiModelProperty(value="收藏类型，0-博客，暂定只有一种类型",name="type")
    private Integer type;

    @ApiModelProperty(value="收藏用户id",name="userId")
    private Long userId;

    @ApiModelProperty(value="收藏文件夹id",name="folderId")
    private Long folderId;

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
        sb.append(", articleName=").append(articleName);
        sb.append(", author=").append(author);
        sb.append(", type=").append(type);
        sb.append(", userId=").append(userId);
        sb.append(", folderId=").append(folderId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}