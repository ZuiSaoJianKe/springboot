package cn.cloudStream.learn.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
@Slf4j
public class BaseContext {
    private static ThreadLocal<Object> threadLocal=new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Object id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Object getCurrentId(){
        return threadLocal.get();
    }
}