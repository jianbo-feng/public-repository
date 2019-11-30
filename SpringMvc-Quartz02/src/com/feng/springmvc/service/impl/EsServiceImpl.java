package com.feng.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feng.springmvc.entity.EsBook;
import com.feng.springmvc.repository.EsBookRepository;
import com.feng.springmvc.service.EsService;

@Service
public class EsServiceImpl implements EsService {
	
	@Autowired
	private EsBookRepository bookRepository;

	@Override
	public void saveBook(EsBook entity) {
		bookRepository.saveBook(entity);
	}

	@Override
	public void search(String key) {
		bookRepository.search("文档");
	}

}
