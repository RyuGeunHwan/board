package com.dw.board.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int boardId;
	private int studentsId;
	private String title;
	private String content;
	private String updateAt;
	private String createAt;
	private int cnt; // 조회수
	
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getStudentsId() {
		return studentsId;
	}
	public void setStudentsId(int studentsId) {
		this.studentsId = studentsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
