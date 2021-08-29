package dev.mvc.tourgrp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tour.TourProcInter;

@Controller
public class TourgrpCont {
  @Autowired
  @Qualifier("dev.mvc.tourgrp.TourgrpProc")
  private TourgrpProcInter tourgrpProc;
  
  @Autowired // CategrpProcInter를 구현한 CategrpProc.java의 객체가 할당
  @Qualifier("dev.mvc.tour.TourProc")
  private TourProcInter tourProc;
  
  public TourgrpCont() {
    System.out.println("-> TourgrpCont created.");
  }
  
  // http://localhost:9091/tourgrp/create.do
  /**
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/tourgrp/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tourgrp/create"); // webapp/WEB-INF/views/tourgrp/create.jsp
    
    return mav; // forward
  }
  
  // http://localhost:9091/tourgrp/create.do
  /**
   * 등록 처리
   * @param tourgrpVO
   * @return
   */
  @RequestMapping(value="/tourgrp/create.do", method=RequestMethod.POST )
  public ModelAndView create(TourgrpVO tourgrpVO) { // tourgrpVO 자동 생성, Form -> VO
    // tourgrpVO tourgrpVO <FORM> 태그의 값으로 자동 생성됨.
    // request.setAttribute("tourgrpVO", tourgrpVO); 자동 실행
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.tourgrpProc.create(tourgrpVO); // 등록 처리
    mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    
    mav.setViewName("/tourgrp/create_msg"); // /webapp/WEB-INF/views/tourgrp/create_msg.jsp

    return mav; // forward
  }

///**
//* 목록
//* http://localhost:9091/tourgrp/list.do
//* @return
//*/
////http://localhost:9091/tourgrp/list.do
// @RequestMapping(value="/tourgrp/list.do", method=RequestMethod.GET )
// public ModelAndView list() {
//   ModelAndView mav = new ModelAndView();
//   
//   List<TourgrpVO> list = this.tourgrpProc.list_tourgrpno_asc();
//   mav.addObject("list", list); // request.setAttribute("list", list);
//
//   mav.setViewName("/tourgrp/list"); // /webapp/WEB-INF/views/tourgrp/list.jsp
//   return mav;
// }
 
  /**
   * Ajax 기반 목록
   * http://localhost:9091/tourgrp/list.do
   * @return
   */
  @RequestMapping(value="/tourgrp/list.do", method=RequestMethod.GET )
  public ModelAndView list_ajax() {
    ModelAndView mav = new ModelAndView();
    
    // 등록 순서별 출력    
    // List<tourgrpVO> list = this.tourgrpProc.list_tourgrpno_asc();
    
    // 출력 순서별 출력
    List<TourgrpVO> list = this.tourgrpProc.list_seqno_asc();
        
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/tourgrp/list_ajax"); // /WEB-INF/views/tourgrp/list_ajax.jsp
    return mav;
  }
  
  /**
   * 조회 + 수정폼
   * @param tourgrpno 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/tourgrp/read_update.do", method=RequestMethod.GET )
  public ModelAndView read_update(int tourgrpno) {
    // request.setAttribute("tourgrpno", int tourgrpno) 작동 안됨.
    
    ModelAndView mav = new ModelAndView();
    
    TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno);
    mav.addObject("tourgrpVO", tourgrpVO);  // request 객체에 저장
    
    List<TourgrpVO> list = this.tourgrpProc.list_tourgrpno_asc();
    mav.addObject("list", list);  // request 객체에 저장

    mav.setViewName("/tourgrp/read_update"); // /WEB-INF/views/tourgrp/read_update.jsp 
    return mav; // forward
  }
  
  ///**
  // * 조회 + 수정폼 + Ajax, VO 전체를 JSON으로 변환하는경우
  // * http://localhost:9091/tourgrp/read_update_ajax.do?tourgrpno=1
  // * {"tourgrpVO":"[tourgrpno=1, name=문화, seqno=1, visible=Y, rdate=2021-04-08 17:01:28]"}
  // * @param tourgrpno 조회할 카테고리 번호
  // * @return
  // */
  //@RequestMapping(value="/tourgrp/read_update_ajax.do", 
  //                              method=RequestMethod.GET )
  //@ResponseBody
  //public String read_update_ajax(int tourgrpno) {
  //    tourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno);
  //  
  //    JSONObject json = new JSONObject();
  //    json.put("tourgrpVO", tourgrpVO);
  //  
  //    return json.toString();
  //
  //}

  
  /**
   * 조회 + 수정폼/삭제폼 + Ajax, , VO에서 각각의 필드를 JSON으로 변환하는경우
   * http://localhost:9091/tourgrp/read_ajax.do?tourgrpno=1
   * {"tourgrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
   * @param tourgrpno 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/tourgrp/read_ajax.do", 
                              method=RequestMethod.GET )
  @ResponseBody
  public String read_ajax(int tourgrpno) {
    TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno);
    
    JSONObject json = new JSONObject();
    json.put("tourgrpno", tourgrpVO.getTourgrpno());
    json.put("name", tourgrpVO.getName());
    json.put("seqno", tourgrpVO.getSeqno());
    json.put("rdate", tourgrpVO.getRdate());
    
    // 자식 레코드의 갯수 추가
    int count_by_tourgrpno = this.tourProc.count_by_tourgrpno(tourgrpno);
    json.put("count_by_tourgrpno", count_by_tourgrpno);
    
    return json.toString();

  }
  
//http://localhost:9090/tourgrp/update.do
/**
* 수정 처리
* @param tourgrpVO
* @return
*/
@RequestMapping(value="/tourgrp/update.do", method=RequestMethod.POST )
public ModelAndView update(TourgrpVO tourgrpVO) {
 // tourgrpVO tourgrpVO <FORM> 태그의 값으로 자동 생성됨.
 // request.setAttribute("tourgrpVO", tourgrpVO); 자동 실행
 
 ModelAndView mav = new ModelAndView();
 
 int cnt = this.tourgrpProc.update(tourgrpVO);
 mav.addObject("cnt", cnt); // request에 저장
 
 mav.setViewName("/tourgrp/update_msg"); // update_msg.jsp
 
 return mav;
}

// http://localhost:9091/tourgrp/read_delete.do
/**
 * 조회 + 삭제폼
 * @param tourgrpno 조회할 카테고리 번호
 * @return
 */
@RequestMapping(value="/tourgrp/read_delete.do", method=RequestMethod.GET )
public ModelAndView read_delete(int tourgrpno) {
  ModelAndView mav = new ModelAndView();
  
  TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno); // 삭제할 자료 읽기
  mav.addObject("tourgrpVO", tourgrpVO);  // request 객체에 저장
  
