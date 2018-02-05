<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css">

<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script> 
  <script src="js/common.js"></script>
  
<script type="text/javascript">
	$(function(){
	
		$("form[name='f1']").submit(function(){
			
			$(".error, .error2, .error3").css("display","none");
			
			if(checkInputEmpty($(this).find("input[name]"))==false){
				return false;
			}
			
			/* if($("input[name='password']").val()!=$("input[name='confirmPassword']").val()){
				$(".error2").css("display","block");
				$("input[name='confirmPassword']").focus();
				return false;
			} */
		})
		
	})
</script>
</head>
<body>
	<form action="password.do" method="post" name="f1">
		<%-- <p>
			<label>아이디</label>
			<input type="password" name="password" value="${auth}">
			
		</p> --%>
		<p>
			<label>기존 암호</label>
			<input type="password" name="password">
			<span class="error">비밀번호를 입력하세요</span>
		</p>
		<p>
			<label>변경 암호 확인</label>
			<input type="password" name="confirmPassword">
			<span class="error">비밀번호 확인을 입력하세요</span>
			<span class="error2">비밀번호가 일치하지 않습니다</span>
		</p>
		<p>
			<input type="submit" value="변경">
		</p>
	</form>
	<c:if test="${passNotMatch!=null }">
		${passNotMatch }
	</c:if>
</body>
</html>