package com.crop.common.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * redis加锁实体类
 * @author linmeng
 * @version 1.0
 * @date 5/9/2020 下午11:37
 */
@Getter
@Setter
@Builder
public class RedisLockEntity {
    private String lockKey;
    private String token;
}
