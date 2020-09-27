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
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", articleId=" + articleId +
                ", articleName=" + articleName +
                ", type=" + type +
                ", userId=" + userId +
                ", folderId=" + folderId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}