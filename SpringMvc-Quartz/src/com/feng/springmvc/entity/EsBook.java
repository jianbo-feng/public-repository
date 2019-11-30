package com.feng.springmvc.entity;

import java.io.Serializable;

import com.feng.springmvc.es.ESearchTypeColumn;

/**
 * ES文档对象
 * @author baby
 * @date 2019/11/05
 */
public class EsBook implements Serializable {

	private static final long serialVersionUID = -1970748896352649123L;
	
	@ESearchTypeColumn(type = "id")
	private String id;
	
	@ESearchTypeColumn(type = "string")
	private String title;
	
	@ESearchTypeColumn(type = "string")
	private String context;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "EsBook [id=" + id + ", title=" + title + ", context=" + context + "]";
	}

	public EsBook(String id, String title, String context) {
		this.id = id;
		this.title = title;
		this.context = context;
	}

	public EsBook() {
	}
	
	

}
