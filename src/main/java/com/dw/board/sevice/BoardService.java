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
	@Transactional(rollbackFor = {Exception.class})
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
