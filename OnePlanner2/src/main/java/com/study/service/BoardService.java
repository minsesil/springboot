package com.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.domain.Board;
import com.study.domain.Reply;
import com.study.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
		
	//게시판 리스트
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	//게시판 글입력
	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	//글 상세보기 (카운트 증가 -> save -> 실행)
	public Optional<Board> selectDetail(Long bno) {
		return boardRepository.findById(bno)
					   .map(board->{
						    board.setCount(board.getCount() + 1);
						    return boardRepository.save(board);
					   });
	}
	
	//글 수정
	public Board update(Board board) {
		Board rboard = boardRepository.findById(board.getBno()).get();
		rboard.setTitle(board.getTitle());
		rboard.setContent(board.getContent());
		
		return boardRepository.save(rboard);
	}


	
	
	
	//댓글 리스트
//	public List<Reply> rlist(Long bno) {
//		return boardRepository.rlist(bno);
//	}

	//댓글 등록
//		public Reply rinsert(Reply reply) {
//		return boardRepository.save(reply);
//		}
	public void rinsert(Reply reply) {
		
		
	}
	

}
