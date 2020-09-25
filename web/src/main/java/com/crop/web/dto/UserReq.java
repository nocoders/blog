package com.crop.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:50
 */
@Data
@ApiModel(value = "用户注册登录前端传递参数",description = "注册登录用户名密码必填，其他非必填")
public class UserReq {

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名称不能为空")
    @Length(max = 32, message = "用户名称长度不能超过32位")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码长度不能超过32位")
    private String password;

    @ApiModelProperty("头像url")
    @Length(max = 32, message = "头像url不能超过128位")
    private String iconUrl;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("手机号")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号不符合格式")
    private String telephone;

    @ApiModelProperty("用户名")
    @Length(max = 32, message = "用户名称长度不能超过32位")
    private String nickName;
}
