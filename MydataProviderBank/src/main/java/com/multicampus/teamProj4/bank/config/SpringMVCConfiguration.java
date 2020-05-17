package com.multicampus.teamProj4.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
public class SpringMVCConfiguration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/public/css");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/public/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/public/img/");
	}
}
