<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-1.12.4.min.js"
  integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"article/readJson.do",
			type:"get",
			data:{"no":1},
			dataType:"json",
			success:function(json){
				console.log(json);
				
				$("#id1").text(json.article.id);
				$("#name").text(json.article.name);
				$("#number").text(json.article.number);
				$("#title").text(json.article.title);
				var date = new Date(json.article.regDate);
				
				$("#date").text(date);
				$("#content").text(json.content.content);
			}
		})
	})
	
</script>
</head>
<body>
	<div id="wrap">
		나오지롱
	</div>
	<p id="id1"></p>
	<p id="name"></p>
	<p id="number"></p>
	<p id="title"></p>
	<p id="content"></p>
	<p id="date"></p>
</body>
</html>