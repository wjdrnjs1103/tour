<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Tour world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../tourgrp/list.do" class='title_link'>투어 그룹</A> > 
  ${tourgrpVO.name } > ${tourVO.name } >
  상품 정보 등록(수정)
</DIV>

<DIV class='content_body'>
  <FORM name='frm' method='POST' action='./product_update.do' class="form-horizontal">
    <input type="hidden" name="tourgrpno" value="${tourVO.tourgrpno }"> 
    <input type="hidden" name="tourno" value="${param.tourno }">
    <input type="hidden" name="contentsno" value="${contentsVO.contentsno}"> 
    
    <div class="form-group">
       <label class="control-label col-md-2">상품명</label>
       <div class="col-md-10">
         ${contentsVO.title }
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">정가</label>
       <div class="col-md-10">
         <input type='number' name='price' value='0' required="required" autofocus="autofocus"
                    min="0" max="10000000" step="100" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">할인률</label>
       <div class="col-md-10">
         <input type='number' name='dc' value='10' required="required"
                    min="0" max="100" step="1" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div> 
    <div class="form-group">
       <label class="control-label col-md-2">판매가</label>
       <div class="col-md-10">
         <input type='number' name='saleprice' value='0' required="required"
                    min="0" max="10000000" step="100" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>         
    <div class="form-group">
       <label class="control-label col-md-2">포인트</label>
       <div class="col-md-10">
         <input type='number' name='point' value='0' required="required"
                    min="0" max="10000000" step="10" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>        
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-primary">취소</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>