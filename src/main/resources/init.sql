-- CREATE database IF NOT EXISTS dw DEFAULT SET utf8 COLLATE utf8_general_ci;
--	-- 만약에 dw라는 데이터베이스가 없다면 만들어라 라는 쿼리!
-- USE dw; 
--	-- USE : 데이터베이스를 만들면 사용하겠다 라는 쿼리!


-- 학생 테이블
-- IF NOT EXISTS : 만약 내가 ~할 테이블이 없다면 ~해라 라는 쿼리.
-- IF NOT EXISTS는 안전장치라고 보면 된다. 만약 없으면 ~해라 라는 해석이 되기 때문에.
CREATE TABLE IF NOT EXISTS students(
    students_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '학생 아이디',
    students_name VARCHAR(20) COMMENT '학생 이름',
    students_password VARCHAR(200) COMMENT '학생 비밀번호',
    -- 비밀번호는 개발자도 알지 못하게 *암호화를 하기 때문에 테이블을 설계할 때 글자 수 제한을 크게 잡아야한다!
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입 날짜'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 게시판 테이블
-- IF NOT EXISTS : 만약 내가 ~할 테이블이 없다면 ~해라 라는 쿼리.
CREATE TABLE IF NOT EXISTS board
(
    board_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '게시판 아이디',
    students_id INTEGER(4) COMMENT '학생 아이디',
    title VARCHAR(50) COMMENT '제목',
    content VARCHAR(100) COMMENT '글 내용',
    update_at DATETIME COMMENT '수정 날짜',
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '작성 날짜',
    CONSTRAINT board_students_id_fk FOREIGN KEY (students_id) REFERENCES students(students_id)
    -- 게시판테이블에 FK가 있는 이유는 테이블 구조가 1(학생):N(게시판)이기 때문이다.
    -- 1:N은 한 학생이 여러개의 게시판을 작성할 수 있는 구조가 된다.
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;