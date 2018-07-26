package com.gzf.config;


import com.gzf.authorization.annotation.UserId;
import com.gzf.persist.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Administrator on 2017/8/14.
 * User参数分解器
 */
@Component
public class UserParameterResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //参数是UserBean类型的，且带有CurrentUser注解
        if(parameter.getParameterType().isAssignableFrom(String.class)
                && parameter.hasParameterAnnotation(UserId.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return (String)nativeWebRequest.getAttribute(Constants.ScopeKey.REQUEST_USER_ID, RequestAttributes.SCOPE_REQUEST);
    }
}
