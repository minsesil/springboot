package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public void insert(Member member) {
		
		//save() : insert 해주는 메소드
		Member rMember = memberRepository.save(member);
		return rMember;
		
	}

}
