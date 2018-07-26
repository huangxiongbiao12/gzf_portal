package com.gzf.authorization.interceptor;

import com.gzf.authorization.annotation.Disauth;
import com.gzf.authorization.token.Token;
import com.gzf.authorization.token.TokenManager;
import com.gzf.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/14.
 * 登陆鉴权拦截器
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            System.out.println(method.getName());
            if("error".equals(method.getName())){
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return true;
            }else if("errorHtml".equals(method.getName())){
                return true;
            }
            //解析token登陆状态
            Token token = tokenManager.parse(request.getHeader(Constants.ScopeKey.HEADER_TOKEN));
            if(method.getAnnotation(Disauth.class) != null){
                return true;//如果标注了Disauth，则直接返回成功
            }
            //验证token登陆状态
            token = tokenManager.check(token);
            if(token != null){
                //登陆验证成功后给当前请求缓存上用户id
                request.setAttribute(Constants.ScopeKey.REQUEST_USER_ID,token.getUserId());
                return true;
            }else {
                //登陆验证失败，标注401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            //因为return的是false，所以直接记录日志
        }else if("OPTIONS".equals(request.getMethod())){
            return true;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex != null){

        }
        super.afterCompletion(request, response, handler, ex);
    }
}
