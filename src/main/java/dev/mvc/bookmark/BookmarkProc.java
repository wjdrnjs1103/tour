package dev.mvc.bookmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.bookmark.BookmarkProc")
public class BookmarkProc implements BookmarkProcInter {
  @Autowired
  private BookmarkDAOInter bookmarkDAO;
 
  public BookmarkProc() {
    System.out.println("-> BookmarkProc created");
  }
  
  @Override
  public int create(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.create(bookmarkVO);
    return cnt;
  }
  
 @Override
  public List<BookmarkVO> list_all() {
    List<BookmarkVO> list = this.bookmarkDAO.list_all();
   return list;
  }
  
  
  @Override
  public List<BookmarkVO> list_by_bookmarkgrpno(int bookmarkgrpno) {
    List<BookmarkVO> list = this.bookmarkDAO.list_by_bookmarkgrpno(bookmarkgrpno);
    
    return list;
  }
  
  @Override
  public List<Bookmarkgrp_BookmarkVO> list_all_join() {
    List<Bookmarkgrp_BookmarkVO> list = this.bookmarkDAO.list_all_join();
    return list;
  }
  
  @Override
  public BookmarkVO read(int bookmarkno) {
    BookmarkVO cateVO = this.bookmarkDAO.read(bookmarkno);
    return cateVO;
  }

  @Override
  public int update(BookmarkVO cateVO) {
    int cnt = this.bookmarkDAO.update(cateVO);
    return cnt;
  }
  
  @Override
  public int delete(int bookmarkno) {
    int cnt = this.bookmarkDAO.delete(bookmarkno);
    return cnt;
  }

}