package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class Config {

	@Value("${spring.cloud.config.server.git.uri}")
	private String springCloudConfigServerGitUri;

	@Value("${spring.cloud.config.server.git.cloneOnStart}")
	private boolean springCloudConfigServerGitCloneOnStart;

	@Value("${management.security.enabled}")
	private boolean managementSecurityEnabled;

	@Value("$spring.cloud.vault.token}")
	private String springCloudVaultToken;

	@Value("${spring.cloud.vault.scheme}")
	private String springCloudVaultScheme;

	@Value("${spring.cloud.vault.kv.enabled}")
	private boolean springCloudVaultKvEnabled;

	@Value("${spring.application.name}")
	private String springApplicationName;

	@Value("${spring.cloud.vault.port}")
	private String springApplicationPort;

	@Value("${spring.cloud.vault.host}")
	private String springApplicationHost;

	@Value("${spring.config.import}")
	private String springConfigImport;

	@Value("${springdoc.api-docs.path}")
	private String springDocApiDocsPath;

	public String getSpringDocApiDocsPath() {
		return springDocApiDocsPath;
	}

	public void setSpringDocApiDocsPath(String springDocApiDocsPath) {
		this.springDocApiDocsPath = springDocApiDocsPath;
	}

	public String getSpringConfigImport() {
		return springConfigImport;
	}

	public void setSpringConfigImport(String springConfigImport) {
		this.springConfigImport = springConfigImport;
	}

	public String getSpringApplicationPort() {
		return springApplicationPort;
	}

	public void setSpringApplicationPort(String springApplicationPort) {
		this.springApplicationPort = springApplicationPort;
	}

	public String getSpringApplicationHost() {
		return springApplicationHost;
	}

	public void setSpringApplicationHost(String springApplicationHost) {
		this.springApplicationHost = springApplicationHost;
	}

	public String getSpringCloudVaultToken() {
		return springCloudVaultToken;
	}

	public void setSpringCloudVaultToken(String springCloudVaultToken) {
		this.springCloudVaultToken = springCloudVaultToken;
	}

	public String getSpringCloudVaultScheme() {
		return springCloudVaultScheme;
	}

	public void setSpringCloudVaultScheme(String springCloudVaultScheme) {
		this.springCloudVaultScheme = springCloudVaultScheme;
	}

	public boolean isSpringCloudVaultKvEnabled() {
		return springCloudVaultKvEnabled;
	}

	public void setSpringCloudVaultKvEnabled(boolean springCloudVaultKvEnabled) {
		this.springCloudVaultKvEnabled = springCloudVaultKvEnabled;
	}

	public String getSpringCloudConfigServerGitUri() {
		return springCloudConfigServerGitUri;
	}

	public void setSpringCloudConfigServerGitUri(String springCloudConfigServerGitUri) {
		this.springCloudConfigServerGitUri = springCloudConfigServerGitUri;
	}

	public boolean isSpringCloudConfigServerGitCloneOnStart() {
		return springCloudConfigServerGitCloneOnStart;
	}

	public void setSpringCloudConfigServerGitCloneOnStart(boolean springCloudConfigServerGitCloneOnStart) {
		this.springCloudConfigServerGitCloneOnStart = springCloudConfigServerGitCloneOnStart;
	}

	public boolean isManagementSecurityEnabled() {
		return managementSecurityEnabled;
	}

	public void setManagementSecurityEnabled(boolean managementSecurityEnabled) {
		this.managementSecurityEnabled = managementSecurityEnabled;
	}

	public String getSpringApplicationName() {
		return springApplicationName;
	}

	public void setSpringApplicationName(String springApplicationName) {
		this.springApplicationName = springApplicationName;
	}

}
