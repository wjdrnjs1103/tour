package dev.mvc.tour;

import java.util.List;

  public interface TourDAOInter {
    /**
     * 등록
     * @param cateVO
     * @return 등록된 갯수
     */
    public int create(TourVO tourVO);
    
    /**
     *  전체 목록
     * @return
     */
    public List<TourVO> list_all();  
    
    /**
     *  categrpno별 목록
     * @return
     */
    public List<TourVO> list_by_tourgrpno(int tourgrpno);  
    
    /**
     * Categrp + Cate join, 연결 목록
     * @return
     */
    public List<Tourgrp_TourVO> list_all_join(); 
    
    /**
     * 조회, 수정폼
     * @param cateno 카테고리 번호, PK
     * @return
     */
    public TourVO read(int tourno);
    
    /**
     * 수정 처리
     * @param cateVO
     * @return
     */
    public int update(TourVO tourVO);
    
    /**
     * 삭제 처리 
     * @param cateno
     * @return
     */
    public int delete(int tourno);
    
    /**
     * 특정 그룹에 속한 레코드 갯수 산출
     * @param categrpno
     * @return
     */
    public int count_by_tourgrpno(int tourgrpno);
    
    /**
     * tourgrpno가 같은 모든 레코드 삭제
     * @param categrpno
     * @return
     */
    public int delete_by_tourgrpno(int tourgrpno);


}
