package com.feng.elasticsearch.jdbc;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.Token;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;

public class TestNlpcnQuery {

    public static void main(String... args) throws Exception {



    }

    /**
     * 验证SQL
     * @param sql SQL查询语句
     * @return and ( a=1 and b=1) or (c=1 and d=1)
     */
    static SQLExpr toSQLExpr(String sql) {
        SQLExprParser parser = new ElasticSqlExprParser(sql);
        SQLExpr expr = parser.expr();

        if (parser.getLexer().token() != Token.EOF) {
            throw new ParserException("illegal sql expr : " + sql);
        }
        return expr;
    }
}
