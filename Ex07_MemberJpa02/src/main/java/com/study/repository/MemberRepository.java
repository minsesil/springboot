package com.study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	//findByEmail
	Optional<Member> findByEmail(String email);

	//findByName
	Optional<Member> findByName(String name);
	
	//findByNameLike
	List<Member> findByNameLike(String name);
	
	//findByNameLikeOrderByNameDesc
	List<Member> findByNameLikeOrderByNameDesc(String name);

	//findByNameLikeSort
	List<Member> findByNameLike(String name, Sort sort);

}
