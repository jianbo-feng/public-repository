package com.feng.session.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableRedisRepositories(basePackages = "com.feng.session.redis.repository")
@EnableCaching
@EnableRedisHttpSession
@SpringBootApplication
public class SessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionRedisApplication.class, args);
    }

    @Value("${project.domain}")
    private String domain;

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();

        domain = domain == null ? "" : domain.trim();
        if (!"".equals(domain)) {
            cookieSerializer.setDomainName(domain);
        }

//        cookieSerializer.setCookieName("");
        cookieSerializer.setCookiePath("/");
//        cookieSerializer.setCookieMaxAge(3600*30);
//        cookieSerializer.setDomainName("localhost");
//        cookieSerializer.setDomainNamePattern("");
        cookieSerializer.setUseBase64Encoding(false);
        cookieSerializer.setUseHttpOnlyCookie(false);   // 需要设置，否则跨域或子域会有问题

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
}
