package com.gzf.authorization.token;


import com.gzf.config.Constants;
import com.gzf.config.GzfProperties;
import com.gzf.util.IdGenerator;
import com.lxy.persistence.redis.Cacher;
import com.lxy.persistence.redis.common.KeyMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * Created by Administrator on 2017/8/14.
 * Token管理器
 * @see Token
 */
@Component
public class TokenManager {

    @Autowired
    private Cacher cacher;
    @Autowired
    private GzfProperties custom;

    /**
     * redis用的key的处理器
     */
    private KeyMarshaller marshaller = KeyMarshaller.getInstance(Constants.RedisKey.TOKEN);

    /**
     * 创建token，token会在配置的时间之后失效
     * @param userId
     *      用户Id
     * @return
     *      创建好的token
     */
    public Token create(String userId, Set<String> privileges, String menu){
        Token token = new Token(userId, IdGenerator.uuid(), privileges, menu);

        cacher.setex(marshaller.enkey(userId), token, custom.getTokenExpireTime(), custom.getDefaultTimeUnit());
        return token;
    }

    public Token create(String userId, Set<String> privileges){
        return create(userId, privileges, null);
    }

    public Token createApp(String userId, Set<String> privileges, String menu){
        Token token = new Token(userId, IdGenerator.uuid(), privileges, menu);
        cacher.setex(marshaller.enkey(userId), token, custom.getTokenAppExpireTime(), custom.getDefaultTimeUnit());
        return token;
    }
    public Token createApp(String userId, Set<String> privileges){
        return createApp(userId, privileges, null);
    }

    /**
     * 根据用户id删除token
     * @param userId
     *      用户id
     */
    public void delete(String userId){
        cacher.del(marshaller.enkey(userId));
    }

    /**
     * 根据用户id，取得token
     * @param userId
     *      用户id
     * @return
     */
    public Token get(String userId){
        return (Token) cacher.get(marshaller.enkey(userId));
    }

    /**
     * 将校验字符串解析为Token
     * @param auth
     *      校验字符串
     * @return
     *      Token
     */
    public Token parse(String auth){
        if(StringUtils.hasText(auth)){
            String[] csv = auth.split(",");
            if(csv.length == 2){
                return new Token(csv[0], csv[1], null, null);
            }
        }
        return null;
    }

    /**
     * 检验token
     * @param token
     *      token
     * @return
     *      检验是否通过
     */
    public Token check(Token token){
        if(token != null){
            Token cached = (Token) cacher.get(marshaller.enkey(token.getUserId()));
            if(cached != null && cached.equals(token)){
                //延长过期时间
                cacher.expire(marshaller.enkey(token.getUserId()),
                        custom.getTokenExpireTime(),
                        custom.getDefaultTimeUnit());
                return cached;//登陆验证成功，返回所缓存的token
            }
        }
        return null;
    }

    public Token refresh(Token token){
        //TODO
        return null;
    }
}
