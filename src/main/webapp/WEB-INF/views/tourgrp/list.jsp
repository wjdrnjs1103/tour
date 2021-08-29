<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>tour world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>투어 그룹</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>그룹 이름</label>
      <input type='text' name='name' value='' required="required" style='width: 25%;'
                 autofocus="autofocus">
  
      <label>순서</label>
      <input type='number' name='seqno' value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
       
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
   
    
  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 15%;'/>
      <col style='width: 35%;'/>
      <col style='width: 25%;'/>    
      <col style='width: 25%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">출력 순서</TH>
      <TH class="th_bs">이름</TH>
      <TH class="th_bs">그룹 생성일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="tourgrpVO" items="${list}">
      <c:set var="tourgrpno" value="${tourgrpVO.tourgrpno }" />
      <TR>
        <TD class="td_bs">${tourgrpVO.seqno }</TD>
        <TD class="td_bs_left">
         <A href="../tour/list_by_tourgrpno.do?tourgrpno=${tourgrpno} ">${tourgrpVO.name }</A>
        <TD class="td_bs">${tourgrpVO.rdate.substring(0, 10) }</TD> 
        <TD class="td_bs">
          <A href="./read_update.do?tourgrpno=${tourgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?tourgrpno=${tourgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
          <A href="./update_seqno_up.do?tourgrpno=${tourgrpno }" title="우선순위 상향"><span class="glyphicon glyphicon-arrow-up"></span></A>
          <A href="./update_seqno_down.do?tourgrpno=${tourgrpno }" title="우선순위 하향"><span class="glyphicon glyphicon-arrow-down"></span></A>         
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>