  List<TourgrpVO> list = this.tourgrpProc.list_tourgrpno_asc();
  mav.addObject("list", list);  // request 객체에 저장

  mav.setViewName("/tourgrp/read_delete"); // read_delete.jsp
  return mav;
}

//http://localhost:9091/tourgrp/delete.do
/**
* 삭제
* @param tourgrpno 조회할 카테고리 번호
* @return
*/
@RequestMapping(value="/tourgrp/delete.do", method=RequestMethod.POST )
public ModelAndView delete(int tourgrpno) {
 ModelAndView mav = new ModelAndView();
 
 TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno); // 삭제 정보
 mav.addObject("tourgrpVO", tourgrpVO);  // request 객체에 저장
 
 int cnt = this.tourgrpProc.delete(tourgrpno); // 삭제 처리
 mav.addObject("cnt", cnt);  // request 객체에 저장
 
 mav.setViewName("/tourgrp/delete_msg"); // delete_msg.jsp

 return mav;
}

//http://localhost:9091/tourgrp/update_seqno_up.do?tourgrpno=1
// http://localhost:9091/tourgrp/update_seqno_up.do?tourgrpno=1000
/**
* 우선순위 상향 up 10 ▷ 1
* @param tourgrpno 카테고리 번호
* @return
*/
@RequestMapping(value="/tourgrp/update_seqno_up.do", 
                           method=RequestMethod.GET )
public ModelAndView update_seqno_up(int tourgrpno) {
 ModelAndView mav = new ModelAndView();
 
 TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno); // 카테고리 그룹 정보
 mav.addObject("tourgrpVO", tourgrpVO);  // request 객체에 저장
 
 int cnt = this.tourgrpProc.update_seqno_up(tourgrpno);  // 우선 순위 상향 처리
 mav.addObject("cnt", cnt);  // request 객체에 저장

 mav.setViewName("/tourgrp/update_seqno_up_msg"); // update_seqno_up_msg.jsp
 return mav;
}  

// http://localhost:9091/tourgrp/update_seqno_down.do?tourgrpno=1
// http://localhost:9091/tourgrp/update_seqno_down.do?tourgrpno=1000
/**
* 우선순위 하향 up 1 ▷ 10
* @param tourgrpno 카테고리 번호
* @return
*/
@RequestMapping(value="/tourgrp/update_seqno_down.do", 
                           method=RequestMethod.GET )
public ModelAndView update_seqno_down(int tourgrpno) {
 ModelAndView mav = new ModelAndView();
 
 TourgrpVO tourgrpVO = this.tourgrpProc.read(tourgrpno); // 카테고리 그룹 정보
 mav.addObject("tourgrpVO", tourgrpVO);  // request 객체에 저장
 
 int cnt = this.tourgrpProc.update_seqno_down(tourgrpno);
 mav.addObject("cnt", cnt);  // request 객체에 저장

 mav.setViewName("/tourgrp/update_seqno_down_msg"); // update_seqno_down_msg.jsp

 return mav;
}



 
  
}