package com.study.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	

	//insert
	@GetMapping("/insert")
	public String insert() {
		memberService.insert();
		return "insert";
	}
	
	
	//selectAll
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		model.addAttribute("members", memberService.selectAll());
		return "selectAll";	
	}
	
	
	//selectById
	@GetMapping("/selectById")
	public String selectById(@RequestParam("id") Long id, Model model) {
		model.addAttribute("member", memberService.selectById(id).get());
		return "select_id";
	}
	
	//selectByEmail
	@GetMapping("/selectByEmail")
	public String selectByEmail(@RequestParam("email") String email, Model model) {
		//Member m = memberService.selectByEmail(email).get();
		//System.out.println("이름 : " + m.getName());
		model.addAttribute("member", memberService.selectByEmail(email).get());
		return "select_email";
	}
		
	//selectByName
	@GetMapping("/selectByName")
	public String selectByName(@RequestParam("name") String name, Model model) {
		model.addAttribute("member", memberService.selectByName(name).get());
		return "select_name";
	}		
	
	//selectByNameLike
	@GetMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		
		model.addAttribute("members", memberService.selectByNameLike(name));
		return "select_name_like";	
	}	
		
	//selectByNameLikeDesc
	@GetMapping("/selectByNameLikeDesc")
	public String selectByNameLikeDesc(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		
		model.addAttribute("members", memberService.selectByNameLikeDesc(name));
		return "select_name_like";	
	}

	//selectByNameLikeSort
	@GetMapping("/selectByNameLikeSort")
	public String selectByNameLikeSort(@RequestParam("name") String search, Model model) {
		String name = search + "%";
		Sort sort = Sort.by(Sort.Order.desc("name"));
		
		model.addAttribute("members", memberService.selectByNameLike(name, sort));
		return "select_name_like";	
	}
	
		
	
}