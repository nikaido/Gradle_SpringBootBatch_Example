package org.example.batch.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BatchDataSourceConfig {
  @Bean(name = "batchDataSource")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource batchDataSource() {
      return DataSourceBuilder.create().build();
  }
}
