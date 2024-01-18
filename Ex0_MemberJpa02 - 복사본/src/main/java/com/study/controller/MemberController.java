package com.study.controller;

import java.time.LocalDate;
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
	

	
//	@GetMapping("/insert")
//	public String insert(@RequestParam("username") String username, Model model) {
//		// 나에게 맞는 생성자를 만드는 용도로 사용
//		// 가독성이 좋다
//		Member member = Member.builder()
//						.username(username)
//						.createDate(LocalDate.now())
//						.build();
//		Member result = memberService.insert(member);
//		model.addAttribute("member", result);
//		
//		return "insert";
//	}
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
		
		model.addAttribute("members", memberService.selectByNameLikeSort(name, sort));
		return "select_name_like";	
	}
	
	
	
	
	
//	// select
//	@GetMapping("/select")
//	public String select(@RequestParam("id") Long id, Model model) {
//		Optional<Member> result = memberService.select(id);				
//		//result.get().getUsername();
//		//result.get().getId();
//		
//		//받을때 Wrapping을 벗겨줘도 된다.
//		//Member member = memberService.select(id).get();
//		
//		// isPresent() : 데이터가 있는지 없는지 체크
//		if(result.isPresent()) {
//			model.addAttribute("member",result.get());  //Member형으로 넘겨줌
//		} else {
//			model.addAttribute("member", null);
//		}
//		return "select";
//	}	
//	
//
//	
//	//delete
//	@GetMapping("/delete")
//	public String delete(@RequestParam("id") Long id) {
//		memberService.delete(id);
//		return "redirect:selectAll";
//	}
//	
//	//update
//	@GetMapping("/update")
//	public String update(Member member, Model model) {
//		model.addAttribute("member",memberService.update(member));
//		return "update";
//	}	
	
	
	
}