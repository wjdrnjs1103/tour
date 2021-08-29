package dev.mvc.tour;

public class Tourgrp_TourVO {
  
  private int r_tourgrpno;
 
  private String r_name;
  
  private int tourno;  
  
  private int tourgrpno;
  
  private String name;
 
  private String rdate;
 
  private int cnt;

  public int getR_tourgrpno() {
    return r_tourgrpno;
  }

  public void setR_tourgrpno(int r_tourgrpno) {
    this.r_tourgrpno = r_tourgrpno;
  }

  public String getR_name() {
    return r_name;
  }

  public void setR_name(String r_name) {
    this.r_name = r_name;
  }

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
  
  @Override
  public String toString() {
    return "[r_tourgrpno=" + r_tourgrpno + ", r_name=" + r_name + ", tourno=" + tourno + ", tourgrpno="
        + tourgrpno + ", name=" + name + ", rdate=" + rdate + ", cnt=" + cnt + "]";
  }
  
}
