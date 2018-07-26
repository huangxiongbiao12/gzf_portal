package com.gzf.config;

import com.lxy.persistence.common.scan.BeanScanner;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuxy/391861737@qq.com
 */
@Configuration
@EnableConfigurationProperties({GzfProperties.class})
public class GzfConfiguration implements ApplicationContextAware {

    private static GzfProperties properties;
    private static ApplicationContext ctx;

    public GzfConfiguration(GzfProperties gzfProperties){
        properties = gzfProperties != null ? gzfProperties : new GzfProperties();
    }

    public static GzfProperties properties(){
        return properties;
    }

    public static ApplicationContext getCtx(){
        return ctx;
    }

    @Bean
    public BeanScanner getBeanScanner(){
        return new BeanScanner();
    }

    /* =============== being aware of Spring's ApplicationContext ================= */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
