/*
 * 사용자 관련 기능을 처리하는 RESTful API를 제공하는 UserController 클래스 (by min)
 */
package com.study.controller;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.service.EmailService;

@RestController
public class UserController {
	
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-verification-email")
    public String sendVerificationEmail(@RequestParam String email) {
        // 사용자 이메일로 랜덤인증코드 생성
        String verificationCode = generateRandomCode();
        
        // 사용자 이메일로 인증코드 전송
        emailService.sendVerificationEmail(email, verificationCode);

        return "이메일을 성공적으로 전송했습니다!";
    }

    private String generateRandomCode() {
    	
        // 랜덤코드 생성
		String digits = "0123456789";
		int codeLength = 4; //코드길이
		
		StringBuilder randomCode = new StringBuilder(codeLength);
		SecureRandom random = new SecureRandom();
		
		for(int i=0; i < codeLength; i++) {
			int randomIndex = random.nextInt(digits.length());
			randomCode.append(digits.charAt(randomIndex));			
		}
		return randomCode.toString();
    }

}
