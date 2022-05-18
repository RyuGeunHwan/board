package com.dw.board.vo;

import lombok.Data;

@Data
public class BoardVO extends StudentsVO {
	private int boardId;
	private String title;
	private String content;
	private String updateAt;
	private String crateAt;

	
}
