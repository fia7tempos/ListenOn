<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listen On♬</title>
<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>
<script src='<c:url value="/resources/js/jquery-form.js"/>'></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script
	src='<c:url value="/resources/song_album_module/js/song_album_module.js"/>'></script>
	<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<script type="text/javascript">

var context_root = '<c:url value="/" />';
var user_id = '${user_id}';

	$(document).ready(function() {
		drawTitle(context_root);
		drawMenu(context_root, 0, user_id);
		drawCounter(context_root,user_id);
		
		default_url("starPoint");
		add_url("top");
		goAjax(1);

		//트렌드 보이기/감추기\\
		$("#trend").click(function() {
			$("#mainDetail").slideToggle();
			$("#hitsongDetail").slideToggle();
			$("#titlesongDetail").slideToggle();
			$("#freesongDetail").slideToggle();
		});

		//평균별점 TOP\\
		$('#mainDetail').click(function() {
			$('#rtc').html("");
			add_url("top");
			goAjax(1);
		});

		//히트곡\\
		$('#hitsongDetail').click(function() {
			$('#rtc').html("");
			add_url("hitsong");
			goAjax(1);
		});
		//타이틀곡\\
		$('#titlesongDetail').click(function() {
			$('#rtc').html("");
			add_url("titlesong");
			goAjax(1);
		});

		//무료곡\\
		$('#freesongDetail').click(function() {
			$('#rtc').html("");
			add_url("freesong");
			goAjax(1);
		});

		//장르 보이기/감추기\\
		$("#genre").click(function() {
			for (var idx = 0; idx <= 16; idx++) {
				$('#genre_' + idx).slideToggle();
			}
		});

		//댄스\\
		$('#genre_' + 0).click(function() {
			$('#rtc').html("");
			add_url("dance");
			goAjax(1);
		});
		//발라드\\
		$('#genre_' + 1).click(function() {
			$('#rtc').html("");
			add_url("ballad");
			goAjax(1);
		});
		//팝\\
		$('#genre_' + 2).click(function() {
			$('#rtc').html("");
			add_url("pop");
			goAjax(1);
		});
		//영화\\
		$('#genre_' + 3).click(function() {
			$('#rtc').html("");
			add_url("movie");
			goAjax(1);
		});
		//드라마\\
		$('#genre_' + 4).click(function() {
			$('#rtc').html("");
			add_url("drama");
			goAjax(1);
		});
		//애니메이션\\
		$('#genre_' + 5).click(function() {
			$('#rtc').html("");
			add_url("animation");
			goAjax(1);
		});
		//뮤지컬\\
		$('#genre_' + 6).click(function() {
			$('#rtc').html("");
			add_url("musical");
			goAjax(1);
		});
		//일렉트로니카\\
		$('#genre_' + 7).click(function() {
			$('#rtc').html("");
			add_url("electronica");
			goAjax(1);
		});
		//롹\\
		$('#genre_' + 8).click(function() {
			$('#rtc').html("");
			add_url("rock");
			goAjax(1);
		});
		//알앤비 / 소울\\
		$('#genre_' + 9).click(function() {
			$('#rtc').html("");
			add_url("soul");
			goAjax(1);
		});
		//랩 / 힙합\\
		$('#genre_' + 10).click(function() {
			$('#rtc').html("");
			add_url("rap");
			goAjax(1);
		});
		//재즈\\
		$('#genre_' + 11).click(function() {
			$('#rtc').html("");
			add_url("jazz");
			goAjax(1);
		});
		//뉴에이지\\
		$('#genre_' + 12).click(function() {
			$('#rtc').html("");
			add_url("newage");
			goAjax(1);
		});
		//CCM\\
		$('#genre_' + 13).click(function() {
			$('#rtc').html("");
			add_url("ccm");
			goAjax(1);
		});
		//월드뮤직\\
		$('#genre_' + 14).click(function() {
			$('#rtc').html("");
			add_url("world");
			goAjax(1);
		});
		
		$('#rtc').css("margin-left","30px");
		
		$('.nav-pills nav-stacked').click(function(){
			alert('안녕');
		})
		
		$('li[name=ul_list]').click(function(){
			$('li[name=ul_list]').removeClass();
			$('#'+this.id).addClass('active');
			
		})
	});
</script>
</head>
<body style="background-color:#2d2d30;">
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center"></div>
		
		<!-- 네비게이션 바 -->
		<nav id="navbar"></nav>

		<!-- 평가수 게이지 -->
		<div id="rec_counter"></div>
		
	
	  	 
	  	<div id="defaultDiv" >
	  		
	  		<ul class="nav nav-pills nav-stacked" style="float:left; margin-left:20px;" align="right" >
			  <li name="ul_list" class="active" id="mainDetail"><a href="javascript:void(0);">평균 별점 top</a></li>
			  <li name="ul_list" id="hitsongDetail"><a href="javascript:void(0);">히트곡</a></li>
			  <li name="ul_list" id="titlesongDetail"><a href="javascript:void(0);">타이틀곡</a></li>
			  <li name="ul_list" id="freesongDetail"><a href="javascript:void(0);">무료곡</a></li>
			  <li name="ul_list" id="genre_0"><a href="javascript:void(0);">가요 - 댄스</a></li>
			  <li name="ul_list" id="genre_1"><a href="javascript:void(0);">가요 - 발라드</a></li>
			  <li name="ul_list" id="genre_2"><a href="javascript:void(0);">POP</a></li>
			  <li name="ul_list" id="genre_3"><a href="javascript:void(0);">OST - 영화</a></li>
			  <li name="ul_list" id="genre_4"><a href="javascript:void(0);">OST - 드라마</a></li>
			  <li name="ul_list" id="genre_5"><a href="javascript:void(0);">OST - 애니메이션</a></li>
			  <li name="ul_list" id="genre_6"><a href="javascript:void(0);">OST - 뮤지컬</a></li>
			  <li name="ul_list" id="genre_7"><a href="javascript:void(0);">일렉트로니카</a></li>
			  <li name="ul_list" id="genre_8"><a href="javascript:void(0);">록/메탈</a></li>
			  <li name="ul_list" id="genre_9"><a href="javascript:void(0);">R&B / Soul</a></li>
			  <li name="ul_list" id="genre_10"><a href="javascript:void(0);">랩 / 힙합</a></li>
			  <li name="ul_list" id="genre_11"><a href="javascript:void(0);">Jazz</a></li>
			  <li name="ul_list" id="genre_12"><a href="javascript:void(0);">New Age</a></li>
			  <li name="ul_list" id="genre_13"><a href="javascript:void(0);">CCM</a></li>
			  <li name="ul_list" id="genre_14" ><a href="javascript:void(0);">월드뮤직</a></li>
		  	</ul>
	  	</div>
  
  

	


	
</body>
</html>

