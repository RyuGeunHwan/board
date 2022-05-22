package com.dw.board.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.BoardMapper;
import com.dw.board.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	
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
	public List<BoardVO> getBoardList(){
		return boardMapper.selectBoard();
	}
}
