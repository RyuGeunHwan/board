-- CREATE database IF NOT EXISTS dw DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- USE dw;

-- 컬럼 추가
-- ALTER TABLE board ADD COLUMN cnt INTEGER(4) DEFAULT 0;
-- root 계정이 계정을 생성함
-- '%' 모든 IP허용
-- create user sangwon@'%' identified by '123';

-- grant로 권한 부여
-- on 데이터베이스이름.테이블이름 (dw.*)
-- grant select,insert,update on dw.* to sangwon@'%';

-- 학생 테이블
CREATE TABLE IF NOT EXISTS students(
    students_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '학생 아이디',
    students_name VARCHAR(20) COMMENT '학생 이름',
    students_password VARCHAR(200) COMMENT '학생 비밀번호',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입 날짜'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 게시판 테이블
CREATE TABLE IF NOT EXISTS board
(
    board_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 아이디',
    students_id INTEGER(4) COMMENT '학생 아이디',
    title VARCHAR(50) COMMENT '제목',
    content VARCHAR(100) COMMENT '글 내용',
    update_at DATETIME COMMENT '수정 날짜',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성 날짜',
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 접속이력 테이블
CREATE TABLE IF NOT EXISTS board_logs
(
	log_id BIGINT(20) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '로그 아이디',
	ip VARCHAR(50) COMMENT '아이피',
	latitude VARCHAR(20) COMMENT '위도',
	longitude VARCHAR(20) COMMENT '경도',
	url VARCHAR(100) COMMENT '요청 url',
	http_method VARCHAR(10) COMMENT 'http method',
	create_at DATETIME COMMENT '접속 시간'
	-- DEFAULT가 없는 이유는 서버에서 로직 실행 시간이 있어서 실제 접속시간과 DB에 저장되는 접속시간이 다를 수 있어서.
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- #DDL
CREATE TABLE IF NOT EXISTS user
(
	user_no BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	id VARCHAR(20) NOT NULL,
	password VARCHAR(100) NOT NULL,
	name VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS role
(
	role_no INT NOT NULL PRIMARY KEY,
	role_name varchar(20) NOT NULL  
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS user_role
(
  user_no BIGINT NOT NULL,
  role_no INT NOT NULL,
  FOREIGN KEY (user_no) REFERENCES user (user_no),
  FOREIGN KEY (role_no) REFERENCES role (role_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
