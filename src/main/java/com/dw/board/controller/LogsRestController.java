package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.sevice.LogsService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/v1")
public class LogsRestController {

	@Autowired
	private LogsService logsService;
	
	@CrossOrigin
	@GetMapping("/logs")
	public PageInfo<Map<String, Object>> getlogsList(
			@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize){
		
		List<Map<String,Object>> list = logsService.getLogsList(pageNum,pageSize);
		
		return new PageInfo<Map<String, Object>>(list);
	}
	
	// select는 controller 클래스에서 insert는 interceptor클래스에서 구현한다.
	@CrossOrigin
	@GetMapping("/logs/logId/{logId}")
	public Map<String,Object> callLogs(@PathVariable("logId") int logId){
		return logsService.getLogs(logId);
	}
	
	
}
