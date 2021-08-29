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
  <%-- tourgrpno가 같은 모든 레코드 삭제 --%>
  function delete_by_tourgrpno(tourgrpno) {
    var f = $('#frm_delete_by_tourgrpno');
    f.attr('action', './delete_by_tourgrpno.do');
    $('#tourgrpno', f).val(tourgrpno)
    f.submit();
  } 

  // 다수의 tourno를 전달하여 contents 레코드 삭제
  function delete_contents_by_all_tourno() {
    var f = $('#frm_delete_by_tourgrpno');
    f.attr('action', '../contents/delete_contents_by_all_tourno.do');
    var tournos = $('#tournos', f).val();
    // console.log('-> tournos: ' + tournos);  // '1','2','3',
    tournos = tournos.substr(0, tournos.length-1);
    // console.log('-> tournos: ' + tournos);  // '1','2','3'
    // console.log('-> action: ' + f.attr('action'));
    // return;
    $('#tournos').val(tournos);
    f.submit();
  }
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>
  <A href="../tourgrp/list.do" class='title_link'>투어 그룹</A> > 
  ${tourgrpVO.name }
</DIV>

<DIV class='content_body'>

  <ASIDE class="aside_right">
    <form name='frm_delete_by_tourgrpno' id='frm_delete_by_tourgrpno' action='' method='post'>
      <input type='hidden' name='tourgrpno' id='tourgrpno' value=''>
      <input type='hidden' name='tournos' id='tournos' value=''>
      <A href="javascript: delete_contents_by_all_tourno()">모든 투어항목의 관련 자료 삭제</A>
      <span class='menu_divide' >│</span>
      <A href="javascript: delete_by_tourgrpno(${param.tourgrpno})">모든 투어항목 삭제</A>
    </form>
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>
  * 「모든 투어항목을 삭제」하기전에 「모든 투어의 관련 자료 삭제」를 먼저 실행해주세요.

  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>투어 그룹 번호</label>
      <input type='hidden' name='tourgrpno' value='${param.tourgrpno }' >
      ${param.tourgrpno } 
    
      <label>투어 이름</label>
      <input type='text' name='name' value='' required="required" style='width: 25%;'
                 autofocus="autofocus">
  
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
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
      <TH class="th_bs">관련<br> 자료수</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="tourVO" items="${list}">
      <c:set var="tourno" value="${tourVO.tourno }" />
      <c:set var="tourgrpno" value="${tourVO.tourgrpno }" />
      
      <script type="text/javascript">
        var tournos = $('#tournos').val();
        tournos = tournos + "${tourno},";  // 1,2,3,
        $('#tournos').val(tournos); // EL -> jquery -> HTML
      </script>
      
      <TR>
        <TD class="td_bs">${tourVO.tourno }</TD>
        <TD class="td_bs">${tourVO.tourgrpno }</TD>
        <TD class="td_bs_left">

<%--           <!-- 검색 하지 않는 목록 -->
          <A href="../contents/list_by_tourno.do?tourno=${tourno }">${tourVO.name }</A>
 --%>
 
<%--            <!-- 검색 하는 목록 -->
           <A href="../contents/list_by_tourno_search.do?tourno=${tourno }">${tourVO.name }</A>
 --%>           
           <!-- 검색 + 페이징 목록 -->  
           <A href="../contents/list_by_tourno_search_paging.do?tourno=${tourno }&now_page=1">${tourVO.name }</A>
        </TD>
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