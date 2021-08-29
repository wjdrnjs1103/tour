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
  $(function() {
    $('#btn_update_cancel').on('click', cancel);
    $('#btn_delete_cancel').on('click', cancel);
  });

  function cancel() {
    $('#panel_create').css("display","");  
    $('#panel_update').css("display","none"); 
    $('#panel_delete').css("display","none");
  }

  /*
  // VO 전체를 JSON으로 변환 받아 처리하는 경우
  function read_update_ajax(tourgrpno) {
    $('#panel_create').css("display","none"); // hide, 태그를 숨김 
    $('#panel_update').css("display",""); // show, 숨겨진 태그 출력 
    
    // console.log('-> tourgrpno:' + tourgrpno);
    var params = "";
    // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    params = 'tourgrpno=' + tourgrpno; // 공백이 값으로 있으면 안됨.
    $.ajax(
      {
        url: '/tourgrp/read_update_ajax.do',
        type: 'get',  // get, post
        cache: false, // 응답 결과 임시 저장 취소
        async: true,  // true: 비동기 통신
        dataType: 'json', // 응답 형식: json, html, xml...
        data: params,      // 데이터
        success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열
          // -> rdata: [tourgrpno=1, name=문화, seqno=1, visible=Y, rdate=2021-04-08 17:01:28]
          // console.log('-> rdata: '+ rdata.tourgrpVO);
          // console.log('-> rdata: '+ rdata.tourgrpVO.tourgrpno); // X
          // console.log('-> 0: '+ rdata.tourgrpVO[0]); // [ , 응답 문자열중 첫번째 문자
          // console.log('-> 1: '+ rdata.tourgrpVO[1]); // c 
          // console.log('-> '+ JSON.parse(rdata.tourgrpVO))  // X
          // console.log('-> '+ eval(rdata.tourgrpVO))  // X
          var str = rdata.tourgrpVO;
          str = str.substring(1, str.length-1); // [, ] 삭제
          // console.log(str); // tourgrpno=1, name=문화, seqno=1, visible=Y, rdate=2021-04-08 17:01:28
          strs = str.split(",");
          // console.log(strs[0]); // tourgrpno=1
          // console.log(strs[0].split("=")[0]); // tourgrpno
          // console.log(strs[0].split("=")[1]); // 1
          var tourgrpno = strs[0].split("=")[1];
          var name = strs[1].split("=")[1];
          var seqno = strs[2].split("=")[1];
          var visible = strs[3].split("=")[1];
          var rdate = strs[4].split("=")[1];

          var frm_update = $('#frm_update');
          $('#tourgrpno', frm_update).val(tourgrpno);
          $('#name', frm_update).val(name);
          $('#seqno', frm_update).val(seqno);
          $('#visible', frm_update).val(visible);
          $('#rdate', frm_update).val(rdate);
          
          // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
          // console.log('-> btn_recom: ' + $('#btn_recom').html());
          // $('#btn_recom').html('♥('+rdata.recom+')');
          // $('#span_animation').hide();
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END

    // $('#span_animation').css('text-align', 'center');
    // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
    // $('#span_animation').show(); // 숨겨진 태그의 출력
  } 
  */

  // 수정폼
  function read_update_ajax(tourgrpno) {
    $('#panel_create').css("display","none"); // hide, 태그를 숨김 
    $('#panel_delete').css("display","none"); // hide, 태그를 숨김
    $('#panel_update').css("display",""); // show, 숨겨진 태그 출력 
    
    // console.log('-> tourgrpno:' + tourgrpno);
    var params = "";
    // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    params = 'tourgrpno=' + tourgrpno; // 공백이 값으로 있으면 안됨.
    $.ajax(
      {
        url: '/tourgrp/read_ajax.do',
        type: 'get',  // get, post
        cache: false, // 응답 결과 임시 저장 취소
        async: true,  // true: 비동기 통신
        dataType: 'json', // 응답 형식: json, html, xml...
        data: params,      // 데이터
        success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열
          // {"tourgrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
          var tourgrpno = rdata.tourgrpno;
          var name = rdata.name;
          var seqno = rdata.seqno;
          var rdate = rdata.rdate;

          var frm_update = $('#frm_update');
          $('#tourgrpno', frm_update).val(tourgrpno);
          $('#name', frm_update).val(name);
          $('#seqno', frm_update).val(seqno);
          $('#rdate', frm_update).val(rdate);
          
          // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
          // console.log('-> btn_recom: ' + $('#btn_recom').html());
          // $('#btn_recom').html('♥('+rdata.recom+')');
          // $('#span_animation').hide();
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END

    // $('#span_animation').css('text-align', 'center');
    // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
    // $('#span_animation').show(); // 숨겨진 태그의 출력
  } 

  // 삭제 폼(자식 레코드가 없는 경우의 삭제)
  function read_delete_ajax(tourgrpno) {
    $('#panel_create').css("display","none"); // hide, 태그를 숨김
    $('#panel_update').css("display","none"); // hide, 태그를 숨김  
    $('#panel_delete').css("display",""); // show, 숨겨진 태그 출력 
    // return;
    
    // console.log('-> tourgrpno:' + tourgrpno);
    var params = "";
    // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    params = 'tourgrpno=' + tourgrpno; // 공백이 값으로 있으면 안됨.
    $.ajax(
      {
        url: '/tourgrp/read_ajax.do',
        type: 'get',  // get, post
        cache: false, // 응답 결과 임시 저장 취소
        async: true,  // true: 비동기 통신
        dataType: 'json', // 응답 형식: json, html, xml...
        data: params,      // 데이터
        success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열
          // {"tourgrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
          var tourgrpno = rdata.tourgrpno;
          var name = rdata.name;
          var seqno = rdata.seqno;
          // var rdate = rdata.rdate;

          var frm_delete = $('#frm_delete');
          $('#tourgrpno', frm_delete).val(tourgrpno);
          
          $('#frm_delete_name').html(name);
          $('#frm_delete_seqno').html(seqno);
          
          // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
          // console.log('-> btn_recom: ' + $('#btn_recom').html());
          // $('#btn_recom').html('♥('+rdata.recom+')');
          // $('#span_animation').hide();
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END

    // $('#span_animation').css('text-align', 'center');
    // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
    // $('#span_animation').show(); // 숨겨진 태그의 출력
  } 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>투어 그룹</DIV>

<DIV class='content_body'>
  <!-- 신규 등록 -->
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>그룹 이름</label>
      <input type='text' name='name' id='name' value='' required="required" style='width: 25%;'
                 autofocus="autofocus">
  
      <label>순서</label>
      <input type='number' name='seqno' id='seqno' value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
       
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
   
  <!-- 수정 -->
  <DIV id='panel_update' 
          style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; 
                    text-align: center; display: none;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
      <input type='hidden' name='tourgrpno' id='tourgrpno' value=''>
      
      <label>그룹 이름</label>
      <input type='text' name='name' id='name' value='' required="required" style='width: 25%;'
                 autofocus="autofocus">
  
      <label>순서</label>
      <input type='number' name='seqno' id='seqno' value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
       
      <button type="submit" id='submit'>저장</button>
      <button type="button" id='btn_update_cancel'>취소</button>
    </FORM>
  </DIV>
  
  <%-- 삭제 --%>
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; 
          width: 100%; text-align: center; display: none;'>
    <div class="msg_warning">카테고리 그룹을 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='tourgrpno' id='tourgrpno' value=''>
        
      <label>그룹 이름</label><span id='frm_delete_name'></span>  
      <label>순서</label>: <span id='frm_delete_seqno'></span>
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" id='btn_delete_cancel'>취소</button>
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
          <A href="../tour/list_by_tourgrpno.do?tourgrpno=${tourgrpno }">${tourgrpVO.name }</A>
        </TD>
        <TD class="td_bs">${tourgrpVO.rdate.substring(0, 10) }</TD>
        
        <TD class="td_bs">
          <%-- <A href="./read_update.do?tourgrpno=${tourgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A> --%>
          <%-- Ajax 기반 수정폼--%>
          <A href="javascript: read_update_ajax(${tourgrpno })" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          
          <%-- <A href="./read_delete.do?tourgrpno=${tourgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A> --%>
          <%-- Ajax 기반 Delete폼--%>
          <A href="javascript: read_delete_ajax(${tourgrpno })" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
          
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