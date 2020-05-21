package com.multicampus.teamProj4.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.multicampus.teamProj4.bank.account.repository.AccountRepository;
import com.multicampus.teamProj4.bank.login.repository.LoginRepository;
import com.multicampus.teamProj4.bank.user.reposiroty.UserRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = { AccountRepository.class, LoginRepository.class, UserRepository.class })
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.multicampus.teamProj4.bank.*" }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { AccountRepository.class, LoginRepository.class, UserRepository.class }))
public class SpringConfiguration {
}