/*
 * 사용자 작업과 관련된 엔드포인트를 관리합니다. 인증 코드를 보내고 코드를 확인하며 비밀번호를 변경하는 기능이 포함
 */

package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study.domain.EmailAuthDto;
import com.study.domain.UserDto;
import com.study.service.ChangePasswordService;


@RestControllerAdvice
@RequestMapping("/mail")
public class UserController {
	
	@Autowired
	private final ChangePasswordService changePasswordService;
	
	   // 생성자 주입 사용
	   public UserController(ChangePasswordService changePasswordService) {
	        this.changePasswordService = changePasswordService;
	    }
	
	// 사용자의 이메일로 인증 코드를 전송
	@PostMapping("/send-authcode")
	public ResponseEntity<?> sendAuthCode(@RequestBody UserDto userDto){
		try {
			
			// EmailAuthDto 객체생성
			EmailAuthDto emailAuthCodeDto = new EmailAuthDto();

			
			// 5분 이내에 다시 인증 번호 전송을 했다면 앞서 요청한 인증번호 삭제
			changePasswordService.deleteExistCode(userDto.getEmail());
			
			//새로운 인증 코드를 이용해 이메일을 전송하고 코드를 받아줌
			emailAuthCodeDto.setAuth_num(changePasswordService.sendEmail(userDto.getEmail()));
			
		       // 성공적인 응답과 함께 인증 코드를 반환
	        return ResponseEntity.ok(new JsonResponse<>(emailAuthCodeDto.getAuth_num()));
	    } catch (Exception e) {
	        // 예외 처리: UnsupportedEncodingException이 발생하면 클라이언트에게 오류 응답을 반환
	        return ResponseEntity.status(500).body(new JsonResponse<>("Error encoding email content: " + e.getMessage()));
	    }
	}

	
	// 제공된 인증 코드를 확인
	@PostMapping("/check-authcode")
	public ResponseEntity<?> checkAuthCode(@RequestBody EmailAuthDto emailAuthDto){
		
		// 입력한 인증코드 확인
		//String response = changePasswordService.verifyCode(emailAuthDto.getEmail(), String.valueOf(emailAuthDto.getAuth_num()));
		String response = changePasswordService.verifyCode(emailAuthDto.getEmail(),
               String.valueOf(emailAuthDto.getAuth_num()));
		
		// 다른 경우에 따라 적절한 응답을 반환
	       if (response.equals("Error: over 5 minute"))// 인증번호가 생성된지 5분이 되어 만료된 상황에서 인증번호를 입력한 경우
	            return ResponseEntity.ok("Error: over 5 minute");
	        else if (response.equals("Error: not correct auth code"))
	            return ResponseEntity.ok("Error: not correct auth code");
	        else
	            return ResponseEntity.ok("Success: correct auth code");
	}

	
	// 성공적인 인증 후 사용자의 비밀번호를 변경
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody UserDto userDto){
		
		changePasswordService.changePassword(userDto.getEmail(), userDto.getPassword());
		return ResponseEntity.ok("Success: password changed");
	}
}
