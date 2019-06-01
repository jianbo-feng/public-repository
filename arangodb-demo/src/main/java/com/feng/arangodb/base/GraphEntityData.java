package com.feng.arangodb.base;

import com.arangodb.*;
import com.arangodb.entity.*;
import com.arangodb.model.VertexCreateOptions;
import com.feng.arangodb.entity.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 创建图数据示例
 */
public class GraphEntityData {

    private static final String GRAPH_DB = "actors_movies_graph";
    protected static final String GRAPH_NAME = "actor-movie";
    protected static final String EDGE_COLLECTION_NAME = "actorIns";
    protected static final String EDGE_COLLECTION_DIRECTORS = "directorIns";
    protected static final String EDGE_COLLECTION_RELATIONSHIPS = "relationships";
    protected static final String VERTEXT_COLLECTION_ACTOR = "actors";
    protected static final String VERTEXT_COLLECTION_MOVIE = "movies";
    protected static final String VERTEXT_COLLECTION_DIRECTOR = "directors";
    private static ArangoDB arangoDB;
    private static ArangoDatabase db;

    public static void main(String... args) {
        if(arangoDB == null) {
            arangoDB = new ArangoDB.Builder()
                    .host(ArangoDbConfig.host, ArangoDbConfig.port)
                    .user(ArangoDbConfig.user)
                    .password(ArangoDbConfig.password)
                    .build();
        }

        // 删除已有库
        try {
            arangoDB.db(GRAPH_DB).drop();
        }
        catch (Exception e) {

        }
        try {
            arangoDB.createDatabase(GRAPH_DB);
            db = arangoDB.db(GRAPH_DB);
            final Collection<EdgeDefinition> edgeDefinitions = new ArrayList<EdgeDefinition>() {{
                add(new EdgeDefinition().collection(EDGE_COLLECTION_NAME)
                        .from(VERTEXT_COLLECTION_ACTOR).to(VERTEXT_COLLECTION_MOVIE));
                add(new EdgeDefinition().collection(EDGE_COLLECTION_DIRECTORS)
                        .from(VERTEXT_COLLECTION_DIRECTOR).to(VERTEXT_COLLECTION_MOVIE));

                add(new EdgeDefinition().collection(EDGE_COLLECTION_RELATIONSHIPS)
                        .from(VERTEXT_COLLECTION_ACTOR).to(VERTEXT_COLLECTION_DIRECTOR));
            }};
            db.createGraph(GRAPH_NAME, edgeDefinitions, null);

            initData();
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
        // 创建顶点信息
        final VertexEntity actor1 = saveActor(new Actor("lilianjie", "李连杰", "1970", "李连杰：功夫皇帝"));
        final VertexEntity actor2 = saveActor(new Actor("chenglong", "成龙", "1960", "成龙：拼命三郎"));
        final VertexEntity actor3 = saveActor(new Actor("fanbingbing", "饭冰冰", "1980", "饭冰冰：睡兵连"));
        final VertexEntity actor4 = saveActor(new Actor("hongjinbao", "红金豹", "1960", "红金豹：睡兵连长"));

        final VertexEntity movie1 = saveMovie(new Movie("m1",  "功夫之王",  "1995",  "1995",  "功夫之王"));
        final VertexEntity movie2 = saveMovie(new Movie("m2",  "不眠之夜",  "1995",  "1995",  "不眠之夜"));

        final VertexEntity director1 = saveDirector(new Director("wongjiawei", "王家卫", "1960", "王家卫：名导"));
        final VertexEntity director2 = saveDirector(new Director("wongjing", "王精", "1960", "三级片名导：王精"));

        // 创建边缘信息
        final EdgeEntity e1 = saveActorIn(new ActorIn("e1", "actor", "1995", "领衔主演", actor1.getId(), movie1.getId()));
        final EdgeEntity e2 = saveActorIn(new ActorIn("e2", "actor", "1995", "领衔主演", actor2.getId(), movie1.getId()));
        final EdgeEntity e3 = saveActorIn(new ActorIn("e3", "actor", "1995", "主演", actor3.getId(), movie1.getId()));
        final EdgeEntity e4 = saveActorIn(new ActorIn("e4", "actor", "1995", "主演", actor4.getId(), movie1.getId()));

        final EdgeEntity e5 = saveActorIn(new ActorIn("e5", "actor", "1995", "领衔主演", actor3.getId(), movie2.getId()));
        final EdgeEntity e6 = saveActorIn(new ActorIn("e6", "actor", "1995", "领衔主演", actor4.getId(), movie2.getId()));

        final EdgeEntity e7 = saveDirectorIn(new DirectorIn("e7", "director", "2000", "导演", director1.getId(), movie1.getId()));
        final EdgeEntity e8 = saveDirectorIn(new DirectorIn("e8", "director", "2005", "导演", director2.getId(), movie2.getId()));

        final EdgeEntity e9 = saveRelationship(new Relationship("e9",  actor1.getId(), director1.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e10 = saveRelationship(new Relationship("e10",  actor1.getId(), director2.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e11 = saveRelationship(new Relationship("e11",  actor1.getId(), actor2.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e12 = saveRelationship(new Relationship("e12",  actor3.getId(), actor4.getId(), "情人"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e13 = saveRelationship(new Relationship("e13",  director1.getId(), director2.getId(), "师傅"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e14 = saveRelationship(new Relationship("e14",  director2.getId(), actor3.getId(), "情人"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e15 = saveRelationship(new Relationship("e15",  director2.getId(), actor2.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e16 = saveRelationship(new Relationship("e16",  director2.getId(), actor1.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
        final EdgeEntity e17 = saveRelationship(new Relationship("e17",  director2.getId(), actor4.getId(), "朋友"), EDGE_COLLECTION_RELATIONSHIPS);
    }

    /**
     * 保存
     * @param edge
     * @return
     * @throws ArangoDBException
     */
    public static EdgeEntity saveActorIn(final ActorIn edge) throws ArangoDBException {
        return db.graph(GRAPH_NAME).edgeCollection(EDGE_COLLECTION_NAME).insertEdge(edge);
    }

    /**
     * 保存
     * @param edge
     * @return
     * @throws ArangoDBException
     */
    public static EdgeEntity saveDirectorIn(final DirectorIn edge) throws ArangoDBException {
        return db.graph(GRAPH_NAME).edgeCollection(EDGE_COLLECTION_DIRECTORS).insertEdge(edge);
    }

    /**
     * 保存人际关系
     * @param edge
     * @return
     * @throws ArangoDBException
     */
    public static EdgeEntity saveRelationship(final Relationship edge, final String collectionName) throws ArangoDBException {
        return db.graph(GRAPH_NAME).edgeCollection(collectionName).insertEdge(edge);
    }

    /**
     * 保存演员
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    public static VertexEntity saveActor(final Actor vertex) throws ArangoDBException {
        return db.graph(GRAPH_NAME).vertexCollection(VERTEXT_COLLECTION_ACTOR).insertVertex(vertex);
    }

    /**
     * 保存导演
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    public static VertexEntity saveDirector(final Director vertex) throws ArangoDBException {
        return db.graph(GRAPH_NAME).vertexCollection(VERTEXT_COLLECTION_DIRECTOR).insertVertex(vertex);
    }

    /**
     * 保存电影
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    public static VertexEntity saveMovie(final Movie vertex) throws ArangoDBException {
        return db.graph(GRAPH_NAME).vertexCollection(VERTEXT_COLLECTION_MOVIE).insertVertex(vertex);
    }

}
