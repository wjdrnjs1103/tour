package dev.mvc.bookmarkgrp;

/*
 * CREATE TABLE bookmarkgrp(
    bookmarkgrpno                INT    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 그룹 번호 ',
    name                             VARCHAR(10)    NOT NULL COMMENT '즐겨찾기 그룹 이름',
    rdate                             DATETIME     NOT NULL COMMENT '등록일'
    ) COMMENT='즐겨찾기 그룹';

 */

public class BookmarkgrpVO {
  
  private int bookmarkgrpno;
  
  private String name;
  
  private String rdate;
 
  public BookmarkgrpVO() {
    
  }
  
  public BookmarkgrpVO(int bookmarkgrpno, String name, String rdate) {
    this.bookmarkgrpno = bookmarkgrpno;
    this.name = name;
    this.rdate = rdate;
  } 
  
  

  public int getBookmarkgrpno() {
    return bookmarkgrpno;
  }

  public void setBookmarkgrpno(int bookmarkgrpno) {
    this.bookmarkgrpno = bookmarkgrpno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
}  
  