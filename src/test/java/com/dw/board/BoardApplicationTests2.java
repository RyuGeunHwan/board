package com.dw.board;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.utils.PageHandler;

@SpringBootTest
class BoardApplicationTests2 {
	
	@Autowired
	private PageHandler pageHandler;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void contextLoads() {
		
		System.out.println("***********Spring test***********");
		int total = boardMapper.selectAllBoardTotalCount();
		System.out.println("전체 게시물(total) => "+total);
		int pageNum = 1;// 현재 페이지 번호
		int pageSize = 3; //한 페이지에 게시물 10개(고정값!)
		int navigatePages = 5; //한 블록에 페이지 5개
		
		
		//setter를 이용하는 이유 : pageHandler클래스 로직에서 원하는 값을 구하기 위해 
		// 필드변수를 이용하여 계산을 하기 때문이다.
		pageHandler.setTotal(total);
		pageHandler.setPageNum(pageNum);
		pageHandler.setPageSize(pageSize);
		pageHandler.setNavigatePages(navigatePages);
		
		
		pageHandler.setNowBlock(pageNum,navigatePages);
		int nowBlock = pageHandler.getNowBlock(); //현재 블록
		// 메소드 파라미터에 값을 넣어준 뒤 getter로 불러오는 이유는
		// 로직을 보면 result값을 필드변수에 대입해주기 때문에 get으로 필드변수에 접근한 것이다.
		System.out.println("현재 블록 => "+nowBlock);
		
		pageHandler.setLastBlock(total);
		int lastBlock = pageHandler.getLastBlock();//마지막 블록
		System.out.println("마지막 블록 => "+lastBlock);
		
		pageHandler.setStartPage(nowBlock);
		int startPage = pageHandler.getStartPage();
		System.out.println("현재 페이지 => "+startPage);
		
		int pages = pageHandler.calcPage(total, pageSize);
		pageHandler.setEndPage(nowBlock, pages);
		int lastPage = pageHandler.getEndPage();
		System.out.println("마지막 페이지 => "+lastPage);
		
		pageHandler.setPreNext(pageNum);
		boolean hasPreviousPage = pageHandler.isHasPreviousPage();
		boolean hasNextPage = pageHandler.isHasNextPage();
		System.out.println("이전 버튼 유무 => "+hasPreviousPage);
		System.out.println("다음 버튼 유무 => "+hasNextPage);
		
		int limitStart = ((pageNum-1) * pageSize);
		// ex) limitStart =((1-1)*10) & ((2-1)*10) & ...
		// 1페이지(pageNum==현재페이지) 게시물 0~10까지(10개)
		// 2페이지(pageNum==현재페이지) 게시물 10~20까지(10개)
		// ...
		List<Map<String, Object>> list = boardMapper.selectBoardTest(limitStart,pageSize);
		// boardMapper.selectBoardTest()를 불러온 이유는 쿼리에서 LIMIT을 이용해서 
		// 페이지당 게시글을 10개씩 짜르는 쿼리의 결과를 받아오기 때문에
		for(Map<String, Object> param : list) {
			String studentsName = (String) param.get("studentsName");
			System.out.println(studentsName);
		}
	
	
	}

}
