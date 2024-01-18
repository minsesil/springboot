package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.domain.Board;
import com.study.domain.Member;
import com.study.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@PostMapping("/binsert")
	public String insert(Board board, Model model) {
		Member m = new Member();
		m.setId("user01");
		
		board.setMember(m);  //member 객체가 들어감
		
		Board result = boardService.insert(board);
		model.addAttribute("board", result);
		
		//result.getMember().getId();		
		return "binsert";
		
	}

}
