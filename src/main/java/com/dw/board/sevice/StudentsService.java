package com.dw.board.sevice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dw.board.mapper.StudentsMapper;
import com.dw.board.vo.StudentsVO;

@Service
public class StudentsService {

	@Autowired
	private StudentsMapper studentsMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 학생 저장
	public int getInsertStudents(StudentsVO vo) {
		// 학생비밀번호 암호화
		String password = vo.getStudentsPassword();
		// DB에 생성할 입력한 비밀번호를 가져온다.
		System.out.println("암호화 전 => "+password);
		
		password = passwordEncoder.encode(password);
		// 암호화할 클래스.메소드(파라미터)에 비밀번호를 대입해준다.
		System.out.println("암호화 후 => "+password);
		vo.setStudentsPassword(password);
		// 암호화한 비밀번호를 다시 set하여 VO필드변수에 단방향 암호화한 password를 값으로 넣어준다.
		int rows = studentsMapper.insertStudents(vo);
		return rows;
	}
	// 가입된 학생인지 아닌지 여부 체크(가입된 학생데이터와 가입할 학생데이터 비교)
		@Transactional(rollbackFor = {Exception.class})
		public boolean isStudents(StudentsVO vo) { // html에서 가져온 데이터
			
			StudentsVO student = studentsMapper.selectStudentsOne(vo);
			// Mapper에 있는 메소드 파라미터에 쿼리의 결과(암호화된 password를 담은 vo)를 받아서 
			// 새로운 클래스 타입 student에 대입!
			// 그러면 메소드(isStudents) 파라미터에 있는 vo는 HTML에서 가져온 데이터이고
			// 변수 student에 있는 vo는 DB에서 가져온 데이터이다.
			if(student == null) { 
				//쿼리 결과가 null로 리턴되면 return false;
				return false;
			}
			//inputPassword : 아직 암호화 되기전 비밀번호, password : 암호화된 비밀번호
			String inputPassword = vo.getStudentsPassword();//HTML에서 받아온 비밀번호
			String password = student.getStudentsPassword();//DB에서 가져온 비밀번호
			System.out.println("HTML에서 보낸 비밀번호 => "+inputPassword);
			System.out.println("암호화된 DB 데이터 비밀번호 => "+password);
			if(passwordEncoder.matches(inputPassword, password)) { //비밀번호 체크, 파라미터 값이 같다면(true) if문 실행
				//matches함수는 암호화된 비밀번호와 입력한 비밀번호(암호화되기 전 비밀번호)를 비교해주는 메소드이다.
				//if문에는 inputPassword와 password를 비교하여 비밀번호가 겹치지 않게 비교해주는 matche함수 사용!
				return true;
			}
			
			return false;
		}
	
	
	
	public List<StudentsVO> getAllStudentsList(){
		return studentsMapper.selectAllStudentsList();
	}
	
	public List<Map<String, Object>> getAllStudentsListByMap(){
		return studentsMapper.selectAllStudentsListByMap();
	}
	
	public StudentsVO getSelectStudents(int studentsId) {
		return studentsMapper.selectStudents(studentsId);
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int removeStudents(int studentsId) {
		int rows = studentsMapper.deleteStudents(studentsId);
		return rows;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int getUpdateStudents(int studentsId, StudentsVO vo) {
		vo.setStudentsId(studentsId);
		// vo클래스에 set을 해줌으로써 vo클래스의 필드변수에 값을 넣어준다.
		//그러면 이미 body로 받은 값과 헤더에서 받은 값("{id}")은 VO클래스에 존재하여 MyBatis에서 쿼리를 계산할 수 있다.
		return studentsMapper.updateStudents(vo);
	}
	
	
	
}
