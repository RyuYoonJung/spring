package com.coolcode.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coolcode.domain.BoardVO;
import com.coolcode.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter @Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister(){
		// init
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("service test title");
		boardVO.setContent("service test content");
		boardVO.setWriter("user00");
		log.info(boardVO);
		// expect
		int exp = 1;
		
		// result
		int result = boardService.register(boardVO);
		
		assertEquals("게시글 추가", exp, result);
		log.info(boardVO);
	}
	
	@Test
	public void testExist(){
		assertNotNull(boardService);
	}
	
	@Test
	public void testGetList(){
		Criteria criteria = new Criteria();
		
		List<BoardVO> result = boardService.getList();
		assertNotNull(result);
		result.forEach(log::info);
	}

	@Test
	public void testRead(){
		BoardVO result = boardService.get(4L);
		assertNotNull(result);
		log.info(result);
	}

	@Test
	public void testModify(){
		// init
		BoardVO boardVO = boardService.get(8l);
		log.info(boardVO);
		boardVO.setTitle("service test modify");
		log.info(boardVO);
		// expect
		boolean exp = true;
		
		// result
		boolean result = boardService.modify(boardVO);
		
		assertEquals("게시글 수정", exp, result);
		log.info(boardVO);
	}

	@Test
	public void testDelete(){
		// init
		Long bno = 4l;
		// expect
		boolean exp = true;
		
		// result
		boolean result = boardService.remove(bno);
		
		assertEquals("게시글 삭제", exp, result);
	}
	
	@Test
	public void testGetTotalCount(){
		log.info(boardService.getTotalCount(new Criteria()));
	}
}
