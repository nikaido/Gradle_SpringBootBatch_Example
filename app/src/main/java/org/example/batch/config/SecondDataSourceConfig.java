package org.example.batch.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "org.example.batch.mapper", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {
  @Bean(name = "secondDataSource")
  @ConfigurationProperties(prefix = "second.datasource")
  public DataSource batchDataSource() {
      return DataSourceBuilder.create().build();
  }
  
  @Bean(name = "secondSqlSessionFactory")
  public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
      SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
      factoryBean.setDataSource(dataSource);
      factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
          .getResources("classpath:/org/example/batch/mapper/*.xml"));
      return factoryBean.getObject();
  }

}
