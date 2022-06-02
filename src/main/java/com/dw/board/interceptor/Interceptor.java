package com.dw.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



//특정 Controller의 핸들러가 실행되기 전이나 후에 추가적인 작업을 원할때 Interceptor를 사용
@Component  // @Component : 본인이 만든 클래스를 Bean으로 등록할 때 쓰는 어노테이션 
			// @Bean : Spring에서 제공해주는 클래스, 외부 라이브러리를 Bean으로 등록할 때 쓰는 어노 테이션
public class Interceptor implements HandlerInterceptor {

	@Override
	// preHandle : controller에 도착하기 전에 요청을 가로채는 함수
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String url = request.getRequestURI();
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) ip = request.getRemoteAddr();
	    System.out.println("접속한 IP ==> "+ip);
	    System.out.println("요청 받은 URL ==> "+url);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

	
	
}
