package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "jsp로 실행";
	}
	
	@RequestMapping("/test1")
	public String test1() {        //경로 : /WEB-INF/views/
		return "test1";
	}
	
	@RequestMapping("/test2")     
	public String test2() {			//경로 : /WEB-INF/views/sub/
		return "sub/test2";
	}
}
