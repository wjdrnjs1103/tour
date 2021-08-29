/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE MEMBER(
		MEMBERNO                      		INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '회원 번호',
		ID                            		VARCHAR(20)		 NOT NULL COMMENT '아이디(UQ)',
		PASSWD                        		VARCHAR(60)		 NOT NULL COMMENT '패스워드',
		MNAME                         		VARCHAR(30)		 NOT NULL COMMENT '성명',
		TEL                           		VARCHAR(14)		 NOT NULL COMMENT '전화번호',
		ZIPCODE                       		VARCHAR(10)		 DEFAULT 0		 NULL  COMMENT '우편번호',
		ADDRESS1                      		VARCHAR(80)		 DEFAULT 0		 NULL  COMMENT '주소1',
		ADDRESS2                      		VARCHAR(50)		 DEFAULT 0		 NULL  COMMENT '주소2',
		MDATE                         		DATE		 NOT NULL COMMENT '가입일',
		GRADE                         		INT(10)		 NOT NULL COMMENT '등급'
) COMMENT='회원';

INSERT INTO member(id, passwd, mname, tel, zipcode,
                                 address1, address2, mdate, grade)
VALUES ('qnaadmin', '1234', '질문답변관리자', '000-0000-0000', '12345',
             '서울시 종로구', '관철동', NOW(), 1);           
INSERT INTO member(id, passwd, mname, tel, zipcode,
                                 address1, address2, mdate, grade)
VALUES ('abcdefgh', '1234', '댓글관리자', '000-0000-0000', '12345',
             '서울시 종로구', '관철동', NOW(), 1);         
				 				 
SELECT memberno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
ORDER BY memberno ASC;    

INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('user1', '1234', '왕눈이', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);
INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('user2', '1234', '키득키', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);
INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('user3', '1234', '김룰루', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);

INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('team1', '1234', '개발팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);
INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('team2', '1234', '웹퍼블팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);
INSERT INTO member(id, passwd, mname, tel, zipcode, address1, address2, mdate, grade)
VALUES ('team3', '1234', '디자인팀', '000-0000-0000', '12345', '서울시 종로구', '관철동', NOW(), 15);

COMMIT;

DROP TABLE member;
 
2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력
 
SELECT memberno, id, passwd, mname, tel, zipcode, address1, address2, mdate
FROM member
ORDER BY memberno ASC;
 
   
3. 조회
 
1) user1 사원 정보 보기
SELECT memberno, id, passwd, mname, tel, zipcode, address1, address2, mdate, grade
FROM member
WHERE memberno = 1;
 
SELECT memberno, id, passwd, mname, tel, zipcode, address1, address2, mdate
FROM member
WHERE id = 'user1';
 
    
4. 수정
UPDATE member 
SET mname='아로미', tel='111-1111-1111', zipcode='00000',
      address1='경기도', address2='파주시', grade=14
WHERE memberno=1;

COMMIT;

 
5. 삭제
1) 모두 삭제
DELETE FROM member;
 
2) 특정 회원 삭제
DELETE FROM member
WHERE memberno=15;

COMMIT;

 
6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE memberno=1;

COMMIT;
 
 
7. 로그인
bookmark
 cnt
 ---
   0