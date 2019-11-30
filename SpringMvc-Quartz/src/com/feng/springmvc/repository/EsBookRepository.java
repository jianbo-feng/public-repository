package com.feng.springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.feng.springmvc.entity.EsBook;
import com.feng.springmvc.es.ElasticSearchPage;

@Repository
public class EsBookRepository {
	
	@Autowired
	private TransportClientRepository client;
	
	/**
	 * 保存对象
	 * @param entity
	 */
	public void saveBook(EsBook entity) {
		client.saveDoc("mybooks", "book", entity.getId(), entity);
	}
	
	/**
	 * 查询信息
	 * @param key
	 */
	public void search(String key) {
		EsBook param = new EsBook();
		param.setTitle(key);
		ElasticSearchPage<EsBook> page= new ElasticSearchPage<>();
		page.setPageSize(10);
		HighlightBuilder highlight = new HighlightBuilder();
		highlight.field("title").preTags("<span style=\"color:red\">").postTags("</span>");
		String[] highlightFieldNames = new String[] {"title", "content"};
		page = client.searchFullText(param,page, highlight, highlightFieldNames ,"mybooks");
		for(EsBook aa : page.getRetList()){
			System.out.println(aa.getId() +"===="+aa.getTitle()+"===title:=="+aa.getContext());
		}
		System.out.println(page.getTotal());
	}
	
//	/**
//	 * 检索
//	 * @param param
//	 * @param page
//	 * @param highlight
//	 * @param indexs
//	 * @return
//	 */
//	public ElasticSearchPage<EsBook> searchFullText(EsBook param,ElasticSearchPage<EsBook> page, HighlightBuilder highlight, String...indexs) {
//		QueryBuilder builder = null;
//		Map<String, Object> map = TransportClientRepository.getObjectMap(param);
//		if (map == null) {
//			return null;
//		}
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//			if (entry.getValue() != null) {
//				builder = QueryBuilders.matchQuery(entry.getKey(),entry.getValue());
//			}
//		}
//		SearchResponse scrollResp = client.prepareSearch(indexs).setFrom(page.getPageNum() * page.getPageSize())
//				.highlighter(highlight).setSize(page.getPageSize())
//				.setQuery(builder).get();
//		List<EsBook> result = new ArrayList<>();
//		for (SearchHit hit : scrollResp.getHits().getHits()) {
//			try {
//				Map<String,HighlightField> highlightResult = hit.getHighlightFields();
//				EsBook bSearch = client.parseObject(param, hit.getSourceAsString());
//				String titleAdd = "";
//				for(Text textTemp : highlightResult.get("title").fragments()){
//					titleAdd += textTemp;
//				}
//				bSearch.setTitle(titleAdd);
//				result.add(titleAdd);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		page.setTotal(scrollResp.getHits().totalHits);
//		page.setParam(param);
//		page.setRetList(result);
//		return page;
//		
//	}
}
