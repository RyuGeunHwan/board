// Security 설정 관련 클래스
// WebSecurityConfig 클래스
package com.dw.board.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	//@EnableWebSecurity을 WebSecurityConfigurerAdapter를 상속하는 객체에 붙여주면 SpringSecurityFilterChain에 등록된다.
	
	//의존성 주입
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	// WebSecurity : 필터 체인 관련 전역 설정을 처리할 수 있는 API 제공 
	public void configure(WebSecurity web) throws Exception {
		// 매칭되는 uri가 필터 체인을 적용하지 않도록 설정 
        web.ignoring().antMatchers("/static/**");
	}
	
	@Override
	 // HttpSecurity : 세부적인 보안기능 설정을 처리할 수 있는 API 제공 
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable() //csrf(Cross site Request forgery) 보호기능
			.formLogin().disable();
		http
			// 공개 리소스 또는 보호받는 리소스에 대한 세부 설정
	        .authorizeRequests()
            // USER 또는 ADMIN 역할이 있어야 html 파일로 이어짐
            // 역할이 없을 경우 로그인 페이지로 이동 
            .antMatchers("/api/v1/login").hasAnyRole("USER", "ADMIN")
            .anyRequest().permitAll()
        .and()
	        // 로그인 폼 기능 세부설정 
	        .formLogin()
            .defaultSuccessUrl("/")
            .permitAll();
	}

}


