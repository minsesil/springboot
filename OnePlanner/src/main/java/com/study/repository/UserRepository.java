package com.study.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	//사용자 비밀번호를 업데이트하는 메서드
	public void updatePassword(String email, String password) {
		// TODO Auto-generated method stub
		
	}
	
	//이메일로부터 사용자 ID를 찾는 메서드
	public Long findUserIdByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
