<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'><A href="../bookmarkgrp/list.do" class='title_link'>즐겨찾기 그룹</A> > 전체 즐겨찾기</DIV>

<DIV class='content_body'>
  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 30%;'/>    
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">즐겨찾기<br> 번호</TH>
      <TH class="th_bs">즐겨찾기<br> 그룹 번호</TH>
      <TH class="th_bs">즐겨찾기 이름</TH>
      <TH class="th_bs">URL</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="bookmarkgrp_BookmarkVO" items="${list}">
      <c:set var="r_bookmarkgrpno" value="${bookmarkgrp_BookmarkVO.r_bookmarkgrpno }" />
      <c:set var="r_name" value="${bookmarkgrp_BookmarkVO.r_name }" />
      <c:set var="bookmarkno" value="${bookmarkgrp_BookmarkVO.bookmarkno }" />
      <c:set var="name" value="${bookmarkgrp_BookmarkVO.name }" />
      <c:set var="link" value="${bookmarkgrp_BookmarkVO.link }" />
      <c:set var="rdate" value="${bookmarkgrp_BookmarkVO.rdate.substring(0, 10) }" />
      
      
      <TR>
        <TD class="td_bs">${r_bookmarkgrpno }</TD>
        <TD class="td_bs">${r_name }</TD>
        <TD class="td_bs">${bookmarkno }</TD>
        <TD class="td_bs_left">${name }</TD>
        <TD class="td_bs">${link }</TD>
        <TD class="td_bs">${rdate }</TD>
        <TD class="td_bs">
          <A href="./read_update.do?bookmarkno=${bookmarkno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?bookmarkno=${bookmarkno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>