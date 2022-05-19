package com.dw.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dw.board.vo.StudentsVO;

@Mapper
public interface StudentsMapper {

	// 1.메소드를 클릭
	// 2.단축키  : Alt + shift + j
	
	
	/**
	 * @param vo
	 * @return
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 18.
	 *comment : 학생저장
	 */
	public int insertStudents(StudentsVO vo);
	
	/**
	 * @return
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 18.
	 *comment : 학생조회
	 */
	public List<StudentsVO> selectAllStudentsList();
	
	public List<Map<String,Object>> selectAllStudentsListByMap();
	
	public StudentsVO selectStudents(int studentsId);
	
	public int deleteStudents(int studentsId);
	
	public int updateStudents(StudentsVO vo);
	
	
	
	/**
	 * @param vo
	 * @return : StudentsVO
	 * @author : Geunhwan Ryu
	 * @date : 2022. 5. 19.
	 *comment : 학생이름으로 학생정보 조회
	 */
	public StudentsVO selectStudentsOne(StudentsVO vo);
}
