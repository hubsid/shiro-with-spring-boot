package com.sidh.practice.springboot.shiroSample;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class ShiroWithSpringBootApplication {

	@Bean
	public Realm realm() {
		JdbcRealm jdbcRealm = new JdbcRealm();
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUser("sa");
		dataSource.setPassword("");
		jdbcRealm.setDataSource(dataSource);
		return jdbcRealm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
		defaultShiroFilterChainDefinition.addPathDefinition("/**", "anon");
		return defaultShiroFilterChainDefinition;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShiroWithSpringBootApplication.class, args);
	}
}
