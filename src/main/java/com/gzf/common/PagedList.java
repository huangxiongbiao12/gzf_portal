package com.gzf.common;


import com.lxy.persistence.mybatis.bean.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 * 带page信息的list
 */
public class PagedList<T> implements Serializable{

    private static final long serialVersionUID = -6326613430908582323L;

    private List<T> list;

    private Page page;

    public PagedList() {}

    public PagedList(List<T> list, Page page) {
        this.list = list;
        if (page != null && page.getPage() != null) {
//            SimpleReflectionHelper.setFieldValue(page,"orderList",null);
            this.page = page;
        }else {
            this.page = null;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
