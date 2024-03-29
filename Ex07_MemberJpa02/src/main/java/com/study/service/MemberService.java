package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.domain.Member;
import com.study.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;


	//insert
	public void insert() {
		Member member;
		member = new Member("test1@test.com","이순신");
		memberRepository.save(member);
		member = new Member("test2@test.com","강감찬");
		memberRepository.save(member);
		member = new Member("test3@test.com","김유신");
		memberRepository.save(member);
		member = new Member("test4@test.com","연개소문");
		memberRepository.save(member);
		member = new Member("test5@test.com","세종대왕");
		memberRepository.save(member);
		member = new Member("test6@test.com","계백");
		memberRepository.save(member);
		member = new Member("test7@test.com","최영");
		memberRepository.save(member);
		member = new Member("test8@test.com","김남신");
		memberRepository.save(member);
		member = new Member("test9@test.com","김옥신");
		memberRepository.save(member);
	}
	
	
	//selectAll
	public List<Member> selectAll() {
		return memberRepository.findAll();
	}
	
	
	//selectById
	public Optional<Member> selectById(Long id) {
		return memberRepository.findById(id);
		
	}

	//selectByEmail
	public Optional<Member> selectByEmail(String email) {
		return memberRepository.findByEmail(email);
		
	}
	
	//selectByName
	public Optional<Member> selectByName(String name){
		return memberRepository.findByName(name);
	}

	
	//selectByNameLike
	public List<Member> selectByNameLike(String name) {
		return memberRepository.findByNameLike(name);		
	}

	
	//selectByNameLikeDesc
	public List<Member> selectByNameLikeDesc(String name) {
		return memberRepository.findByNameLikeOrderByNameDesc(name);
	}

	//selectByNameLikeSort (selectByNameLike써야 selectByNameLikeSort는 없다)
	public List<Member> selectByNameLike(String name, Sort sort) {
		return memberRepository.findByNameLike(name, sort);
	}


}
