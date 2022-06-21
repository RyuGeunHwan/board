package com.dw.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String callLoginPage() {
		return "login";
	}

	
	@GetMapping("/join")
	public String callLogin() {
		return "join";
	}
	
	@GetMapping("/logout")
	public String callLoginout(HttpSession httpSession) {
		//세션 remove
		httpSession.removeAttribute("studentsId");
		httpSession.removeAttribute("studentsName");
		return "login";
	}
	@GetMapping({"","/"})
	public String index() {
		// 템플릿 엔진 : 머스테치
		// 머스테치 기본폴더 : src/main/resources/
		// 뷰리졸버 설정 : templates(prefix), .mustache(suffix)
		// => pom.xml에 설정해주었기 때문에 야믈 파일에는 설정 안해도됨.(생략가능)
		return "join.jsp";
	}
}
