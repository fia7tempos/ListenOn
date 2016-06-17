<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선호 가수</title>

<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jqcloud/css/jqcloud.css'/>" />
<script src="<c:url value='/resources/jqcloud/js/jqcloud.js'/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	var $artistList = new Array();
	
	$.ajax({
		url : "prefer_artist",
		method : "POST",
		async:false //전역 변수에 담을 수 있도록 설정
	}).done(function(data){
		
		$.each(data,function(key, val){
				var $jobj = new Object();
				console.log(val.artist_name);
				$jobj.text = val.artist_name;
				$jobj.weight = 11 - (1+key); 
				$artistList.push($jobj);
				
				
				
		});
	});
	
	
	
	$('#inputCloud').jQCloud($artistList,{
		autoResize : 'true',
		shape : 'elliptic', // elliptic, rectangular
		width : "650",
		height : "300"
		
	});
	
	//$('#inputCloud').jQCloud('$artistList', new_words);
})
</script>

</head>
<body>
<div id="inputCloud" >

</div>

</body>
</html>