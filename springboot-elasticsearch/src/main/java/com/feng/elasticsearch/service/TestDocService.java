package com.feng.elasticsearch.service;

import com.feng.elasticsearch.entity.TestDoc;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestDocService {

    void save(TestDoc s);

    Page<TestDoc> search(String type, String key);

    List<TestDoc> search(String key);
}
