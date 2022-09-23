package com.example.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.VaultCredentials;

@Configuration
public class VaultConfig {

	@Autowired
	private VaultCredentials vaultCredentials;

	private Logger logger = LoggerFactory.getLogger(VaultConfig.class);

	@Bean(name = "mySqlDataSource")
	@Primary
	public DataSource getMySqlDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/dbtest");
		logger.info("dbusername in vault config: " + vaultCredentials.getDbusername());
		dataSourceBuilder.username(vaultCredentials.getDbusername());
		dataSourceBuilder.password(vaultCredentials.getDbpassword());
		return dataSourceBuilder.build();
	}

}
