package com.feng.elasticsearch.service.impl;

import com.feng.elasticsearch.entity.Book;
import com.feng.elasticsearch.respository.BookRespository;
import com.feng.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

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
}
