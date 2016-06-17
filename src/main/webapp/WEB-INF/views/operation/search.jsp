<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">



<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script src="<c:url value='/resources/song_album_module/js/song_album_module.js'/>"></script>
<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	

<script type="text/javascript">

//세션 있는가 보자
var context_root = '<c:url value="/" />';
var user_id = '${user_id}';
	
	$(document).ready(function() {
		
		default_url("operation/song");
		add_url("${search_keyword}");
		goAjax(1);		
	});
	
	//상단 컴포넌트 그리기
	$(document).ready(function() {
		drawTitle(context_root);
		drawMenu(context_root,5,user_id);
		drawCounter(context_root,user_id);
	});
	
	
</script>
</head>
<body>
<div id="test"></div>
	<!-- 	배경 -->
	<div class="bg-1">
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center"></div>
		
		<!-- 네비게이션 바 -->
		<nav id="navbar"></nav>

		<!-- 평가수 게이지 -->
		<div id="rec_counter"></div>
		<div id="defaultDiv"></div>

	</div>

</body>
</html>