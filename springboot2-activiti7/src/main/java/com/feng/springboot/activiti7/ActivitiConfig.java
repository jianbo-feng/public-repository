package com.feng.springboot.activiti7;

import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserGroupManager userGroupManager;

    private int corePoolSize = 10;
    private int maxPoolSize = 30;
    private int keepAliveSeconds = 300;
    private int queueCapacity = 300;

    /**
     * 处理引擎配置
     * @param transactionManager
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager) {

        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        SpringAsyncExecutor asyncExecutor = new SpringAsyncExecutor();
        asyncExecutor.setTaskExecutor(workFlowAsync());
        configuration.setAsyncExecutor(asyncExecutor);

        configuration.setDatabaseSchemaUpdate("true");
        configuration.setUserGroupManager(userGroupManager);
        configuration.setHistoryLevel(HistoryLevel.FULL);
        configuration.setDbHistoryUsed(true);
        return configuration;
    }

    /**
     * 线程池
     *
     * @return
     */
    @Primary
    @Bean("workFlowTaskExecutor")
    public ThreadPoolTaskExecutor workFlowAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("workFlowTaskExecutor-");
        executor.initialize();
        return executor;
    }
}
