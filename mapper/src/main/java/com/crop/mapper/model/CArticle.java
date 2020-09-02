package com.crop.mapper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "com.crop.mapper.model.CArticle",description="")
@Data
public class CArticle implements Serializable {
    @ApiModelProperty(value="主键id",name="id")
    private Long id;

    @ApiModelProperty(value="用户id",name="userId")
    private Long userId;

    @ApiModelProperty(value="文章标题",name="title")
    @Length(max = 64, message = "文章标题名长度最长为64")
    private String title;

    @ApiModelProperty(value="文章描述",name="description")
    @Length(max = 128, message = "文章描述名长度最长为128")
    private String description;

    @ApiModelProperty(value="是否原创，1-原创，0-转载",name="isOriginal")
    private Integer isOriginal;

    @ApiModelProperty(value="状态，1-草稿，0-发布",name="status")
    private Integer status;

    @ApiModelProperty(value="浏览量",name="views")
    private Integer views;

    @ApiModelProperty(value="评论数量",name="comments")
    private Integer comments;

    @ApiModelProperty(value="点赞数量",name="likes")
    private Integer likes;

    @ApiModelProperty(value="收藏量",name="collections")
    private Integer collections;

    @ApiModelProperty(value="",name="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value="",name="updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", isOriginal=").append(isOriginal);
        sb.append(", status=").append(status);
        sb.append(", views=").append(views);
        sb.append(", comments=").append(comments);
        sb.append(", likes=").append(likes);
        sb.append(", collections=").append(collections);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}