package com.robotstore;


import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "entityManagerFactory",
	basePackages = { "com.robotstore.repo" }
)
public class RobotstoreDBConfig {
  
@Autowired
private Environment env;
	
	   @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(env.getProperty("robotstore.datasource.driver-class-name"));
	      dataSource.setUrl(env.getProperty("robotstore.datasource.url"));
	      dataSource.setUsername(env.getProperty("robotstore.datasource.username"));
	      dataSource.setPassword(env.getProperty("robotstore.datasource.password"));
	      return dataSource;
	   }
	 	
	
//	@Primary
//	@Bean(name = "dataSource")
//	@ConfigurationProperties(prefix = "robotstore.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
  
	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.robotstore.model" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	   Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	       return properties;
	   }	 
	   @Bean
	   public PlatformTransactionManager transactionManager(
	     EntityManagerFactory emf){
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(emf);
	       return transactionManager;
	   }	
	
}