package com.feng.elasticsearch.respository;

import com.feng.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRespository extends ElasticsearchRepository<Book, String> {

    List<Book> findByBookNameLike(String bookName);
}
