/*
 * Spring MVC에서 홈 페이지를 처리하는 간단한 컨트롤러 클래스 (by min)
 */
package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

}
