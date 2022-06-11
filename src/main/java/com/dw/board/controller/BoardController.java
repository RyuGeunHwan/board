package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dw.board.sevice.BoardService;
import com.github.pagehelper.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/home")
	public String callHomepage() { 
		//템플릿 엔진을 사용 하게 되면 리턴타입은 String이어야 한다.
		// why? jsp파일 script에 js파일 주소를 설정 해주었기 때문에 jsp 파일명을 리턴해준다고 생각하면 된다.
		return "index"; //jsp파일 이름으로 return해주기
	}
	
	@GetMapping("/board")
	public String callBoardPage(
			ModelMap map,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		
		List<Map<String,Object>> list = boardService.getBoardList(pageNum, pageSize);
		
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		
		map.addAttribute("pageHelper", pageInfo);
		
		return "board";
	}
	
	@GetMapping("/board/search")
	public String callSerchList(
			ModelMap map,
			@RequestParam("writer") String writer,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		
		List<Map<String, Object>> list = boardService.getSearchBoardList(writer,pageNum,pageSize);
		
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		
		map.addAttribute("pageHelper", pageInfo);
		
		return "board";
	}
}
