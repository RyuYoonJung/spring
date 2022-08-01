package com.coolcode.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coolcode.domain.CriteriaReply;
import com.coolcode.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Autowired @Setter
	private ReplyMapper mapper;
//	319573
//	319572
//	319571
//	319542
//	319541
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
		log.info(mapper);
	}
	
	@Test
	public void testInsert(){
		ReplyVO vo = new ReplyVO();
		vo.setBno(319573l);
		vo.setReply("댓글내용");
		vo.setReplyer("작성자");
		
		mapper.insert(vo);
	}
	
	@Test
	public void testCreate() {
		Long[] bnoArr = {319573l, 319572l, 319571l, 319542l,319541l};
		IntStream.rangeClosed(1	, 100).forEach(r->{
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[r%5]);
			vo.setReply("댓글내용" + r);
			vo.setReplyer("작성자"+ r);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead(){
		ReplyVO replyVO = mapper.read(5L);
		log.info(replyVO);
	}
	
	@Test
	public void testDelete(){
		mapper.delete(5L);
	}
	
	@Test
	public void testUpdate(){
		ReplyVO replyVO = mapper.read(1L);
		replyVO.setReply("수정된 내용");
		
		mapper.update(replyVO);
	}
	
	@Test
	public void testList(){
		CriteriaReply cri = new CriteriaReply();
		cri.setLastRno(100l);
		mapper.getListWithPaging(319573l, new CriteriaReply());
	}
}