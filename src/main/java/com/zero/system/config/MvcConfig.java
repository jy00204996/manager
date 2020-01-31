package com.zero.system.config;

import com.zero.system.interceptor.LogInterceptor;
import com.zero.system.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MvcConfig
 * @Description None
 * @Date 2019/7/16 10:08
 * @Created by WDD
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${ACTPATH}")
    private String realPath;

    @Value("${URLACTPATH}")
    private String virPath;

    @Bean
    public LogInterceptor setBean(){
        return new LogInterceptor(); // 注入spring
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //springboot中配置addResourceHandler和addResourceLocations，使得可以从磁盘中读取图片、视频、音频等
        registry.addResourceHandler(virPath+"/**").addResourceLocations("file:" + realPath+"/");
    }

   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/manager/**")
                .excludePathPatterns("/static/**","/","/manager/login");
        registry.addInterceptor(setBean()).addPathPatterns("/manager/**")
                .excludePathPatterns("/static/**","/","/manager/login");
    }*/
}
