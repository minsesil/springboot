package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.domain.Member;
import com.study.service.MemberService;



@Controller
@SessionAttributes({"loginUser"})
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@Autowired
	PasswordEncoder pEncoder;
	
	
	@RequestMapping("/")
	public String root() throws Exception{
		return "index";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	//회원가입
	@GetMapping("/enrollForm")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	//아이디체크
	@GetMapping("/idCheck")
	@ResponseBody
	public boolean checkId(@RequestParam("id") String id) {
		return memberService.idCheck(id);
	}
	
	//회원가입입력
	@PostMapping("/memberInsert")
	public String memberInsert(Member member) {
		//String enPass=pEncoder.encode(member.getPassword());
		member.setPassword(pEncoder.encode(member.getPassword()));
		memberService.insert(member);
		return "redirect:/";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member loginUser = memberService.login(member);
		if(loginUser != null && pEncoder.matches(member.getPassword(), loginUser.getPassword())) {
			
			model.addAttribute("loginUser", loginUser);
		}
		return "redirect:/";		
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete())
			status.setComplete();
		return "redirect:/";			
	}
	
	

	
	
	
	

}
