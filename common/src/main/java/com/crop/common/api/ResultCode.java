package com.crop.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午5:00
 */
@Getter
@AllArgsConstructor
public enum  ResultCode {
    SUCCESS(200L, "操作成功"),
    FAILED(500L, "操作失败"),
    VALIDATE_FAILED(404L, "参数检验失败"),
    UNAUTHORIZED(401L, "暂未登录或token已经过期"),
    FORBIDDEN(403L, "没有相关权限");

    private long code;
    private String message;
}
