package com.dw.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.sevice.BoardService;
import com.dw.board.vo.BoardVO;

@RestController
@RequestMapping("/api/v1")
//@RequestMapping("") : url 중복되는 주소를 한꺼번에 정의한는 어노테이션
//url주소 앞에 /api/v1 이(가) 자동으로 붙음.
//url주소를 검색할 때 /api/v1를 붙여야함.
public class BoardRestController {
	
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 저장
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardService.getBoardInsert(vo);
	}
	
	
	// 게시판 조회
	@CrossOrigin
	@GetMapping("/board")
	public List<BoardVO> callBoardList(){
		return boardService.getBoardList();
	}
	
}
