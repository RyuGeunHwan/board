package com.dw.board.sevice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.HomeMapper;
import com.dw.board.vo.UserPrincipalVO;
import com.dw.board.vo.UserVO;

@Service
public class HomeService implements UserDetailsService{

	@Autowired
	private HomeMapper homeMapper;
	//Bean으로 등록되어 있는 메소드(WebsecurityConfig 클래스 참고)
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* DB에서 유저정보를 불러온다.
	 * Custom한 Userdetails 클래스를 리턴 해주면 된다.
	 * */
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		ArrayList<UserVO> userAuthes = homeMapper.findByUserId(id);
		
		if(userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User "+id+" Not Found!");
		}
		
		return new UserPrincipalVO(userAuthes);
	}
	
	// 오류 catch(Insert, Delete, Update할 때는 @Transactional 꼭 써주기! )
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String InsertUser(UserVO userVO) {
		System.out.println(userVO);
		System.out.println(userVO.getId());
		System.out.println(userVO.getPassword());
		System.out.println(userVO.getName());
		userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
		int flag = homeMapper.userSave(userVO);		
		if (flag > 0) {

			int userNo = homeMapper.findUserNo(userVO.getId());
			int roleNo = homeMapper.findRoleNo(userVO.getRoleName());

			homeMapper.userRoleSave(userNo, roleNo);

			return "success";
		}	 	
		return "fail";
	}

}