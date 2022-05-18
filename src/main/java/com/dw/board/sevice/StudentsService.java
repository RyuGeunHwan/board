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
		password = passwordEncoder.encode(password);
		vo.setStudentsPassword(password);
		int rows = studentsMapper.insertStudents(vo);
		return rows;
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
		return studentsMapper.updateStudents(vo);
	}
}
