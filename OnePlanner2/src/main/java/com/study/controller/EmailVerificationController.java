/*
 * 이메일 인증을 위한 기능을 제공하는 컨트롤러 (by min)
 */
package com.study.controller;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.service.EmailService;

@Controller
public class EmailVerificationController {
	
    @Autowired
    private EmailService emailService;
	
    @GetMapping("/email-verification-form")
    public String showEmailVerificationForm() {
        return "email-verification-form";
    }

    @PostMapping("/verify-email")   //email-verification-form.html
    public String verifyEmail(@RequestParam("email") String email) {
    	
        // 이메일을 확인하고 인증코드 전송
        String verificationCode = generateRandomCode();
        emailService.sendVerificationEmail(email, verificationCode);
        
        // 로그메시지 추가
        System.out.println("해당 이메일로 전송완료: " + email);
        System.out.println("인증코드: " + verificationCode);
        
        return "verification-email-sent";
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
