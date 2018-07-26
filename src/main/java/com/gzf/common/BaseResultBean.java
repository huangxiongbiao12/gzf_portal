package com.gzf.common;

import java.io.Serializable;

/**
 * Created by huangxiongbiao on 2017/8/15.
 * 返回结果
 */
public class BaseResultBean implements Serializable{

    private static final long serialVersionUID = -829789357734303448L;

    /** 正常 */
    public static final int NORMAL = 500;
    /** 错误 */
    public static final int ERROR = 501;
    public static final String SUCCESS = "成功";
    public static final String Param_ERROR = "参数错误";
    public static final String Database_Insert_ERROR = "插入数据库错误";
    public static final String Database_Update_ERROR = "数据更新失败";
    public static final String No_Authority_ERROR = "您没有权限执行此操作";
    public static final String Not_Login_Error = "登录过期,请重新登录";
    public static final String Tooken_Check_Error = "token校验失败";
    public static final String Login_Request_Error = "登录请求失败";
    public static final String Database_Select_ERROR = "查询数据失败";
    public static final String Database_Delete_ERROR = "删除数据失败";

    /**
     * 参数错误结果Bean静态化
     */
    public static final BaseResultBean PARAM_ERROR = new BaseResultBean(ERROR, Param_ERROR);

    /*状态码 500-正常*/
    private int status;
    /*返回的数据*/
    private Object data;
    /**
     * 返回的消息
     */
    private String message;

    public BaseResultBean(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public BaseResultBean(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResultBean(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
