package com.feng.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.elasticsearch.entity.Book;
import com.feng.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private BookService bookService;

    @GetMapping("save")
    public JSONObject save() {
        JSONObject vo = new JSONObject();
        Book entity = new Book();
        entity.setId(UUID.randomUUID().toString());
        entity.setBookName("英雄无敌之天下第一");
        entity.setAuthorName("匿名");
        bookService.save(entity);
        return vo;
    }

    @GetMapping("all")
    public JSONObject all() {
        JSONObject vo = new JSONObject();
        vo.put("data", bookService.all());
        return vo;
    }

    @GetMapping("search")
    public JSONObject search(@RequestParam(defaultValue = "", required = false) String key) {
        JSONObject vo = new JSONObject();
        vo.put("data", bookService.findByBookNameLike(key));
        return vo;
    }
}
