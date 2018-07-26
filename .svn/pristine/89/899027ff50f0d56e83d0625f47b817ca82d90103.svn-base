package com.gzf.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/12.
 */
public enum Templater {

    /**
     * 尖括号模板
     */
    ANGULAR("<(.+?)>"),
    ;

    private Pattern pattern;//正则表达式样板

    private Templater(String regex){
        this.pattern = Pattern.compile(regex);
    }

    private static Log log = LogFactory.getLog(Templater.class);

    public static final char INDICATOR_REF = '@';

    public static final char INDICATOR_BUNDARY_FIELD = '#';

    /**
     * 取得根据tokens替换过值之后的内容
     * @param content
     * 		文本内容
     * @param tokens
     * 		tokens
     * @return
     * 		固化的内容
     */
    public String solid(String content, Map<String, String> tokens){
        if(content != null){
            Matcher matcher = pattern.matcher(content);
            StringBuffer buffer = new StringBuffer();
            while(matcher.find()){
                //尝试从tokens中找到键所对应的值
                String matched = matcher.group(1);
                String token = tokens == null ? null : tokens.get(matched);
                if(token != null){
                    //用token替换捕获到的组
                    matcher.appendReplacement(buffer, token);
                }else{
                    matcher.appendReplacement(buffer, toString(resolve(matched)));
                }
            }
            matcher.appendTail(buffer);
            return buffer.toString();
        }
        return null;
    }

    private static Object resolve(String matched){
        if(matched != null && matched.length() >= 1 && matched.charAt(0) == INDICATOR_REF){
            try {
                int sharpIndex = matched.indexOf(INDICATOR_BUNDARY_FIELD);//尝试取得井号索引
                if(sharpIndex != -1){
                    Class<?> clz = Class.forName(matched.substring(1, sharpIndex));
                    Field field = clz.getField(matched.substring(sharpIndex + 1));
                    if(clz != null && field != null){
                        return field.get(clz);
                    }
                }else{
                        return Class.forName(matched.substring(1));
                }
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                log.error(e);
            }
        }
        return null;
    }

    private static String toString(Object obj){
        if(obj == null){
            return "";
        }else if(obj instanceof String){
            return (String) obj;
        }
        return obj.toString();
    }
}
