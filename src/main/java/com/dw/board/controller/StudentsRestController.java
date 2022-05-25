package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dw.board.sevice.StudentsService;
import com.dw.board.vo.StudentsVO;

@RestController
@RequestMapping("/api/v1")
// @RequestMapping("") : url 중복되는 주소를 한꺼번에 정의한는 어노테이션
// url주소 앞에 /api/v1 이(가) 자동으로 붙음.
// url주소를 검색할 때 /api/v1를 붙여야함.
public class StudentsRestController {

	@Autowired
	private StudentsService studentsSevice;
	
	
	// 중요한 정보를 서버에 전송할 때 POST 사용!!!
	// DB데이터와 입력데이터를 비교하는 POST메소드
	@CrossOrigin
	@PostMapping("/login")
	public boolean callIsLogin(@RequestBody StudentsVO vo, HttpSession httpSession) {//HttpSession : 세션 저장 클래스
		boolean isLogin = studentsSevice.isStudents(vo);// 로그인을 할때 입력한 비밀번호와 DB에 있는 비밀번호가 일치하는지 확인하는 boolean형 메소드
		if(isLogin) {
			//HttpSession 저장하는 방식 : Key, value
			// public void setAttribute(String name, Object value); => HttpSession 클래스에 있는 메소드
			httpSession.setAttribute("name", vo.getStudentsName());
		}
		return isLogin;
	}
	
	// post는 body로 데이터를 받는다.
	//보안을 목적으로 하기때문에 body로 받는다.
	// ex) password가 url에 보이면 안되기 때문에!
	// 학생 저장
	@CrossOrigin //템플릿엔진을 사용하지 않았을때 쓰는 어노테이션
	@PostMapping("/students") ///api/v1/students : api주소이고 버전은 1이다 라는것을 알 수 있음
	public int callSaveStudents(@RequestBody StudentsVO vo) {
		return studentsSevice.getInsertStudents(vo);
	}
	
	// VO로 학생조회
	@CrossOrigin //템플릿엔진을 사용하지 않을경우 결과를 출력하고 싶으면 CrossOrigin어노테이션을 붙여주어야한다.
	@GetMapping("/students")
	public List<StudentsVO> callStudentsList(){
		return studentsSevice.getAllStudentsList();
	}
	
	// Map으로 학생조회
	@CrossOrigin
	@GetMapping("/students/map")
	public List<Map<String,Object>> callStudentsListByMap(HttpSession httpSession){
		
		//public Object getAttribute(setAttribute함수의 key값을 입력);
		// 접근 방법은 Map과 동일 : get("key명")
		// 세션 데이터 가져오기(추후 로직 구현 예정)
//		String name = (String)httpSession.getAttribute("name"); 
//		System.out.println("세션에서 가져온 이름은 ===> "+ name);
//		if(name == null) { // 로그인 한 사람이 없거나 로그인 정보가 틀렸으면 return null;
//			return null;
//		}
		return studentsSevice.getAllStudentsListByMap();
	}
	
	// 특정 학생 조회(PK로 조회 예정)
	@GetMapping("/students/id/{id}")
	public StudentsVO callStudents(@PathVariable("id") int studentsId) {
		return studentsSevice.getSelectStudents(studentsId);
	}
	
	// 특정 학생 삭제
	@DeleteMapping("/students/id/{id}")
	public int callRemoveStudents(@PathVariable("id") int studentsId) {
		return studentsSevice.removeStudents(studentsId);
	}
	
	// 특정 학생 수정
	@PatchMapping("/students/id/{id}")
	public int callUpdateStudents(@PathVariable("id") int studentsId, @RequestBody StudentsVO vo) {
		return studentsSevice.getUpdateStudents(studentsId, vo);
	}
}
