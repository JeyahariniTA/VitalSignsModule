package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("example")
public class VaultCredentials {

	String dbusername;
	String dbpassword;

	public String getDbusername() {
		return dbusername;
	}

	public void setDbusername(String dbusername) {
		this.dbusername = dbusername;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

}
