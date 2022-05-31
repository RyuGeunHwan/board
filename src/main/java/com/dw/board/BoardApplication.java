package com.dw.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

@SpringBootApplication
public class BoardApplication {

	public static void main(java.lang.String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
