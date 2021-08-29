package dev.mvc.tourgrp;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

 
// Autowired 기능에의해 자동 할당될 때 사용되는 이름
@Component("dev.mvc.tourgrp.TourgrpProc")
public class TourgrpProc implements TourgrpProcInter {
  @Autowired  // DI: Spring 자동으로 구현한 DAO class 객체를 할당
  private TourgrpDAOInter tourgrpDAO;
  // private CategrpDAOInter categrpDAO = new CategrpDAO();

  @Override
  public int create(TourgrpVO tourgrpVO) {
    int cnt = tourgrpDAO.create(tourgrpVO);
    
    return cnt;
  }

  @Override
  public List<TourgrpVO> list_tourgrpno_asc() {
    List<TourgrpVO> list = null;
    list = this.tourgrpDAO.list_tourgrpno_asc();
    return list;
  }
  
  @Override
  public List<TourgrpVO> list_seqno_asc() {
    List<TourgrpVO> list = null;
    list = this.tourgrpDAO.list_seqno_asc();
    return list;
  }
  
  @Override
  public TourgrpVO read(int tourgrpno) {
    TourgrpVO tourgrpVO = null;
    tourgrpVO = this.tourgrpDAO.read(tourgrpno);
    
    return tourgrpVO;
  }
  
  @Override
  public int update(TourgrpVO tourgrpVO) {
    int cnt = 0;
    cnt = this.tourgrpDAO.update(tourgrpVO);
    
    return cnt;
  }
  
  @Override
  public int delete(int tourgrpno) {
    int cnt = 0;
    cnt = this.tourgrpDAO.delete(tourgrpno);
    
    return cnt;
  }
  
  @Override
  public int update_seqno_up(int tourgrpno) {
    int cnt = 0;
    cnt = this.tourgrpDAO.update_seqno_up(tourgrpno);
    
    return cnt;
  }

  @Override
  public int update_seqno_down(int tourgrpno) {
    int cnt = 0;
    cnt = this.tourgrpDAO.update_seqno_down(tourgrpno);    
    return cnt;
  }
  
  
  
  
}