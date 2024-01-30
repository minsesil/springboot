package com.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.domain.Reply;
import com.study.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	ReplyRepository replyRepository;
	
	//댓글입력
	public Reply rinsert(Reply reply) {
		return replyRepository.save(reply);
	}

	//댓글리스트
	public List<Reply> replyList(Long bno) {
		return replyRepository.findAllByrefBno(bno);
	}
}


