/**********************************/
/* Table Name: 투어 그룹 */
/**********************************/
CREATE TABLE tourgrp(
		tourgrpno                     		INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '투어 그룹 번호',
		name                          		VARCHAR(20)		 NOT NULL COMMENT '이름',
		seqno                         		INT(20)		 DEFAULT 0		 NOT NULL COMMENT '출력 순서',
		rdate                         		DATETIME		 NOT NULL COMMENT '그룹 생성일'
) COMMENT='투어 그룹';

SELECT * FROM tourgrp;

INSERT INTO tourgrp(name, seqno, rdate)
VALUES( '여행', 1, NOW());
INSERT INTO tourgrp(name, seqno, rdate)
VALUES( '항공', 2, NOW());
INSERT INTO tourgrp(name, seqno, rdate)
VALUES( '호텔', 3, NOW());

commit; 
