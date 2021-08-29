package dev.mvc.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.tour.TourProc")
public class TourProc implements TourProcInter {
  @Autowired
  private TourDAOInter tourDAO;
 
  public TourProc() {
    System.out.println("-> TourProc created");
  }
  
  @Override
  public int create(TourVO tourVO) {
    int cnt = this.tourDAO.create(tourVO);
    return cnt;
  }
  
  @Override
  public List<TourVO> list_all() {
    List<TourVO> list = this.tourDAO.list_all();
    return list;
  }
  
  @Override
  public List<TourVO> list_by_tourgrpno(int tourgrpno) {
    List<TourVO> list = this.tourDAO.list_by_tourgrpno(tourgrpno);
    return list;
  }
  
  @Override
  public List<Tourgrp_TourVO> list_all_join() {
    List<Tourgrp_TourVO> list = this.tourDAO.list_all_join();
    return list;
  }
  
  @Override
  public TourVO read(int tourno) {
    TourVO tourVO = this.tourDAO.read(tourno);
    return tourVO;
  }

  @Override
  public int update(TourVO tourVO) {
    int cnt = this.tourDAO.update(tourVO);
    return cnt;
  }
  
  @Override
  public int delete(int tourno) {
    int cnt = this.tourDAO.delete(tourno);
    return cnt;
  }
  
  @Override
  public int count_by_tourgrpno(int tourgrpno) {
    int cnt = this.tourDAO.count_by_tourgrpno(tourgrpno);
    return cnt;
  }
  
  @Override
  public int delete_by_tourgrpno(int tourgrpno) {
    int cnt = this.tourDAO.delete_by_tourgrpno(tourgrpno);
    return cnt;
  }
  
  
  
}