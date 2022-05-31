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
	 *comment : 게시판 저장(C)
	 */
	public int insertBoard(BoardVO vo);
	
	
	/**
	 * @return : List
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 게시판 전체조회(R)
	 */
	public List<Map<String,Object>> selectBoard();
	
	
	/**
	 * @param boardId
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 26.
	 *comment : 게시물 수정(U)
	 */
	public int updateBoard(@RequestBody BoardVO vo);
	
	/**
	 * @param boardId
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 26.
	 *comment : 게시물 삭제(D)
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
	
	/**
	 * @param vo
	 * @return : int
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 27.
	 *comment : 게시글 클릭시 조회수 카운트
	 */
	public int updateBoardViews(BoardVO vo);
	
	/**
	 * @return : List<Map<String,Object>>
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 27.
	 *comment : 검색창에 입력한 값의 특정 게시글 검색 조회
	 */
	public List<Map<String, Object>> selectSearchBoardList(String studentsName);
	
	
	/**
	 * @return : List<Map<String,Object>>
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 31.
	 *comment : 단일행이기 때문에 List로는 받지 않는다.
	 * 방법1. VO클래스에 이름이 같은 필드변수를 만들어줘서 BoardVO로 받는 방법
	 * 방법2. Map으로 받는 방법
	 */
	public Map<String, Object> selectBoardStatistics();
}
