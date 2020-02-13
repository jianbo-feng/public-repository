package com.feng.datasource.multiple.dynamic.mybatis.yaml;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.feng.datasource.multiple.dynamic.mybatis.exception.MyException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.UUID;

/**
 * 读取Yaml
 */
public class ReadYaml {

    public static void main(String... args) {
        Yaml yaml = new Yaml();
        Iterable<Object> iterable = yaml.loadAll(ReadYaml.class.getClassLoader().getResourceAsStream("application.yml"));
        for (Object o : iterable) {
            System.err.println(o);
        }


        yamlWrite();

        yamlRead();
    }

    public static void yamlWrite() {
        try {
            TestBean testBean = new TestBean();
            testBean.setUid(UUID.randomUUID().toString());
            testBean.setName("测试源");
            testBean.setAge(111);
            testBean.setSex("man");

            Yaml yaml = new Yaml();
            String dumpAsMap = yaml.dumpAsMap(testBean);
            // 使用流写入yaml文件
            FileWriter fileWriter = new FileWriter(ReadYaml.class.getClassLoader().getResource("").getPath()+"/config/test.yml");
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.write(dumpAsMap);

            // 收尾
            fileWriter.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void yamlRead() {
        try {
            Yaml yaml = new Yaml();
            File file = new File(ReadYaml.class.getClassLoader().getResource("").getPath()+"/config/test.yml");
            String jsonStr = JSON.toJSONString(yaml.load(new FileInputStream(file)));
            System.err.println("jsonStr => " + jsonStr);
            TestBean user = JSON.parseObject(jsonStr, TestBean.class);
            System.out.println(user);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
