package com.study.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.domain.Member;
import com.study.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "menu";
	}
	
	@GetMapping("/insert")
	public String insert(@RequestParam("username") String username, Model model) {
		// 나에게 맞는 생성자를 만드는 용도로 사용
		// 가독성이 좋다
		Member member = Member.builder()
						.username(username)
						.createDate(LocalDate.now())
						.build();
		Member result = memberService.insert(member);
		model.addAttribute("member", result);
		
		return "insert";
	}

}