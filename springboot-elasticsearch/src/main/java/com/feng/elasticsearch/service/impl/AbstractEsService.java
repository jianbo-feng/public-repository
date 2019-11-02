package com.feng.elasticsearch.service.impl;

import com.feng.elasticsearch.common.StringUtil;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * 抽象业务类
 */
public abstract class AbstractEsService {

    /**
     * 根据关键词检索
     * @param pageable
     * @param key
     * @return
     */
    public SearchQuery searchQuery(Pageable pageable, String key) {
        SearchQuery searchQuery = null;
        key = StringUtil.trim(key);
        if(!"".equals(key)) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(queryStringQuery(key));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 短语检索
     * @param pageable
     * @param fieldName
     * @param key
     * @return
     */
    public SearchQuery searchQueryByPhrase(Pageable pageable, String fieldName, String key) {
        SearchQuery searchQuery = null;
        fieldName = StringUtil.trim(fieldName);
        key = StringUtil.trim(key);
        if(!"".equals(fieldName) && !"".equals(key)) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(matchPhraseQuery(fieldName, key));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 精确检索
     * @param pageable
     * @param fieldName
     * @param key
     * @return
     */
    public SearchQuery searchQueryByTerm(Pageable pageable, String fieldName, String key) {
        SearchQuery searchQuery = null;
        fieldName = StringUtil.trim(fieldName);
        key = StringUtil.trim(key);
        if(!"".equals(fieldName) && !"".equals(key)) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(termQuery(fieldName, key));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 精确检索
     * @param pageable
     * @param fieldName
     * @param values
     * @return
     */
    public SearchQuery searchQueryByTerms(Pageable pageable, String fieldName, Object... values) {
        SearchQuery searchQuery = null;
        fieldName = StringUtil.trim(fieldName);
        if(!"".equals(fieldName) && values != null && values.length > 0) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(termsQuery(fieldName, values));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 精确检索
     * @param pageable
     * @param fieldName
     * @param values
     * @return
     */
    public SearchQuery searchQueryByTerms(Pageable pageable, String fieldName, Collection<?> values) {
        SearchQuery searchQuery = null;
        fieldName = StringUtil.trim(fieldName);
        if(!"".equals(fieldName) && values != null && !values.isEmpty()) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(termsQuery(fieldName, values));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 查询相似度
     * @param pageable
     * @param fieldNames
     * @param likeTexts
     * @return
     */
    public SearchQuery searchMoreLikeThisQuery(Pageable pageable, String[] fieldNames, String[] likeTexts) {
        SearchQuery searchQuery = null;
        if(fieldNames != null && fieldNames.length > 0) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(moreLikeThisQuery(fieldNames,likeTexts,null));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 模糊匹配(字符串的相似度)
     * @param pageable
     * @param fieldName
     * @param key
     * @return
     */
    public SearchQuery searchQueryByFuzzy(Pageable pageable, String fieldName, Object key) {
        SearchQuery searchQuery = null;
        fieldName = StringUtil.trim(fieldName);
        if(key != null) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(fuzzyQuery(fieldName, key).fuzziness(Fuzziness.AUTO));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 多字段同时检索一个关键词
     * @param pageable
     * @param key
     * @param fieldNames
     * @return
     */
    public SearchQuery searchQuery(Pageable pageable, String key, String ...fieldNames) {
        SearchQuery searchQuery = null;
        key = StringUtil.trim(key);
        if(!"".equals(key) && fieldNames != null && fieldNames.length > 0) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(multiMatchQuery(key, fieldNames));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 组合检索
     * @param pageable
     * @param termQueries 精确搜索条件(key:字段,value:关键字)
     * @param matchQueries 模糊搜索条件(key:字段,value:关键字)
     * @return
     */
    public SearchQuery searchQuery(Pageable pageable, Map<String,Object> termQueries, Map<String,Object> matchQueries) {
        SearchQuery searchQuery = null;
        if(termQueries != null && !termQueries.isEmpty() && matchQueries != null && !matchQueries.isEmpty()) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            if(termQueries != null && !termQueries.isEmpty()) {
                termQueries.forEach((k,v) -> nativeSearchQueryBuilder.withQuery(termQuery(k,v)));
            }
            if(matchQueries != null && !matchQueries.isEmpty()) {
                matchQueries.forEach((k,v) -> nativeSearchQueryBuilder.withQuery(matchQuery(k,v)));
            }
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }

        return searchQuery;
    }

    /**
     * 全检索
     * @param pageable
     * @return
     */
    public SearchQuery searchAllQuery(Pageable pageable) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(matchAllQuery());
        return nativeSearchQueryBuilder.withPageable(pageable).build();
    }

    /**
     * 通配符查询构造器
     * @param name 字段名
     * @param query 查询字符串(如：'*a*')
     * @return
     */
    public QueryBuilder wildcardQuery(String name, String query) {
        return QueryBuilders.wildcardQuery(name,query);
    }

    /**
     * 通配符查询检索
     * @param pageable 分页信息
     * @param key 查询字符串(如：'*a*')
     * @param fieldName 字段名
     * @return
     */
    public SearchQuery wildcardQuery(Pageable pageable, String key, String fieldName) {
        SearchQuery searchQuery = null;
        key = StringUtil.trim(key);
        fieldName = StringUtil.trim(fieldName);
        if(!"".equals(key) && !"".equals(fieldName)) {
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(wildcardQuery(fieldName,key));
            searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        }
        else {
            searchQuery = searchAllQuery(pageable);
        }
        return searchQuery;
    }

    /**
     * 功能描述:多条件查询之bool query
     * @param pageable 分页信息
     * @param indexs 索引数组
     * @param types 类型数组
     * @param musts 必须查询
     * @param mustNots 不能查询
     * @param shoulds 应查询(OR)
     * @param filters 拦截条件
     * @return
     * @see <a href="https://blog.csdn.net/zhouzhiwengang/article/details/97392088">参考</a>
     */
    public SearchQuery searchBooleanQuery(Pageable pageable, String[] indexs, String[] types, List<QueryBuilder> musts,
                                          List<QueryBuilder> mustNots, List<QueryBuilder> shoulds,
                                          List<QueryBuilder> filters) {
        BoolQueryBuilder boolQuery =  QueryBuilders.boolQuery();
        musts.stream().forEach(item->{
            boolQuery.must(item);
        });
        mustNots.stream().forEach(item->{
            boolQuery.mustNot(item);
        });
        shoulds.stream().forEach(item ->{
            boolQuery.should(item);
        });
        filters.stream().forEach(item->{
            boolQuery.filter(item);
        });
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices(indexs).withTypes(types).withQuery(boolQuery);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        return searchQuery;
    }


    /**
     * 功能描述:多条件检索之constant score query
     * @param pageable 分页信息
     * @param indexs 索引数组
     * @param types  类型数组
     * @param name   文档属性名称
     * @param value  文档属性值
     * @param boost  权重值
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public SearchQuery searchConstantScoreQuery(Pageable pageable, String[] indexs, String[] types,
                                                String name, Object value, float boost) {
        TermQueryBuilder termQuery = QueryBuilders.termQuery(name, value);
        ConstantScoreQueryBuilder constantScoreQuery = QueryBuilders.constantScoreQuery(termQuery).boost(boost);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices(indexs).withTypes(types).withQuery(constantScoreQuery);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        return searchQuery;
    }

    /**
     * 功能描述:多条件查询之dis max query
     * @param pageable 分页信息
     * @param indexs   索引数组
     * @param types    类型数组
     * @param query    查询条件对列
     * @param boost    权重值
     * @param breaker  判断值
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SearchQuery searchDisMaxQuery(Pageable pageable, String[] indexs, String[] types, List<QueryBuilder> query,
                                         float boost, float breaker) {
        DisMaxQueryBuilder disMaxQuery = QueryBuilders.disMaxQuery();
        query.stream().forEach(item ->{
            disMaxQuery.add(item);
        });
        disMaxQuery.boost(boost).tieBreaker(breaker);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices(indexs).withTypes(types).withQuery(disMaxQuery);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        return searchQuery;
    }

    /**
     *  功能描述:多条件查询之function score query
     *  @param pageable 分页信息
     * @param indexs 索引数组
     * @param types  类型数组
     * @param matchQuery  条件
     * @param fieldName   文档属性名称
     * @param origin
     * @param scale
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public SearchQuery searchFunctionScoreQuery(Pageable pageable, String[] indexs, String[] types, QueryBuilder matchQuery,
                                                String fieldName, Object origin, Object scale) {
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] functions = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery,  ScoreFunctionBuilders.randomFunction().seed(Math.round(Math.random() * 100))),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(ScoreFunctionBuilders.exponentialDecayFunction(fieldName, origin, scale))
        };
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices(indexs).withTypes(types).withQuery(functionScoreQuery(functions));
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        return searchQuery;
    }

    /**
     * 功能描述:多条件查询之boost query
     * @param pageable 分页信息
     * @param indexs 索引数组
     * @param types  类型数组
     * @param positiveQuery   条件
     * @param negativeQuery   条件
     * @param boost  权重值
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SearchQuery searchBoostiQuery(Pageable pageable, String[] indexs, String[] types, QueryBuilder positiveQuery,
                                         QueryBuilder negativeQuery, float boost) {
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(positiveQuery, negativeQuery);
        boostingQuery.boost(boost);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices(indexs).withTypes(types).withQuery(boostingQuery);
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        return searchQuery;
    }
}
