package com.fgt.walletsystem.configurations.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "databaseAuditorAwareImplementation")
public class DatabaseAuditConfig {
}
