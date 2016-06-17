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
<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script
	src='<c:url value="/resources/song_album_module/js/song_album_module.js"/>'></script>
<script type="text/javascript">
	var context_root = '<c:url value="/" />';
	var user_id = '${user_id}';
	$(document).ready(function() {
		
		if('${user_recommend_total_count}' < 15){
			swal({   
				title: "추천 음악",
				text : "최소 15개 평가가 필요합니다.",
						type: "info",   
						showCancelButton: false,   
						confirmButtonColor: "#DD6B55",   
						confirmButtonText: "별점 등록하러 가기",
						closeOnConfirm: false,
						closeOnCancel : false,
			}, function(){   
				location.href="/www/starPoint/main";
			});
			
		}
		

		default_url("recommend");
		add_url("favorite/singer");
		goAjax(1);

		drawTitle(context_root);
		drawMenu(context_root, 0, user_id);
		drawCounter(context_root, user_id);

		//선호가수 선택\\
		$('#favorite_singer').click(function() {

			$('#rtc').html("");
			add_url("favorite/singer");
			goAjax(1);
		});

		//선호 출시연도 선택\\
		$('#favorite_issue_date').click(function() {

			$('#rtc').html("");
			add_url("favorite/issue_date");
			goAjax(1);
		});

		//선호 플레이타임 선택\\
		$('#favorite_play_time').click(function() {

			$('#rtc').html("");
			add_url("favorite/play_time");
			goAjax(1);
		});

		//키워드-거짓말 선택\\
		$('#keyword_lie').click(function() {

			$('#rtc').html("");
			add_url("keyword/lie");
			goAjax(1);
		});

		//키워드-리메이크 선택\\
		$('#keyword_Remake').click(function() {

			$('#rtc').html("");
			add_url("keyword/Remake");
			goAjax(1);
		});

		//키워드-리믹스 선택\\
		$('#keyword_Remix').click(function() {

			$('#rtc').html("");
			add_url("keyword/Remix");
			goAjax(1);
		});

		//키워드-믿음 선택\\
		$('#keyword_trust').click(function() {

			$('#rtc').html("");
			add_url("keyword/trust");
			goAjax(1);
		});

		//키워드-사랑 선택\\
		$('#keyword_love').click(function() {

			$('#rtc').html("");
			add_url("keyword/love");
			goAjax(1);
		});

		//키워드-생일 선택\\
		$('#keyword_birthday').click(function() {

			$('#rtc').html("");
			add_url("keyword/birthday");
			goAjax(1);
		});

		//키워드-소원 선택\\
		$('#keyword_wish').click(function() {

			$('#rtc').html("");
			add_url("keyword/wish");
			goAjax(1);
		});

		//키워드-시간 선택\\
		$('#keyword_time').click(function() {

			$('#rtc').html("");
			add_url("keyword/time");
			goAjax(1);
		});

		//키워드-이별 선택\\
		$('#keyword_parting').click(function() {

			$('#rtc').html("");
			add_url("keyword/parting");
			goAjax(1);
		});

		//키워드-이야기 선택\\
		$('#keyword_story').click(function() {

			$('#rtc').html("");
			add_url("keyword/story");
			goAjax(1);
		});

		//키워드-친구 선택\\
		$('#keyword_friend').click(function() {

			$('#rtc').html("");
			add_url("keyword/friend");
			goAjax(1);
		});

		//키워드-톡 선택\\
		$('#keyword_talk').click(function() {

			$('#rtc').html("");
			add_url("keyword/talk");
			goAjax(1);
		});

		//대한민국 선택\\
		$('#Korea').click(function() {

			$('#rtc').html("");
			add_url("state/korea");
			goAjax(1);
		});

		//아메리카 선택\\
		$('#America').click(function() {

			$('#rtc').html("");
			add_url("state/america");
			goAjax(1);
		});

		//아시아 선택\\
		$('#Asia').click(function() {

			$('#rtc').html("");
			add_url("state/asia");
			goAjax(1);
		});

		//유럽 선택\\
		$('#Europe').click(function() {

			$('#rtc').html("");
			add_url("state/europe");
			goAjax(1);
		});

		//걸그룹 선택\\
		$('#group_girl').click(function() {

			$('#rtc').html("");
			add_url("group/girl");
			goAjax(1);
		});

		//보이그룹 선택\\
		$('#group_boy').click(function() {

			$('#rtc').html("");
			add_url("group/boy");
			goAjax(1);
		});

		//혼성그룹 선택\\
		$('#group_hybrid').click(function() {

			$('#rtc').html("");
			add_url("group/hybrid");
			goAjax(1);
		});

		//솔로녀 선택\\
		$('#solo_girl').click(function() {

			$('#rtc').html("");
			add_url("solo/girl");
			goAjax(1);
		});

		//솔로남 선택\\
		$('#solo_boy').click(function() {

			$('#rtc').html("");
			add_url("solo/boy");
			goAjax(1);
		});

		$('#rtc').css("margin-left", "30px");

		$('li[name=drop_list]').on("click", function() {
			$('li[name=drop_list]').removeClass("active");
			$('#' + this.id).addClass("active");
		})
	});
