package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.sevice.BoardService;
import com.dw.board.vo.BoardVO;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1")
//@RequestMapping("") : url 중복되는 주소를 한꺼번에 정의한는 어노테이션
//url주소 앞에 /api/v1 이(가) 자동으로 붙음.
//url주소를 검색할 때 /api/v1를 붙여야함.
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 저장(C : Create(INSERT))
	@CrossOrigin
	@PostMapping("/board")
	public int callSaveBoard(@RequestBody BoardVO vo) {
		return boardService.getBoardInsert(vo);
	}
	
	
	// 게시판 전체 조회(R : READ(SELECT)) & pageHandler
	@CrossOrigin
	@GetMapping("/board")
	//리턴타입을 List<Map<String,Object>> => PageInfo<Map<String,Object>>로 변경
	public PageInfo<Map<String,Object>> callBoardList(
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize){
		
		 List<Map<String,Object>> list = boardService.getBoardList(pageNum, pageSize);
		 
		return new PageInfo<Map<String,Object>>(list);
	}
	// 게시물 수정(U : UPDATE)
	@CrossOrigin
	@PatchMapping("/board/boardId/{id}")
	public int callUpdateBoard(@PathVariable("id") int boardId, @RequestBody BoardVO vo) {
		return boardService.getUpdateBoard(boardId,vo);
	}
	
	// 게시물 삭제(D : DELETE)
	@CrossOrigin
	@DeleteMapping("/board/boardId/{id}")
	public int callRemoveBoard(@PathVariable("id") int boardId) {
		return boardService.getRemoveBoard(boardId);
	}
	
	// 게시물 상세보기
	@CrossOrigin
	@GetMapping("/board/boardId/{id}")
	public BoardVO callBoard(@PathVariable("id") int boardId) {
		return boardService.getDetailBoard(boardId);
	}
	
	@CrossOrigin
	@PatchMapping("/board/views/boardId/{id}")
	public int callBoardViews(@PathVariable("id") int boardId) {
		System.out.println(boardId);
		return boardService.getUpdateBoardViews(boardId);
	}
	
	
	// 쿼리 스트링으로 검색한 작성자 게시판 리스트 조회
	// 리턴타입을
	@CrossOrigin
	@GetMapping("/board/search")
	public PageInfo<Map<String, Object>> callBoardSearch(
			@RequestParam("writer") String writer,
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize){
		
		// boardService클래스에 파라미터 값 넘겨주기
		List<Map<String, Object>> list = boardService.getSearchBoardList(writer,pageNum,pageSize);
		
		//페이징 구현
		return new PageInfo<Map<String,Object>>(list);
	}
	
	// 게시판 통계 조회
	@CrossOrigin
	@GetMapping("/board/statistics")
	public Map<String, Object> callBoardStatistics(){
		return boardService.getBoardStatistics();
	}
	
	
	
	
}
