package dev.mvc.bookmark;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.bookmarkgrp.BookmarkgrpProcInter;
import dev.mvc.bookmarkgrp.BookmarkgrpVO;

@Controller
public class BookmarkCont {
  @Autowired
  @Qualifier("dev.mvc.bookmark.BookmarkProc")
  private BookmarkProcInter bookmarkProc;
  
  @Autowired
  @Qualifier("dev.mvc.bookmarkgrp.BookmarkgrpProc")
  private BookmarkgrpProcInter bookmarkgrpProc;
  
  public BookmarkCont() {
    System.out.println("-> BookmarkCont created.");
  }
  
  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/bookmark/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  
  // http://localhost:9091/bookmarkgrp/create.do
  /**
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/bookmark/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmark/create"); // webapp/WEB-INF/views/bookmarkgrp/create.jsp
    
    return mav; // forward
  }
  
  // http://localhost:9091/bookmarkgrp/create.do
  /**
   * 등록 처리
   * @param bookmarkgrpVO
   * @return
   */
  @RequestMapping(value="/bookmark/create.do", method=RequestMethod.POST )
  public ModelAndView create(BookmarkVO bookmarkVO) { // bookmarkgrpVO 자동 생성, Form -> VO
    // BookmarkgrpVO bookmarkgrpVO <FORM> 태그의 값으로 자동 생성됨.
    // request.setAttribute("bookmarkgrpVO", bookmarkgrpVO); 자동 실행
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.bookmarkProc.create(bookmarkVO); // 등록 처리
    mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    
    mav.setViewName("/bookmark/create_msg"); // /webapp/WEB-INF/views/bookmarkgrp/create_msg.jsp

    return mav; // forward
  }
  
  // http://localhost:9091/bookmark/list_all.do 
  /**
   * 전체 목록
   * @return
   */
  @RequestMapping(value="/bookmark/list_all.do", method=RequestMethod.GET )
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    List<BookmarkVO> list = this.bookmarkProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/bookmark/list_all"); // /bookmark/list_all.jsp
    return mav;
  }
  
  /**
   * 카테고리 그룹별 전체 목록
   * http://localhost:9091/bookmark/list_by_bookmarkgrpno.do?bookmarkgrpno=1 
   * @return
   */
  @RequestMapping(value="/bookmark/list_by_bookmarkgrpno.do", method=RequestMethod.GET )
  public ModelAndView list_by_bookmarkno(int bookmarkgrpno) {
    ModelAndView mav = new ModelAndView();
    
    List<BookmarkVO> list = this.bookmarkProc.list_by_bookmarkgrpno(bookmarkgrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    BookmarkgrpVO  bookmarkgrpVO = bookmarkgrpProc.read(bookmarkgrpno); // 카테고리 그룹 정보
    mav.addObject("bookmarkgrpVO", bookmarkgrpVO); 
    
    mav.setViewName("/bookmark/list_by_bookmarkgrpno"); // /bookmark/list_by_bookmarkgrpno.jsp
    return mav;
  }

  /**
   * Categrp + Cate join, 연결 목록
   * http://localhost:9091/cate/list_all_join.do 
   * @return
   */
  @RequestMapping(value="/bookmark/list_all_join.do", method=RequestMethod.GET )
  public ModelAndView list_all_join() {
    ModelAndView mav = new ModelAndView();
    
    List<Bookmarkgrp_BookmarkVO> list = this.bookmarkProc.list_all_join();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/bookmark/list_all_join"); // /cate/list_all_join.jsp
    return mav;
  }
  
  /**
   * 조회 + 수정폼 http://localhost:9091/cate/read_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/bookmark/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int bookmarkno, int bookmarkgrpno) {
    // int bookmarkno = Integer.parseInt(request.getParameter("bookmarkno"));
    // int bookmarkgrpno = Integer.parseInt(request.getParameter("bookmarkgrpno"));
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmark/read_update"); // read_update.jsp

    BookmarkgrpVO bookmarkgrpVO = this.bookmarkgrpProc.read(bookmarkgrpno);
    mav.addObject("bookmarkgrpVO", bookmarkgrpVO);
    
    BookmarkVO bookmarkVO = this.bookmarkProc.read(bookmarkno);
    mav.addObject("bookmarkVO", bookmarkVO);
    // request.setAttribute("bookmarkVO", bookmarkVO);

    List<BookmarkVO> list = this.bookmarkProc.list_by_bookmarkgrpno(bookmarkgrpno);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * 
   * @param bookmarkVO
   * @return
   */
  @RequestMapping(value = "/bookmark/update.do", method = RequestMethod.POST)
  public ModelAndView update(BookmarkVO bookmarkVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.bookmarkProc.update(bookmarkVO);
    
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("bookmarkno", bookmarkVO.getBookmarkno());
    mav.addObject("bookmarkgrpno", bookmarkVO.getBookmarkgrpno());
    mav.addObject("name", bookmarkVO.getName());
    mav.addObject("url", "/bookmark/update_msg");  // /bookmark/create_msg -> /bookmark/update_msg.jsp로 최종 실행됨.
    
    mav.setViewName("redirect:/bookmark/msg.do"); // 새로고침 문제 해결, request 초기화
    
    return mav;
  }
  
  /**
   * 조회 + 삭제폼 http://localhost:9091/cate/read_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/bookmark/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int bookmarkno, int bookmarkgrpno) {
    // int bookmarkno = Integer.parseInt(request.getParameter("bookmarkno"));
    // int bookmarkgrpno = Integer.parseInt(request.getParameter("bookmarkgrpno"));
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/bookmark/read_delete"); // read_delete.jsp

    BookmarkgrpVO bookmarkgrpVO = this.bookmarkgrpProc.read(bookmarkgrpno);
    mav.addObject("bookmarkgrpVO", bookmarkgrpVO);
    
    BookmarkVO bookmarkVO = this.bookmarkProc.read(bookmarkno);
    mav.addObject("bookmarkVO", bookmarkVO);
    // request.setAttribute("bookmarkVO", bookmarkVO);

    List<BookmarkVO> list = this.bookmarkProc.list_by_bookmarkgrpno(bookmarkgrpno);
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * 삭제 처리
   * 
   * @param bookmarkVO
   * @return
   */
  @RequestMapping(value = "/bookmark/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int bookmarkno, int bookmarkgrpno) {
    ModelAndView mav = new ModelAndView();
    // 삭제될 레코드 정보를 삭제하기전에 읽음
    BookmarkVO bookmarkVO = this.bookmarkProc.read(bookmarkno); 
    
    int cnt = this.bookmarkProc.delete(bookmarkno);
    
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("bookmarkno", bookmarkVO.getBookmarkno());
    mav.addObject("bookmarkgrpno", bookmarkVO.getBookmarkgrpno());
    mav.addObject("name", bookmarkVO.getName());
    mav.addObject("url", "/bookmark/delete_msg");  // /bookmark/delete_msg.jsp로 최종 실행됨.
    
    mav.setViewName("redirect:/bookmark/msg.do"); // 새로고침 문제 해결, request 초기화
    
    return mav;
  }
  
}