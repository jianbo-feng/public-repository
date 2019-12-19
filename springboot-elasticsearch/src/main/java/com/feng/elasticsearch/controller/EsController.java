package com.feng.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.elasticsearch.entity.Book;
import com.feng.elasticsearch.entity.TestDoc;
import com.feng.elasticsearch.service.BookService;
import com.feng.elasticsearch.service.TestDocService;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private TransportClient transportClient;

    @Autowired
    private BookService bookService;

    @Autowired
    private TestDocService docService;

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
        vo.put("test", bookService.testSearch());
        vo.put("test2", bookService.testSearch2());
        return vo;
    }

    @GetMapping("search")
    public JSONObject search(@RequestParam(defaultValue = "", required = false) String key) {
        JSONObject vo = new JSONObject();
        vo.put("data", bookService.findByBookNameLike(key));
        return vo;
    }

    @GetMapping("mapping")
    public JSONObject mapping() {
        JSONObject vo = new JSONObject();
        return vo;
    }

    @GetMapping("sales")
    public JSONObject sales() {
        JSONObject vo = new JSONObject();
        try {
            String parentId = "AAA121212";
            vo.put("code", 200);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @GetMapping("/doc/save")
    public JSONObject docSave() {
        JSONObject vo = new JSONObject();
        TestDoc doc = new TestDoc();
        doc.setId(UUID.randomUUID().toString());
        doc.setDate(new Date());
        doc.setName("doc-23");
        doc.setContent("第二十三遍文档");
        doc.setType("smart");
        doc.setRoleId("user2");
//        docService.save(doc);
//
//        doc = new TestDoc();
//        doc.setId(UUID.randomUUID().toString());
//        doc.setDate(new Date());
//        doc.setName("doc-3");
//        doc.setContent("第三遍文档");
//        doc.setType("smart");
//        doc.setRoleId("admin");
//        docService.save(doc);
//
//        doc = new TestDoc();
//        doc.setId(UUID.randomUUID().toString());
//        doc.setDate(new Date());
//        doc.setName("doc-1");
//        doc.setContent("第一遍文档");
//        doc.setType("article");
//        doc.setRoleId("user");
//        docService.save(doc);
//
//        doc = new TestDoc();
//        doc.setId(UUID.randomUUID().toString());
//        doc.setDate(new Date());
//        doc.setName("doc-2");
//        doc.setContent("第二遍文档");
//        doc.setType("article");
//        doc.setRoleId("user");
//        docService.save(doc);
//
//        doc = new TestDoc();
//        doc.setId(UUID.randomUUID().toString());
//        doc.setDate(new Date());
//        doc.setName("doc-4");
//        doc.setContent("第四遍文档");
//        doc.setType("smart");
//        doc.setRoleId("user");
//        docService.save(doc);
//
//        doc = new TestDoc();
//        doc.setId(UUID.randomUUID().toString());
//        doc.setDate(new Date());
//        doc.setName("doc-14");
//        doc.setContent("第十四遍文档");
//        doc.setType("smart");
//        doc.setRoleId("user");
//        docService.save(doc);

        doc = new TestDoc();
        doc.setId(UUID.randomUUID().toString());
        doc.setDate(new Date());
        doc.setName("ABdoc-53-2019-12-19 --008");
        doc.setContent("This is AbcD第五十三遍文档008");
        doc.setType("smart");
        doc.setRoleId("user2");
        doc.setAssignedRoleId("Admin_1234567");
        docService.save(doc);
        return vo;
    }

    @GetMapping("/doc/search")
    public JSONObject docSearch(@RequestParam(defaultValue = "") String key,@RequestParam(defaultValue = "smart") String type) {
        JSONObject vo = new JSONObject();
//        vo.put("data", docService.search(key));
        vo.put("page", docService.search(type, key));
        return vo;
    }
}
