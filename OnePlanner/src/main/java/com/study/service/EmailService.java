package com.study.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage; // SimpleMailMessage 가져오기

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
	
    private final JavaMailSender javaMailSender;
    
    //생성자 주입
    //@Autowired
    public EmailService(JavaMailSender javaMailSender) {
    	this.javaMailSender = javaMailSender;
    }
    
    /*
     * 간단한 텍스트 기반 이메일을 전송하는 메서드
     * @param to : 수신자 이메일주소
     * @param subject : 이메일 제목
     * @param body : 이메일 내용
     */
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);				//수신자 이메일 주소 설정
        message.setSubject(subject);	//이메일 제목 설정
        message.setText(body);			//이메일 내용 설정
        javaMailSender.send(message);	//생성한 이메일 메시지를 전송
    }
    
    /**
     * HTML 기반 이메일을 전송하는 메서드
     *
     * @param to      수신자 이메일 주소
     * @param subject 이메일 제목
     * @param body    HTML 형식의 이메일 내용
     */
    public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }    
}