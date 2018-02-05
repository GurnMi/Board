<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${auth!=null }">
		${auth.id}[${auth.name}]님. 반갑습니다.<br>
		<a href="logout.do">[로그아웃]</a>
		<a href="password.do">[비밀번호 변경 하기]</a>
		<br>
		<a href="article/write.do">[게시글 글쓰기]</a>
		<a href="article/list.do">[게시글 보기]</a>
		
		<!-- 주소앞에 /있으면 제일 앞 /부터 주소변경
			없으면 마지막 /부터 주소 변경 -->
	</c:if>
	<c:if test="${auth==null }">
		<a href="join.do">[회원 가입하기]</a>
		<a href="login.do">[로그인 하기]</a>
	</c:if>
	<br>
	<a href="login.do">[회원리스트보기]</a>
	<c:if test="${result!=null }">
		${result }
	</c:if>
	<a href="article/write.do">[게시글 글쓰기]</a>
		<a href="article/list.do">[게시글 보기]</a>
</body>
</html>