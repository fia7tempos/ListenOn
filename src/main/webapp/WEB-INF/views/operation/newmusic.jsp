
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>

<script src="<c:url value='/resources/song_album_module/js/song_album_module.js'/>"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function(){
	
	$('.btn-primary').click(function(){
		
		$.ajax({
				 url : this.id
				 
				}).done(function(data){
					console.log(data);
					
				});
		
		
		
	})
})
</script>

</head>
<body>
<div class="btn-group btn-group-justified">
    <a id="newalbumlist" class="btn btn-primary">최신앨범</a>
    <a id="newalbumgenrelist" class="btn btn-primary">장르별 최신앨범</a>
    <a id="newsonglist" class="btn btn-primary">최신곡</a>
    <a id="newsonggenrelist" class="btn btn-primary">장르별 최신곡</a>
    <a id="gettop100genresongchartlist" class="btn btn-primary">장르별 Top100 노래</a>
    <a id="gettop100songchartlist" class="btn btn-primary">Top100 노래</a>
 </div>
 
 <div id="insertData">
 여기
 </div>
 
 
</body>
</html>