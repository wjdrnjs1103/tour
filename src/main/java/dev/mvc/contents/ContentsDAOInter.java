package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContentsDAOInter {
  /**
   * 등록
   * @param contentsVO
   * @return
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * 특정 카테고리의 등록된 글목록
   * @return
   */
  public List<ContentsVO> list_by_tourno(int tourno);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_tourno_search(HashMap<String, Object> hashMap);

  /**
   * 카테고리별 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<ContentsVO> list_by_tourno_search_paging(HashMap<String, Object> map);
  
  /**
   * 상품 정보 수정 처리
   * @param contentsVO
   * @return
   */
  public int product_update(ContentsVO contentsVO);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public ContentsVO read(int contentsno);
  
  /**
   * 텍스트 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_text(ContentsVO contentsVO);
  
  /**
   * 파일 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_file(ContentsVO contentsVO);
  
  /**
   * 삭제
   * @param contentsno
   * @return
   */
  public int delete(int contentsno);
  
  /**
   * 추천수 증가
   * @param contentsno
   * @return
   */
  public int update_recom(int contentsno);
  
  /**
   * 다수의 tourno를 전달하여 contents 레코드 삭제
   * @param tournos
   * @return
   */
  public int delete_contents_by_all_tourno(Map<String, Object> tournos);

  
  
  
}