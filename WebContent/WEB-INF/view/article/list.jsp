<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, tr, th, td{
		border: 1px solid grey;
		border-collapse: collapse;
	}
	th, td{
		padding: 10px;
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
			<th>제목</th>
			<th>작성자</th>
			<th>작성 날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="item" items="${list }">
		<tr>
			<td>${item.number }</td>
			<td><a href="read.do?no=${item.number}">${item.title }</a></td>
			<td>${item.name }</td>
			<td><fmt:formatDate value="${item.regDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
			<td>${item.readCount }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>