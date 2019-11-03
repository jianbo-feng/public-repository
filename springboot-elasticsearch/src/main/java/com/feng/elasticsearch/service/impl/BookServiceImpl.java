package com.feng.elasticsearch.service.impl;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.fastjson.JSON;
import com.feng.elasticsearch.entity.Book;
import com.feng.elasticsearch.entity.TestDoc;
import com.feng.elasticsearch.respository.BookRespository;
import com.feng.elasticsearch.service.BookService;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl extends AbstractEsService<Book> implements BookService {

    @Autowired
    private BookRespository bookRespository;

    @Override
    public void save(Book book) {
        bookRespository.save(book);
    }

    @Override
    public Iterable<Book> all() {
        return bookRespository.findAll();
    }

    @Override
    public List<Book> findByBookNameLike(String key) {
        return bookRespository.findByBookNameLike(key);
    }

    @Override
    public List<Map<String, Object>> testSearch() {
        return this.queryIndexContent("books", " and id in ('0b46ef5c-d6a2-44e3-9891-3347f6b8dc76')");
    }

    @Override
    public List<Book> testSearch2() {
        Pageable pageable = PageRequest.of(0, 20);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        String indexName = "books", condition = " and id in ('0b46ef5c-d6a2-44e3-9891-3347f6b8dc76')";
        nativeSearchQueryBuilder.withQuery(createQueryBuilderByWhere(indexName,condition));
        SearchQuery searchQuery = nativeSearchQueryBuilder.withPageable(pageable).build();
        Page<Book> page = bookRespository.search(searchQuery);
        System.err.println("直接将SQL语句查询方式嵌入：\n\t" + JSON.toJSONString(page.getContent()));
        return this.toObjectList(testSearch(), Book.class);
    }
}
