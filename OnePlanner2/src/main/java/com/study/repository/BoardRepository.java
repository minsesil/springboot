package com.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Board;
import com.study.domain.Reply;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	//리스트(소드이름:list)
	//public List<Board> list();  
	
	//상세보기
	//public Board detailBoard(String no);  
	
	//public int totalRecord();  //토탈갯수
	
	//글입력
	//public int insert(Board board);
	
	//글삭제
	//public int deleteBoard(String no);
	
	//댓글
	//public List<Reply> rlist(Long bno);

	//Reply save(Reply reply);

	

}
