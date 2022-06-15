package com.dw.board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController implements ErrorController {

	
	@GetMapping("/error")
	 public String handelError(ModelMap map, HttpServletRequest request) {
		
		// 에러메세지의 코드를 String으로 형 변환을 하여 status(변수)에 넣어준다.
		String status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
		System.out.println("Error Code => "+status);
		
		
		if(status.equals("404")) {
			return "error/error404";
			// error == 폴더명을 의미하는 것이다.
		}
		if(status.equals("500")) {
			return "error/error500";
		}
		
		
		return null;
	}
	
	
	
}
