/**********************************/
/* Table Name: 컨텐츠-여행 상품 */
/**********************************/
CREATE TABLE CONTENTS(
		CONTENTSNO                    INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '컨텐츠 번호',
		ADMINNO                       INT(10)		 NOT NULL COMMENT '관리자 번호',
		tourno                        INT(10)		 NULL  COMMENT '투어 번호',
		TITLE                         VARCHAR(100)		 NOT NULL COMMENT '제목',
		CONTENT                       VARCHAR(4000)		 NOT NULL COMMENT '내용',
		RECOM                         INT(10)   DEFAULT 0		 NOT NULL COMMENT '추천수',
		CNT                           INT(10)   DEFAULT 0		 NOT NULL COMMENT '조회수',
		REPLYCNT                      INT(10)   DEFAULT 0		 NOT NULL COMMENT '댓글수',
		PASSWD                        VARCHAR(20)		 NOT NULL COMMENT '패스워드',
		WORD                          VARCHAR(300)		 NULL  COMMENT '검색어',
		RDATE                         DATE		 NOT NULL COMMENT '등록일',
		FILE1                         VARCHAR(100)		 NULL  COMMENT '메인 이미지',
		FILE1SAVED                    VARCHAR(100)		 NULL  COMMENT '실제 저장된 메인 이미지',
		THUMB1                        VARCHAR(100)		 NULL  COMMENT '메인 이미지 Preview',
		SIZE1                         INT(10)   DEFAULT 0		 NULL  COMMENT '메인 이미지 크기',
		PRICE                         INT(10)   DEFAULT 0		 NOT NULL COMMENT '정가',
		DC                            INT(10)   DEFAULT 0		 NOT NULL COMMENT '할인률',
		SALEPRICE                     INT(10)   DEFAULT 0		 NULL  COMMENT '판매가',
		POINT                         INT(10)   DEFAULT 0		 NOT NULL COMMENT '포인트',
		SALECNT                       INT(10)   DEFAULT 0		 NOT NULL COMMENT '가능 인원',
  FOREIGN KEY (tourno) REFERENCES tour (tourno),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
) COMMENT='컨텐츠-여행 상품';

INSERT INTO contents(adminno, tourno, title, content, recom, cnt, replycnt, passwd, word, rdate,
                              file1, file1saved, thumb1, size1, price, dc, saleprice, point, salecnt)
VALUES(1, 1, '[제주도]애월', '[제주에어카텔/ 제주 드라이브 코스추천]항공+마레보리조트+렌터카3일', 1, 1, 1, '123', '제주도여행', NOW(),
            'trip.jpg', 'trip_1.jpg', 'trip_t.jpg', 1000, 185000, 5, 175850, 3700, 100);
            
INSERT INTO contents(adminno, tourno, title, content, recom, cnt, replycnt, passwd, word, rdate,
                              file1, file1saved, thumb1, size1, price, dc, saleprice, point, salecnt)
VALUES(1, 1, '[제주도]서귀포', '[제주에어카텔/샹그릴라요트+카멜리아힐+승마쿠폰 제공] 항공+서귀포KAL+렌터카3일', 1, 1, 1, '123', '제주도여행', NOW(),
            'trip.jpg', 'trip_1.jpg', 'trip_t.jpg', 1000, 266000, 3, 258020, 5320, 80);            
            
SELECT contentsno, adminno, tourno, title, content, recom, cnt, replycnt, passwd, word, rdate,
                              file1, file1saved, thumb1, size1, price, dc, saleprice, point, salecnt 
    FROM contents
    WHERE title LIKE CONCAT('%', '3', '%')
    ORDER BY tourno DESC;
 

-- 검색 + 페이징 + 메인 이미지
-- 1 page
SELECT contentsno, adminno, tourno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, price, dc, saleprice, point
FROM contents
WHERE tourno=1 AND word LIKE '%서귀포%'
ORDER BY contentsno DESC
LIMIT 0, 3;

-- 2 page
SELECT contentsno, adminno, tourno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, price, dc, saleprice, point
FROM contents
WHERE tourno=1 AND word LIKE '%서귀포%'
ORDER BY contentsno DESC
LIMIT 3, 3;

-- 3 page
SELECT contentsno, adminno, tourno, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, price, dc, saleprice, point
FROM contents
WHERE tourno=1 AND word LIKE '%서귀포%'
ORDER BY contentsno DESC
LIMIT 6, 3;

-- 등록 기능 제작 응용 상품 정보의 등록
UPDATE contents
SET price=5000, dc=10, saleprice=4500, point=250
WHERE contentsno = 7;

commit;







DROP TABLE contents

DELETE FROM contents 
WHERE contentsno = 4;


SELECT contentsno, adminno, tourno, title, content, recom, cnt, replycnt, passwd, word, rdate,
                              file1, file1saved, thumb1, size1, price, dc, saleprice, point, salecnt
FROM contents
ORDER BY contentsno ASC;
