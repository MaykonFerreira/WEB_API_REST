package com.maykon.apirest.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.maykon.apirest.services.DBService;

@Configuration
@Profile("test")
public class ProdConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	 
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		System.out.println(strategy);
		if (!"create".equals(strategy)){
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
		
	}
	

}
