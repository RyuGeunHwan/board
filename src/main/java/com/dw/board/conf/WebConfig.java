package com.dw.board.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dw.board.interceptor.Interceptor;

@Configuration
// @MapperScan을 붙여 해당 경로가 mybatis mapper 경로야~ 라고 설정
@MapperScan(value= {"com.dw.board.mapper"})
public class WebConfig implements WebMvcConfigurer {
	//의존성 주입
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
