package dev.mvc.bookmark;

import java.util.List;

public interface BookmarkProcInter {
  /**
   * 등록
   * @param cateVO
   * @return 등록된 갯수
   */
  public int create(BookmarkVO bookmarkVO);
  
  /**
   *  전체 목록
   * @return
   */
  public List<BookmarkVO> list_all();  
  
  /**
   *  categrpno별 목록
   * @return
   */
  public List<BookmarkVO> list_by_bookmarkgrpno(int bookmarkgrpno);   
  
  /**
   * Categrp + Cate join, 연결 목록
   * @return
   */
  public List<Bookmarkgrp_BookmarkVO> list_all_join();
  
  /**
   * 조회, 수정폼
   * @param cateno 카테고리 번호, PK
   * @return
   */
  public BookmarkVO read(int bookmarkno);
  
  /**
   * 수정 처리
   * @param cateVO
   * @return
   */
  public int update(BookmarkVO bookmarkVO);
  
  /**
   * 삭제 처리 
   * @param cateno
   * @return
   */
  public int delete(int bookmarkno);
  
}