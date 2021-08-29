/**********************************/
/* Table Name: 주문_결재 */
/**********************************/
CREATE TABLE order_pay(
		order_payno                   		INTEGER(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '주문 번호',
		MEMBERNO                      		INTEGER(10)		 NULL  COMMENT '회원 번호',
		rname                         		VARCHAR(30)		 NOT NULL COMMENT '수취인성명',
		rtel                          		VARCHAR(14)		 NOT NULL COMMENT '수취인 전화번호',
		rzipcode                      		VARCHAR(5)		 NULL  COMMENT '수취인 우편번호',
		raddress1                     		VARCHAR(80)		 NOT NULL COMMENT '수취인 주소1',
		raddress2                     		VARCHAR(50)		 NOT NULL COMMENT '수취인 주소2',
		paytype                       		INTEGER(1)		 DEFAULT 0		 NOT NULL COMMENT '결재 종류',
		amount                        		INTEGER(10)		 DEFAULT 0		 NOT NULL COMMENT '결재금액',
		rdate                         		DATE		 NOT NULL COMMENT '주문날짜',
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
) COMMENT='주문_결제';

-- 등록  
-- 결재 종류(paytype):  1: 신용 카드, 2: 모바일, 3: 포인트, 4: 계좌 이체, 5: 직접 입금  
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (1, 1, '홍길순', '111-2222-3333', '12345',
             '서울시 종로구', '관철동', 1, 32000, NOW());
             
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (2, 1, '아로미', '111-2222-3333', '12345',
             '서울시 종로구', '관철동', 1, 15000, NOW());
             
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (3, 1, '왕눈이', '111-2222-3333', '12345',
             '서울시 종로구', '관철동', 1, 63000, NOW());


-- 목록
SELECT order_payno, memberno, rname, rtel, rzipcode, raddress1, raddress2, paytype, amount, rdate
FROM order_pay
ORDER BY order_payno ASC;