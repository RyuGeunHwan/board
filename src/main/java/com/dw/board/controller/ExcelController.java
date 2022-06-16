package com.dw.board.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dw.board.sevice.ExcelService;

/**
 * @author dw-004 2022. 6. 16.
 * @comment : excel다운로드 받는 컨트롤러
 */
@Controller
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	// 엑셀, 사진, 한글, PDF, Zip, 영상 파일 등등.. return type이 없음. 에브리바디 모두 void or ResponseEnity
	// 페이지 이름으로 return(X)
	@GetMapping("/excel")
	// try/catch 문법을 여러번 써야할 경우 메소드에 throws Exception를 적어줘서 한번에 잡을 수 있다.
	// HttpServletResponse response를 이용해서 엑셀파일로 데이터를 보냄.
	public void downloadExcelFile(HttpServletResponse response) throws Exception {
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String title = "DW아카데미_게시판";

		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(today + "_" + title, "UTF-8") + ".xls");// 엑셀 파일이름 수정
		Workbook workBook = excelService.makeExcelForm();
		workBook.write(response.getOutputStream());
		workBook.close();

		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

}
