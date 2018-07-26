package com.gzf.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 实体类转json时的日期处理
 * 作者: hbl
 * 创建时间: 2017年2月21日 下午4:09:37
 */
public class JsonDateValueProcessor implements JsonValueProcessor{

    @Override
    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String arg0, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value){
        //遇到类型为日期的，就自动转换成“yyyy-MM-dd HH:mm:ss”格式的字符串
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

}
