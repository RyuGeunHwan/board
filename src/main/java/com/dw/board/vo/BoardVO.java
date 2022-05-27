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
}
