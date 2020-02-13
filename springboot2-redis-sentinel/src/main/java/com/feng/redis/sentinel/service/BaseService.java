package com.feng.redis.sentinel.service;

import java.util.Optional;

public interface BaseService<T, ID> {

    /**
     * 保存
     * @param t
     * @return
     */
    Object save(T t);

    /**
     * 保存列表
     * @param iterable
     * @return
     */
    Object saveAll(Iterable<T> iterable);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Optional<T> get(ID id);

    /**
     * 是否存在
     * @param id
     * @return
     */
    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T t);

    void deleteAll(Iterable<? extends T> iterable);

    void deleteAll();
}
