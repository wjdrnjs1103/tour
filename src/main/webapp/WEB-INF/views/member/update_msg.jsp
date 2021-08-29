<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head> 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
  <DIV class='title_line'>
    가입 정보 수정
  </DIV>

  <DIV class='content_body'>
    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do'>회원 가입</A>
      <span class='menu_divide' >│</span> 
      <A href='./list.do'>목록</A>
    </ASIDE> 
  
    <div class='menu_line'></div>
   
    <DIV class='message'>
    <fieldset class='fieldset_basic'>
      <UL>
        <c:choose>
          <c:when test="${param.cnt == 1 }">
            <LI class='li_none'>
              <span class='span_success'>회원 정보를 수정했습니다.</span>
            </LI>
            <LI class='li_none'>
              <button type='button' 
                          onclick="location.href='./read.do?memberno=${param.memberno}'"
                          class="btn btn-info">변경 확인</button>
              <button type='button' 
                          onclick="location.href='./list.do'"
                          class="btn btn-info">목록</button>                        
            </LI>
          </c:when>
          <c:otherwise>
            <LI class='li_none'>
              <span class='span_fail'>회원정보 수정에 실패했습니다.</span>
            </LI>
            <LI class='li_none'>
              <button type='button' onclick="history.back();" class="btn btn-primary">재시도</button>
              <button type='button' onclick="location.href='./list.do'" class="btn btn-primary">목록</button>                        
            </LI>
          </c:otherwise>
        </c:choose>
       </UL>
    </fieldset>
   
  </DIV>
</DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>