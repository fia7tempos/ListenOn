
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE>
<html lang="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>

<script
	src="<c:url value='/resources/song_album_module/js/song_album_module.js'/>"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/lightslider.css'/>" />
<script src="<c:url value='/resources/js/lightslider.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-ui.js'/>"></script>




<script type="text/javascript">

	$(document).ready(function() {
		default_url("song");
	});
	
	//title 이미지슬라이드
	$(document).ready(function() {
		
		function getImages() {
			$.ajax({
				url : '<c:url value="/operation/imagelist"/>',
				type : 'get'
			}).done(function(data) {
				for (var i = 0; i < data.length; i += 3) {
					$('<li/>').append($('<img/>', {
						src : data[i],
						width : 300
					})).append($('<img/>', {
						src : data[i + 1],
						width : 300
					})).append($('<img/>', {
						src : data[i + 2],
						width : 300
					})).appendTo($('#autoplay'));
				}
				var autoplaySlider = $('#autoplay').lightSlider({
					autoWidth : false,
					auto : true,
					loop : true,
					pauseOnHover : true,
					mode : 'fade',
					speed : 800,
					verticalHeight : 300,
					adaptiveHeight : true,
				});

			});
		};
		getImages();
	});
	
	//navbar 스크롤이벤트
	var scroll_animate = function() {
			var current_scrollTop = $(window).scrollTop(); 
			if(current_scrollTop >300){
				$('#navbar').addClass('navbar-fixed-top').switchClass( "navbar-inverse", "navbar-default", 1000, "easeInOutQuad" );
				
			}else{
				$('#navbar').removeClass('navbar-fixed-top').switchClass( "navbar-default", "navbar-inverse", 1000, "easeInOutQuad" );
			}
	}
	window.setInterval(scroll_animate, 100);
</script>

</head>
<body>

</body>
</html>