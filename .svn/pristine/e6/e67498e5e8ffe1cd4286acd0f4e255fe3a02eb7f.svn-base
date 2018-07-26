package com.gzf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 * 连接器，
 */
public class Concater {

    /*
    分隔符
     */
    private char splitor;

    private List<String> targets;

    public Concater(char splitor, String[] init){
        this.splitor = splitor;
        this.targets = new ArrayList<>();
        if(init != null){
            for(String i : init){
                this.targets.add(this.concat("", i));
            }
        }else{
            this.targets.add("");
        }
    }

    //multiply by 字符串数组
    public Concater multiply(String[] by){
        if(by != null && by.length != 0){
            int currentSize = this.targets.size();//当前builder的size
            for(int i = 0; i < currentSize; i ++){
                String current = this.targets.get(i);
                for(int j = 0; j < by.length; j ++){
                    if(j == 0){
                        this.targets.set(i, this.concat(current, by[j]));
                    }else{
                        this.targets.add(this.concat(current, by[j]));
                    }
                }
            }
        }
        return this;
    }

    //plus by 字符串
    public Concater plus(String by){
        if(this.targets.size() == 0){
            this.targets.add(by);
        }else{
            for(int i = 0; i < this.targets.size(); i ++){
                this.targets.set(i, this.concat(this.targets.get(i), by));
            }
        }
        return this;
    }

    public List<String> list(){
        return this.targets;
    }

    private String concat(String tar, String str){
        if(str != null && str.length() != 0){
            //现有字符串末尾没有分隔符，且要连接的字符串开头也没有分隔符，则添加分隔符
            if((tar.length() == 0 || tar.charAt(tar.length() - 1) != this.splitor)
                    && str.charAt(0) != this.splitor){
                tar += this.splitor;
            }
            tar += (str == null ? "" : str);
        }
        return tar;
    }

}
