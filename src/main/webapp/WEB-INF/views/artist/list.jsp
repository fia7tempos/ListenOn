<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script type="text/javascript" src=""></script>

<title>Insert title here</title>

</head>
<body>
<table border="2">
<tr>
	<th>artist_no</th>
	<th>artist_key</th>
	<th>artist_name</th>
	<th>sex</th>
	<th>nationality_name</th>
	<th>act_type_name</th>
	<th>genre_names</th>
	<th>artist_img<br/></th>
</tr>
<c:forEach items="${list}" var="vo">
<tr>
	<td>${vo.artist_no}</td>
	<td>${vo.artist_key}</td>
	<td>${vo.artist_name}</td>
	<td>${vo.sex}</td>
	<td>${vo.nationality_name}</td>
	<td>${vo.act_type_name}</td>
	<td>${vo.genre_names}</td>
	<td><img src="${vo.artist_img}"/><br/></td>
</tr>
</c:forEach>
</table>
</body>
</html>