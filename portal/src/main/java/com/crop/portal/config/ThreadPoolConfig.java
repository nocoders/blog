package com.crop.portal.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 * @author linmeng
 * @version 1.0
 * @date 6/9/2020 下午11:13
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {

    @Autowired
    private ThreadPoolProperties poolProperties;

    @Bean(name = "portalFixedThreadPool")
    public ThreadPoolExecutor portalFixedThreadPool(){

        return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize()
                , poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat(poolProperties.getThreadNamePrefix()+"%s").build());
    }
}
