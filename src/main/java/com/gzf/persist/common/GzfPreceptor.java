package com.gzf.persist.common;
import com.gzf.persist.pojo.common.BaseEntity;
import com.gzf.util.IdGenerator;
import com.lxy.persistence.common.util.helper.ReflectionHelper;
import com.lxy.persistence.mybatis.definition.bean.TableDefinition;
import com.lxy.persistence.mybatis.template.CrudTemplate;
import com.lxy.persistence.mybatis.template.JoinClues;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxy/391861737@qq.com
 * 增删改查预处理器
 */

public class GzfPreceptor implements CrudTemplate.CrudPreceptor {

    @Override
    public <T> T handle(CrudTemplate.Type type, TableDefinition tableDefinition, T t, Map<String, Object> map) {
        if(t == null) return null;
        switch (type){
            case C: //增
                if(t instanceof BaseEntity){
                    BaseEntity entity = (BaseEntity) t;
                    this.id(entity, tableDefinition);
                    this.ctime(entity);
                    this.mtime(entity);
                    this.normalize(entity);
                    return t;
                }
                break;
            case R: //查
                if(t instanceof BaseEntity){
                    this.normalize((BaseEntity) t);
                    return t;
                }
                break;
            case U: //改
                if(t instanceof BaseEntity){
                    this.mtime((BaseEntity) t);
                    return t;
                }
                break;
            case D: //删

                break;
            case QUERY:
                if(t instanceof BaseEntity){
                    this.normalize((BaseEntity) t);
                    return t;
                }
                break;
            case FREEQUERY:
                if(t instanceof JoinClues){
                    this.normalize(((JoinClues) t).innerlist());
                    this.normalize(((JoinClues) t).outerlist());
                }
            case SOFTD:

                break;

        }
        return t;
    }

    private void ctime(BaseEntity entity){
        this.ctime(entity, false);
    }

    private void mtime(BaseEntity entity){
        this.mtime(entity, false);
    }

    private void normalize(List<JoinClues.JoinClue> clues){
        if(clues != null){
            for(JoinClues.JoinClue clue : clues){
                if(BaseEntity.class.isAssignableFrom(clue.getTdefi().getRef())){
                    if(clue.getT() == null) clue.setT(clue.getTdefi().newInstance());
                    this.normalize((BaseEntity) clue.getT());
                }
            }
        }
    }

    private void normalize(BaseEntity entity){
        this.normalize(entity, false);
    }

    private void id(BaseEntity entity, TableDefinition tdefi){
        if(tdefi != null && entity != null){
            ReflectionHelper.setFieldValue(entity, tdefi.getId().getRef(), IdGenerator.uuid());
        }
    }

    private void ctime(BaseEntity entity, boolean force){
        if(entity != null && (force || entity.getCtime() == null)){
            entity.setCtime(new Date());
        }
    }

    private void mtime(BaseEntity entity, boolean force){
        if(entity != null && (force || entity.getMtime() == null)){
            entity.setMtime(new Date());
        }
    }

    private void normalize(BaseEntity entity, boolean force){
        if(entity != null && (force || StringUtils.isEmpty(entity.getEditState()))){
            entity.setEditState(BaseEntity.EDIT_STATE_NORMAL);
        }else if ("-".equals(entity.getEditState())) {
            entity.setEditState(null);
        }
    }
}
