package com.gzf.persist.pojo.common;

import com.lxy.persistence.mybatis.bean.SoftDeletable;
import com.lxy.persistence.mybatis.definition.annotation.Column;

import java.util.Date;

/**
 * Created by liuxy/391861737@qq.com
 * 实体基类
 */

public class BaseEntity implements SoftDeletable{

    public static final String EDIT_STATE_NORMAL = "NORMAL";

    public static final String EDIT_STATE_DELETE = "DELETE";

    @Column
    protected Date ctime;

    @Column
    protected Date mtime;

    @Column
    protected String editState;

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getEditState() {
        return editState;
    }

    public void setEditState(String editState) {
        this.editState = editState;
    }

    @Override
    public void delete() {
        this.editState = EDIT_STATE_DELETE;
    }
}
