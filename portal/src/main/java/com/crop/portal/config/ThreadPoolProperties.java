package com.crop.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置
 * @author linmeng
 * @version 1.0
 * @date 6/9/2020 下午11:18
 */
@ConfigurationProperties("executor")
@Component
@Data
public class ThreadPoolProperties {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;
    private Integer keepAliveSeconds;
    private String threadNamePrefix;

}
