package com.gzf.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 公共方法
 * <p>作者：hbl
 * <p>时间：2017-08-09 11:47
 */
public class CommonUtil {

    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }
    //保留两位小数
    public static double formatDouble2(double d) {
        return Double.parseDouble(String.format("%.2f", d));
    }
    //保留两位小数
    public static double sub(Double v1,Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }
}
