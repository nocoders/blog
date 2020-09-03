package com.crop.web.config;

import com.crop.security.config.SecurityConfig;
import com.crop.security.util.JwtTokenUtil;
import com.crop.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO
 *
 * @author linmeng
 * @version 1.0
 * @date 22/8/2020 下午4:41
 */
@Configuration
@EnableWebSecurity
public class BlogWebSecurityConfig extends SecurityConfig {

    @Autowired
    private UserService userService;

    public BlogWebSecurityConfig() {
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userService.loadUserByUsername(username);
    }
}