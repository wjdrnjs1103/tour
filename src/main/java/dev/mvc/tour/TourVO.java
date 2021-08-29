package dev.mvc.tour;

/*
 
 CREATE TABLE tour(
    tourno                            INT    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '투어 번호',
    tourgrpno                       INT    NOT NULL COMMENT '투어 그룹 번호',
    name                              VARCHAR(20)    NOT NULL COMMENT '투어 이름',
    rdate                               DATETIME     NOT NULL COMMENT '등록일',
    cnt                                 INT(10)    NOT NULL COMMENT '관련 자료 수',
  FOREIGN KEY (tourgrpno) REFERENCES tourgrp (tourgrpno)
) COMMENT='투어';

*/

public class TourVO {
  
  private int tourno;  

  private int tourgrpno;

  private String name;

  private String rdate;

  private int cnt;

  public int getTourno() {
    return tourno;
  }

  public void setTourno(int tourno) {
    this.tourno = tourno;
  }

  public int getTourgrpno() {
    return tourgrpno;
  }

  public void setTourgrpno(int tourgrpno) {
    this.tourgrpno = tourgrpno;
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

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  
  
  
}
