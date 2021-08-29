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
 
<DIV class='title_line'>
  <A href="../bookmarkgrp/list.do" class='title_link'>즐겨찾기 그룹</A> > 
  <A href="./list_by_bookmarkgrpno.do?bookmarkgrpno=${param.bookmarkgrpno }" class='title_link'>${bookmarkgrpVO.name }</A> > 
  ${bookmarkVO.name } 수정 
</DIV>

<DIV class='content_body'>

  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./update.do'>
      <input type="hidden" name="bookmarkno" value="${param.bookmarkno }">
      
      <label>즐겨찾기 그룹 번호</label>
      <input type='number' name='bookmarkgrpno' value='${param.bookmarkgrpno }' 
                 required="required" min="1" max="100" step="1" autofocus="autofocus">
      <label>즐겨찾기 이름</label>
      <input type='text' name='name' value='${bookmarkVO.name }' required="required" style='width: 25%;'>
      <label>링크</label>
      <input type='text' name='link' value='${bookmarkVO.link }' 
                 required="required" style='width: 25%;'>    
  
      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="location.href='./list_by_bookmarkgrpno.do?bookmarkgrpno=${bookmarkVO.bookmarkgrpno}'">취소</button>
    </FORM>
  </DIV>

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
    <c:forEach var="bookmarkVO" items="${list}">
      <c:set var="bookmarkno" value="${bookmarkVO.bookmarkno }" />
      <c:set var="bookmarkgrpno" value="${bookmarkVO.bookmarkgrpno }" />
      <TR>
        <TD class="td_bs">${bookmarkVO.bookmarkno }</TD>
        <TD class="td_bs">${bookmarkVO.bookmarkgrpno }</TD>
        <TD class="td_bs_left">${bookmarkVO.name }</TD>
        <TD class="td_bs">${bookmarkVO.link }</TD>
        <TD class="td_bs">${bookmarkVO.rdate.substring(0, 10) }</TD>
        <TD class="td_bs">
          <A href="./read_update.do?bookmarkno=${bookmarkno }&bookmarkgrpno=${bookmarkgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?bookmarkno=${bookmarkno }&bookmarkgrpno=${bookmarkgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>