package com.example.jwttutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // httpServletRequest를 사용하는 모든 요청들에 대한 접근 제한을 설정하겠다.
        // /api/hello에 대한 요청은 인증없이 접근을 허용하겠다.
        // 나머지 요청들은 모두 인증을 받아야한다.

        http
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated();
    }


}
