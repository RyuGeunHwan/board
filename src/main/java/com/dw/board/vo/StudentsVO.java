package com.dw.board.vo;

import lombok.Data;

@Data
public class StudentsVO {

	private int studentsId;
	private String studentsName;
	private String studentsPassword;
	private String createAt;
	public int getStudentsId() {
		return studentsId;
	}
	public void setStudentsId(int studentsId) {
		this.studentsId = studentsId;
	}
	public String getStudentsName() {
		return studentsName;
	}
	public void setStudentsName(String studentsName) {
		this.studentsName = studentsName;
	}
	public String getStudentsPassword() {
		return studentsPassword;
	}
	public void setStudentsPassword(String studentsPassword) {
		this.studentsPassword = studentsPassword;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	
}
