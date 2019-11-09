package com.feng.redis.sentinel.repository;

import com.feng.redis.sentinel.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
