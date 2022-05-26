package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

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
	public List<Map<String,Object>> selectBoard();
	
	
	/**
	 * @param boardId
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 26.
	 *comment : 게시물 수정
	 */
	public int updateBoard(@RequestBody BoardVO vo);
	
	/**
	 * @param boardId
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 26.
	 *comment : 게시물 삭제
	 */
	public int removeBoard(@Param("id") int boardId);
	
	/**
	 * @param boardId
	 * @return : BoardVO
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 26.
	 *comment : 게시물 상세보기
	 */
	public BoardVO detailBoard(@Param("id") int boardId);
	
	
	
	
}
