/*
 * 이메일 인증과 관련된 데이터를 전송하는 데 사용되는 객체
 */

package com.study.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="emailauth")//table="고칠테이블명"
@Data
@NoArgsConstructor  // 롬복이 기본 생성자를 생성
public class EmailAuthDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;  // id 필드 추가 (기본적으로 JPA 엔터티는 ID 필드를 가져야 함)	
	private String auth_num;
	private LocalDateTime created_at;
	private String email;


	//생성자
    public EmailAuthDto(Long user_id, String auth_num, LocalDateTime created_at, String email) {
        this.user_id = user_id;
    	this.auth_num = auth_num;
        this.created_at = created_at;
        this.email = email;
	}



	//롬복을 사용하면서 자동으로 생성하지만 에러떠서
//	public void setAuth_num(String auth_num) {
//		this.auth_num = auth_num;
//		
//	}

	

}
