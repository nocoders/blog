package com.crop.mapper.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评论回复前端传递参数
 * @author linmeng
 * @version 1.0
 * @date 11/9/2020 上午9:29
 */
@Data
public class CommentReplyParam {

    /** 针对评论回复时，根评论id跟回复id相同，针对回复回复时两者不同 */
    @ApiModelProperty(value="根评论id",name="commentId")
    @NotNull(message = "评论id不能为空")
    private Long commentId;

    @ApiModelProperty(value="回复id，针对该评论回复的上一条评论或回复",name="replyId")
    @NotNull(message = "回复id不能为空")
    private Long replyId;

    @ApiModelProperty(value="回复类型，0：针对评论回复，1：针对回复回复",name="replyType")
    @NotNull(message = "回复类型不能为空")
    @Range(min = 0,max = 1,message = "回复类型只能选0,1")
    private Integer replyType;

    @ApiModelProperty(value="目标用户id",name="toUid")
    @NotNull(message = "被回复用户id不能为空")
    private Long toUid;


    @ApiModelProperty(value="评论内容",name="content")
    @Length(max = 1000, message = "评论内容名长度最长为1000")
    @NotBlank(message = "回复内容不能为空")
    private String content;
}
