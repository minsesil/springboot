<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %><!-- 마이바티스와 조금 다르다 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
	<h1>게시판</h1>
	
	<table>
		<tr>
			<td colspan="4">총 레코드수 : </td>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
	</table>
	<a href="writeForm"><button type="button">글작성</button></a>
</body>
</html>