package dev.mvc.tourgrp;

/*
tourgrpno           INT NOT NULL PRIMARY KEY,
name                 VARCHAR(30) NOT NULL,
seqno                INT DEFAULT 0  NOT NULL,
rdate                 DATETIME NOT NULL
*/

public class TourgrpVO {
 
  private int tourgrpno;

  private String name;
 
  private int seqno;

  private String rdate;

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

  public int getSeqno() {
    return seqno;
  }

  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
  @Override
  public String toString() {
    return "TourgrpVO [tourgrpno=" + tourgrpno + ", name=" + name + ", seqno=" +seqno+ ", rdate=" + rdate + "]";
  }//변수 값 변수 값..... 소스 >> 투 스트링 
  
}
