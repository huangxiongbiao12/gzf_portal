package com.gzf.service.common.iml;

import com.gzf.common.BaseResultBean;
import com.gzf.common.PagedList;
import com.gzf.persist.mapper.common.BaseMapper;
import com.gzf.persist.pojo.common.BaseEntity;
import com.gzf.service.common.BaseService;
import com.gzf.util.StringUtil;
import com.lxy.persistence.PersistenceDefault;
import com.lxy.persistence.mybatis.bean.Order;
import com.lxy.persistence.mybatis.bean.Page;

import java.util.List;

/**
 * Created by huangxiongbiao on 2018/1/17.
 * 基础的增删改查接口
 */
public abstract class BaseServiceIml<E extends BaseEntity> implements BaseService<E> {

    public E get(String id){
        return this.getMapper().get(id);
    }
    /**
     * 保存entity
     * @param entity
     * @return
     */
    @Override
    public BaseResultBean save(E entity){
        int i = getMapper().save(entity);
        if (i==1) {
            return new BaseResultBean(BaseResultBean.NORMAL, entity);
        }else {
            return new BaseResultBean(BaseResultBean.ERROR, BaseResultBean.Database_Insert_ERROR);
        }
    }

    /**
     * 查询entity列表
     * @param entity
     * @param page
     * @return
     */
    @Override
    public BaseResultBean list(E entity, Page page){
        if(page == null) page = PersistenceDefault.page();
        page.addOrder("ctime", Order.Scending.DESC);
        List list = getMapper().query(entity,null, page);
        PagedList pagedList = new PagedList(list, page);
        return new BaseResultBean(BaseResultBean.NORMAL, pagedList);
    }

    /**
     * 修改entity
     * @param entity
     * @return
     */
    @Override
    public BaseResultBean update(E entity){
        int i = getMapper().update(entity);
        if (i==1) {
            return new BaseResultBean(BaseResultBean.NORMAL, "修改成功!");
        }else {
            return new BaseResultBean(BaseResultBean.ERROR, BaseResultBean.Database_Update_ERROR);
        }
    }

    /**
     * 删除entity，多个删除时逗号隔开
     * @param ids
     * @return
     */
    @Override
    public BaseResultBean delete(String ids){
        if (StringUtil.isNullOrEmpty(ids))
            return new BaseResultBean(BaseResultBean.ERROR, "请输入需要删除的参数！");
        String[] idArr = ids.split(",");
        int i = getMapper().softDelete(idArr);
        if (i >= 1) {
            return new BaseResultBean(BaseResultBean.NORMAL, "删除成功");
        }
        return new BaseResultBean(BaseResultBean.ERROR, "删除失败");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public BaseResultBean getById(String id) {
        if (StringUtil.isNullOrEmpty(id))
            return new BaseResultBean(BaseResultBean.ERROR, "请输入需要查询的信息！");
        E e = getMapper().get(id);
        return new BaseResultBean(BaseResultBean.NORMAL, e);
    }

    protected abstract BaseMapper<E> getMapper();

}
