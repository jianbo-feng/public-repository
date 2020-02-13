package com.feng.springmvc.es;

import java.util.List;

/**
 * 
 * ES查询分页
 * @author baby
 *
 * @param <T>
 */
public class ElasticSearchPage <T> {
	
	private String scrollId;
	
	private long total;
	private int pageSize;
	private int pageNum;
	
	private T param;
	private List<T> retList;
	private List<String> scrollIds;
	public String getScrollId() {
		return scrollId;
	}
	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	public List<T> getRetList() {
		return retList;
	}
	public void setRetList(List<T> retList) {
		this.retList = retList;
	}
	public List<String> getScrollIds() {
		return scrollIds;
	}
	public void setScrollIds(List<String> scrollIds) {
		this.scrollIds = scrollIds;
	}
	@Override
	public String toString() {
		return "ElasticSearchPage [scrollId=" + scrollId + ", total=" + total + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", param=" + param + ", retList=" + retList + ", scrollIds=" + scrollIds + "]";
	}
	
	
}
