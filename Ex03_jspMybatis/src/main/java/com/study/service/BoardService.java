package com.study.service;

import java.util.List;

import com.study.dto.Board;

public interface BoardService {
	public List<Board> list();  //board이 list목록 가져오기
	public Board detailBoard(String no);  
	public int totalRecord();  //토탈갯수
	public int insertBoard(Board board);
	public int deleteBoard(String no);
	

}
