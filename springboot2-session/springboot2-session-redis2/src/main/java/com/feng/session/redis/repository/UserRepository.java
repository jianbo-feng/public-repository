package com.feng.session.redis.repository;

import com.feng.session.redis.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户信息
 * @author Feng
 */
public interface UserRepository extends CrudRepository<User, String> {

}
