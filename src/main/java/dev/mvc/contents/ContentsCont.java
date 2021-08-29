package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tour.TourProcInter;
import dev.mvc.tour.TourVO;
import dev.mvc.tourgrp.TourgrpProcInter;
import dev.mvc.tourgrp.TourgrpVO;
//import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ContentsCont {
  @Autowired
  @Qualifier("dev.mvc.tourgrp.TourgrpProc")
  private TourgrpProcInter tourgrpProc;
  
  @Autowired
  @Qualifier("dev.mvc.tour.TourProc")
  private TourProcInter tourProc;
  
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc;
  
  public ContentsCont() {
    System.out.println("-> ContentsCont created.");
  }

  /**
   * 새로고침 방지
   * @return
   */
  @RequestMapping(value="/contents/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 등록폼
   * 사전 준비된 레코드: 관리자 1번, tourno 1번, tourgrpno 1번을 사용하는 경우 테스트 URL
   * http://localhost:9091/contents/create.do?tourno=1
   * 
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.GET)
  public ModelAndView create(int tourno) {
    ModelAndView mav = new ModelAndView();
    
    TourVO tourVO = this.tourProc.read(tourno);
    TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
    
    mav.addObject("tourVO", tourVO);
    mav.addObject("tourgrpVO", tourgrpVO);
    
    mav.setViewName("/contents/create"); // /webapp/WEB-INF/views/contents/create.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }
  
  /**
   * 등록 처리 http://localhost:9090/contents/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";          // 원본 파일명 image
    String file1saved = "";  // 저장된 파일명, image
    String thumb1 = "";     // preview image

    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    //  --> User dir: F:\ai8\ws_frame\resort_v1sbm3a
    
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage
    String upDir =  user_dir + "/src/main/resources/static/contents/storage/"; // 절대 경로
    
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = contentsVO.getFile1MF();
    
    file1 = mf.getOriginalFilename(); // 원본 파일명
    long size1 = mf.getSize();  // 파일 크기
    
    if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1saved = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1saved)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
        thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
      }
      
    }    
    
    contentsVO.setFile1(file1);
    contentsVO.setFile1saved(file1saved);
    contentsVO.setThumb1(thumb1);
    contentsVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.contentsProc.create(contentsVO); 
    
    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> contentsno: " + contentsVO.getContentsno());
    // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect parameter 적용
    // -------------------------------------------------------------------
    
//    if (cnt == 1) {
//      tourProc.increaseCnt(contentsVO.gettourno()); // 글수 증가
//    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // System.out.println("--> tourno: " + contentsVO.gettourno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    mav.addObject("tourno", contentsVO.getTourno()); // redirect parameter 적용
    mav.addObject("url", "/contents/create_msg"); // create_msg.jsp, redirect parameter 적용

    // 연속 입력 지원용 변수, Call By Reference에 기반하여 contentsno를 전달 받음
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    mav.setViewName("redirect:/contents/msg.do"); 
    
    return mav; // forward
  }

  /**
   * 카테고리별 목록 http://localhost:9090/contents/list_by_tourno.do?tourno=1
   * 
   * @return
   */
   @RequestMapping(value = "/contents/list_by_tourno.do", method = RequestMethod.GET)
    public ModelAndView list_by_tourno(int tourno) { 
      ModelAndView mav = new  ModelAndView();  
      
      TourVO tourVO = this.tourProc.read(tourno); 
      mav.addObject("tourVO", tourVO);
      
      TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
      mav.addObject("tourgrpVO", tourgrpVO);
      
      List<ContentsVO> list = this.contentsProc.list_by_tourno(tourno);
      mav.addObject("list", list);

      // 테이블 이미지 기반, list_by_tourno.jsp
      mav.setViewName("/contents/list_by_tourno"); // /views/contents/list_by_tourno.jsp
      
      return mav; // forward 
    }
  
   /**
    * 목록 + 검색 지원
    * http://localhost:9090/contents/list_by_tourno_search.do?tourno=1&word=스위스
    * @param tourno
    * @param word
    * @return
    */
     @RequestMapping(value = "/contents/list_by_tourno_search.do", method = RequestMethod.GET)
     public ModelAndView list_by_tourno_search(@RequestParam(value="tourno", defaultValue="1") int tourno,
                                                                     @RequestParam(value="word", defaultValue="") String word ) {
     
     ModelAndView mav = new ModelAndView(); 
          
     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용 
     HashMap<String, Object> map = new HashMap<String, Object>(); 
     map.put("tourno", tourno); // #{tourno}
     map.put("word", word); // #{word}
     
     // 검색 목록 
     List<ContentsVO> list = contentsProc.list_by_tourno_search(map);
     mav.addObject("list", list);
     
     // 검색된 레코드 갯수 
     int search_count = contentsProc.search_count(map);
     mav.addObject("search_count", search_count);
     
     TourVO tourVO = tourProc.read(tourno); 
     mav.addObject("tourVO", tourVO);
     
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     mav.addObject("tourgrpVO", tourgrpVO);
     
     mav.setViewName("/contents/list_by_tourno_search");   // /contents/list_by_tourno_search.jsp
     
     return mav; 
   }
    
   /**
    * 목록 + 검색 + 페이징 지원
    * http://localhost:9090/contents/list_by_tourno_search_paging.do?tourno=1&word=스위스&now_page=1
    * 
    * @param tourno
    * @param word
    * @param now_page
    * @return
    */
   @RequestMapping(value = "/contents/list_by_tourno_search_paging.do", method = RequestMethod.GET)
   public ModelAndView list_by_tourno_search_paging(
       @RequestParam(value = "tourno", defaultValue = "1") int tourno,
       @RequestParam(value = "word", defaultValue = "") String word,
       @RequestParam(value = "now_page", defaultValue = "1") int now_page,
       HttpServletRequest request) {
     System.out.println("--> now_page: " + now_page);

     ModelAndView mav = new ModelAndView();

     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
     HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("tourno", tourno); // #{tourno}
     map.put("word", word); // #{word}
     map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

     // 검색 목록
     List<ContentsVO> list = contentsProc.list_by_tourno_search_paging(map);
     mav.addObject("list", list);

     // 검색된 레코드 갯수
     int search_count = contentsProc.search_count(map);
     mav.addObject("search_count", search_count);

     TourVO tourVO = tourProc.read(tourno);
     mav.addObject("tourVO", tourVO);

     TourgrpVO tourgrpVO = tourgrpProc.read(tourVO.getTourgrpno());
     mav.addObject("tourgrpVO", tourgrpVO);

     /*
      * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
      * 18 19 20 [다음]
      * @param list_file 목록 파일명
      * @param tourno 카테고리번호
      * @param search_count 검색(전체) 레코드수
      * @param now_page 현재 페이지
      * @param word 검색어
      * @return 페이징 생성 문자열
      */
     String paging = contentsProc.pagingBox("list_by_tourno_search_paging.do", tourno, search_count, now_page, word);
     mav.addObject("paging", paging);

     mav.addObject("now_page", now_page);

     // /contents/list_by_tourno_table_img1_search_paging.jsp
     mav.setViewName("/contents/list_by_tourno_search_paging");
     
  // -------------------------------------------------------------------------------
     // 쇼핑 카트 장바구니에 상품 등록전 로그인 폼 출력 관련 쿠기  
     // -------------------------------------------------------------------------------
     Cookie[] cookies = request.getCookies();
     Cookie cookie = null;

     String ck_id = ""; // id 저장
     String ck_id_save = ""; // id 저장 여부를 체크
     String ck_passwd = ""; // passwd 저장
     String ck_passwd_save = ""; // passwd 저장 여부를 체크

     if (cookies != null) {  // Cookie 변수가 있다면
       for (int i=0; i < cookies.length; i++){
         cookie = cookies[i]; // 쿠키 객체 추출
         
         if (cookie.getName().equals("ck_id")){
           ck_id = cookie.getValue();                                 // Cookie에 저장된 id
         }else if(cookie.getName().equals("ck_id_save")){
           ck_id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
         }else if (cookie.getName().equals("ck_passwd")){
           ck_passwd = cookie.getValue();                          // Cookie에 저장된 password
         }else if(cookie.getName().equals("ck_passwd_save")){
           ck_passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
         }
       }
     }
     
     System.out.println("-> ck_id: " + ck_id);
     
     mav.addObject("ck_id", ck_id); 
     mav.addObject("ck_id_save", ck_id_save);
     mav.addObject("ck_passwd", ck_passwd);
     mav.addObject("ck_passwd_save", ck_passwd_save);
     // -------------------------------------------------------------------------------
     
     return mav;
   }
    
   /**
    * Grid 형태의 화면 구성 http://localhost:9091/contents/list_by_tourno_grid.do?tourno=1
    * 
    * @return
    */
   @RequestMapping(value = "/contents/list_by_tourno_grid.do", method = RequestMethod.GET)
   public ModelAndView list_by_tourno_grid(int tourno) {
     ModelAndView mav = new ModelAndView();
     
     TourVO tourVO = this.tourProc.read(tourno);
     mav.addObject("tourVO", tourVO);
     
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     mav.addObject("tourgrpVO", tourgrpVO);
     
     List<ContentsVO> list = this.contentsProc.list_by_tourno(tourno);
     mav.addObject("list", list);

     // 테이블 이미지 기반, /webapp/contents/list_by_tourno_grid.jsp
     mav.setViewName("/contents/list_by_tourno_grid");

     return mav; // forward
   }
   
   /**
    * 상품 정보 수정 폼
    * 사전 준비된 레코드: 관리자 1번, tourno 1번, tourgrpno 1번을 사용하는 경우 테스트 URL
    * http://localhost:9091/contents/create.do?tourno=1
    * 
    * @return
    */
   @RequestMapping(value = "/contents/product_update.do", method = RequestMethod.GET)
   public ModelAndView product_update(int tourno, int contentsno) {
     ModelAndView mav = new ModelAndView();
     
     TourVO tourVO = this.tourProc.read(tourno);
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     ContentsVO contentsVO = this.contentsProc.read(contentsno);
     
     mav.addObject("tourVO", tourVO);
     mav.addObject("tourgrpVO", tourgrpVO);
     mav.addObject("contentsVO", contentsVO);
     
     mav.setViewName("/contents/product_update"); // /views/contents/product_update.jsp
     // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
     // mav.addObject("content", content);

     return mav; // forward
   }
   
   /**
    * 상품 정보 수정 처리 http://localhost:9091/contents/product_update.do
    * 
    * @return
    */
   @RequestMapping(value = "/contents/product_update.do", method = RequestMethod.POST)
   public ModelAndView product_update(ContentsVO contentsVO) {
     ModelAndView mav = new ModelAndView();
     
     // Call By Reference: 메모리 공유, Hashcode 전달
     int cnt = this.contentsProc.product_update(contentsVO);
     
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("tourno", contentsVO.getTourno()); // redirect parameter 적용

     // 연속 입력 지원용 변수, Call By Reference에 기반하여 contentsno를 전달 받음
     mav.addObject("contentsno", contentsVO.getContentsno());
     
     mav.addObject("url", "/contents/product_update_msg"); // product_update_msg.jsp

     mav.setViewName("redirect:/contents/msg.do"); 
     
     return mav; // forward
   }
   
   // http://localhost:9091/contents/read.do
   /**
    * 조회
    * @return
    */
   @RequestMapping(value="/contents/read.do", method=RequestMethod.GET )
   public ModelAndView read(HttpServletRequest request, int contentsno) {
     //System.out.println("-> now_page: " + now_page);
     ModelAndView mav = new ModelAndView();

     ContentsVO contentsVO = this.contentsProc.read(contentsno);

     mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

     TourVO tourVO = this.tourProc.read(contentsVO.getTourno());
     mav.addObject("tourVO", tourVO); 

     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     mav.addObject("tourgrpVO", tourgrpVO); 
     
     mav.setViewName("/contents/read"); // /WEB-INF/views/contents/read.jsp
     
  // -------------------------------------------------------------------------------
     // 쇼핑 카트 장바구니에 상품 등록전 로그인 폼 출력 관련 쿠기  
     // -------------------------------------------------------------------------------
     Cookie[] cookies = request.getCookies();
     Cookie cookie = null;

     String ck_id = ""; // id 저장
     String ck_id_save = ""; // id 저장 여부를 체크
     String ck_passwd = ""; // passwd 저장
     String ck_passwd_save = ""; // passwd 저장 여부를 체크

     if (cookies != null) {  // Cookie 변수가 있다면
       for (int i=0; i < cookies.length; i++){
         cookie = cookies[i]; // 쿠키 객체 추출
         
         if (cookie.getName().equals("ck_id")){
           ck_id = cookie.getValue();                                 // Cookie에 저장된 id
         }else if(cookie.getName().equals("ck_id_save")){
           ck_id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
         }else if (cookie.getName().equals("ck_passwd")){
           ck_passwd = cookie.getValue();                          // Cookie에 저장된 password
         }else if(cookie.getName().equals("ck_passwd_save")){
           ck_passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
         }
       }
     }
     
     System.out.println("-> ck_id: " + ck_id);
     
     mav.addObject("ck_id", ck_id); 
     mav.addObject("ck_id_save", ck_id_save);
     mav.addObject("ck_passwd", ck_passwd);
     mav.addObject("ck_passwd_save", ck_passwd_save);
     // -------------------------------------------------------------------------------
         
     return mav;
   }
   
   /**
    * 수정 폼
    * http://localhost:9091/contents/update_text.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/contents/update_text.do", method = RequestMethod.GET)
   public ModelAndView update_text(int contentsno) {
     ModelAndView mav = new ModelAndView();
     
     ContentsVO contentsVO = this.contentsProc.read_update_text(contentsno);
     TourVO tourVO = this.tourProc.read(contentsVO.getTourno());
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     
     mav.addObject("contentsVO", contentsVO);
     mav.addObject("tourVO", tourVO);
     mav.addObject("tourgrpVO", tourgrpVO);
     
     mav.setViewName("/contents/update_text"); // /WEB-INF/views/contents/update_text.jsp
     // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
     // mav.addObject("content", content);

     return mav; // forward
   }

   /**
    * 수정 처리
    * http://localhost:9091/contents/update_text.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/contents/update_text.do", method = RequestMethod.POST)
   public ModelAndView update_text(ContentsVO contentsVO) {
     ModelAndView mav = new ModelAndView();
     
     int cnt = this.contentsProc.update_text(contentsVO); // 수정 처리
     
     mav.addObject("contentsno", contentsVO.getContentsno());
     
     mav.setViewName("redirect:/contents/read.do"); 

     return mav; // forward
   }
   
   /**
    * 파일 수정 폼
    * http://localhost:9091/contents/update_file.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/contents/update_file.do", method = RequestMethod.GET)
   public ModelAndView update_file(int contentsno) {
     ModelAndView mav = new ModelAndView();
     
     ContentsVO contentsVO = this.contentsProc.read(contentsno);
     TourVO tourVO = this.tourProc.read(contentsVO.getTourno());
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     
     mav.addObject("contentsVO", contentsVO);
     mav.addObject("tourVO", tourVO);
     mav.addObject("tourgrpVO", tourgrpVO);
     
     mav.setViewName("/contents/update_file"); // /WEB-INF/views/contents/update_file.jsp

     return mav; // forward
   }

   /**
    * 파일 수정 처리 http://localhost:9091/contents/update_file.do
    * 
    * @return
    */
   @RequestMapping(value = "/contents/update_file.do", method = RequestMethod.POST)
   public ModelAndView update_file(HttpServletRequest request, ContentsVO contentsVO) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // 파일 삭제 코드 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     ContentsVO vo = contentsProc.read(contentsVO.getContentsno());
//     System.out.println("contentsno: " + vo.getContentsno());
//     System.out.println("file1: " + vo.getFile1());
     
     String file1saved = vo.getFile1saved();
     String thumb1 = vo.getThumb1();
     long size1 = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제
     sw = Tool.deleteFile(upDir, thumb1);     // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료 시작
     // -------------------------------------------------------------------
     
     // -------------------------------------------------------------------
     // 파일 전송 코드 시작
     // -------------------------------------------------------------------
     String file1 = "";          // 원본 파일명 image

     // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
     // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로
     
     // 전송 파일이 없어도 fnamesMF 객체가 생성됨.
     // <input type='file' class="form-control" name='file1MF' id='file1MF' 
     //           value='' placeholder="파일 선택">
     MultipartFile mf = contentsVO.getFile1MF();
     
     file1 = mf.getOriginalFilename(); // 원본 파일명
     size1 = mf.getSize();  // 파일 크기
     
     if (size1 > 0) { // 파일 크기 체크
       // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
       file1saved = Upload.saveFileSpring(mf, upDir); 
       
       if (Tool.isImage(file1saved)) { // 이미지인지 검사
         // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
         thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
       }
       
     }    
     
     contentsVO.setFile1(file1);
     contentsVO.setFile1saved(file1saved);
     contentsVO.setThumb1(thumb1);
     contentsVO.setSize1(size1);
     // -------------------------------------------------------------------
     // 파일 전송 코드 종료
     // -------------------------------------------------------------------
     
     // Call By Reference: 메모리 공유, Hashcode 전달
     int cnt = this.contentsProc.update_file(contentsVO);
     
     mav.addObject("contentsno", contentsVO.getContentsno());
     
     mav.setViewName("redirect:/contents/read.do"); 

     return mav; // forward
   } 
   
   /**
    * 삭제 폼
    * @param contentsno
    * @return
    */
   @RequestMapping(value="/contents/delete.do", method=RequestMethod.GET )
   public ModelAndView delete(int contentsno) { 
     ModelAndView mav = new  ModelAndView();
     
     // 삭제할 정보를 조회하여 확인
     ContentsVO contentsVO = this.contentsProc.read(contentsno);
     mav.addObject("contentsVO", contentsVO);     
     
     TourVO tourVO = this.tourProc.read(contentsVO.getTourno());
     mav.addObject("tourVO", tourVO);
     
     TourgrpVO tourgrpVO = this.tourgrpProc.read(tourVO.getTourgrpno());
     mav.addObject("tourgrpVO", tourgrpVO);
     
     
     mav.setViewName("/contents/delete");  // contents/delete.jsp
     
     return mav; 
   }
   
   
   /**
    * 삭제 처리 http://localhost:9091/contents/delete.do
    * 
    * @return
    */
   @RequestMapping(value = "/contents/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(HttpServletRequest request, int contentsno, int now_page, int tourno, String word) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // 파일 삭제 코드 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     ContentsVO vo = contentsProc.read(contentsno);
//     System.out.println("contentsno: " + vo.getContentsno());
//     System.out.println("file1: " + vo.getFile1());
     
     String file1saved = vo.getFile1saved();
     String thumb1 = vo.getThumb1();
     long size1 = 0;
     boolean sw = false;
     
     // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
     String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

     sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제
     sw = Tool.deleteFile(upDir, thumb1);     // Folder에서 1건의 파일 삭제
     // System.out.println("sw: " + sw);
     // -------------------------------------------------------------------
     // 파일 삭제 종료 시작
     // -------------------------------------------------------------------
     
     int cnt = this.contentsProc.delete(contentsno);  //DBMS 삭제
     
     if (cnt == 1) {
       // -------------------------------------------------------------------------------------
       // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리
       HashMap<String, Object> map = new HashMap<String, Object>();
       map.put("tourno", tourno);
       map.put("word", word);
       // 10번째 레코드를 삭제후
       // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
       // 페이지수를 4->3으로 감소 시켜야함.
       if (contentsProc.search_count(map) % Contents.RECORD_PER_PAGE == 0) {
         now_page = now_page - 1;
         if (now_page < 1) {
           now_page = 1; // 시작 페이지
         }
       }
       // -------------------------------------------------------------------------------------
     }
     mav.addObject("now_page", now_page);
     mav.addObject("tourno", tourno);
     
     mav.setViewName("redirect:/contents/list_by_tourno_search_paging.do"); 
     
     return mav;
   }   
   
   /**
    * 추천수 Ajax 수정 처리
    * http://localhost:9091/contents/update_recom_ajax.do?contentsno=30
    * 
    * @return
    */
   @RequestMapping(value = "/contents/update_recom_ajax.do", method = RequestMethod.POST)
   @ResponseBody
   public String update_recom_ajax(int contentsno) {
     try {
       Thread.sleep(3000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }

     int cnt = this.contentsProc.update_recom(contentsno); // 추천수 증가
     int recom = this.contentsProc.read(contentsno).getRecom(); // 새로운 추천수 읽음
         
     JSONObject json = new JSONObject();
     json.put("cnt", cnt);
     json.put("recom", recom);
     
     return json.toString();
   }
   
   /**
    * 다수의 tourno를 전달하여 contents 레코드 삭제
    * @param contentsno
    * @return
    */
   @RequestMapping(value="/contents/delete_contents_by_all_tourno.do", method=RequestMethod.POST)
   public ModelAndView delete_contents_by_all_tourno(String tournos) { 
     ModelAndView mav = new  ModelAndView();
     
     String[] tournos_array = tournos.split(",");  // "1,2,3"
     List<Integer> tournos_list = new ArrayList<Integer>();
     
     for(int index=0; index < tournos_array.length; index++) {
       tournos_list.add(Integer.parseInt(tournos_array[index]));  // 1,2,3
     }
     
     Map<String, Object> tournos_map = new HashMap<String, Object>();
     tournos_map.put("tournos_list", tournos_list);
     
     this.contentsProc.delete_contents_by_all_tourno(tournos_map);

     mav.setViewName("redirect:/contents/list_by_tourno_search_paging.do"); 
     
     return mav; 
   }
 
}