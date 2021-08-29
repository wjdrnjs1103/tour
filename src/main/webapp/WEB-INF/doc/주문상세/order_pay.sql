/**********************************/
/* Table Name: 주문상세 */
/**********************************/
CREATE TABLE order_item(
		order_itemno                  		INTEGER(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '주문상세번호',
		MEMBERNO                      		INTEGER(10)		 NULL  COMMENT '회원 번호',
		order_payno                   		INTEGER(10)		 NOT NULL COMMENT '주문 번호',
		CONTENTSNO                    		INTEGER(10)		 NULL  COMMENT '컨텐츠 번호',
		cnt                           		INTEGER(5)		 DEFAULT 1		 NOT NULL COMMENT '수량',
		tot                           		INTEGER(10)		 DEFAULT 0		 NOT NULL COMMENT '합계',
		stateno                       		INTEGER(1)		 DEFAULT 0		 NOT NULL COMMENT '주문상태',
		rdate                         		DATE		 NOT NULL COMMENT '주문날짜',
  FOREIGN KEY (order_payno) REFERENCES order_pay (order_payno),
  FOREIGN KEY (CONTENTSNO) REFERENCES CONTENTS (CONTENTSNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
) COMMENT='주문상세';

DROP TABLE order_item

-- 등록  
-- 배송 상태(stateno):  1: 결재 완료, 2: 상품 준비중, 3: 배송 시작, 4: 배달중, 5: 오늘 도착, 6: 배달 완료  
INSERT INTO order_item(order_itemno, memberno, order_payno, contentsno, cnt, tot, stateno, rdate)
VALUES (1, 1, 1, 1, 1, 10000, 1, NOW());   -- (contentsno)1번 사용 확인

-- 전체 목록
SELECT order_itemno, memberno, order_payno, contentsno, cnt, tot, stateno, rdate
FROM order_item
ORDER BY order_itemno DESC;

--회원별 목록
SELECT order_itemno, memberno, order_payno, contentsno, cnt, tot, stateno, rdate
FROM order_item
WHERE memberno=1
ORDER BY order_itemno DESC;