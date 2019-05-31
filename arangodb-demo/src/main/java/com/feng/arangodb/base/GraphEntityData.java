package com.feng.arangodb.base;

import com.arangodb.*;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.model.VertexCreateOptions;

/**
 * 创建图数据
 */
public class GraphEntityData {

    private static final String TEST_DB = "actors_movies_db1";
    private static ArangoDB arangoDB;
    private static ArangoDatabase db;

    public static void main(String... args) {
        try {
            ArangoDB arangoDB = new ArangoDB.Builder()
                    .host(ArangoDbConfig.host, ArangoDbConfig.port)
                    .user(ArangoDbConfig.user)
                    .password(ArangoDbConfig.password)
                    .build();
            try {
                arangoDB.db(TEST_DB).drop();
            } catch (final ArangoDBException e) {
            }
//            arangoDB.createDatabase(TEST_DB);
            db = arangoDB.db(TEST_DB);

            initData();

            ArangoGraph graph = db.graph("some-graph");
            ArangoVertexCollection collection = graph.vertexCollection("some-vertex-collection");

            BaseDocument document = new BaseDocument();
            document.addAttribute("some", "data");
            collection.insertVertex(document, new VertexCreateOptions());
        }
        catch (ArangoDBException e) {
            e.printStackTrace();
        }
        finally {
            arangoDB.shutdown();
        }
    }

    /**
     * 初始化数据
     */
    private static void initData() {



    }

}
