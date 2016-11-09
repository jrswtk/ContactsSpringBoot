package com.user.reg.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.user.reg.config.EmailConfig;
import com.user.reg.config.MessageConfig;
import com.user.reg.config.SecurityConfig;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.user.reg")
@EnableJpaRepositories(basePackages = {"com.user.reg.repository"})
@EnableTransactionManagement
@EntityScan(basePackages = {"com.user.reg.domain", "com.user.reg.service"})
@Import({SecurityConfig.class, EmailConfig.class, MessageConfig.class})
public class ContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}
}
