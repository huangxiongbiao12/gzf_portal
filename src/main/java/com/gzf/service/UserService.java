package com.gzf.service;

import com.gzf.authorization.annotation.Disauth;
import com.gzf.common.BaseResultBean;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gzf.service.common.BaseService;
import com.gzf.persist.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	user 
 */
@RestController
@RequestMapping("/user")
public interface UserService extends BaseService<User>{


    /**
     * 增加用户，密码md5加密
     * @param user
     * @return
     */
    @RequestMapping("add")
    public BaseResultBean add(User user);

    /**
     * 登录接口
     * @param req
     * @param resp
     * @param loginName
     * @param password
     * @return
     */
    @Disauth
    @RequestMapping("login")
    public BaseResultBean login(HttpServletRequest req, HttpServletResponse resp,
                                @RequestParam("userName") String loginName, @RequestParam("password") String password);

    /**
     * 退出登录接口
     * @param req
     * @return
     */
    @RequestMapping("logout")
    public BaseResultBean logout(HttpServletRequest req);

}

