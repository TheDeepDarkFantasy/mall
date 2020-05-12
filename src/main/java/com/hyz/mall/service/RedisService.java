package com.hyz.mall.service;

/**
 * @author hyz
 * @date 2020/05/06
 * redis 操作service
 */

public interface RedisService {
    /**
     * 存储数据
     * @param key
     * @param value
     */
    void set(String key,String value);

    /**
     * 获取数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置超时时间
     * @param key
     * @param exprire
     * @return
     */
    boolean exprire(String key,long exprire);

    /**
     * 删除数据
     * @param key
     */
    void remove(String key);

    /**
     * 自增操作
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key,long delta);
}
