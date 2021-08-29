<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Tour world</title>
<!-- /static 기준 -->
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
</head>
<body>
<jsp:include page="./menu/top.jsp" flush='false' />
  
  <DIV style='width: 100%; margin: 30px auto; text-align: center;'>
    <%-- /static/images/resort01.jpg --%>
    <IMG src='/images/yellow.jpg' style='width: 50%;'>
  </DIV>
  
  <DIV style='margin: 0px auto; width: 90%;'>
    <DIV style='float: left; width: 50%;'>
     </DIV>
     <DIV style='float: left; width: 50%;'>
    </DIV>  
  </DIV>
  
  <TR>
  <A class='menu_link'  href='/bookmarkgrp/list.do'  style = 'color: #000000;' >즐겨찾기 추가</A><span class='bottom_menu_sep'> </span> <br>
  <A class='menu_link'  href='/bookmark/list_all.do'  style = 'color: #000000;' >즐겨찾기</A><span class='bottom_menu_sep'> </span> <br>
  <A class='menu_link'  href='/bookmark/list_all_join.do' style = 'color: #000000;'>즐겨찾기조인</A><span class='bottom_menu_sep'> </span>
  </TR>
  
 
  <DIV style='width: 94.8%; margin: 0px auto;'>
  </DIV>  
 
<jsp:include page="./menu/bottom.jsp" flush='false' />
 
</body>
</html>


