package com.crop.mapper.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com.crop.mapper.model.CArticleCommentReply",description="")
@Data
public class CArticleCommentReply implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="根评论id",name="commentId")
    private Long commentId;

    @ApiModelProperty(value="回复id，针对该评论回复的上一条评论或回复",name="replyId")
    private Long replyId;

    @ApiModelProperty(value="回复类型，0：针对评论回复，1：针对回复回复",name="replyType")
    private Integer replyType;

    @ApiModelProperty(value="回复用户id",name="fromUid")
    private Long fromUid;

    @ApiModelProperty(value="目标用户id",name="toUid")
    private Long toUid;

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
        sb.append(", commentId=").append(commentId);
        sb.append(", replyId=").append(replyId);
        sb.append(", replyType=").append(replyType);
        sb.append(", fromUid=").append(fromUid);
        sb.append(", toUid=").append(toUid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}