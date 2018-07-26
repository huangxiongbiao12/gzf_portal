package com.gzf.config.web;

import com.gzf.authorization.interceptor.AuthInterceptor;
import com.gzf.config.Constants;
import com.gzf.config.UserParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 * WebMvc配置
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private UserParameterResolver userParameterResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userParameterResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(false)
                .allowedMethods("GET", "POST", "OPTIONS")
                .exposedHeaders(Constants.ScopeKey.HEADER_TOKEN)
                .maxAge(3600);
    }

}
