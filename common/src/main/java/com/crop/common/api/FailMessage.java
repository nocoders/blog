package com.crop.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FailMessage {
    DUPLICATE_USERNAME("用户名称重复"),
    USERNAME_OR_PASSWORD_FALSE("用户名或密码错误"),

    ;

    private String message;

}
