package com.gzf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangweiguang on 2017/6/22.
 */
public class ListUtil {
    public static <T> List<T> toList(T t){
        List<T> list = new ArrayList<T>(1) ;
        list.add(t) ;
        return list;
    }
}
