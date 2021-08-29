package dev.mvc.bookmarkgrp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookmarkgrpCont {
  @Autowired
  @Qualifier("dev.mvc.bookmarkgrp.BookmarkgrpProc")
  private BookmarkgrpProcInter bookmarkgrpProc;
  
  public BookmarkgrpCont() {
    System.out.println("-> BookmarkgrpCont created.");
  }
  
  // http://localhost:9091/bookmarkgrp/create.do
  /**
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/bookmarkgrp/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmarkgrp/create"); // webapp/WEB-INF/views/bookmarkgrp/create.jsp
    
    return mav; // forward
  }
  
  // http://localhost:9091/bookmarkgrp/create.do
  /**
   * 등록 처리
   * @param bookmarkgrpVO
   * @return
   */
  @RequestMapping(value="/bookmarkgrp/create.do", method=RequestMethod.POST )
  public ModelAndView create(BookmarkgrpVO bookmarkgrpVO) { // bookmarkgrpVO 자동 생성, Form -> VO
    // BookmarkgrpVO bookmarkgrpVO <FORM> 태그의 값으로 자동 생성됨.
    // request.setAttribute("bookmarkgrpVO", bookmarkgrpVO); 자동 실행
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.bookmarkgrpProc.create(bookmarkgrpVO); // 등록 처리
    mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    
    mav.setViewName("/bookmarkgrp/create_msg"); // /webapp/WEB-INF/views/bookmarkgrp/create_msg.jsp

    return mav; // forward
  }
  
  // http://localhost:9091/bookmarkgrp/list.do
  @RequestMapping(value="/bookmarkgrp/list.do", method=RequestMethod.GET )
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<BookmarkgrpVO> list = this.bookmarkgrpProc.list_bookmarkgrpno_asc();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/bookmarkgrp/list"); // /webapp/WEB-INF/views/bookmarkgrp/list.jsp
    return mav;
  }
  
//http://localhost:9090/bookmarkgrp/read_update.do
 /**
  * 조회 + 수정폼
  * @param bookmarkgrpno 조회할 카테고리 번호
  * @return
  */
 @RequestMapping(value="/bookmarkgrp/read_update.do", method=RequestMethod.GET )
 public ModelAndView read_update(int bookmarkgrpno) {
   // request.setAttribute("bookmarkgrpno", int bookmarkgrpno) 작동 안됨.
   
   ModelAndView mav = new ModelAndView();
   
   BookmarkgrpVO bookmarkgrpVO = this.bookmarkgrpProc.read(bookmarkgrpno);
   mav.addObject("bookmarkgrpVO", bookmarkgrpVO);  // request 객체에 저장
   
   List<BookmarkgrpVO> list = this.bookmarkgrpProc.list_bookmarkgrpno_asc();
   mav.addObject("list", list);  // request 객체에 저장

   mav.setViewName("/bookmarkgrp/read_update"); // /WEB-INF/views/bookmarkgrp/read_update.jsp 
   return mav; // forward
 }
 
//http://localhost:9090/bookmarkgrp/update.do
/**
 * 수정 처리
 * @param bookmarkgrpVO
 * @return
 */
@RequestMapping(value="/bookmarkgrp/update.do", method=RequestMethod.POST )
public ModelAndView update(BookmarkgrpVO bookmarkgrpVO) {
  // bookmarkgrpVO bookmarkgrpVO <FORM> 태그의 값으로 자동 생성됨.
  // request.setAttribute("bookmarkgrpVO", bookmarkgrpVO); 자동 실행
  
  ModelAndView mav = new ModelAndView();
  
  int cnt = this.bookmarkgrpProc.update(bookmarkgrpVO);
  mav.addObject("cnt", cnt); // request에 저장
  
  mav.setViewName("/bookmarkgrp/update_msg"); // update_msg.jsp
  
  return mav;
}
// http://localhost:9091/bookmarkgrp/read_delete.do
/**
 * 조회 + 삭제폼
 * @param bookmarkgrpno 조회할 카테고리 번호
 * @return
 */
@RequestMapping(value="/bookmarkgrp/read_delete.do", method=RequestMethod.GET )
public ModelAndView read_delete(int bookmarkgrpno) {
  ModelAndView mav = new ModelAndView();
  
  BookmarkgrpVO bookmarkgrpVO = this.bookmarkgrpProc.read(bookmarkgrpno); // 삭제할 자료 읽기
  mav.addObject("bookmarkgrpVO", bookmarkgrpVO);  // request 객체에 저장
  
  List<BookmarkgrpVO> list = this.bookmarkgrpProc.list_bookmarkgrpno_asc();
  mav.addObject("list", list);  // request 객체에 저장

  mav.setViewName("/bookmarkgrp/read_delete"); // read_delete.jsp
  return mav;
}

  //http://localhost:9091/bookmarkgrp/delete.do
  /**
  * 삭제
  * @param bookmarkgrpno 조회할 카테고리 번호
  * @return
  */
  @RequestMapping(value="/bookmarkgrp/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(int bookmarkgrpno) {
     ModelAndView mav = new ModelAndView();
     
     BookmarkgrpVO bookmarkgrpVO = this.bookmarkgrpProc.read(bookmarkgrpno); // 삭제 정보
     mav.addObject("bookmarkgrpVO", bookmarkgrpVO);  // request 객체에 저장
     
     int cnt = this.bookmarkgrpProc.delete(bookmarkgrpno); // 삭제 처리
     mav.addObject("cnt", cnt);  // request 객체에 저장
     
     mav.setViewName("/bookmarkgrp/delete_msg"); // delete_msg.jsp
    
     return mav;
  }


  
}