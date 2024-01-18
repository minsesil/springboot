package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.Board;
import com.study.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;   //의존성 주입
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "redirect:list";   //list 파일 생성하기 (루트띄울때 파일: list.jsp)
	}
	
	/*
	 * 요청 처리 후 응답페이지로 결과를 담는 방법
	 * 1. Model
	 *    - 뷰로 전달하고자 하는 데이터를 맵형식(key=value)로 담을수 있는 객체
	 *    - requestScope이다
	 *      단, setAttribute가 아닌 addAttribute메소드 이용
	 *      
	 * 2. ModelAndView (많이사용하진 않는다)
	 *    - Model 영역에 뷰로 전달하고자 하는 데이터를 맵형식(key=value)로 담을수 있는 객체
	 *      View 영역은 응답뷰에 대한 정보를 담을수 있는 공간
	 *      
	 *      ex) public String list(ModelAndView){
		 *          List<Board> rlist = boardService.list();
		 *          mv.addObject("boardList", rlist);  //key(이름내맘대로), 값
		 *          mv.setViewName("/list");
		 *          return mv;
	 *          }
	 */
	@RequestMapping("/list")
	public String list(Model model) {
//		List<Board> list = boardService.list();
//		System.out.println("갯수 : " + list.size());
//		System.out.println("0번타이틀 : " + list.get(0).getTitle());		
		
		model.addAttribute("list", boardService.list());
		model.addAttribute("totalRecord", boardService.totalRecord());
		return "list";
	}
	
	// 글쓰기 폼
	@GetMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	/*
	 * 파라미터(요청시 전달값)를 받는 방법
	 * 1. HttpServletRequest를 이용하여 전달받기(기존 jsp/servlet 때의 전달방식)
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 *    - 속성
	 *      value = uri에서 바인딩하게 될 값
	 *      required(true|false) : 필수적으로 값이 전달되게 되는지 안받아도 되는지(true일때 값이 안들어오면 에러)
	 *      defaultValue : 값이 없을 때 기본값으로 사용할 값
	 *      
	 *      - @RequestParam("전달된 name(key)") 변수명 : defaultValue를 안넣을 때는 value는 생략가능
	 *    -사용법
	 *        @RequestParam(value="key" [, defaultValue="", required=true]) 자료형 변수명
	 *        @RequestParam("key")
	 *        
	 * 3. 커멘트 객체 방식
	 * 	  dto클래스 담는 방식
	 *    요청시 전달값의 키(name)을 dto클래스에 담고자 하는 필드명을 작성
	 *    
	 *    스프링컨테이너가 해당 객체를 기본생성자를 생성 후 setter메소드를 찾아서 전달된 값을 해당 필드에 내부적으로 담아주는 원리
	 *    - 반드시 기본생성자가 필요함 (Board.java - @NoArgsConstructor)
	 *    - setter메소드가 필요함
	 *    - 키이름이 dto의 필드명과 같아야 됨
	 */
	
	// 글쓰기입력
	@PostMapping("write")
	public String write(Board board) {
//		System.out.println("제목 : " + board.getTitle());
//		System.out.println("작성자 : " + board.getWriter());
//		System.out.println("내용 : " + board.getContent());
		boardService.insertBoard(board);
		return "redirect:list";  //list 재호출	
	}
	
	// 상세보기
	@GetMapping("/detail")
	public String detailBoard(HttpServletRequest request, Model model) {
		model.addAttribute("dBoard", boardService.detailBoard(request.getParameter("no")));
		return "detailForm";		
	}
	
	//삭제
	@GetMapping("/delete")
	public String delete(@RequestParam(value="no", defaultValue="1") String bno) {  //param방식 
		boardService.deleteBoard(bno);
		return "redirect:list";
	}
	
	
}
