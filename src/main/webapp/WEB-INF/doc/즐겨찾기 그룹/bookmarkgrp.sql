/**********************************/
/* Table Name: 즐겨찾기 그룹 */
/**********************************/
CREATE TABLE bookmarkgrp(
    bookmarkgrpno               INT(10)    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 그룹 번호 ',
    name                             VARCHAR(10)    NOT NULL COMMENT '즐겨찾기 그룹 이름',
    rdate                             DATETIME     NOT NULL COMMENT '등록일',
) COMMENT='즐겨찾기 그룹';

SELECT * FROM bookmarkgrp;

INSERT INTO bookmarkgrp(name, rdate )
VALUES('카페', NOW());
INSERT INTO bookmarkgrp(name, rdate )
VALUES('캠핑', NOW());
INSERT INTO bookmarkgrp(name, rdate )
VALUES('개발', NOW());

commit