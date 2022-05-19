CREATE TABLE 회원가입
(
member_id INTEGER(5) AUTO_INCREMENT NOT NULL COMMENT '자동 1씩 증가',
password varchar(200) NOT NULL COMMENT '비밀번호 단방향 암호화',
member_name varchar(10) NOT NULL,
gender varchar(1) CHECK (gender IN ('M','W')) COMMENT 'M : 남자 , W : 여자',
phone_number varchar(15),
email varchar(30),
CONSTRAINT member_id_PK PRIMARY KEY (member_id)
);



