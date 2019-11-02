package com.feng.elasticsearch.service.impl;

import com.feng.elasticsearch.common.DateUtil;
import com.feng.elasticsearch.common.StringUtil;
import com.feng.elasticsearch.entity.TestDoc;
import com.feng.elasticsearch.respository.TestDocRepository;
import com.feng.elasticsearch.service.TestDocService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.feng.elasticsearch.common.DateUtil.FORMAT_DATE_TIME;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class TestDocServiceImpl extends AbstractEsService implements TestDocService {

    @Autowired
    private TestDocRepository testDocRepository;

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
        BoolQueryBuilder totalFilter = QueryBuilders.boolQuery()
                .filter(termQuery("type", type))
                .filter(rangeQuery("date").from(startDate.getTime()).to(endDate.getTime()))
                .filter(termsQuery("roleId", "admin", "user2"));
        key = StringUtil.trim(key);
        if (!"".equals(key)) {
            totalFilter.must(multiMatchQuery(key, "name", "content"));
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

        nativeSearchQueryBuilder.withQuery(totalFilter)
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .withHighlightFields(new HighlightBuilder.Field("content"));

//        nativeSearchQueryBuilder.withQuery(QueryBuilders.termsQuery("roleId", "admin", "user2"));
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "name", "content"));
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();

        Page<TestDoc> page = testDocRepository.search(searchQuery);
        return page;
    }

    @Override
    public List<TestDoc> search(String key) {
        return testDocRepository.findByNameLike(key);
    }
}
