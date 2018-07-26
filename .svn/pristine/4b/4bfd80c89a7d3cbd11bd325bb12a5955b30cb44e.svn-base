package com.gzf.authorization.token;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

/**
 * Created by liuxy / mail:391861737@qq.com.
 */
@Service
public class TokenExpiredListener implements MessageListener, InitializingBean {

    private static Log log = LogFactory.getLog(TokenExpiredListener.class);

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(redisTemplate.getConnectionFactory());
        listenerContainer.addMessageListener(this, new PatternTopic("__key*__:expired"));
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("token expired: " + message.getBody());
    }
}
