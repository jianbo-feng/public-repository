package com.feng.redis.sentinel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class AbstractBaseService<T, ID> {

    @Autowired
    private CrudRepository crudRepository;

    /**
     * 保存
     * @param t
     * @return
     */
    public Object save(T t) {
        return crudRepository.save(t);
    }

    /**
     * 保存列表
     * @param iterable
     * @return
     */
    public Object saveAll(Iterable<T> iterable) {
        return crudRepository.saveAll(iterable);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    public Optional<T> get(ID id) {
        return crudRepository.findById(id);
    }

    /**
     * 是否存在
     * @param id
     * @return
     */
    public boolean existsById(ID id) {
        return crudRepository.existsById(id);
    }

    public Iterable<T> findAll() {
        return crudRepository.findAll();
    }

    public Iterable<T> findAllById(Iterable<ID> ids) {
        return crudRepository.findAllById(ids);
    }

    public long count() {
        return crudRepository.count();
    }

    public void deleteById(ID id) {
        crudRepository.deleteById(id);
    }

    public void delete(T t) {
        crudRepository.delete(t);
    }

    public void deleteAll(Iterable<? extends T> iterable) {
        crudRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        crudRepository.deleteAll();
    }
}
