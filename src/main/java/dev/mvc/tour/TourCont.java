package dev.mvc.tour;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tourgrp.TourgrpProcInter;
import dev.mvc.tourgrp.TourgrpVO;

@Controller
public class TourCont {
  @Autowired
  @Qualifier("dev.mvc.tour.TourProc")
  private TourProcInter tourProc;
  
  @Autowired
  @Qualifier("dev.mvc.tourgrp.TourgrpProc")
  private TourgrpProcInter tourgrpProc;
  
  public TourCont() {
    System.out.println("-> TourCont created.");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/tour/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼 http://localhost:9091/tour/create.do?tourgrpno=1
   * 
   * @return
   */
  @RequestMapping(value = "/tour/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tour/create"); // /webapp/WEB-INF/views/tour/create.jsp

    return mav;
  }

  /**
   * 등록처리
   * http://localhost:9091/tour/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/tour/create.do", method = RequestMethod.POST)
  public ModelAndView create(TourVO tourVO) {
    ModelAndView mav = new ModelAndView();

    // System.out.println("-> tourgrpno: " + tourVO.gettourgrpno());
    
    int cnt = this.tourProc.create(tourVO);
    mav.addObject("cnt", cnt);
    mav.addObject("tourgrpno", tourVO.getTourgrpno());
    mav.addObject("name", tourVO.getName());
    mav.addObject("url", "/tour/create_msg");  // /tour/create_msg -> /tour/create_msg.jsp
    
    mav.setViewName("redirect:/tour/msg.do");
    // response.sendRedirect("/tour/msg.do");
    
    return mav;
  }
  
//http://localhost:9091/tour/list.do?tourgrpno=1 기존의 url 사용
 /**
  * tourgrp + tour join 전체 목록
  * @return
  */
  @RequestMapping(value="/tour/list_all.do", method=RequestMethod.GET )
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    List<TourVO> list = this.tourProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/tour/list_all"); // /bookmark/list_all.jsp
    return mav;
  }
  
  /**
   * 카테고리 그룹별 전체 목록
   * http://localhost:9091/tour/list_by_tourgrpno.do?tourgrpno=1 
   * @return
   */
  @RequestMapping(value="/tour/list_by_tourgrpno.do", method=RequestMethod.GET )
  public ModelAndView list_by_tourgrpno(int tourgrpno) {
    ModelAndView mav = new ModelAndView();
    
    List<TourVO> list = this.tourProc.list_by_tourgrpno(tourgrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    TourgrpVO tourgrpVO = tourgrpProc.read(tourgrpno); // 카테고리 그룹 정보
    mav.addObject("tourgrpVO", tourgrpVO); 
    
    mav.setViewName("/tour/list_by_tourgrpno"); // /tour/list_by_tourgrpno.jsp
    return mav;
  }
  
  /**
   * Categrp + Cate join, 연결 목록
   * http://localhost:9091/tour/list_all_join.do 
   * @return
   */
  @RequestMapping(value="/tour/list_all_join.do", method=RequestMethod.GET )
  public ModelAndView list_all_join() {
    ModelAndView mav = new ModelAndView();
    
    List<Tourgrp_TourVO> list = this.tourProc.list_all_join();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/tour/list_all_join"); // /cate/list_all_join.jsp
    return mav;
  }
  
  /**
   * 조회 + 수정폼 http://localhost:9091/cate/read_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/tour/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int tourno, int tourgrpno) {
    // int tourno = Integer.parseInt(request.getParameter("tourno"));
    // int tourgrpno = Integer.parseInt(request.getParameter("tourgrpno"));
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tour/read_update"); // read_update.jsp

    TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno);
    mav.addObject("tourgrpVO", tourgrpVO);
    
    TourVO tourVO = this.tourProc.read(tourno);
    mav.addObject("tourVO", tourVO);
    // request.setAttribute("tourVO", tourVO);

    List<TourVO> list = this.tourProc.list_by_tourgrpno(tourgrpno);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * 
   * @param tourVO
   * @return
   */
  @RequestMapping(value = "/tour/update.do", method = RequestMethod.POST)
  public ModelAndView update(TourVO tourVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.tourProc.update(tourVO);
    
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("tourno", tourVO.getTourno());
    mav.addObject("tourgrpno", tourVO.getTourgrpno());
    mav.addObject("name", tourVO.getName());
    mav.addObject("url", "/tour/update_msg");  // /tour/create_msg -> /tour/update_msg.jsp로 최종 실행됨.
    
    mav.setViewName("redirect:/tour/msg.do"); // 새로고침 문제 해결, request 초기화
    
    return mav;
  }
  
  
  /**
   * 조회 + 삭제폼 http://localhost:9091/tour/read_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/tour/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int tourno, int tourgrpno) {
    // int tourno = Integer.parseInt(request.getParameter("tourno"));
    // int tourgrpno = Integer.parseInt(request.getParameter("tourgrpno"));
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tour/read_delete"); // read_update.jsp

    TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno);
    mav.addObject("tourgrpVO", tourgrpVO);
    
    TourVO tourVO = this.tourProc.read(tourno);
    mav.addObject("tourVO", tourVO);
    // request.setAttribute("tourVO", tourVO);

    List<TourVO> list = this.tourProc.list_by_tourgrpno(tourgrpno);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  
  /**
   * 삭제 처리
   * 
   * @param tourVO
   * @return
   */
  @RequestMapping(value = "/tour/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int tourno, int tourgrpno) {
    ModelAndView mav = new ModelAndView();
    //삭제될 레코드 정보를 삭제하기전에 읽음.
    TourVO tourVO = this.tourProc.read(tourno);
    
    int cnt = this.tourProc.delete(tourno);
    
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("tourno", tourVO.getTourno());
    mav.addObject("tourgrpno", tourVO.getTourgrpno());
    mav.addObject("name", tourVO.getName());
    mav.addObject("url", "/tour/delete_msg");  // /tour/delete_msg.jsp로 최종실행
    
    mav.setViewName("redirect:/tour/msg.do"); // 새로고침 문제 해결, request 초기화
    
    return mav;
  }
  
  /**
   * tourgrpno가 같은 모든 레코드 삭제
   * 
   * @param tourVO
   * @return
   */
  @RequestMapping(value = "/tour/delete_by_tourgrpno.do", method = RequestMethod.POST)
  public ModelAndView delete_by_tourgrpno(int tourgrpno) {
    ModelAndView mav = new ModelAndView();
    int cnt = this.tourProc.delete_by_tourgrpno(tourgrpno);
    
    mav.addObject("tourgrpno", tourgrpno);

    mav.setViewName("redirect:/tour/list_by_tourgrpno.do"); // 새로고침 문제 해결, request 초기화
    
    return mav;
  }
  
  
  
}