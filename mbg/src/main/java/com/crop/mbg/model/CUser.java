package com.crop.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "com.crop.mbg.model.CUser",description="")
@Data
public class CUser implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="用户名称",name="userName")
    @Length(max = 32, message = "用户名称名长度最长为32")
    private String userName;

    @ApiModelProperty(value="密码",name="password")
    @Length(max = 64, message = "密码名长度最长为64")
    private String password;

    @ApiModelProperty(value="手机号码",name="telephone")
    @Length(max = 11, message = "手机号码名长度最长为11")
    private String telephone;

    @ApiModelProperty(value="邮箱",name="email")
    @Length(max = 32, message = "邮箱名长度最长为32")
    private String email;

    @ApiModelProperty(value="昵称",name="nickName")
    @Length(max = 64, message = "昵称名长度最长为64")
    private String nickName;

    @ApiModelProperty(value="头像url",name="iconUrl")
    @Length(max = 128, message = "头像url名长度最长为128")
    private String iconUrl;

    @ApiModelProperty(value="创建时间",name="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value="修改时间",name="updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @ApiModelProperty(value="状态：1-启用，0-禁用",name="status")
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append(", nickName=").append(nickName);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}