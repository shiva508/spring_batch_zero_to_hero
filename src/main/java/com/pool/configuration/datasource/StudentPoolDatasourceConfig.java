package com.pool.configuration.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StudentPoolDatasourceConfig {
	@Primary
	@Bean(name = "dataSourcebd")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSourceShiva")
	@ConfigurationProperties(prefix = "spring.shivadatasource")
	public DataSource dataSourceShiva() {
		return DataSourceBuilder.create().build();
	}
}
