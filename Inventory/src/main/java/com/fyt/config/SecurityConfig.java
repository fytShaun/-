//package com.fyt.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                // 配置权限规则
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") // 指定登录页面的URL
//                .defaultSuccessUrl("/custom-login") // 登录成功后的重定向URL
//                .permitAll()
//                .and()
//                .logout()
//                // 配置注销规则
//                .permitAll();
//    }
//}
