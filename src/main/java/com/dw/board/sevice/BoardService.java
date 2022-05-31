package com.dw.board.sevice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.mapper.StudentsMapper;
import com.dw.board.utils.PageHandler;
import com.dw.board.vo.BoardVO;
import com.dw.board.vo.StudentsVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private StudentsMapper studentMapper;
	@Autowired
	private PageHandler pageHendler;
	
	/**
	 * @param vo
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 
	 */
	@Transactional(rollbackFor = {Exception.class}) //오류를 잡아주는 어노테이션
	//@Transactional 어노테이션이 있는데도 오류가 났음에도 불구하고 데이터는 안들어갔지만
	// default가 auto increment인 컬럼에 데이터는 안들어갔지만 오류난 구간은 건너뛰고 자동증감됨 
	// 질문. 이유는???
	public int getBoardInsert(BoardVO vo) {
		List<StudentsVO> list = studentMapper.selectAllStudentsList();
		return boardMapper.insertBoard(vo);
	}
	
	/**
	 * @return : List<BoardVO>
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 
	 */
	@Transactional(rollbackFor = {Exception.class})
	//게시판 전체 조회
	public List<Map<String,Object>> getBoardList(){

		return boardMapper.selectBoard();
	}
	
	// 게시물 수정
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateBoard(int boardId, BoardVO vo) {
		vo.setBoardId(boardId); //VO에 Id를 셋팅 해줘서 mapper에 있는 vo 수정값이랑 Id가 전부 있게 하자.
		return boardMapper.updateBoard(vo);
	}
	
	// 게시물 삭제
	@Transactional(rollbackFor = {Exception.class})
	public int getRemoveBoard(int boardId) {
		System.out.println(boardId);
		return boardMapper.removeBoard(boardId);
	}
	
	// 게시물 상세보기
	@Transactional(rollbackFor = {Exception.class})
	public BoardVO getDetailBoard(int boardId) {
		return boardMapper.detailBoard(boardId);
	}
	
	//게시글 조회 수 증가
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateBoardViews(int BoardId) {
		//1. 게시판 번호를 이용해서 조회수 컬럼을  select
		BoardVO vo = boardMapper.detailBoard(BoardId);//게시물 상세보기 쿼리 결과 가져오기
		int views = vo.getCnt(); //조회수 가져오기
		views++; //2. 조회수 1증가
		vo.setCnt(views);// cnt(카운트)를 가져와서 증가 시킨뒤 다시 set해주어서 update까지 이어진다.
		vo.setBoardId(BoardId);
		return boardMapper.updateBoardViews(vo);//3. 증가한 조회수를 update
	}
	
	// 작성자 검색하여 게시글 조회
	public List<Map<String,Object>> getSearchBoardList(String studentsName){
		return boardMapper.selectSearchBoardList(studentsName);
	}
	
	/**
	 * @return : Map<String,Object>
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 31.
	 *comment : //학생 수, 게시글 수, 작성자 수, 총 조회 수 통계
	 */
	public Map<String, Object> getBoardStatistics(){
		return boardMapper.selectBoardStatistics();
	}


}
