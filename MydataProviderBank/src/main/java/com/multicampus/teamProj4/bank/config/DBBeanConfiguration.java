package com.multicampus.teamProj4.bank.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.multicampus.teamProj4.bank.login.entity.LoginEntity;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:Properties/dbUser.properties")
public class DBBeanConfiguration {
	
	@Bean
	public DataSource datasource(@Value("${dbuser}")String user, @Value("${password}")String password, @Value("${url}")String url) {
		HikariDataSource datasource = new HikariDataSource();
		datasource.setUsername(user);
		datasource.setPassword(password);
		datasource.setJdbcUrl(url);
		datasource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
		datasource.setMaximumPoolSize(10);
		datasource.setMaxLifetime(30000);
		
		return datasource;
	}
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setAnnotatedClasses(LoginEntity.class);
		
		return localSessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager traHibernateTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}	
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT,MySQL5Dialect.class.getName());
		properties.put(AvailableSettings.SHOW_SQL,String.valueOf(true));
		properties.put(AvailableSettings.HBM2DDL_AUTO,"update");
		
		return properties;
	}
}
