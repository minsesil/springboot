/*
 * 이메일 관련 서비스에서 구현해야 할 메소드들을 정의하는 인터페이스 (by min)
 */
package com.study.service;

import java.io.UnsupportedEncodingException;
import jakarta.mail.MessagingException;

public interface MailServiceInter {
	
    String sendSimpleMessage(String to) throws Exception;
    String createKey();
    String createMessage(String to) throws MessagingException, UnsupportedEncodingException;

}
