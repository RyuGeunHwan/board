package com.dw.board.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dw.board.interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	// Interceptor에 @component어노테이션이 없으면 @Autowired사용할 수 없음.
	@Autowired // === new로 호출하는것과 같음
	private Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//우리가 만든 interceptor를 스프링에 등록
		registry.addInterceptor(interceptor).excludePathPatterns(
				"/api/v1/logs",
				"/login",
				"/join",
				"/api/v1/login",
				"/resources/static/css/*",
				"/resources/static/js/*",
				"/resources/static/images/*",
				"/error");
		// .excludePathPatterns를 설정하게 되면 중간에 interceptor되지 않는다.
	}
	
}
