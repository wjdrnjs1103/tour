package dev.mvc.bookmarkgrp;

import java.util.List;

import dev.mvc.tourgrp.TourgrpVO;

public interface BookmarkgrpProcInter {
  
  /**
   * 등록
   * @param sungrpVO
   * @return 등록된 레코드 갯수
   */
  public int create(BookmarkgrpVO bookmarkgrpVO);
  
  /**
   * 등록 순서별 목록
   * @return
   */
  public List<BookmarkgrpVO> list_bookmarkgrpno_asc();
  
  /**
   * 조회, 수정폼, 삭제폼
   * @param categrpno 카테고리 그룹 번호, PK
   * @return
   */
  public BookmarkgrpVO read(int bookmarkgrpno);
  
  /**
   * 수정 처리
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(BookmarkgrpVO bookmarkgrpVO);
  
  /**
   * 삭제 처리
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int delete(int bookmarkgrpno);
  
  
}
