package com.crop.security.config;

import com.crop.security.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Iterator;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:47
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        Iterator var3 = this.ignoreUrlsConfig().getUrls().iterator();

        while(var3.hasNext()) {
            String url = (String)var3.next();
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)registry.antMatchers(new String[]{url})).permitAll();
        }

        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)registry.antMatchers(HttpMethod.OPTIONS)).permitAll();
        ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)registry.and()).authorizeRequests().anyRequest()).authenticated().and()).csrf().disable()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
