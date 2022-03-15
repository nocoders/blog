package com.crop.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 签名工具类
 * @author linmeng
 * @version 1.0
 * @date 2022年1月14日 15:39
 */
public class SignUtil {

    /**
     * HmacSHA256 生成签名
     * @param data 请求数据拼接字符串
     * @param appKey 身份标识
     * @author linmeng
     * @date 2022年1月14日 15:40
     * @return java.lang.String
     */
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
