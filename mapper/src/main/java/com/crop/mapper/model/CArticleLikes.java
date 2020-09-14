package com.crop.mapper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com.crop.mapper.model.CArticleLikes",description="")
@Data
public class CArticleLikes implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="文章id",name="articleId")
    private Long articleId;

    @ApiModelProperty(value="点赞用户id",name="userId")
    private Long userId;

    @ApiModelProperty(value="点赞状态，1：点赞，0：点赞后又取消",name="status")
    private Integer status;

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
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}