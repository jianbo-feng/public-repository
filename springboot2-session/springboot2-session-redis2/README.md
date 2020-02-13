# springboot-session-redis集成
## 说明
```
如果定义Bean：HeaderHttpSessionIdResolver，会优先HeaderHttpSessionIdResolver，然后才是CookieSerializer

@Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();

//        cookieSerializer.setCookieName("");
        cookieSerializer.setCookiePath("/");
//        cookieSerializer.setCookieMaxAge(3600*30);
//        cookieSerializer.setDomainName("localhost");
//        cookieSerializer.setDomainNamePattern("");
        cookieSerializer.setUseBase64Encoding(false);

        // 取消仅限同一站点设置
        cookieSerializer.setSameSite(null);
        return cookieSerializer;
    }

    /**
     * 使用请求头作为会话认证(即使用"authentication-info"作为Session Storage中的某一个key)
     * @return
     */
//    @Bean
//    public HeaderHttpSessionIdResolver setHeaderHttpSessionIdResolver() {
//        return HeaderHttpSessionIdResolver.authenticationInfo();
//    }


```
