<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jqcloud/css/jqcloud.css'/>" />
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script src="<c:url value='/resources/jqcloud/js/jqcloud.js'/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	var wordArray = [
	                	{text:"김씨", weight: 20, link:"https://search.naver.com/search.naver?where=nexearch&query=%EA%B9%80%EC%94%A8&sm=top_hty&fbm=1&ie=utf8"},
	                	{text:"오씨", weight: 16},
	                	{text:"배씨", weight: 12},
	                	{text:"신씨", weight: 12},
	                	{text:"윤씨", weight: 12},
	                	{text:"한씨", weight: 12},
	                	{text:null, weight: 7},
	                	{text:undefined, weight: 7},
	                	{text:"송씨", weight: 7},
	                	{text: null, weight: 7},
	                	{text:"조씨", weight: 7},
	                	{text: null, weight: 7},
	                ]
			
	$('#inputCloud').jQCloud(wordArray);
	
});
</script>
</head>
<body>
<div id="inputCloud" style="width: 300px; height: 200px;"></div>
</body>
</html>