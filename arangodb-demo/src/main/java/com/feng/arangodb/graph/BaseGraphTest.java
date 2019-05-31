package com.feng.arangodb.graph;

import java.util.ArrayList;
import java.util.Collection;

import com.arangodb.internal.ArangoDBConstants;
import com.feng.arangodb.base.ArangoDbConfig;
import com.feng.arangodb.entity.Actor;
import com.feng.arangodb.entity.ActorIn;
import com.feng.arangodb.entity.Movie;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.entity.EdgeEntity;
import com.arangodb.entity.VertexEntity;

/**
 * 图数据测试
 */
public abstract class BaseGraphTest {

    protected static final String TEST_DB = "java_driver_graph_test_db";
    protected static final String TEST_DB2 = "java_driver_graph_test_db2";
    protected static ArangoDB arangoDB;
    protected static ArangoDatabase db, db2;
    protected static final String GRAPH_NAME = "traversalGraph";
    protected static final String EDGE_COLLECTION_NAME = "edges";
    protected static final String VERTEXT_COLLECTION_NAME = "circles";

    protected static final String GRAPH_NAME2 = "actor-movie";
    protected static final String EDGE_COLLECTION_NAME2 = "actorIns";
    protected static final String VERTEXT_COLLECTION_NAME2 = "actors";
    protected static final String VERTEXT_COLLECTION_NAME3 = "movies";

    @BeforeClass
    public static void init() {
        if (arangoDB == null) {
            arangoDB = new ArangoDB.Builder()
                    .host(ArangoDBConstants.DEFAULT_HOST, ArangoDBConstants.DEFAULT_PORT)
                    .user(ArangoDBConstants.DEFAULT_USER)
                    .password(ArangoDbConfig.password)
                    .build();
        }
        try {
            arangoDB.db(TEST_DB).drop();
            arangoDB.db(TEST_DB2).drop();
        } catch (final ArangoDBException e) {
        }
        arangoDB.createDatabase(TEST_DB);
        arangoDB.createDatabase(TEST_DB2);
        db = arangoDB.db(TEST_DB);
        db2 = arangoDB.db(TEST_DB2);

        final Collection<EdgeDefinition> edgeDefinitions = new ArrayList<>();
        final EdgeDefinition edgeDefinition = new EdgeDefinition().collection(EDGE_COLLECTION_NAME)
                .from(VERTEXT_COLLECTION_NAME).to(VERTEXT_COLLECTION_NAME);
        edgeDefinitions.add(edgeDefinition);
        try {
            db.createGraph(GRAPH_NAME, edgeDefinitions, null);
            addExampleElements();
        } catch (final ArangoDBException ex) {

        }

        final Collection<EdgeDefinition> edgeDefinitions2 = new ArrayList<>();
        final EdgeDefinition edgeDefinition2 = new EdgeDefinition().collection(EDGE_COLLECTION_NAME2)
                .from(VERTEXT_COLLECTION_NAME2).to(VERTEXT_COLLECTION_NAME3);
        edgeDefinitions2.add(edgeDefinition2);
        try {
            db2.createGraph(GRAPH_NAME2, edgeDefinitions2, null);
            addTestData();
        } catch (final ArangoDBException ex) {

        }
    }

    @AfterClass
    public static void shutdown() {
//        arangoDB.db(TEST_DB).drop();
        arangoDB.shutdown();
        arangoDB = null;
    }

    public static void addTestData() throws ArangoDBException {

        // 创建顶点信息
        final VertexEntity lilianjie = saveActor(new Actor("lilianjie", "李连杰", "1970", "李连杰：功夫皇帝"));
        final VertexEntity chenglong = saveActor(new Actor("chenglong", "成龙", "1960", "成龙：拼命三郎"));
        final VertexEntity fanbingbing = saveActor(new Actor("fanbingbing", "饭冰冰", "1980", "饭冰冰：睡兵连"));
        final VertexEntity hongjinbao = saveActor(new Actor("hongjinbao", "洪金宝", "1960", "洪金宝：睡兵连长"));

        final VertexEntity m1 = saveMovie(new Movie("m1",  "功夫之王",  "1995",  "1995",  "功夫之王"));
        final VertexEntity m2 = saveMovie(new Movie("m2",  "不眠之夜",  "1995",  "1995",  "不眠之夜"));
        // 创建边缘信息
        final EdgeEntity e1 = saveActorIn(new ActorIn("e1", "领衔主演", "1995", "领衔主演", lilianjie.getId(), m1.getId()));
        final EdgeEntity e2 = saveActorIn(new ActorIn("e2", "领衔主演", "1995", "领衔主演", chenglong.getId(), m1.getId()));
        final EdgeEntity e3 = saveActorIn(new ActorIn("e3", "主演", "1995", "主演", fanbingbing.getId(), m1.getId()));
        final EdgeEntity e4 = saveActorIn(new ActorIn("e4", "主演", "1995", "主演", hongjinbao.getId(), m1.getId()));

        final EdgeEntity e5 = saveActorIn(new ActorIn("e5", "领衔主演", "1995", "领衔主演", hongjinbao.getId(), m2.getId()));
        final EdgeEntity e6 = saveActorIn(new ActorIn("e6", "领衔主演", "1995", "领衔主演", fanbingbing.getId(), m2.getId()));

    }

