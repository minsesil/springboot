/*
 * 이메일 전송을 위한 JavaMailSender를 구성하는 설정 클래스
 */
package com.study.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
    @Bean
    public JavaMailSender javaMailSender(){
    	
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        javaMailSender.setDefaultEncoding("UTF-8"); 				 // 메일함 한글깨짐 방지
        
        javaMailSender.setHost("smtp.gmail.com");  					 // SMTP 서버명
        javaMailSender.setUsername("m91302368@gmail.com");   	 	 // 네이버 아이디
        javaMailSender.setPassword("taqp qmso pmuf jbdk");  		 // 네이버 비밀번호
        javaMailSender.setPort(465);								 // SMTP 포트

        javaMailSender.setJavaMailProperties(getMailProperties());   // 메일 인증서버 가져오기

        return javaMailSender;
    }

    // 메일 인증서버 정보 가져오기
    private Properties getMailProperties(){
    	
        Properties properties = new Properties();
        
        properties.setProperty("mail.transport.protocol", "smtp");   	   // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true");				   // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "false"); 	   // TLS 사용시 true, SSL 사용시 false
        properties.setProperty("mail.debug", "true"); 					   // 디버그 사용 
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");   // ssl 인증 서버 (smtp 서버명) 
        properties.setProperty("mail.smtp.ssl.enable", "true"); 		   // ssl 사용안함
        
        return properties;
    }

}
