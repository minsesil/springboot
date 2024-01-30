/*
 * 이메일을 보내는 로직, 인증 코드를 생성하고 확인하는 로직, 그리고 비밀번호를 변경하는 데 관련된 비즈니스 로직을 처리
 */

package com.study.service;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.study.domain.EmailAuthDto;
import com.study.repository.EmailAuthRepository;
import com.study.repository.UserRepository;

import org.thymeleaf.context.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; // 이 부분을 추가
import javax.mail.MessagingException; // 이 부분을 추가


@Slf4j
@Service
@RequiredArgsConstructor
public class ChangePasswordService {
	
	//실제 메일 전송을 위한 send() 메서드 사용
	@Autowired
	private final JavaMailSender  emailSender;
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	//이메일 인증 코드에 대한 CRUD 수행
	private final EmailAuthRepository emailAuthRepository;
	
	//emal로 해당 user의 id 찾기
	private final UserRepository userRepository;
	
	//생성된 인증번호
	private String authNum;
	
	//타임리프를 이용한 context 설정
	private final SpringTemplateEngine templateEngine;

	private EmailAuthDto emailAuthDto;

	
	//이메일 전송 메서드 - 실제 메일전송 (controller에서 호출)
	public String sendEmail(String toEmail){
		
        try {
            // 메일 전송에 필요한 정보 설정
            MimeMessage emailForm = createEmailForm(toEmail);

            // 실제 메일 전송
            emailSender.send(emailForm);
            
            // 생성된 인증코드반환
            createCode(toEmail);
            String generatedAuthNum = authNum;
            //return String.valueOf(authNum);  // 생성된 인증코드 반환
            
            // 생성된 인증코드 삭제
            deleteExistCode(toEmail);
            
            return generatedAuthNum;
            
        } catch (MessagingException e) {
            log.error("MessagingException while sending email: {}", e.getMessage(), e);
            throw new RuntimeException("Error sending email", e);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException while sending email: {}", e.getMessage(), e);
            throw new RuntimeException("Error encoding email content", e);
        }
    }
	
	
	//이메일 양식 작성
	public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException{
		
		String auth_num = createCode(email);  //인증코드 생성
		String setFrom = "m91302368@gmail.com";  //email-config에 설정한 자신의 이메일 주소
		String toEmail = email;  //받는사람
		String title = "인증코드는 " + authNum + "입니다";   //제목
		
		MimeMessage message = emailSender.createMimeMessage();
		message.addRecipients(MimeMessage.RecipientType.TO, toEmail);  //보낼 이메일 설정
		message.setSubject(title);  //제목설정
		//message.setFrom(setFrom);   //송신자 이메일
		message.setFrom(new InternetAddress(setFrom, "Your Name"));   // 송신자 이메일
		
		//message.setText(setContext(authNum), "utf-8", "html");
	    // HTML 형식의 이메일 내용 설정
	    message.setText("<html><body><p>인증코드는 <strong>" + authNum + "</strong>입니다</p></body></html>", "utf-8", "html");

		
		return message;		
	}
	
	
	//랜덤 인증코드 생성
	public String createCode(String email) {
		Random random = new Random();
		authNum = String.valueOf(random.nextInt(90000)+10000);  //범위: 1000~9999
		
//		this.emailAuthDto = new EmailAuthDto(null, email, 0, null);
//		   Long userId = userRepository.findUserIdByEmail(email);
//		    if (userId == null) {
//		        // 사용자 ID를 찾을 수 없는 경우에 대한 예외처리 또는 로깅
//		        log.error("Cannot find user ID for email: {}", email);
//		        // 예외처리 로직 또는 원하는 대응 추가
//		        return;
//		    }
		this.emailAuthDto = new EmailAuthDto();    
		this.emailAuthDto.setUser_id(null);  //null 또는 원하는 값을 설정
		emailAuthDto.setAuth_num(authNum);
		emailAuthDto.setCreated_at(LocalDateTime.now());
		emailAuthDto.setEmail(email);
		
		emailAuthRepository.createAuthDto(emailAuthDto);
		
		return authNum;
	}
		
	
	//타임리프를 이용한 context 설정
	public String setContext(String authNum) {
		Context context = new Context();
		context.setVariable("code", authNum);  //생성한 인증번호가 th:text="${code}와 매핑
		return templateEngine.process("mail", context);   //mail.html
	}
	
	//한 유저가 2번이상 연속으로 인증코드를 보낼 경우에 대한 예외처리를 위해 기존코드 삭제
	public void deleteExistCode(String email) { 
		emailAuthRepository.deleteByEmail(userRepository.findUserIdByEmail(email));		
	}
	
	//만료된 인증코드 삭제
//	public void deleteExpiredAuthNum() {
//		List<EmailAuthDto> emailAuthEntityList = emailAuthRepository.findAll();
//		
//		for(EmailAuthDto emailAuthDto : emailAuthEntityList) {
//			
//			//현재시간 - 인증번호 발급시간 계산하여 5분이상 지난경우 삭제
//			Duration duration = Duration.between(emailAuthDto.getCreated_at(), LocalDateTime.now());
//			
//			if(duration.toMinutes() >= 5) {
//				emailAuthRepository.deleteByUserId(emailAuthDto.getUser_id());
//			}
//		}
//	}
	
	//인증코드 확인
	public String verifyCode(String email, String code) {
		EmailAuthDto emailAuthDto = emailAuthRepository.findByUserId(userRepository.findUserIdByEmail(email));
		
	    if (emailAuthDto != null) {
	        String authNumStr = String.valueOf(emailAuthDto.getAuth_num());

		
			if(code.equals(authNumStr)) {
				LocalDateTime now = LocalDateTime.now();
				Duration duration = Duration.between(emailAuthDto.getCreated_at(), now);
				
				//5분 이내로 인증코드를 맞춘경우 (잠깐닫음)
//				if(duration.toMinutes() <5) {  
//					//emailAuthRepository.findByUserId(emailAuthDto.getUser_id());
//					emailAuthRepository.deleteByUserId(emailAuthDto.getUser_id());
//					return "Success";				
//				}
//				else {
//					emailAuthRepository.deleteByUserId(emailAuthDto.getUser_id());
//					return "Error: over 5 minute";
//				}
			}
	    }
		//인증코드가 틀린 경우
		return "Error: not correct auth code";
		
	}
	
	//시용자의 비밀번호 변경하는 메서드
	public void changePassword(String email, String password) {
		//String encodedPassword = passwordEncoder.encode(password);
		userRepository.updatePassword(email, password);
	}

}
	
