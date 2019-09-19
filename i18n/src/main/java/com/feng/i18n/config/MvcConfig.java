package com.feng.i18n.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private Log log = LogFactory.getLog(getClass());

    @Bean
    public CookieLocaleResolver localeResolver() {
        log.info("create Cookie Locale Resolver.");
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("locale");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("create Locale Change Interceptor.");

        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");

        registry.addInterceptor(interceptor);
    }
}
