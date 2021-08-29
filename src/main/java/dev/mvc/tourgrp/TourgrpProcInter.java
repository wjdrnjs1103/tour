package dev.mvc.tourgrp;
import java.util.List;


public interface TourgrpProcInter {
  /**
   * 등록
   * @param categrpVO
   * @return 등록된 레코드 갯수
   */
  public int create(TourgrpVO tourgrpVO);
  
  /**
   * 등록 순서별 목록
   * @return
   */
  public List<TourgrpVO> list_tourgrpno_asc();
  
  /**
   * 출력 순서별 목록
   * @return
   */
  public List<TourgrpVO> list_seqno_asc();
  
  /**
   * 조회, 수정폼, 삭제폼
   * @param categrpno 카테고리 그룹 번호, PK
   * @return
   */
  public TourgrpVO read(int tourgrpno);
  
  /**
   * 수정 처리
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(TourgrpVO tourgrpVO);
  
  /**
   * 삭제 처리
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int delete(int tourgrpno);
  
  /**
   * 출력 순서 상향
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_up(int tourgrpno);
 
  /**
   * 출력 순서 하향
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_down(int tourgrpno); 
  
  
  
}