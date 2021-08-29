/**********************************/
/* Table Name: 쇼핑카트 */
/**********************************/
CREATE TABLE CART(
		CARTNO                        		INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '쇼핑카트 번호',
		CONTENTSNO                    		INT(10)		 NULL  COMMENT '컨텐츠 번호',
		MEMBERNO                      		INT(10)		 NOT NULL  COMMENT '회원 번호',
		CNT                           		INT(10)	 DEFAULT 0		 NOT NULL COMMENT '수량',
		RDATE                         		DATE		 NOT NULL COMMENT '날짜',
  FOREIGN KEY (CONTENTSNO) REFERENCES CONTENTS (CONTENTSNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
) COMMENT='쇼핑카트';

-- INSERT
SELECT contentsno, title, price FROM contents;  -- 1번 사용 확인
SELECT memberno, mname FROM member;  -- 2번 사용 확인

INSERT INTO cart(cartno, contentsno, memberno, cnt, rdate)
VALUES(1, 1, 1, 1, NOW());

INSERT INTO cart(cartno, contentsno, memberno, cnt, rdate)
VALUES(2, 2, 1, 1, NOW());
commit;

DROP TABLE cart;


-- LIST
SELECT cartno, contentsno, memberno, cnt, rdate FROM cart ORDER BY cartno ASC;

    CARTNO CONTENTSNO   MEMBERNO        CNT RDATE              
---------- ---------- ---------- ---------- -------------------
         1          9          1          1 2021-06-16 05:08:26
         2         10          1          1 2021-06-16 05:08:26
         
-- LIST contents join
SELECT t.cartno, c.contentsno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.cnt, t.rdate 
FROM contents c, cart t
WHERE c.contentsno = t.contentsno
ORDER BY cartno ASC;

         
-- READ
SELECT t.cartno, c.contentsno, c.title, c.price, t.memberno, t.cnt, t.rdate 
FROM contents c, cart t
WHERE (c.contentsno = t.contentsno) AND t.cartno=1;


-- UPDATE
UPDATE cart
SET cnt=2
WHERE cartno=1;
commit;

-- DELETE
DELETE FROM cart WHERE cartno=2;
commit;
