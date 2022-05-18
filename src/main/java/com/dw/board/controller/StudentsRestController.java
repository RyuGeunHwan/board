package com.dw.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// post는 body로 데이터를 받는다.
	//보안을 목적으로 하기때문에 body로 받는다.
	// ex) password가 url에 보이면 안되기 때문에!
	// 학생 저장
	@PostMapping("/students") ///api/v1/students : api주소이고 버전은 1이다 라는것을 알 수 있음
	public int callSaveStudents(@RequestBody StudentsVO vo) {
		return studentsSevice.getInsertStudents(vo);
	}
	
	// VO로 학생조회
	@GetMapping("/students")
	public List<StudentsVO> callStudentsList(){
		return studentsSevice.getAllStudentsList();
	}
	
	// Map으로 학생조회
	@GetMapping("/students/map")
	public List<Map<String,Object>> callStudentsListByMap(){
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
