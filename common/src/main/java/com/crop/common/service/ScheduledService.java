package com.crop.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 定时任务
 * @author linmeng
 * @version 1.0
 * @date 2022年1月17日 09:21
 */
@Component
@Slf4j
public class ScheduledService {
    private String url = "http://116.62.166.217:7080/service/iknown/open-api/parse-jkm";
    private String appKey = "bfb3da53491343b6982b9db12a9a4216";
    private String secret = "61451d70fa9e420e966b3c4b9cc5ccc4";
    /**
     *
     * 测试健康码稳定性
     * @author linmeng
     * @date 2022年1月17日 09:12
     * @return void
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void testJkm() throws Exception {
        StopWatch watch = new StopWatch("统计调用第三方接口时长");
        watch.start();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bizData = new HashMap<>(),param = new HashMap<>();
        paramInit(bizData, param);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(param);
        JSONObject forObject;
        try {
            forObject = restTemplate.postForObject(url, entity, JSONObject.class);
            watch.stop();
            long time = watch.getTotalTimeMillis();
            if ("200".equals(forObject.getString("code"))) {
                log.info("调用成功，返回结果200，消耗时间：{}，接口路径：{}，请求参数：{},响应参数：{}",time,url,JSON.toJSONString(entity),forObject);
            }else {
                log.info("调用成功，返回结果失败，消耗时间：{}，接口路径：{}，请求参数：{},响应参数：{}",time,url,JSON.toJSONString(entity),forObject);
            }
        }catch (Exception e){
            log.info("网络异常,异常信息：{}",e.toString());
        }
    }

    private void paramInit(Map<String, Object> bizData, Map<String, Object> param) throws Exception {
        // 绍兴  qrcode
        bizData.put("qrCode","https://h5.dingtalk.com/healthAct/index.html?qrCode=V2a7f3b4bbe3aeb51b97dfe653db051cd7#/result");
        param.put("appKey",appKey);
        param.put("bizData", JSON.toJSONString(bizData));
        param.put("deviceId", "D001");
        param.put("format", "json");
        param.put("requestId", UUID.randomUUID().toString().replaceAll("-",""));
        param.put("timestamp", System.currentTimeMillis());
        param.put("version", "1.0");

        String signStr = appKey + param.get("bizData") + param.get("deviceId") + param.get("format") +
                param.get("requestId") + param.get("timestamp") + param.get("version") + secret;
        String sign = hmacSHA256(signStr, appKey);
        param.put("sign",sign);
    }

    public static String hmacSHA256(String data, String appKey) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new
                SecretKeySpec(appKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] bytes = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (byte item : bytes) {
            builder.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return builder.toString().toLowerCase();
    }
}
