package com.gzf.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuxy / mail:391861737@qq.com.
 * 线程池
 */
public enum ExePool {
    /**
     * 单例
     */
    INSTANCE;

    private ExecutorService executor;

    ExePool(){
        this.executor = Executors.newCachedThreadPool();
    }

    /**
     * 返回线程池
     */
    public ExecutorService executor(){
        return this.executor;
    }
}
