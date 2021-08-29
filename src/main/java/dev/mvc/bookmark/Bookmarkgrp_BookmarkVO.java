package dev.mvc.bookmark;

public class Bookmarkgrp_BookmarkVO {
  /** 카테고리 그룹 번호 */
  private int r_bookmarkgrpno;
  /**  카테고리 이름 */
  private String r_name;
  
  private int bookmarkno;
  
  private int bookmarkgrpno;
  
  private String name;
  
  private String link;
  
  private String rdate;
  
  
  public int getR_bookmarkgrpno() {
    return r_bookmarkgrpno;
  }

  public void setR_bookmarkgrpno(int r_bookmarkgrpno) {
    this.r_bookmarkgrpno = r_bookmarkgrpno;
  }

  public String getR_name() {
    return r_name;
  }

  public void setR_name(String r_name) {
    this.r_name = r_name;
  }

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

  
  @Override
  public String toString() {
    return "[r_bookmakrgrpno=" + r_bookmarkgrpno + ", r_name=" + r_name + ", bookmarkno=" + bookmarkno + ", bookmarkgrpno="
        + bookmarkgrpno + ", name=" + name + ", link=" + link + ", rdate=" + rdate + "]";
  }
    
}