    private static void addExampleElements() throws ArangoDBException {

        // Add circle circles
        final VertexEntity vA = createVertex(new Circle("A", "1"));
        final VertexEntity vB = createVertex(new Circle("B", "2"));
        final VertexEntity vC = createVertex(new Circle("C", "3"));
        final VertexEntity vD = createVertex(new Circle("D", "4"));
        final VertexEntity vE = createVertex(new Circle("E", "5"));
        final VertexEntity vF = createVertex(new Circle("F", "6"));
        final VertexEntity vG = createVertex(new Circle("G", "7"));
        final VertexEntity vH = createVertex(new Circle("H", "8"));
        final VertexEntity vI = createVertex(new Circle("I", "9"));
        final VertexEntity vJ = createVertex(new Circle("J", "10"));
        final VertexEntity vK = createVertex(new Circle("K", "11"));

        // Add relevant edges - left branch:
        saveEdge(new CircleEdge(vA.getId(), vB.getId(), false, true, "left_bar"));
        saveEdge(new CircleEdge(vB.getId(), vC.getId(), false, true, "left_blarg"));
        saveEdge(new CircleEdge(vC.getId(), vD.getId(), false, true, "left_blorg"));
        saveEdge(new CircleEdge(vB.getId(), vE.getId(), false, true, "left_blub"));
        saveEdge(new CircleEdge(vE.getId(), vF.getId(), false, true, "left_schubi"));

        // Add relevant edges - right branch:
        saveEdge(new CircleEdge(vA.getId(), vG.getId(), false, true, "right_foo"));
        saveEdge(new CircleEdge(vG.getId(), vH.getId(), false, true, "right_blob"));
        saveEdge(new CircleEdge(vH.getId(), vI.getId(), false, true, "right_blub"));
        saveEdge(new CircleEdge(vG.getId(), vJ.getId(), false, true, "right_zip"));
        saveEdge(new CircleEdge(vJ.getId(), vK.getId(), false, true, "right_zup"));
    }

    /**
     * 创建边缘信息
     * @param edge
     * @return
     * @throws ArangoDBException
     */
    private static EdgeEntity saveEdge(final CircleEdge edge) throws ArangoDBException {
        return db.graph(GRAPH_NAME).edgeCollection(EDGE_COLLECTION_NAME).insertEdge(edge);
    }

    /**
     * 创建顶点信息
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    private static VertexEntity createVertex(final Circle vertex) throws ArangoDBException {
        return db.graph(GRAPH_NAME).vertexCollection(VERTEXT_COLLECTION_NAME).insertVertex(vertex);
    }

    /**
     * 保存
     * @param edge
     * @return
     * @throws ArangoDBException
     */
    public static EdgeEntity saveActorIn(final ActorIn edge) throws ArangoDBException {
        return db2.graph(GRAPH_NAME2).edgeCollection(EDGE_COLLECTION_NAME2).insertEdge(edge);
    }

    /**
     * 保存演员
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    public static VertexEntity saveActor(final Actor vertex) throws ArangoDBException {
        return db2.graph(GRAPH_NAME2).vertexCollection(VERTEXT_COLLECTION_NAME2).insertVertex(vertex);
    }

    /**
     * 保存电影
     * @param vertex
     * @return
     * @throws ArangoDBException
     */
    public static VertexEntity saveMovie(final Movie vertex) throws ArangoDBException {
        return db2.graph(GRAPH_NAME2).vertexCollection(VERTEXT_COLLECTION_NAME3).insertVertex(vertex);
    }
}
