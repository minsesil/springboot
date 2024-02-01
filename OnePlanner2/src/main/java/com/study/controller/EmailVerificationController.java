/*
 * 이메일 인증을 위한 기능을 제공하는 컨트롤러
 */
package com.study.controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.study.service.EmailService;

@Controller
@SessionAttributes("verificationCodes")
//@Scope("singleton")
public class EmailVerificationController {
	
	// 인증 코드를 저장하는 Map
    private Map<String, String> getVerificationCodesFromSession(Model model) {
        Map<String, String> verificationCodes = (Map<String, String>) model.getAttribute("verificationCodes");
        if (verificationCodes == null) {
            verificationCodes = new HashMap<>();
            model.addAttribute("verificationCodes", verificationCodes);
        }
        return verificationCodes;
    }
		
	
    @Autowired
    private EmailService emailService;
	
    @GetMapping("/verification-email-form")
    public String showEmailVerificationForm() {
    	return "verification/verification-email-form";
    }

    @PostMapping("/verify-email")   //verification-email-form.html
    public String verifyEmail(@RequestParam("email") String email, Model model) {   	
         // 이메일을 확인하고 인증코드 전송
        String verificationCode = generateRandomCode();
        emailService.sendVerificationEmail(email, verificationCode);
        
        // 세션에서 verificationCodes를 가져오거나 생성
        Map<String, String> verificationCodes = getVerificationCodesFromSession(model);

        // 이메일과 인증 코드를 Map에 저장
        verificationCodes.put(email, verificationCode);
        
        // 로그메시지
        System.out.println("해당 이메일로 전송완료: " + email);
        System.out.println("인증코드: " + verificationCode);
        System.out.println("verificationCodes 맵에 저장된 값: " + verificationCodes);
        
        // 인증코드를 입력할 수 있는 페이지로 이동
        model.addAttribute("email", email);  // 모델에 이메일 정보 추가
        return "verification/verification-email-form";  
    }
    
    
    //인증완료 페이지로
    @PostMapping("/verification-email-sent")
    public String verifyEmailCode(@RequestParam("email") String email,
                                  @RequestParam("verificationCode") String verificationCode,
                                  Model model) {
    	System.out.println("verifyEmailCode 메서드 호출");
    	
    	// 생성한 인증 코드 가져오기
        String generatedCode = getGeneratedCode(email, model);
    	
        // 인증 코드를 확인하고 처리하는 로직
        if (isValidCode(email, verificationCode, model)) {
            // 인증 코드가 유효한 경우
            model.addAttribute("email", email);
            model.addAttribute("alertMessage", "인증이 성공적으로 완료되었습니다!");
     
            // 사용한 인증코드를 삭제
            removeVerificationCode(email, model);
            
            System.out.println("성공일때 리다이렉트 경로: /verification-email-form");
            return "redirect:/verification-email-form";  // 이메일입력폼으로 이동
        
        } else {
            // 인증 코드가 유효하지 않은 경우
            model.addAttribute("email", email);
            model.addAttribute("error", "인증 코드가 올바르지 않습니다. 다시 시도해주세요.");
            System.out.println("성공아닐때 리다이렉트 경로: /verification-code-form");
            return "verification/verification-code-form";  //코드입력폼으로 이동
        }
    } 

    

    private void removeVerificationCode(String email, Model model) {
        Map<String, String> verificationCodes = getVerificationCodesFromSession(model);
       // 사용한 인증코드를 삭제
        verificationCodes.remove(email);
    }
	
	private boolean isValidCode(String email, String verificationCode, Model model) {
		// 해당 이메일에 대한 인증 코드를 가져움
	    String generatedCode = getGeneratedCode(email, model); // 받은 실제 이메일을 전달
		    System.out.println("사용자 입력 코드: " + verificationCode);
		    System.out.println("저장된 코드: " + generatedCode);
	    return generatedCode != null && verificationCode.equals(generatedCode);
	}

	
	private String getGeneratedCode(String email, Model model) {
	    // 해당 이메일에 대한 인증 코드를 가져옴
	    Map<String, String> verificationCodes = getVerificationCodesFromSession(model);
	    return verificationCodes.getOrDefault(email, "");
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
