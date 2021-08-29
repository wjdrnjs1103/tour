package dev.mvc.bookmarkgrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
 
// Autowired 기능에의해 자동 할당될 때 사용되는 이름
@Component("dev.mvc.bookmarkgrp.BookmarkgrpProc")
public class BookmarkgrpProc implements BookmarkgrpProcInter {
  @Autowired  // DI: Spring 자동으로 구현한 DAO class 객체를 할당
  private BookmarkgrpDAOInter bookmarkgrpDAO;

  @Override
  public int create(BookmarkgrpVO bookmarkgrpVO) {
    
    int cnt = this.bookmarkgrpDAO.create(bookmarkgrpVO);
    return cnt;
  }
  
  @Override
  public List<BookmarkgrpVO> list_bookmarkgrpno_asc() {
    List<BookmarkgrpVO> list = null;
    list = this.bookmarkgrpDAO.list_bookmarkgrpno_asc();
    return list;
  }
  
  @Override
  public BookmarkgrpVO read(int bookmarkgrpno) {
    BookmarkgrpVO bookmakrgrpVO = null;
    bookmakrgrpVO = this.bookmarkgrpDAO.read(bookmarkgrpno);
    
    return bookmakrgrpVO;
  }
  
  @Override
  public int update(BookmarkgrpVO bookmarkgrpVO) {
    int cnt = 0;
    cnt = this.bookmarkgrpDAO.update(bookmarkgrpVO);
    
    return cnt;
  }
  
  @Override
  public int delete(int bookmarkgrpno) {
    int cnt = 0;
    cnt = this.bookmarkgrpDAO.delete(bookmarkgrpno);
    
    return cnt;
  }
  

  
}
