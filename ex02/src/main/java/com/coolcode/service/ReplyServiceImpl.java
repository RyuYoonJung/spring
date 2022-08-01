package com.coolcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolcode.domain.CriteriaReply;
import com.coolcode.domain.ReplyVO;
import com.coolcode.mapper.BoardMapper;
import com.coolcode.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor 
public class ReplyServiceImpl implements ReplyService{
	private BoardMapper boardmapper;
	private ReplyMapper mapper;
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		boardmapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insertSelectKey(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		boardmapper.updateReplyCnt(mapper.read(rno).getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, CriteriaReply cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(bno, cri);
	}
	
}
