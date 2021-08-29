<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV class='container_main'> 
  <%-- 화면 상단 메뉴 --%>
  <DIV class='top_img'>
    <DIV class='top_menu_label'>여행을 갑시다</DIV>
    <NAV class='top_menu'>
      <span style='padding-left: 0.5%;'></span>
      <A class='menu_link'  href='/' >JK TOUR</A><span class='top_menu_sep'> </span> 
      
      <c:choose>
        <c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
          <A class='menu_link'  href='/member/login.do' >Login</A><span class='top_menu_sep'> </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.id } <A class='menu_link'  href='/member/logout.do' >Logout</A><span class='top_menu_sep'> </span>
        </c:otherwise>
      </c:choose>     
      
      <A class='menu_link'  href='/tourgrp/list.do'>투어 그룹</A><span class='top_menu_sep'> </span> 
      <A class='menu_link'  href='/tour/list_all.do'>투어 전체 목록</A><span class='top_menu_sep'> </span> 
      <A class='menu_link'  href='/tour/list_all_join.do'>투어 전체 목록 Join</A><span class='top_menu_sep'> </span>
      <A class='menu_link'  href='/cart/list_by_memberno.do'>쇼핑카트</A><span class='top_menu_sep'> </span>
      <A class='menu_link'  href='/order_pay/list_by_memberno.do'>주문결제</A><span class='top_menu_sep'> </span>
                       
      관리자[
      <A class='menu_link'  href='/member/list.do'>회원</A>
      ]
    </NAV>
  </DIV>
  
  <%-- 내용 --%> 
  <DIV class='content'>