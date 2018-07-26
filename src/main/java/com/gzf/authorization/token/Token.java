package com.gzf.authorization.token;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/14.
 */
public class Token implements Serializable {

    private static final long serialVersionUID = -7195818219149330529L;

    private String userId;

    private String uuid;

    private Set<String> privileges;

    private String menus;

    public Token(){}

    public Token(String userId, String uuid, Set<String> privileges, String menus){
        this.userId = userId;
        this.uuid = uuid;
        this.privileges = privileges;
        this.menus = menus;
    }
    public Token(String userId, String uuid, Set<String> privileges){
        this.userId = userId;
        this.uuid = uuid;
        this.privileges = privileges;
    }
    /**
     * 校验权限，即所请求的接口是否在当前token所拥有的权限列表中
     * @param path
     *      所请求接口
     * @return
     *      校验结果
     */
    public boolean confirm(String path){
        return this.privileges != null && this.privileges.contains(path);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<String> privileges) {
        this.privileges = privileges;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object that) {
        if(that != null
                && that instanceof Token){
            return this.getUuid().equals(((Token) that).getUuid())
                    && this.getUserId().equals(((Token) that).getUserId());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.userId + "," + this.uuid;
    }

}