</script>
</head>
<body style="background-color: #2D2D2D">
	<!-- 이미지 슬라이더 -->
	<div id="img_slider" align="center"></div>

	<!-- 네비게이션 바 -->
	<nav id="navbar"></nav>

	<!-- 평가수 게이지 -->
	<div id="rec_counter"></div>

	<div id="defaultDiv">

		<ul class="nav nav-pills nav-stacked"
			style="float: left; margin-left: 20px;" align="right">

			<li class="dropdown" name="drop_list" id="drop_list_id_1"><a
				class="dropdown-toggle" data-toggle="dropdown" href="#">Keyword
					<span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<li id="keyword_lie"><a href="javascript:void(0);">거짓말</a></li>
					<li id="keyword_Remake"><a href="javascript:void(0);">리메이크</a></li>
					<li id="keyword_Remix"><a href="javascript:void(0);">리믹스</a></li>
					<li id="keyword_trust"><a href="javascript:void(0);">믿음</a></li>
					<li id="keyword_love"><a href="javascript:void(0);">사랑</a></li>
					<li id="keyword_birthday"><a href="javascript:void(0);">생일</a></li>
					<li id="keyword_wish"><a href="javascript:void(0);">소원</a></li>
					<li id="keyword_time"><a href="javascript:void(0);">시간</a></li>
					<li id="keyword_parting"><a href="javascript:void(0);">이별</a></li>
					<li id="keyword_story"><a href="javascript:void(0);">이야기</a></li>
					<li id="keyword_friend"><a href="javascript:void(0);">친구</a></li>
					<li id="keyword_talk"><a href="javascript:void(0);">Talk</a></li>
				</ul></li>

			<li class="dropdown" name="drop_list" id="drop_list_id_2"><a
				class="dropdown-toggle" data-toggle="dropdown" href="#">Nation <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li id="Korea"><a href="javascript:void(0);">한국</a></li>
					<li id="America"><a href="javascript:void(0);">아메리카</a></li>
					<li id="Asia"><a href="javascript:void(0);">아시아</a></li>
					<li id="Europe"><a href="javascript:void(0);">유럽</a></li>
				</ul></li>

			<li class="dropdown" name="drop_list" id="drop_list_id_3"><a
				class="dropdown-toggle" data-toggle="dropdown" href="#">Group
					Kinds <span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<li id="group_girl"><a href="javascript:void(0);">걸 그룹</a></li>
					<li id="group_boy"><a href="javascript:void(0);">보이 그룹</a></li>
					<li id="group_hybrid"><a href="javascript:void(0);">혼성 그룹</a></li>
					<li id="solo_girl"><a href="javascript:void(0);">여자 솔로</a></li>
					<li id="solo_boy"><a href="javascript:void(0);">남자 솔로</a></li>
				</ul></li>

			<li class="dropdown" name="drop_list" id="drop_list_id_4"><a
				class="dropdown-toggle" data-toggle="dropdown" href="#">Analysis
					<span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<li id="favorite_singer"><a href="javascript:void(0);">선호
							가수</a></li>
					<li id="favorite_issue_date"><a href="javascript:void(0);">선호
							출시연도</a></li>
					<li id="favorite_play_time"><a href="javascript:void(0);">선호
							플레이타임</a></li>
				</ul></li>

		</ul>


	</div>




</body>
</html>