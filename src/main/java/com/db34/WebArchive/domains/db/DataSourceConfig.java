package com.db34.WebArchive.domains.db;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

// Source: https://www.baeldung.com/spring-boot-configure-data-source-programmatic
public class DataSourceConfig {
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
// All properties not overwritten here are defined in application.properties		

// to be defined here:
//		spring.datasource.url=jdbc:h2:tcp://172.17.0.6:1521/webarchive
//		spring.datasource.username=scott
//		spring.datasource.password=T1g3rH2$Docker		
        System.out.println("created DataSourceConfig");
		dataSourceBuilder.url("jdbc:h2:mem:test");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
}
