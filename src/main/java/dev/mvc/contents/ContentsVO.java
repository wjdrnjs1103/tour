package dev.mvc.contents;

import org.springframework.web.multipart.MultipartFile;

/*
    CONTENTSNO                    INT(10)    NOT NULL    PRIMARY KEY AUTO_INCREMENT COMMENT '컨텐츠 번호',
    ADMINNO                       INT(10)    NOT NULL COMMENT '관리자 번호',
    tourno                        INT(10)    NULL  COMMENT '투어 번호',
    TITLE                         VARCHAR(100)     NOT NULL COMMENT '제목',
    CONTENT                       VARCHAR(4000)    NOT NULL COMMENT '내용',
    RECOM                         INT(10)    NOT NULL COMMENT '추천수',
    CNT                           INT(10)    NOT NULL COMMENT '조회수',
    REPLYCNT                      INT(10)    NOT NULL COMMENT '댓글수',
    PASSWD                        VARCHAR(20)    NOT NULL COMMENT '패스워드',
    WORD                          VARCHAR(300)     NULL  COMMENT '검색어',
    RDATE                         DATE     NOT NULL COMMENT '등록일',
    FILE1                         VARCHAR(100)     NULL  COMMENT '메인 이미지',
    FILE1SAVED                    VARCHAR(100)     NULL  COMMENT '실제 저장된 메인 이미지',
    THUMB1                        VARCHAR(100)     NULL  COMMENT '메인 이미지 Preview',
    SIZE1                         INT(10)    NULL  COMMENT '메인 이미지 크기',
    PRICE                         INT(10)    NOT NULL COMMENT '정가',
    DC                            INT(10)    NOT NULL COMMENT '할인률',
    SALEPRICE                     INT(10)    NULL  COMMENT '판매가',
    POINT                         INT(10)    NOT NULL COMMENT '포인트',
    SALECNT                       INT(10)    NOT NULL COMMENT '가능 인원',
    
*/
public class ContentsVO {
  /** 컨텐츠 번호 */
  private int contentsno;
  /** 관리자 번호 */
  private int adminno;
  /** 카테고리 번호*/
  private int tourno;
  /** 제목 */
  private String title = "";
  /** 내용 */
  private String content = "";
  /** 추천수 */
  private int recom;
  /** 조회수 */
  private int cnt = 0;
  /** 댓글수 */
  private int replycnt = 0;
  /** 패스워드 */
  private String passwd = "";
  /** 검색어 */
  private String word = "";
  /** 등록 날짜 */
  private String rdate = "";
  
  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지 */
  private String file1saved = "";  
  /** 메인 이미지 preview */
  private String thumb1 = "";
  /** 메인 이미지 크기 */
  private long size1;
  /** 정가 */
  private int price;
  /** 할인률 */
  private int dc;
  /** 판매가 */
  private int saleprice;
  /** 포인트 */
  private int point;
  /** 재고 수량 */
  private int salecnt;
  
  /** 
  이미지 MultipartFile 
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
                   value='' placeholder="파일 선택" >
  */
 private MultipartFile file1MF;
 
 public MultipartFile getFile1MF() {
   return file1MF;
 }
 public void setFile1MF(MultipartFile file1mf) {
   file1MF = file1mf;
 }
 
 
 //파일 크기 단위 출력 
 private String size1_label;
 
 public String getSize1_label() {
   return size1_label;
 }
 public void setSize1_label(String size1_label) {
   this.size1_label = size1_label;
 } 
  
  
  public int getContentsno() {
    return contentsno;
  }
  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getTourno() {
    return tourno;
  }
  public void setTourno(int tourno) {
    this.tourno = tourno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getRecom() {
    return recom;
  }
  public void setRecom(int recom) {
    this.recom = recom;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public int getReplycnt() {
    return replycnt;
  }
  public void setReplycnt(int replycnt) {
    this.replycnt = replycnt;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile1saved() {
    return file1saved;
  }
  public void setFile1saved(String file1saved) {
    this.file1saved = file1saved;
  }
  public String getThumb1() {
    return thumb1;
  }
  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getDc() {
    return dc;
  }
  public void setDc(int dc) {
    this.dc = dc;
  }
  public int getSaleprice() {
    return saleprice;
  }
  public void setSaleprice(int saleprice) {
    this.saleprice = saleprice;
  }
  public int getPoint() {
    return point;
  }
  public void setPoint(int point) {
    this.point = point;
  }
  public int getSalecnt() {
    return salecnt;
  }
  public void setSalecnt(int salecnt) {
    this.salecnt = salecnt;
  }
  
  
  
}
