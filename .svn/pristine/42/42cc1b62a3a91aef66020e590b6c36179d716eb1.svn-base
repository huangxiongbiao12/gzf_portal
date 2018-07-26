package com.gzf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuxy/391861737@qq.com
 */
@ConfigurationProperties(prefix = "gzf")
public class GzfProperties {

    /**
     * 数据库名
     */
    private String db = "";

    /**
     * 实体所在包名列表
     */
    private String[] entityPackages = {""};

    private List<String> controllerPackages = new ArrayList<>();

    /**
     * 默认分页每页行数
     */
    private Integer defaultPageSize = 20;

    /**
     * 默认时间单位
     */
    private TimeUnit defaultTimeUnit = TimeUnit.MINUTES;

    /**
     * token失效时间
     */
    private Long tokenExpireTime = 30L;
    private Long tokenAppExpireTime = 60*24*7L;
    /** 字体文件路径 */
    public static final String VERIFY_CODE_FRONT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/ALGER.TTF" ;
    /**
     * 通用日期格式
     */
    public String commonDateFormat = "yyyy-MM-dd HH:mm:ss";

    private String commonCharset = "UTF-8";

    private String uploadDir = "upload-dir";
    /**
     * 门锁客户端id
     */
    private String appid = "";
    /**
     *   门锁服务地址
     */
    private String cardInterface = "";
    /**
     * 默认时区
     */
    private String DEFAULT_DATE_TIMEZONE = "GMT+8" ;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String[] getEntityPackages() {
        return entityPackages;
    }

    public void setEntityPackages(String[] entityPackages) {
        this.entityPackages = entityPackages;
    }

    public List<String> getControllerPackages() {
        return controllerPackages;
    }

    public void setControllerPackages(List<String> controllerPackages) {
        this.controllerPackages = controllerPackages;
    }

    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public TimeUnit getDefaultTimeUnit() {
        return defaultTimeUnit;
    }

    public void setDefaultTimeUnit(TimeUnit defaultTimeUnit) {
        this.defaultTimeUnit = defaultTimeUnit;
    }

    public Long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public Long getTokenAppExpireTime() {
        return tokenAppExpireTime;
    }

    public void setTokenAppExpireTime(Long tokenAppExpireTime) {
        this.tokenAppExpireTime = tokenAppExpireTime;
    }

    public static String getVerifyCodeFrontPath() {
        return VERIFY_CODE_FRONT_PATH;
    }

    public String getCommonDateFormat() {
        return commonDateFormat;
    }

    public void setCommonDateFormat(String commonDateFormat) {
        this.commonDateFormat = commonDateFormat;
    }

    public String getCommonCharset() {
        return commonCharset;
    }

    public void setCommonCharset(String commonCharset) {
        this.commonCharset = commonCharset;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCardInterface() {
        return cardInterface;
    }

    public void setCardInterface(String cardInterface) {
        this.cardInterface = cardInterface;
    }

    public String getDEFAULT_DATE_TIMEZONE() {
        return DEFAULT_DATE_TIMEZONE;
    }

    public void setDEFAULT_DATE_TIMEZONE(String DEFAULT_DATE_TIMEZONE) {
        this.DEFAULT_DATE_TIMEZONE = DEFAULT_DATE_TIMEZONE;
    }

}
