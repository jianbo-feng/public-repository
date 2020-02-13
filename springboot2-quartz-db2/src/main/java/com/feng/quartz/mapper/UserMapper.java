package com.feng.quartz.mapper;

import com.github.pagehelper.Page;
import com.feng.quartz.base.BaseMapper;
import com.feng.quartz.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:02
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    Page<User> getUsers();
}
