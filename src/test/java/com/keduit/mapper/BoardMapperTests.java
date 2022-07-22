package com.keduit.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	public void testGetList() {

		mapper.getList().forEach(board -> log.info(board));

	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		
		cri.setPageNum(5);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));	
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 테스트");
		board.setContent("내용 테스트임.");
		board.setWriter("tester01");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertKey() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 테스트 insert key");
		board.setContent("내용 테스트임.select key");
		board.setWriter("tester02");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
		
	}
	
	@Test
	public void testRead() {
		BoardVO board = new BoardVO();
		board = mapper.read(3L);
		
		log.info(board);
	}

	@Test
	public void delete() {
		int result = mapper.delete(2L);
		log.info("delete.........");
		log.info("delete 갯수 => " + result);
	
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용...");
		board.setWriter("tester00");
		board.setBno(4L);
		
		int result = mapper.update(board);
		log.info("..............");
		log.info("update 갯수 => " + result);
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("test");
		cri.setType("TC");
		
		List<BoardVO>list = mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
		int total = mapper.getTotalCount(cri);
		log.info("test total count............." + total);
	}
	
}
