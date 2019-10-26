package com.feng.datasource.multiple.dynamic.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql主库配置类
 * @日期： 2018年7月5日 下午10:05:25
 * @作者： Chendb
 */
@Configuration
@MapperScan(basePackages = "com.feng.datasource.multiple.dynamic.repository.cluster", sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {

	@Value("${mybatis.cluster.mapper-locations}")
	private String mapperLocations;

	@Value("${mybatis.cluster.type-aliases-package}")
	private String typeAliasesPackage;



	/**
	 * 创建数据源
	 *@return DataSource
	 */
	@Bean(name = "clusterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.cluster")
	public DataSource productDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 创建工厂
	 *@param dataSource
	 *@throws Exception
	 *@return SqlSessionFactory
	 */
	@Bean(name = "clusterSqlSessionFactory")
	public SqlSessionFactory productSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
//		bean.setTypeAliasesPackage("com.feng.datasource.multiple.dynamic.entity");
		bean.setTypeAliasesPackage(typeAliasesPackage);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/cluster/*.xml"));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return bean.getObject();

	}

	/**
	 * 创建事务
	 *@param dataSource
	 *@return DataSourceTransactionManager
	 */
	@Bean(name = "clusterTransactionManager")
	public DataSourceTransactionManager productDataSourceTransactionManager(@Qualifier("clusterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 创建模板
	 *@param sqlSessionFactory
	 *@return SqlSessionTemplate
	 */
	@Bean(name = "clusterSqlSessionTemplate")
	public SqlSessionTemplate productSqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
