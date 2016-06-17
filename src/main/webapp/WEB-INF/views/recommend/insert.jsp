<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/rating/star-rating.css'/>" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="<c:url value='/resources/js/star-rating.js'/>"></script>
<script src='<c:url value="/resources/js/jquery-form.js"/>'></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$("#input-id").rating({min:0, max:5, step:0.5, size:5,
			captionElement: '#caption_div',
			showClear: false,
			starCaptions:{
			0: '평가없음',
		    0.5: '재앙이에요',
		    1: '듣기싫어요',
		    1.5: '안좋아요',
		    2: '글쎄요',
		    2.5: '뭐 그럭저럭',
		    3: '들을만 해요',
		    3.5: '괜찮은 노래',
		    4: '좋아해요',
		    4.5: '정말 좋은 곡',
		    5: '전설의 레전드'
		}});
	});
	
	$(document).ready(function(){
		$('#commentForm').ajaxForm(function(data){
			if(data=='success'){
				alert('입력 성공');
			}else{
				alert('실패');
			}
		});
	});
</script>

</head>
<body>
	${songVO}
	<form id="commentForm" action="recommend" method="POST">
		<div id="caption_div"></div>
		<input id="input-id" name="star_point" type="number"/>
		<input name="comment" type="text"/>
		<input type="hidden" name="song_key" value="${songVO.song_key}"/>
		<input type="hidden" name="album_key" value="${songVO.album_key}"/>
		<input type="hidden" name="artist_key" value="${songVO.artist_key}"/>
		<input type="submit" />
	</form>
	

</body>
</html>