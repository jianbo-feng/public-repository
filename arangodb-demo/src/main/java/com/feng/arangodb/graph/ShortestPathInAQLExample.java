package com.feng.arangodb.graph;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;

public class ShortestPathInAQLExample extends BaseGraphTest {

    public static class Pair {

        private String vertex;
        private String edge;

        public String getVertex() {
            return vertex;
        }

        public void setVertex(final String vertex) {
            this.vertex = vertex;
        }

        public String getEdge() {
            return edge;
        }

        public void setEdge(final String edge) {
            this.edge = edge;
        }

    }

    @Test
    public void queryShortestPathFromAToD() throws ArangoDBException {
        String queryString = "FOR v, e IN OUTBOUND SHORTEST_PATH 'circles/A' TO 'circles/D' GRAPH 'traversalGraph' RETURN {'vertex': v._key, 'edge': e._key}";
        ArangoCursor<Pair> cursor = db.query(queryString, null, null, Pair.class);
        final Collection<String> collection = toVertexCollection(cursor);
        assertThat(collection.size(), is(4));
//        assertThat(collection, hasItems("A", "B", "C", "D"));

        queryString = "WITH circles FOR v, e IN OUTBOUND SHORTEST_PATH 'circles/A' TO 'circles/D' edges RETURN {'vertex': v._key, 'edge': e._key}";
        cursor = db.query(queryString, null, null, Pair.class);
        assertThat(collection.size(), is(4));
//        assertThat(collection, hasItems("A", "B", "C", "D"));
    }

    @Test
    public void queryShortestPathByFilter() throws ArangoDBException {
        String queryString = "FOR a IN circles FILTER a._key == 'A' FOR d IN circles FILTER d._key == 'D' FOR v, e IN OUTBOUND SHORTEST_PATH a TO d GRAPH 'traversalGraph' RETURN {'vertex':v._key, 'edge':e._key}";
        ArangoCursor<Pair> cursor = db.query(queryString, null, null, Pair.class);
        final Collection<String> collection = toVertexCollection(cursor);
        assertThat(collection.size(), is(4));
//        assertThat(collection, hasItems("A", "B", "C", "D"));

        queryString = "FOR a IN circles FILTER a._key == 'A' FOR d IN circles FILTER d._key == 'D' FOR v, e IN OUTBOUND SHORTEST_PATH a TO d edges RETURN {'vertex': v._key, 'edge': e._key}";
        cursor = db.query(queryString, null, null, Pair.class);
        assertThat(collection.size(), is(4));
//        assertThat(collection, hasItems("A", "B", "C", "D"));
    }

    protected Collection<String> toVertexCollection(final ArangoCursor<Pair> cursor) {
        final List<String> result = new ArrayList<String>();
        for (; cursor.hasNext();) {
            final Pair pair = cursor.next();
            result.add(pair.getVertex());
        }
        return result;
    }
}
