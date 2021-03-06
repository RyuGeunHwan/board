package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.board.vo.LogVO;


// log같은 기록데이터는 insert와 select만 구현한다.(delete,update X)
@Mapper
public interface LogsMapper {

	public int insertLogs(LogVO logVO); //접속이력 저장
	
	public List<Map<String, Object>> selectBoardLogs(int logId); // 접속이력 전체 조회

}
