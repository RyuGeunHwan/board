package com.dw.board.sevice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dw.board.mapper.HomeMapper;
import com.dw.board.vo.UserPrincipalVO;
import com.dw.board.vo.UserVO;

@Service
public class SecurityService implements UserDetailsService {
	
	@Autowired
	private HomeMapper homeMapper;
	
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    	 //DB로부터 회원 정보를 가져온다.
		ArrayList<UserVO> userAuthes = homeMapper.findByUserId(id);
		
		if(userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User "+id+" Not Found!");
		}
		
		return new UserPrincipalVO(userAuthes); //UserDetails 클래스를 상속받은 UserPrincipalVO 리턴한다.
    }
}