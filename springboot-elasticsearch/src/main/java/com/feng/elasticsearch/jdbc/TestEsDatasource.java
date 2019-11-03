package com.feng.elasticsearch.jdbc;

import com.alibaba.fastjson.JSON;
import com.amazon.opendistroforelasticsearch.jdbc.ElasticsearchDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestEsDatasource {

    public static void main(String... args) throws Exception {
        String url = "jdbc:elasticsearch://localhost:9200";
        ElasticsearchDataSource ds = new ElasticsearchDataSource();
        ds.setUrl(url);
        Connection conn = ds.getConnection();
//        PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM books");
        while (rs.next()) {
            System.err.println(JSON.toJSON(rs));
        }

        System.err.println("测试 ES Datasource 链接成功");
        st.close();
//        ps.close();
        conn.close();

    }
}
