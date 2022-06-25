package com.zushiye.config;

import com.zushiye.handle.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/16 18:33
 */

//@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //允许全部请求跨域
//        registry.addMapping("/**");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //添加拦截器
//        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns("/login");
//    }
}
