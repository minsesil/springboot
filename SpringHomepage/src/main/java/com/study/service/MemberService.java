package com.study.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;



@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	//아이디체크
	public boolean idCheck(String id) {
		return memberRepository.existsById(id);
	}
	
	//회원가입입력
	public Member insert(Member member) {
		return memberRepository.save(member);
	}
	
	//회원로그인
	public Member login(Member member) {
		Optional<Member> loginUser = memberRepository.findById(member.getId());
		if(loginUser.isPresent())
			return loginUser.get();
		else
			return null;
		
	}
	
	

	
	

}
