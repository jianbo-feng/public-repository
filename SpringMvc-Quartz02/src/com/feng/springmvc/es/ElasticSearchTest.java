package com.feng.springmvc.es;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ElasticSearchTest {
	public final String ES_URL = "127.0.0.1";
    
    public final int ES_PORT = 9200;
    
    
    public static RestHighLevelClient getClientConnection() {
        
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http")
                    )
            );
        
        return client;
    }
    
    public static void searchById() throws IOException {
        RestHighLevelClient client = getClientConnection();
        GetRequest getRequest = new GetRequest("gateway_log", "DceJqGwBqlIig5BB05Z-", "");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        client.close();
    }
    
    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-search.html
     *
     * @throws IOException
     */
    public static void paginationSearch() throws IOException {
        
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchPhraseQuery("eventType", "WAN_ONOFF"));
        sourceBuilder.from(0);
        sourceBuilder.size(1);
        sourceBuilder.timeout(new TimeValue(1000));
        sourceBuilder.trackTotalHits(true);
        searchRequest.source(sourceBuilder);
        
        
        RestHighLevelClient client = getClientConnection();
        SearchResponse response = client.search(new SearchRequest("gateway_log")
                .source(sourceBuilder), RequestOptions.DEFAULT);
      
        System.out.println(response.toString());
        client.close();
    }
    
    public static void paginationSearch2() throws IOException {
        RestHighLevelClient client = getClientConnection();
        
        
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();        
        
//        RangeQueryBuilder rangeQuery= QueryBuilders.rangeQuery("count").gte(8);
//        boolQuery.filter(rangeQuery);        
//        
//        MatchQueryBuilder matchQuery = new MatchQueryBuilder("eventType", "WAN_ONOFF");
//        boolQuery.must(matchQuery);    
        
        SearchResponse response = client.search(new SearchRequest("books")
                .source(new SearchSourceBuilder()
                        .query(boolQuery)
                        .from(0)
                        .size(2)
                        .trackTotalHits(true)
                ), RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());
        System.out.println(response.toString());
        client.close();
    }
    
    
    public static void main(String[] args) throws IOException {
        //searchById();
        //paginationSearch();
        paginationSearch2();
    }
}
