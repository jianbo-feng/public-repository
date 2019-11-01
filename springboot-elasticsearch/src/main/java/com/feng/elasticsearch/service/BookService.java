package com.feng.elasticsearch.service;

import com.feng.elasticsearch.entity.Book;

import java.util.List;

public interface BookService {

    void save(Book book);

    Iterable<Book> all();

    List<Book> findByBookNameLike(String key);
}
