package com.crop.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FailMessage {
    DUPLICATE_USERNAME("用户名称重复");

    private String message;

}
