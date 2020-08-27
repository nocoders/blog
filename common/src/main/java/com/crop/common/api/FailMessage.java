package com.crop.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FailMessage {
    DUPLICATE_USERNAME("用户名称重复"),
    USERNAME_OR_PASSWORD_FALSE("用户名或密码错误"),
    ID_NOT_NULL_WHEN_UPDATE("修改时主键不能为空")
    ;

    private String message;

}
