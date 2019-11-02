package com.feng.elasticsearch.respository;

import com.feng.elasticsearch.entity.TestDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TestDocRepository extends ElasticsearchRepository<TestDoc, String> {

    List<TestDoc> findByNameLike(String key);
}
