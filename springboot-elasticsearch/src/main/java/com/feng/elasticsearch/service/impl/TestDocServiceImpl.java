package com.feng.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.feng.elasticsearch.common.DateUtil;
import com.feng.elasticsearch.common.StringUtil;
import com.feng.elasticsearch.entity.TestDoc;
import com.feng.elasticsearch.respository.TestDocRepository;
import com.feng.elasticsearch.service.TestDocService;
import org.apache.lucene.index.Terms;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

import static com.feng.elasticsearch.common.DateUtil.FORMAT_DATE_TIME;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

@Service
public class TestDocServiceImpl extends AbstractEsService<TestDoc> implements TestDocService {

    @Autowired
    private TestDocRepository testDocRepository;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(TestDoc s) {
        testDocRepository.save(s);
    }

    @Override
    public Page<TestDoc> search(String type, String key) {
        Pageable pageable = PageRequest.of(0, 20);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        /**
         * 查询说明：
         * (过滤条件)：
         *  1、type = {type}
         *  2、日期范围查询 （//时间段 一定要有头有尾 不然会出现慢查询）由于时间会被存储为long型数据，所以需要使用getTime
         *  2、roleId in ('admin', 'user2')
         * 模糊查询条件
         *  1、多字段查询key （字段：name、content）
         */
        Date startDate = DateUtil.str2Date("2019-11-02 12:46:45", FORMAT_DATE_TIME);
        startDate = DateUtil.str2Date("2019-10-02 12:46:45", FORMAT_DATE_TIME);
        Date endDate = new Date();
        String assignedRoleId = "FENGJIANBO547";
        assignedRoleId = "Admin_1234567";
//        assignedRoleId = "Admin-1234567";
//        assignedRoleId = "user3;";
//        assignedRoleId = "user2;";
//        assignedRoleId = "user4;";
        assignedRoleId = QueryParser.escape(assignedRoleId.toLowerCase(Locale.CHINESE));
        BoolQueryBuilder totalFilter = QueryBuilders.boolQuery()
                .filter(termQuery("type", type))        // 必须匹配类型
                // 满足一个指派角色
//                .filter(matchPhraseQuery("assignedRoleId", assignedRoleId))
                .filter(boolQuery() // 任意满足其中一个指派角色即可
                    .should(matchPhraseQuery("assignedRoleId", QueryParser.escape("Admin_1234567".toLowerCase(Locale.CHINESE))))
                        .should(matchPhraseQuery("assignedRoleId", QueryParser.escape("user3".toLowerCase(Locale.CHINESE))))
                )

//                .filter(termQuery("assignedRoleId", "user2")) // 不支持Admin-1234567 或 user2;user3
//                .filter(regexpQuery("assignedRoleId", assignedRoleId+"[;]?")) // 不支持Admin-1234567
//                .filter(matchPhraseQuery("assignedRoleId", assignedRoleId)) // 使用短语搜索，不对关键字(例如Admin-1234567)进行分词
//                    .filter(wildcardQuery("assignedRoleId", "*" + QueryParser.escape(assignedRoleId.toLowerCase()) + "*")) // 不支持Admin-1234567
//                .filter(regexpQuery("roleId", "user2[;]?")) // 不支持Admin-1234567
                .filter(rangeQuery("date").from(startDate.getTime()).to(endDate.getTime())); // 必须匹配时间范围
                //.filter(termsQuery("roleId", "admin", "user2"));        // 必须满足roleId in ('admin', 'user2')
                //.filter(termsQuery("assignedRoleId", "admin", "user2;"));
        key = QueryParser.escape(StringUtil.trim(key)).toLowerCase(Locale.CHINESE);
        if (!"".equals(key)) {

            /**
             * 使用multiMatchQuery检索会出现数据不完整的情况；
             * 使用boolQuery里面加2个通配查询的方式又不能体现分词检索
             * 所以采用boolQuery里面加 两个should（即'OR'）查询方式，即要么满足multiMatchQuery要么满足 boolQuery里面加2个通配查询 方式
             * 先通配查询，然后模糊查询，最后多字段分词检索
             */
            totalFilter.must(boolQuery()//.minimumShouldMatch(1)
                    .should(boolQuery()     // 使用短语搜索，不对关键字(例如Admin-1234567)进行分词
                            .should(matchPhraseQuery("name", key))
                            .should(matchPhraseQuery("content", key))
                    )
                    .should(boolQuery()                                             // 通配查询
                            .should(wildcardQuery("name", "*" + key + "*"))
                            .should(wildcardQuery("content", "*" + key + "*"))
                    )
                    .should(boolQuery()                                             // 模糊查询
                            .should(fuzzyQuery("name", key).fuzziness(Fuzziness.AUTO))
                            .should(fuzzyQuery("conent", key).fuzziness(Fuzziness.AUTO))
                    )
                    .should(multiMatchQuery(key, "name", "content"))    // 多字段分词检索
            );
        }

        /**
         * 添加高亮
         */
//        HighlightBuilder.Field hfield= new HighlightBuilder.Field("name")
//                .preTags("<em style='color:red'>")
//                .postTags("</em>")
//                .fragmentSize(100);
//        HighlightBuilder.Field hfield2 = new HighlightBuilder.Field("content")
//                .preTags("<em style='color:red'>")
//                .postTags("</em>")
//                .fragmentSize(100);
//        nativeSearchQueryBuilder.withHighlightFields(hfield, hfield2);

        String preTags = "<em style='color:red'>", postTags = "</em>";
        // 高亮字段(如果搜索结果的文档中需要设置高亮的字段没有值或为null，则有可能导致搜索结果异常或查询不到结果)
        List<String> highFieldNames = Arrays.asList("name", "content");
        if (null != highFieldNames && !highFieldNames.isEmpty()) {
            for (String fName : highFieldNames) {
                nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field(fName)
                        .preTags(preTags)
                        .postTags(postTags)
                        .fragmentSize(10).numOfFragments(3).noMatchSize(150));
            }
        }

