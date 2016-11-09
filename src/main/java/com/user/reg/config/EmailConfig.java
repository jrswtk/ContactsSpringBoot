package com.user.reg.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class EmailConfig {

	@Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.message.register.title}")
    private String registerMessageTitle;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setProtocol("smtp");
        sender.setUsername(mailUsername);
        sender.setPassword(mailPassword);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.quitwait", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    @Bean(name = "registerMessage")
    public SimpleMailMessage registerMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(registerMessageTitle);
        return message;
    }
	
}
