/**********************************/
/* Table Name: 즐겨찾기 */
/**********************************/
CREATE TABLE bookmark(
    bookmarkno                        INT(10)    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 번호',
    bookmarkgrpno                     INT(10)    NOT NULL COMMENT '즐겨찾기 그룹 번호',
    name                              VARCHAR(20)    NOT NULL COMMENT '즐겨찾기 이름',
    link                              VARCHAR(50)    NOT NULL COMMENT '즐겨찾기 링크',
    rdate                             DATETIME     NOT NULL COMMENT '등록일',
  FOREIGN KEY (bookmarkgrpno) REFERENCES bookmarkgrp (bookmarkgrpno)
) COMMENT='즐겨찾기';

SELECT * FROM bookmark;

DROP TABLE bookmark


INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(1, '강화도카페', 'http://www.cafe1.co.kr', NOW());
INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(1, '서울cafe', 'http://www.cafe2.co.kr', NOW());
INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(2, '안면도 캠핑장', 'http://www.camping.co.kr', NOW());
INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(3, '자바', 'http://www.oracle.com', NOW());
INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(3, '오라클', 'http://www.oracle.com', NOW());
INSERT INTO bookmark(bookmarkgrpno, name, link, rdate)
VALUES(3, '오라클zmf', 'http://www.oracle.com', NOW());

SELECT r.bookmarkgrpno as r_bookmarkgrpno, r.name as r_name,
           c.bookmarkno, c.bookmarkgrpno, c.name, c.rdate, c.link
FROM bookmarkgrp r, bookmark c
WHERE r.bookmarkgrpno = c.bookmarkgrpno
ORDER BY bookmarkgrpno ASC, bookmarkno ASC;

SELECT COUNT(*)as cnt
FROM bookmark;

SELECT COUNT(*) FROM bookmark;

DELETE FROM bookmark 
WHERE bookmarkno = 6;

DROP TABLE bookmark;

