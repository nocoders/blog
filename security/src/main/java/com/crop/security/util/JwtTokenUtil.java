package com.crop.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT token生成工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午8:55
 */
@Slf4j
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据载荷生成token
     *
     * @param claims
     * @author linmeng
     * @date 23/8/2020 下午3:40
     * @return java.lang.String
     */
    public String generatorToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate((long)claims.get(CLAIM_KEY_CREATED)))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
    public String generatorToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, System.currentTimeMillis());

        return generatorToken(claims);
    }

        /**
         * 生成token的过期时间
         * @param createTime 创建时间
         */
    public Date generateExpirationDate(long createTime) {
        return new Date(createTime + expiration * 1000);
    }

    /**
     * 根据token获取用户名
     * @param token
     * @author linmeng
     * @date 24/8/2020 下午4:52
     * @return java.lang.String
     */
    public String getUserNameFromToken(String  token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }

        return username;
    }

    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) &&!isTokenExpired(token);
    }
    /**
     * 校验token是否过期
     * @param token
     * @author linmeng
     * @date 3/9/2020 上午11:15
     * @return boolean
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 获取token过期时间
     * @param token
     * @author linmeng
     * @date 3/9/2020 上午11:16
     * @return java.util.Date
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
    /**
     * 在token中获取负载
     * @param token
     * @author linmeng
     * @date 24/8/2020 下午5:06
     * @return io.jsonwebtoken.Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.error("JWT格式验证失败:{}",token);
        }

        return claims;
    }
}
