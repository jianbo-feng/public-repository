package com.feng.arangodb.graph;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.Protocol;
import com.arangodb.internal.ArangoDBConstants;
import com.feng.arangodb.base.ArangoDbConfig;
import org.junit.AfterClass;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized.Parameters;

public class BaseTest {

    @Parameters
    public static Collection<ArangoDB.Builder> builders() {
        return Arrays.asList(//
                new ArangoDB.Builder().useProtocol(Protocol.VST), //
                new ArangoDB.Builder().useProtocol(Protocol.HTTP_JSON), //
                new ArangoDB.Builder().useProtocol(Protocol.HTTP_VPACK) //
        );
    }

    protected static final String TEST_DB = "java_driver_test_db";
    protected static ArangoDB arangoDB;
    protected static ArangoDatabase db;

    public BaseTest(final ArangoDB.Builder builder) {
        super();
        if (arangoDB != null) {
            shutdown();
        }
        arangoDB = builder
                .host(ArangoDBConstants.DEFAULT_HOST, ArangoDBConstants.DEFAULT_PORT)
                .user(ArangoDBConstants.DEFAULT_USER)
                .password(ArangoDbConfig.password)
                .build();
        try {
            arangoDB.db(TEST_DB).drop();
        } catch (final ArangoDBException e) {
        }
        arangoDB.createDatabase(TEST_DB);
        db = arangoDB.db(TEST_DB);
    }

    @AfterClass
    public static void shutdown() {
//        arangoDB.db(TEST_DB).drop();
        arangoDB.shutdown();
        arangoDB = null;
    }

    protected boolean requireVersion(final int major, final int minor) {
        final String[] split = arangoDB.getVersion().getVersion().split("\\.");
        return Integer.valueOf(split[0]) >= major && Integer.valueOf(split[1]) >= minor;
    }
}
