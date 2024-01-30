package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	 private EmailService emailService;
		
		// 이메일 전송
	    @PostMapping("/send-email")
	    public String sendEmail(@RequestParam String email) {
	        // 본인인증 코드 생성
	        String verificationCode = generateVerificationCode();

	        // 이메일 전송
	        emailService.sendEmail(email, "Email Verification", "Your verification code is: " + verificationCode);

	        // 본인인증 코드를 저장하거나 세션에 저장
	        return "Email sent successfully";
	    }

	    private String generateVerificationCode() {
	        // 본인인증 코드 생성 로직 구현
	        // (랜덤한 숫자 또는 문자열 생성 등)
	        return "123456";
	    }

}
