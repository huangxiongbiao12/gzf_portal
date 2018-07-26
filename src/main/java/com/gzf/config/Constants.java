package com.gzf.config;

/**
 * Created by Administrator on 2017/8/14.
 * 静态常量类
 * <br/>
 * 添加新的常量时，请新建静态内部类作为命名空间，再将常量写在该内部类之内
 * <br/>
 * 请参考：
 * @see ScopeKey#HEADER_TOKEN
 */
public class Constants {

    ////global////
    /**
     * 请将全局常量写在global分割线内
     */


    ////global////

    /**
     * http各个scope中缓存值的key的相关常量
     */
    public static class ScopeKey {
        /**
         * Header中存储token的字段的key
         */
        public static final String HEADER_TOKEN = "t_o_k_e_n";

        /**
         * request中存储当前用户id的key
         */
        public static final String REQUEST_USER_ID = "current.user.id";
    }

    /**
     * 短信发送配置条件
     *
     */
    public static class MessageKey {

        public static final String SHORT_MESSAGE_URL = "http://202.91.244.252:30001/yqx/v1/sms/single_send";

        public static final String SHORT_MESSAGE_ACCOUNT = "5152";

        public static final String SHORT_MESSAGE_APIKEY = "a26d25b3997a187ac2009726c1655801";
    }

    public static class RedisKey {
        public static final String TOKEN = "token";
    }
}
