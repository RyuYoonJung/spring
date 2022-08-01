package com.coolcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolcode.domain.BoardAttachVO;
import com.coolcode.domain.BoardVO;
import com.coolcode.domain.Criteria;
import com.coolcode.mapper.BoardAttachMapper;
import com.coolcode.mapper.BoardMapper;
import com.coolcode.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service @AllArgsConstructor @Log4j
public class BoardServiceImpl implements BoardService  {
	private BoardMapper boardMapper;
	private BoardAttachMapper boardAttachMapper;
	private ReplyMapper replyMapper;
	
	@Transactional
	public int register(BoardVO boardVO){
		log.info("register(" + boardVO+")");
		int result = boardMapper.insertSelectKey(boardVO);
		// 첨부시 db반영
		boardVO.getAttachs().forEach(attach -> {
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return result;
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		boardAttachMapper.findBy(bno);
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO boardVO) {
		// TODO Auo-generated method stub
		// 게시글의 첨부파일 일괄삭제
		boardAttachMapper.deleteAll(boardVO.getBno());
		
		//첨부파일 재등록
		boardVO.getAttachs().forEach(attach -> {
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return boardMapper.update(boardVO) > 0;
	}

	
	@Override
	@Transactional
	public boolean remove(Long bno) {
		replyMapper.deleteAll(bno);
//		boardAttachMapper.deleteAll(bno) > 0;
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return boardMapper.getList();
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttacks(Long bno) {
		// TODO Auto-generated method stub
		return boardAttachMapper.findBy(bno);
	}
}
