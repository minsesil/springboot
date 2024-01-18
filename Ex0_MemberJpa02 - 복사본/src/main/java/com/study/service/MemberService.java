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

//	public Member insert(Member member) {
//		// save() : insert해주는 메소드
//		Member rMember = memberRepository.save(member);
//		return rMember;
//	}
//
//
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
	
	
	/*
	 * Optional<T> : NullpointerException을 방지하기 위해 사용
	 * 				 기존의 반환 값 타입 T에 Optional<T>를 Wrapping하여, 
	 * 				 null 대신 Optional안에 빈 타입 객체를 돌려준다.
	 * 		ex) Member member= memberRepository.findById("userid"); -> 그 값이 없을 때
	 * 			member.getUsername(); => 호출 시 NullpointerException 발생
	 */
	
	//select
	public Optional<Member> select(Long id) {
		// findById() : Entity에서 @Id가 붙은 필드를 의미
		Optional<Member> member = memberRepository.findById(id);
		return member;	
	}
	

	
	//delete
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	//update
	public Member update(Member member) {
		// insert메서드와 같은 방법으로 한다
		// @Id 필드의 값이 DB에 들어있으면 업데이트, 없으면 insert 해줌
		return memberRepository.save(member);   //여기서 updte는 save
		
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

	//selectByNameLikeSort
	public List<Member> selectByNameLikeSort(String name, Sort sort) {
		return memberRepository.findByNameLikeSort(name, sort);
	}


}
