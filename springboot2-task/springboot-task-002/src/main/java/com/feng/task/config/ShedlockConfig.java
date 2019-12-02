package com.feng.task.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
//import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
//import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedlockConfig {

    /**
     * JDBC Template
     * @param dataSource
     * @return
     */
    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
//        return new JdbcTemplateLockProvider(dataSource);
        return new JdbcTemplateLockProvider(dataSource, "public.shedlock");
    }

    /*@Bean
    public ScheduledLockConfiguration scheduledLockConfiguration(LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(10)
                .withDefaultLockAtMostFor(Duration.ofMinutes(10))
                .build();
    }*/
}
