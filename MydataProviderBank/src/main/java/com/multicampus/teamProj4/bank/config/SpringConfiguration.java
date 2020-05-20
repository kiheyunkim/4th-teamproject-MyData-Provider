package com.multicampus.teamProj4.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.multicampus.teamProj4.bank.account.Repository.AccountRepository;

@Configuration
@EnableJpaRepositories(basePackages = {"com.multicampus.teamProj4.bank.*"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.multicampus.teamProj4.bank.*"})
public class SpringConfiguration {
}