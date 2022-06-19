package com.dw.board.sevice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.LogsMapper;
import com.dw.board.vo.LogVO;
import com.github.pagehelper.PageHelper;

@Service
public class LogsService {

	@Autowired
	private LogsMapper logsMapper;
	
	@Transactional(rollbackFor = {Exception.class})
	public int setLogs(LogVO logVO) {
		return logsMapper.insertLogs(logVO);
	}
	
	public List<Map<String, Object>> getLogsList(int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		//PageHelper.startPage() : grdle에 다운받은 pageHelper를 사용한것.
		return logsMapper.selectBoardLogs(0);
	}
	
	public Map<String,Object> getLogs(int logId){
		
		List<Map<String, Object>> list = logsMapper.selectBoardLogs(logId);
		
		return list.get(0);
	}
	
}
