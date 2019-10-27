package com.feng.datasource.multiple.dynamic.mybatis.yaml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class YamlTest {

    public static void main(String... args) throws Exception {

        String jsonStr = "{\"columnCount\":4,\"fields\":[{\"autoIncrement\":false,\"binary\":false,\"blob\":false,\"collationIndex\":63,\"columnLabel\":\"userId\",\"databaseName\":\"test\",\"decimals\":0,\"encoding\":\"ISO-8859-1\",\"flags\":20483,\"fromFunction\":false,\"fullName\":\"t_user.userId\",\"javaType\":4,\"length\":11,\"multipleKey\":false,\"mysqlType\":\"INT\",\"mysqlTypeId\":3,\"name\":\"userId\",\"notNull\":true,\"originalName\":\"userId\",\"originalTableName\":\"t_user\",\"primaryKey\":true,\"readOnly\":false,\"singleBit\":false,\"tableName\":\"t_user\",\"uniqueKey\":false,\"unsigned\":false,\"valueNeedsQuoting\":false,\"zeroFill\":false},{\"autoIncrement\":false,\"binary\":false,\"blob\":false,\"collationIndex\":255,\"columnLabel\":\"userName\",\"databaseName\":\"test\",\"decimals\":0,\"encoding\":\"UTF-8\",\"flags\":0,\"fromFunction\":false,\"fullName\":\"t_user.userName\",\"javaType\":12,\"length\":1020,\"multipleKey\":false,\"mysqlType\":\"VARCHAR\",\"mysqlTypeId\":253,\"name\":\"userName\",\"notNull\":false,\"originalName\":\"userName\",\"originalTableName\":\"t_user\",\"primaryKey\":false,\"readOnly\":false,\"singleBit\":false,\"tableName\":\"t_user\",\"uniqueKey\":false,\"unsigned\":false,\"valueNeedsQuoting\":true,\"zeroFill\":false},{\"autoIncrement\":false,\"binary\":false,\"blob\":false,\"collationIndex\":255,\"columnLabel\":\"password\",\"databaseName\":\"test\",\"decimals\":0,\"encoding\":\"UTF-8\",\"flags\":0,\"fromFunction\":false,\"fullName\":\"t_user.password\",\"javaType\":12,\"length\":1020,\"multipleKey\":false,\"mysqlType\":\"VARCHAR\",\"mysqlTypeId\":253,\"name\":\"password\",\"notNull\":false,\"originalName\":\"password\",\"originalTableName\":\"t_user\",\"primaryKey\":false,\"readOnly\":false,\"singleBit\":false,\"tableName\":\"t_user\",\"uniqueKey\":false,\"unsigned\":false,\"valueNeedsQuoting\":true,\"zeroFill\":false},{\"autoIncrement\":false,\"binary\":false,\"blob\":false,\"collationIndex\":255,\"columnLabel\":\"phone\",\"databaseName\":\"test\",\"decimals\":0,\"encoding\":\"UTF-8\",\"flags\":0,\"fromFunction\":false,\"fullName\":\"t_user.phone\",\"javaType\":12,\"length\":1020,\"multipleKey\":false,\"mysqlType\":\"VARCHAR\",\"mysqlTypeId\":253,\"name\":\"phone\",\"notNull\":false,\"originalName\":\"phone\",\"originalTableName\":\"t_user\",\"primaryKey\":false,\"readOnly\":false,\"singleBit\":false,\"tableName\":\"t_user\",\"uniqueKey\":false,\"unsigned\":false,\"valueNeedsQuoting\":true,\"zeroFill\":false}]}";

        //读取 JSON 字符串
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonStr);
        //转换成 YAML 字符串
        String yamlStr = new YAMLMapper().writeValueAsString(jsonNodeTree);

//        System.err.println("yamlStr : " + yamlStr);

        Yaml yaml = new Yaml();
        //将 JSON 字符串转成 Map
        Map<String, Object> map = (Map<String, Object>) yaml.load(yamlStr);
        //转换成 YAML 字符串
        yamlStr = yaml.dumpAsMap(map);
        System.err.println("yamlStr : " + yamlStr);

        // 使用流写入yaml文件
        FileWriter fileWriter = new FileWriter(ReadYaml.class.getClassLoader().getResource("").getPath()+"/config/test.yml");
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.write(yamlStr);

        // 收尾
        fileWriter.close();
        writer.close();
    }
}
