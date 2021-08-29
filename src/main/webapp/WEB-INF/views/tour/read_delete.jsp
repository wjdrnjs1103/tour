<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Tour world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>
  <A href="../tourgrp/list.do" class='title_link'>투어 그룹</A> > 
  <A href="./list_by_tourgrpno.do?tourgrpno=${param.tourgrpno }" class='title_link'>${tourgrpVO.name }</A> > 
  ${tourVO.name } 수정 
</DIV>

<DIV class='content_body'>

  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <div class="msg_warning">투어를 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='tourno' id='tourno' value="${tourVO.tourno }">
      <input type='hidden' name='tourgrpno' id='tourgrpno' value="${param.tourgrpno }">
      
      <label>그룹 번호</label>: ${tourVO.tourgrpno }  
      <label>카테고리</label>: ${tourVO.name}  
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="location.href='./list_by_tourgrpno.do?tourgrpno=${param.tourgrpno}'">취소</button>
    </FORM>
  </DIV>

  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 40%;'/>
      <col style='width: 15%;'/>    
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">투어<br> 번호</TH>
      <TH class="th_bs">투어<br> 그룹 번호</TH>
      <TH class="th_bs">투어 이름</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">관련 자료수</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="tourVO" items="${list}">
      <c:set var="tourno" value="${tourVO.tourno }" />
      <c:set var="tourgrpno" value="${tourVO.tourgrpno }" />
      <TR>
        <TD class="td_bs">${tourVO.tourno }</TD>
        <TD class="td_bs">${tourVO.tourgrpno }</TD>
        <TD class="td_bs_left">${tourVO.name }</TD>
        <TD class="td_bs">${tourVO.rdate.substring(0, 10) }</TD>
        <TD class="td_bs">${tourVO.cnt }</TD>
        <TD class="td_bs">
          <A href="./read_update.do?tourno=${tourno }&tourgrpno=${tourgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?tourno=${tourno }&tourgrpno=${tourgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>