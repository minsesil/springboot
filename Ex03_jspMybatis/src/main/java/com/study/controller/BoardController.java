package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;   //의존성 주입
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "list";   //list파일생성하기 (루트띄울때 파일: list.jsp)
	}

}
