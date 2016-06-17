<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>

<script
	src="<c:url value='/resources/song_album_module/js/song_album_module2.js'/>"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/lightslider.css'/>" />
<script src="<c:url value='/resources/js/lightslider.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-ui.js'/>"></script>
<script type="text/javascript">

	//세션 있는가 보자
	var user_id = '${user_id}';
	var count_caption = [[-1,'최소 10개 이상의 음악을 평가해야 취향분석이 가능해요'],[9,'평가를 많이 할수록 취향분석이 정확해져요.'],[15,'자, 25개 갑시다!'],[25,'40개는 찍어야 어디가서 나 음악좀 듣는다고 말하죠.'],[40,'와~ 정말 50개 찍을 기세네요!'],[69,'에이~ 살면서 설마 음악 100개 안들으셨겠어요?'],[89,'이제 웬만한 친구보다 제가 당신을 더 잘 알걸요?'],[99,'제가 무슨 말을 할지 궁금하지 않으세요?'],[119,'이왕 이렇게 된 거, 지금까지 들었던 음악 다 찾아보세요!'],[149,'당신의 플레이리스트, 7시간 돌파!'],[199,'오, 음악 많이 들으셨네요. 인정합니다! :)'],[249,'오예, 200개 돌파! 300개에 도전해보세요!']];

	$(document).ready(function() {
		//평가수 게이지
		if(user_id!=undefined){		
			function refresh_count(){
				$.ajax({
			
				url: '/www/user/analysis/'+user_id+'/rec_count',
				type: 'get'
			}).done(function(data){
				console.log(data);
				var curr = data; 
				var max,min,idx;
				for(var i=count_caption.length-1;i>=0;i--){
					if(curr>(count_caption[i-1][0])) {
						max = count_caption[i][0]+1;
						min = count_caption[i-1][0];
						$('#count_caption').text(count_caption[i][1]);
						idx=i;
						break;
					}
				}
				if(min<0) min=0;
				console.log(curr+ ' ' + min + ' ' + max );
				$('#rec_gauge').css('width',(curr-min)/(max-min)*100 + '%').text(data + '/' + max);
			});
			}
		}
		default_url("/www/operation/newsonglist");
		add_url('1');
		goAjax(1);
		insertCallback = refresh_count;
		refresh_count();
		
		
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
		}
		;
		getImages();
		
	});

	//navbar 스크롤이벤트
	var scroll_animate = function() {
		var current_scrollTop = $(window).scrollTop();
		if (current_scrollTop > 300) {
			$('#navbar').addClass('navbar-fixed-top').switchClass(
					"navbar-inverse", "navbar-default", 1000, "easeInOutQuad");

		} else {
			$('#navbar').removeClass('navbar-fixed-top').switchClass(
					"navbar-default", "navbar-inverse", 1000, "easeInOutQuad");
		}
	}
	window.setInterval(scroll_animate, 100);
	
	//평가수 게이지
	$(document).ready(function() {	
		
		
	
	});
	
</script>
</head>



<body>

	<!-- 	배경 -->
	<div class="bg-1">
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center">
			<ul id="autoplay"
				class="gallery content-slider list-unstyled clearfix">

			</ul>
		</div>

		<!-- 네비게이션 바 -->
		<nav id="navbar" class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">ListenON</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="<c:url value='/operation/newalbumlist'/>">인기차트</a></li>
						<li><a href="<c:url value='/operation/todaytopsongchartlist'/>">최신음악</a></li>
						<li><a href="<c:url value='/operation/todaytopsongchartlist'/>">추천음악</a></li>
						<li><a href="<c:url value='/operation/song'/>">검색</a></li>
						<li class="active"><a href="<c:url value='/recommendation/${user_id}'/>">별점등록</a></li>
						<li><a href="<c:url value='/user/analysis'/>">취향분석</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value='/user/join'/>"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="<c:url value='/user/login'/>"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</div>
			</div>
		</nav>
		

		<!-- 평가수 게이지 -->
		<div class="well col-lg-12 col-md-12 text-success" style="background-color:rgba(50,50,50,1);">
		<div id="count_caption" class="well"></div>
		<div class="progress" style="background-color:rgba(150,150,150,1);">
			<div id="rec_gauge" class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="40" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%">0%</div>
		</div>
		</div>


		<div class="container">
			<!-- 			Contents가 들어갈 div id=rtc -->
			<div class="row text-center" id="rtc"></div>

			<!-- 			로딩이미지 들어갈 div id = loadimg -->

			<div align="center" id="loadimg">
				<img src='<c:url value="/resources/loading/img-loading.gif"/>'
					width="20%" />
			</div>
			<br />

			<div align="center">

				<button type="button" class="btn btn-default" id="btnMore"
					style="width: 60%">More</button>
			</div>

		</div>
</body>
</html>