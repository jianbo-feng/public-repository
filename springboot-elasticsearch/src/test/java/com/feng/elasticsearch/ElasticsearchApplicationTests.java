package com.feng.elasticsearch;

import com.feng.elasticsearch.entity.Book;
import com.feng.elasticsearch.respository.BookRespository;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    BookRespository bookRespository;

    //@Test
    void contextLoads() {

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setBookName("西游记");
        book.setAuthorName("吴晨恩");
        bookRespository.index(book);


    }

    @Autowired
    ElasticsearchTemplate template;

    //@Test
    void testTemplate() throws Exception {

        Client client = template.getClient();
        XContentBuilder builder = jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .endObject();
        IndexResponse response = client.prepareIndex("people", "_doc", "1")
                .setSource(builder).get();

        System.out.println(response.getIndex());

    }

}
