package com.feng.arangodb.base;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;

public class TestArangoDemo {

    public static void main(String... args) throws Exception {
        //配置和打开连接以启动ArangoDB
        ArangoDB arangoDB = new ArangoDB.Builder().host("127.0.0.1", 8529).user("root").password("root").build();

        try
        {
            //创建数据库
            String dbName = "arangdb-demo";
            arangoDB.createDatabase(dbName);

            //创建Collection集合
            String collectionName = "TestCollection";
            CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);

            //创建document文档
            BaseDocument myObject = new BaseDocument();
            myObject.setKey("myKey");
            myObject.addAttribute("name", "张三");
            myObject.addAttribute("age", 42);
            arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);

            //查询document文档
            BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("myKey", BaseDocument.class);
            System.out.println("Key: " + myDocument.getKey());
            System.out.println("Attribute name: " + myDocument.getAttribute("name"));
            System.out.println("Attribute age: " + myDocument.getAttribute("age"));

            //更新document文档
            myObject.addAttribute("sex", "男");
            arangoDB.db(dbName).collection(collectionName).updateDocument("myKey", myObject);

            //删除document文档
            //arangoDB.db(dbName).collection(collectionName).deleteDocument("myKey");

            //执行AQL语句
//					String query = "FOR t IN TestCollection FILTER t.name == @name "
//							+ "REMOVE t IN TestCollection LET removed = OLD RETURN removed";
//					Map<String, Object> bindVars = new MapBuilder().put("name", "张三").get();
//					ArangoCursor<BaseDocument> cursor = arangoDB.db(dbName).query(query, bindVars, null,BaseDocument.class);
//					cursor.forEachRemaining(aDocument -> {
//						System.out.println("Removed document " + aDocument.getKey());
//					});
            arangoDB.shutdown();
        }
        catch (ArangoDBException e)
        {
            e.printStackTrace();
        }
    }
}
