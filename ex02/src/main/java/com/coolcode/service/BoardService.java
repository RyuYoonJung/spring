package com.coolcode.service;

import java.util.List;

import com.coolcode.domain.BoardAttachVO;
import com.coolcode.domain.BoardVO;
import com.coolcode.domain.Criteria;


public interface BoardService {
	int register(BoardVO boardVO);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO boardVO);

	boolean remove(Long bno);

	List<BoardVO> getList();

	List<BoardVO> getList(Criteria cri);

	int getTotalCount(Criteria cri);
	
	List<BoardAttachVO> getAttacks(Long bno);

}
