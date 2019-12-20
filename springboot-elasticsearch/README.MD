# Springboot-elasticsearch 
## 访问链接
```
http://localhost:9996/es/doc/search?type=smart&key=%E4%B8%89
```
## 特别说明
```
JavaBean中的字段设置
// 指派ID(使用scriptQuery时需要设置fielddata，因为需要把该字段取出来在内存中做比较；正因为需要取出来在内存中比较，所以效率会降低)
    // FieldType.Text
    @Field(type = FieldType.Text, store = true, fielddata = true)
    private String assignedRoleId;
    
核心代码业务逻辑实现
// 获取assignedRoleId为null(或"")
        Script script = new Script("doc['assignedRoleId'].length == 0");
        ScriptQueryBuilder scriptQueryBuilder = QueryBuilders.scriptQuery(script);

        // 获取assignedRoleId为null(或"")，或者assignedRoleId中包含"user3"的指派角色id信息
        ScriptQueryBuilder scriptQueryBuilder2 = QueryBuilders.scriptQuery(new Script(
                "doc['assignedRoleId'].length == 0 || doc['assignedRoleId'].indexOf('user3') >= 0"));
        BoolQueryBuilder totalFilter = QueryBuilders.boolQuery()
                .filter(termQuery("type", type))        // 必须匹配类型
                // 满足一个指派角色
//                .filter(matchPhraseQuery("assignedRoleId", assignedRoleId))
//                .filter(boolQuery() // 任意满足其中一个指派角色即可
//                    .should(matchPhraseQuery("assignedRoleId", QueryParser.escape("Admin_1234567".toLowerCase(Locale.CHINESE))))
//                        .should(matchPhraseQuery("assignedRoleId", QueryParser.escape("user3".toLowerCase(Locale.CHINESE))))
//                )

                // 测试assignedRoleId==''或为null的情况
//                .filter(scriptQueryBuilder)
                // 测试使用scriptQuery：获取assignedRoleId为null(或"")，或者assignedRoleId中包含"user3"的指派角色id信息
                .filter(scriptQueryBuilder2)

//                .filter(termQuery("assignedRoleId", "user2")) // 不支持Admin-1234567 或 user2;user3
//                .filter(regexpQuery("assignedRoleId", assignedRoleId+"[;]?")) // 不支持Admin-1234567
//                .filter(matchPhraseQuery("assignedRoleId", assignedRoleId)) // 使用短语搜索，不对关键字(例如Admin-1234567)进行分词
//                    .filter(wildcardQuery("assignedRoleId", "*" + QueryParser.escape(assignedRoleId.toLowerCase()) + "*")) // 不支持Admin-1234567
//                .filter(regexpQuery("roleId", "user2[;]?")) // 不支持Admin-1234567
                .filter(rangeQuery("date").from(startDate.getTime()).to(endDate.getTime())); // 必须匹配时间范围
                //.filter(termsQuery("roleId", "admin", "user2"));        // 必须满足roleId in ('admin', 'user2')
                //.filter(termsQuery("assignedRoleId", "admin", "user2;"));
        key = QueryParser.escape(StringUtil.trim(key)).toLowerCase(Locale.CHINESE);

```
