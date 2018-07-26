package com.gzf.service.common;

import com.gzf.common.BaseResultBean;
import com.gzf.persist.pojo.common.BaseEntity;
import com.lxy.persistence.mybatis.bean.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huangxiongbiao on 2018/1/17.
 * 基础的增删改查接口
 */
public interface BaseService<E extends BaseEntity> {

    E get(String id);

    /**
     * 保存entity
     * @param entity
     * @return
     */
    @RequestMapping("save")
    public BaseResultBean save(E entity);

    /**
     * 查询entity列表
     * @param entity
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseResultBean list(E entity, Page page);

    /**
     * 修改entity
     * @param entity
     * @return
     */
    @RequestMapping("update")
    public BaseResultBean update(E entity);

    /**
     * 删除entity，多个删除时逗号隔开
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    public BaseResultBean delete(@RequestParam("ids") String ids);

    /**
     * 根据id获取数据的详情
     * @param id
     * @return
     */
    @RequestMapping("getById")
    public BaseResultBean getById(@RequestParam("id") String id);

}
