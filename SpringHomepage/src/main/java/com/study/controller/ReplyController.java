package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.domain.Reply;
import com.study.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	
	@PostMapping("/rinsert")
	@ResponseBody
	public Reply rinsert(Reply reply) {
		System.out.println("댓글등록" + reply);
		return replyService.rinsert(reply);
	}
	
}
