package com.crop.web.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author taozun
 * @Date 20.11.17 18:19
 * @Description
 */
@Configuration
public class CommonRestTemplateConfig {
    @Bean("restTemplate")
    public RestTemplate restTemplate()  {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(60000);
        requestFactory.setConnectTimeout(60000);//连接超时时间为1秒
        requestFactory.setReadTimeout(70000);
        CloseableHttpClient httpClient = getHttpsClient();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;

    }

    public CloseableHttpClient getHttpsClient() {

        CloseableHttpClient httpClient;
        //ignoreSSL为true时，绕过证书
        if (true) {
            SSLContext sslContext = null;
            try {
                sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                }).build();
            } catch (NoSuchAlgorithmException e) {
                e.getStackTrace();
            } catch (KeyManagementException e) {
                e.getStackTrace();
            } catch (KeyStoreException e) {
                e.getStackTrace();
            }
            httpClient = HttpClients.custom().setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
}
