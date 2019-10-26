package com.feng.datasource.multiple.mybatis.repository.cluster;


import com.feng.datasource.multiple.mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClusterUserMapper {
	/**
	 * 获取全部用户数据
	 *@return List<User> 用户列表
	 */
	List<User> getAll();

	/**
	 * 获取用户数据
	 *@param userId 用户id
	 *@return User 用户数据
	 */
	User get(Integer userId);

	/**
	 * 保存用户
	 *@param User 用户数据
	 *@return Integer 是否成功，1成功，0失败
	 */
	Integer insert(User User);

	/**
	 * 更新用户
	 *@param User 用户数据
	 *@return Integer 是否成功，1成功，0失败
	 */
	Integer update(User User);

	/**
	 * 删除用户
	 *@param userId 用户id
	 *@return Integer 是否成功，1成功，0失败
	 */
	Integer delete(Integer userId);
}
