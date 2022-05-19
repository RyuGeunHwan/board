package com.dw.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	/**
	 * @param vo
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 게시판 저장
	 */
	public int insertBoard(BoardVO vo);
	
	
	/**
	 * @return : List
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 게시판 전체조회
	 */
	public List<BoardVO> selectBoard();
	
}
