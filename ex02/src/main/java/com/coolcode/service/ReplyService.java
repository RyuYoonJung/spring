package com.coolcode.service;

import java.util.List;

import com.coolcode.domain.CriteriaReply;
import com.coolcode.domain.ReplyVO;

public interface ReplyService {
	int register(ReplyVO vo);
	
	ReplyVO get(Long rno);
	
	int modify(ReplyVO vo);
	
	int remove(Long rno);
	
	List<ReplyVO> getList(Long bno, CriteriaReply cri);
}
