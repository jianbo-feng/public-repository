package com.feng.arangodb.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;

/**
 * 使用AQL进行图查询
 */
public class GraphTraversalsInAQLExample extends BaseGraphTest {

    @Test
    public void queryAllVertices() throws ArangoDBException {
        String queryString = "FOR v IN 1..3 OUTBOUND 'circles/A' GRAPH 'traversalGraph' RETURN v._key";
        ArangoCursor<String> cursor = db.query(queryString, null, null, String.class);
        Collection<String> result = cursor.asListRemaining();
        assertThat(result.size(), is(10));

        queryString = "WITH circles FOR v IN 1..3 OUTBOUND 'circles/A' edges RETURN v._key";
        cursor = db.query(queryString, null, null, String.class);
        result = cursor.asListRemaining();
        assertThat(result.size(), is(10));
    }

    @Test
    public void queryDepthTwo() throws ArangoDBException {
        String queryString = "FOR v IN 2..2 OUTBOUND 'circles/A' GRAPH 'traversalGraph' return v._key";
        ArangoCursor<String> cursor = db.query(queryString, null, null, String.class);
        Collection<String> result = cursor.asListRemaining();
        assertThat(result.size(), is(4));
//        assertThat(result, hasItems("C", "E", "H", "J"));

        queryString = "FOR v IN 2 OUTBOUND 'circles/A' GRAPH 'traversalGraph' return v._key";
        cursor = db.query(queryString, null, null, String.class);
        result = cursor.asListRemaining();
        assertThat(result.size(), is(4));
//        assertThat(result, hasItems("C", "E", "H", "J"));
    }

    @Test
    public void queryWithFilter() throws ArangoDBException {
        String queryString = "FOR v, e, p IN 1..3 OUTBOUND 'circles/A' GRAPH 'traversalGraph' FILTER p.vertices[1]._key != 'G' RETURN v._key";
        ArangoCursor<String> cursor = db.query(queryString, null, null, String.class);
        Collection<String> result = cursor.asListRemaining();
        assertThat(result.size(), is(5));
//        assertThat(result, hasItems("B", "C", "D", "E", "F"));

        queryString = "FOR v, e, p IN 1..3 OUTBOUND 'circles/A' GRAPH 'traversalGraph' FILTER p.edges[0].label != 'right_foo' RETURN v._key";
        cursor = db.query(queryString, null, null, String.class);
        result = cursor.asListRemaining();
        assertThat(result.size(), is(5));
//        assertThat(result, hasItems("B", "C", "D", "E", "F"));

        queryString = "FOR v,e,p IN 1..3 OUTBOUND 'circles/A' GRAPH 'traversalGraph' FILTER p.vertices[1]._key != 'G' FILTER p.edges[1].label != 'left_blub' return v._key";
        cursor = db.query(queryString, null, null, String.class);

        result = cursor.asListRemaining();
        assertThat(result.size(), is(3));
//        assertThat(result, hasItems("B", "C", "D"));

        queryString = "FOR v,e,p IN 1..3 OUTBOUND 'circles/A' GRAPH 'traversalGraph' FILTER p.vertices[1]._key != 'G' AND    p.edges[1].label != 'left_blub' return v._key";
        cursor = db.query(queryString, null, null, String.class);
        result = cursor.asListRemaining();
        assertThat(result.size(), is(3));
//        assertThat(result, hasItems("B", "C", "D"));
    }

    @Test
    public void queryOutboundInbound() throws ArangoDBException {
        String queryString = "FOR v IN 1..3 OUTBOUND 'circles/E' GRAPH 'traversalGraph' return v._key";
        ArangoCursor<String> cursor = db.query(queryString, null, null, String.class);
        Collection<String> result = cursor.asListRemaining();
        assertThat(result.size(), is(1));
//        assertThat(result, hasItems("F"));

        queryString = "FOR v IN 1..3 INBOUND 'circles/E' GRAPH 'traversalGraph' return v._key";
        cursor = db.query(queryString, null, null, String.class);
        result = cursor.asListRemaining();
        assertThat(result.size(), is(2));
//        assertThat(result, hasItems("B", "A"));

        queryString = "FOR v IN 1..3 ANY 'circles/E' GRAPH 'traversalGraph' return v._key";
        cursor = db.query(queryString, null, null, String.class);

        result = cursor.asListRemaining();
        assertThat(result.size(), is(6));
//        assertThat(result, hasItems("F", "B", "C", "D", "A", "G"));
    }

}
