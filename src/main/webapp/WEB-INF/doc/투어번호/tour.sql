
/**********************************/
/* Table Name: 투어  */
/**********************************/
CREATE TABLE tour(
		tourno                        		INT(20)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '투어 번호',
		tourgrpno                     	INT(10)		 NOT NULL COMMENT '투어 그룹 번호',
		name                          		VARCHAR(20)		 NOT NULL COMMENT '투어 이름',
		rdate                         	  	DATETIME		 NOT NULL COMMENT '등록일',
		cnt                           		  INT(10)		 NOT NULL COMMENT '관련 자료 수',
  FOREIGN KEY (tourgrpno) REFERENCES tourgrp (tourgrpno)
) COMMENT='투어';

DROP TABLE tour;

INSERT INTO tour(tourgrpno, name, rdate, cnt)
VALUES(1, '국내 여행', NOW(), 0);
INSERT INTO tour(tourgrpno, name, rdate, cnt)
VALUES(1, '해외 여행', NOW(), 0);
INSERT INTO tour(tourgrpno, name, rdate, cnt)
VALUES(1, '패키지 여행', NOW(), 0);

SELECT r.tourgrpno as r_tourgrpno, r.name as r_name,
           c.tourno, c.tourgrpno, c.name, c.rdate, c.cnt
FROM tourgrp r, tour c
WHERE r.tourgrpno = c.tourgrpno
ORDER BY tourgrpno ASC, tourno ASC;

SELECT tourno, tourgrpno, name, rdate, cnt
FROM tour
WHERE tourno=1;

UPDATE tour
SET tourgrpno=1, name='가자고', cnt=0
WHERE tourno = 6;

SELECT * FROM tour;

commit;



SELECT * FROM tour;
