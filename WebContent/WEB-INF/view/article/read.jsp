<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 500px;
	}	
	table, tr, td, th{
		padding:10px;
		border: 1px solid black;
		border-collapse: collapse;
	}
	
</style>
</head>
<body>
	<p>
		<a href="write.do">[글쓰기]</a>
		<a href="${pageContext.request.contextPath}/index.jsp">[메인]</a>
	</p>
	<table>
		<tr>
			<th>번호</th>
			<td>${article.number }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${article.name }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${article.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${content.content }</td>
		</tr>
	</table>
	<p>
		<a href="modify.do?no=${article.number }">[게시글 수정]</a>
		<a href="delete.do?no=${article.number }">[게시글 삭제]</a>
	</p>
</body>
</html>