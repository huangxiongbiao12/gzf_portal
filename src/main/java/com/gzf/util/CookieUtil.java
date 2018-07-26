package com.gzf.util;

import net.sf.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by yangweiguang on 2017/6/30.
 */
public class CookieUtil {
    /**
     * 创建token
     * @return
     */
    public static JSONObject getCookie(HttpServletRequest req, String cookieName) {
        try {
            //HttpServletRequest req = (HttpServletRequest) request;
            Cookie[] cs = req.getCookies();
            Cookie cookie = null;
            String dataLogin = "";
            if (cs != null) {
                for (Cookie c : cs) {
                    if (cookieName.equals(c.getName())) {
                        cookie = c;
                        break;
                    }
                }
            }

            dataLogin = cookie.getValue();
            dataLogin = URLDecoder.decode(dataLogin, "UTF-8");
            JSONObject xJson = JSONObject.fromObject(dataLogin);

            return  xJson;
        } catch (Exception ex) {
            return null;
        }

    }
}
