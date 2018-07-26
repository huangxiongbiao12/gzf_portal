package com.gzf.util;

import java.lang.annotation.*;

/**
 * 自定义的注解
 * 用于spring aop操作日志
 * <p>作者：hbl
 * <p>时间：2017-08-09 11:35
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String type()  default "";
    String content()  default "";

}
