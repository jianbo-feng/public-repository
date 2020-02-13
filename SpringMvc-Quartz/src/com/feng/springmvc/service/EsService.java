package com.feng.springmvc.service;

import com.feng.springmvc.entity.EsBook;

/**
 * ES业务处理
 * @author baby
 * @date 2019/11/05
 */
public interface EsService {

	/**
	 * 保存对象
	 * @param entity
	 */
	void saveBook(EsBook entity);
	
	/**
	 * 查询信息
	 * @param key
	 */
	void search(String key);
}
