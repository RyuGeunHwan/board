package com.dw.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.sevice.LogsService;
import com.dw.board.vo.LogVO;



//특정 Controller의 핸들러가 실행되기 전이나 후에 추가적인 작업을 원할때 Interceptor를 사용
@Component  // @Component : 본인이 만든 클래스를 Bean으로 등록할 때 쓰는 어노테이션 
			// @Bean : Spring에서 제공해주는 클래스, 외부 라이브러리를 Bean으로 등록할 때 쓰는 어노 테이션
public class Interceptor implements HandlerInterceptor {

	@Autowired
	private LogsService logsService;
	
	// preHandle : controller에 도착하기 전에 요청을 가로채는 함수
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String url = request.getRequestURI();
		String ip = request.getHeader("X-Forwarded-For");
		String httpMethod = request.getMethod();
		if (ip == null) ip = request.getRemoteAddr();
	    System.out.println("접속한 IP ==> "+ip);
	    System.out.println("요청 받은 URL ==> "+url);
	    System.out.println("HTTP Method ==> "+httpMethod);
	    
	    SimpleDateFormat formatter = 
	    		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
	    // ***Locale.KOREA 사용 이유 : 한국 시간으로 강제로 맞추기 위해서 사용
	    String time = formatter.format(Calendar.getInstance().getTime());
	    
	    System.out.println("time ==> "+time);
	    
	    // insert는 interceptor에서 실행 <-> select는 controller에서 실행
	    LogVO vo = new LogVO();
	    vo.setUrl(url);
	    vo.setIp(ip);
	    vo.setHttpMethod(httpMethod);
	    vo.setLatitude("36.3519375");
	    vo.setLongitude("127.3781875");
	    vo.setCreateAt(time);
	    logsService.setLogs(vo);
	    
		return true;
	}
//	    출력 결과
//	    접속한 IP ==> 0:0:0:0:0:0:0:1
//	    요청 받은 URL ==> /api/v1/board
//	    HTTP Method ==> GET
//	    time ==> 2022-06-03 10:09:55
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

	
	
}