        nativeSearchQueryBuilder.withQuery(totalFilter)
//                .withHighlightFields(
//                        new HighlightBuilder.Field("name")
//                                .preTags(preTags)
//                                .postTags(postTags)
//                                .fragmentSize(10).numOfFragments(3).noMatchSize(150),
//                        new HighlightBuilder.Field("content")
//                                .preTags(preTags)
//                                .postTags(postTags)
//                                .fragmentSize(10).numOfFragments(3).noMatchSize(150))
                .withSort(SortBuilders.fieldSort("roleId").order(SortOrder.ASC));

//        nativeSearchQueryBuilder.withQuery(QueryBuilders.termsQuery("roleId", "admin", "user2"));
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "name", "content"));
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();

        AggregatedPage<TestDoc> docs = getAggregatedPage(searchQuery, TestDoc.class, highFieldNames);
        /* // 原来的写法
        AggregatedPage<TestDoc> docs = elasticsearchTemplate.queryForPage(searchQuery, TestDoc.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<T> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (null == searchHit) {
                        return null;
                    }

                    T tEntity = JSON.parseObject(searchHit.getSourceAsString(), clazz);
                    Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                    if (null != highFieldNames && !highFieldNames.isEmpty()) {
                        for (String fName : highFieldNames) {
                            if (highlightFieldMap.containsKey(fName)) {
                                try {
                                    HighlightField highlightField = highlightFieldMap.get(fName);
                                    String tmpVal = "";
                                    for (Text text : highlightField.fragments()) {
                                        tmpVal += text;
                                    }
                                    Field cField = clazz.getDeclaredField(fName);
                                    cField.setAccessible(true);
                                    cField.set(tEntity, tmpVal);
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    chunk.add(tEntity);
                }
                if (chunk.size() > 0) {
//                    return new AggregatedPageImpl<>((List<T>) chunk);
                    return new AggregatedPageImpl<>(chunk);
                }
                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                if (null != searchHit) {
                    return JSON.parseObject(searchHit.getSourceAsString(), type);
                }
                return null;
            }
        });
         */
        return docs;
//        Page<TestDoc> page = testDocRepository.search(searchQuery);
//        return page;
    }

    @Override
    public List<TestDoc> search(String key) {
        return testDocRepository.findByNameLike(key);
    }
}
