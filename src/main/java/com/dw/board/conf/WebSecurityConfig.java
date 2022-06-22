// Security 설정 관련 클래스
// WebSecurityConfig 클래스
package com.dw.board.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dw.board.sevice.HomeService;

@Configuration
@EnableWebSecurity
@MapperScan(value= {"com.dw.board.mapper"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	//@EnableWebSecurity을 WebSecurityConfigurerAdapter를 상속하는 객체에 붙여주면 SpringSecurityFilterChain에 등록된다.
	
	@Autowired
	private HomeService homeService;
	
	//Bean으로 등록되어 있는 메소드(WebsecurityConfig 클래스 참고)
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider(HomeService homeService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(homeService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
      //실제 인증을 진행할 Provider 
		auth.authenticationProvider(authenticationProvider(homeService));
    }
	
	
	@Override
	// WebSecurity : 필터 체인 관련 전역 설정을 처리할 수 있는 API 제공
	// 이미지,자바스크립트,css 디렉토리 보안 설정 
	public void configure(WebSecurity web) throws Exception {
		// 매칭되는 uri가 필터 체인을 적용하지 않도록 설정 
        web.ignoring().antMatchers("/static/**");
        //이미지 파일, css 파일, 자바스크립트 파일 을 접근 가능하게 처리하는 소스
	}
	
	@Override
	// HttpSecurity : 세부적인 보안기능 설정을 처리할 수 있는 API 제공 
	// //HTTP 관련 보안 설정 **가장 중요 
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable() //csrf(Cross site Request forgery) 보호기능
			.formLogin().disable()
			.csrf().ignoringAntMatchers("/user/save");
		http
			.authorizeRequests()
			.antMatchers("/user/save").permitAll()
			.antMatchers("/").hasAnyAuthority("ADMIN","USER")
			.anyRequest().authenticated()
        .and()
	        // 로그인 폼 기능 세부설정 
	        .formLogin()
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.deleteCookies("JSESSIONID")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
	}

}


