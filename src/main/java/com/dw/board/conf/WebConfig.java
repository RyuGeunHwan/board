package com.dw.board.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dw.board.interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//우리가 만든 interceptor를 스프링에 등록
		registry.addInterceptor(interceptor).excludePathPatterns("/api/v1/logs");
		// .excludePathPatterns를 설정하게 되면 중간에 interceptor되지 않는다.
		registry.addInterceptor(interceptor).excludePathPatterns("/api/v1/students/map");

	}
	
	
	

}
