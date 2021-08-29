package dev.mvc.bookmark;

/*
    CREATE TABLE bookmark(
    bookmarkno                   INT    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 번호',
    bookmarkgrpno              INT    NOT NULL COMMENT '즐겨찾기 그룹 번호 ',
    name                           VARCHAR(20)    NOT NULL COMMENT '즐겨찾기 이름',
    link                              VARCHAR(50)    NOT NULL COMMENT '즐겨찾기 링크',
    rdate                            DATETIME     NOT NULL COMMENT '등록일',
    FOREIGN KEY (bookmarkgrpno) REFERENCES bookmarkgrp (bookmarkgrpno)
    ) COMMENT='즐겨찾기';
*/

public class BookmarkVO {
  
  private int bookmarkno;
  
  private int bookmarkgrpno;
  
  private String name;
  
  private String link;
  
  private String rdate;

  public int getBookmarkno() {
    return bookmarkno;
  }

  public void setBookmarkno(int bookmarkno) {
    this.bookmarkno = bookmarkno;
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

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}
