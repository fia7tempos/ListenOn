<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listen On♬</title>
<link>
<!-- <link rel="stylesheet" -->
<%-- 	href="<c:url value='/resources/css/bootstrap/bootstrap.css'/>"/> --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/dark-hive/jquery-ui.css'/>" />
<script src=''></script>
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<%-- <script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script> --%>
<script src='<c:url value="/resources/js/bootstrap.js"/>'></script>
<!-- chart plugin -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/jqchart/css/jquery.jqplot.css'/>" />
<script
	src="<c:url value='/resources/js/upper_menu.js'/>"></script>

<script src='<c:url value="/resources/jqchart/js/jquery.jqplot.js"/>'></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.enhancedLegendRenderer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.canvasTextRenderer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.canvasAxisLabelRenderer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.bubbleRenderer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.json2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.meterGaugeRenderer.js'/>"></script>
<script src="<c:url value='/resources/song_album_module/js/user_analysis_list.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/jqcloud/css/jqcloud.css'/>" />
<script src="<c:url value='/resources/jqcloud/js/jqcloud.js'/>"></script>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<style type="text/css">

#str {
	letter-spacing: 10px;
	font-size: 25px;
	color: white;
}

.bg-1 {
	background: #2d2d30;
	color: #bdbdbd;
}

.bg-1 h3 {
	color: #fff;
}

.bg-1 p {
	font-style: italic;
}

#addUser {
	background-image: url("/www/resources/img/glyphicons/png/glyphicons-793-user-vr-add.png");
	width: 20px;
	
}

.thumbnail {
	padding: 0 0 15px 0;
	border: none;
	border-radius: 0;
}

.thumbnail p {
	margin-top: 15px;
	color: #555;
}

.btn {
	padding: 10px 20px;
	background-color: #333;
	color: #f1f1f1;
	border-radius: 0;
	transition: .2s;
}

.btn:hover, .btn:focus {
	border: 1px solid #333;
	background-color: #fff;
	color: #000;
}

div[name=follow_div] {
	float:left; 
	margin-right:30px; 
	margin-bottom:30px; 
	width:160px; 
	height:230px
}



div[name=follow_div] img{
	height:120px;
	width: 120px;
	margin-bottom : 10px;
}

div[name=follow_div] div{
	font-weight: bold;
}



div[name=follow_div] button{
	margin-top : 10px;
}

</style>

<script type="text/javascript">
	//세션 있는가 보자
	var context_root = '<c:url value="/" />';
	var root = "/www/user/analysis/";
	var user_root = "/www/user/";
	var user_id = '${target_user_id}';
$(document).ready(function() {
	
	
	//상단 컴포넌트 그리기
	$(document).ready(function() {
		drawTitle(context_root);
		drawMenu(context_root,5,'${user_id}');
		
		
	});
});


	$(document).ready(function() {
		
						//장르차트 호출
						var pref_genre_jsonURL = root+user_id+"/pref_genre";
						
						$.ajax({
							url : pref_genre_jsonURL,
							type : "GET"
						}).done(
								function(data) {
									jsondata = [];
									for ( var i in data) {
										jsondata.push([
												(Math.random() * 100) + 1, //x좌표
												(Math.random() * 100) + 1, //y좌표
												data[i].cnt * 200,			//반지름
												data[i].genreName ]);		//라벨
									}
									makePrefGenre(jsondata);
								});

						//장르차트 생성
						var makePrefGenre = function(data) {

							plot1 = $.jqplot('chart_genre', [ data ], {
								title : '선호 장르',
								seriesDefaults : {
									renderer : $.jqplot.BubbleRenderer,
									rendererOptions : {
										bubbleAlpha : 0.6,
										highlightAlpha : 0.8
									},
									shadow : true,
									shadowAlpha : 0.05
								},
								grid : {
									drawBorder : false,
									shadow : false,
									// background: 'rgba(0,0,0,0)'  works to make transparent.
									background : 'white'
								}
							});
						}
						//장르차트 끝

						//별점차트 호출
						var avg_star_jsonURL = root+user_id+"/avg_stars";

						var jsondata = [];
						var starplot = function() {
							$.ajax({
						
							url : avg_star_jsonURL,
							type : "GET"
						}).done(
								function(data) {
									var max = 0;
									for ( var i in data) {
										jsondata.push([ data[i].star_point,
												data[i].cnt ]);
										if (max < data[i].cnt)
											max = data[i].cnt;
									}
									jsondata.sort().reverse();
									makeAvgStarPlot(jsondata, max);
								});
						}

						//별점차트 생성
						var makeAvgStarPlot = function(data, max) {
							plot1 = $
									.jqplot(
											'chart_star',
											[ data ],
											{
												animate: true,
												animateReplot: true,
												title : '별점 분포',
												stackSeries : false,
												seriesColors : [ '#FFcc77',
														'#B9CDE5' ],
												seriesDefaults : {
													showMarker : false,
													fill : true,
													fillToZero: true,													
													fillAndStroke : true,
													rendererOptions: {
									                    smooth: true,
									                }
												},
												axes : {
													xaxis : {
														pad : 0.5,
														min : 0.5,
														max : 5,
														numberTicks : 11,
														label : '별점',
														labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
														tickInterval : 0.5,
														tickOptions : {
															showGridline : false
														}
													},
													yaxis : {
														min : 0,
														max : max + 5,
														label : '갯수',
														labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
														tickOptions : {
															showGridline : false,
														}
													}
												},
												grid : {
													drawBorder : false,
													shadow : false,
													// background: 'rgba(0,0,0,0)'  works to make transparent.
													background : 'white'
												}
											});
							//별점차트끝
							
							//부스터 호출
							var playtime_jsonURL = root+user_id+"/playtime";
								$.ajax({
								url : playtime_jsonURL,
								type : "GET"
							}).done(
								function(data) {
									jsondata = [];
									jsondata.push(data);
									var caption;
									if(data>500) caption=data+"분. 당신만의 음악 관점이 생기셨겠네요.";
									else if(data>300) caption=data+"분이라니 진정한 음악매니아십니다.";
									else if(data>200) caption=data+'분! 대단해요 300분 도전해봐요';
									else if(data>100) caption= data+'분! 200분까지 멀지 않았어요';
									else if(data>50) caption=data+'분 동안 음악을 들으셨네요';
									else caption='아직 평가하신 음악이 없습니다.';
									makeBooster(jsondata,caption);
							});
							//부스터 생성
							var makeBooster = function(data,caption){
							   $.jqplot('booster',[data],{
									title:caption,
							       seriesDefaults: {
							           renderer: $.jqplot.MeterGaugeRenderer,
							           rendererOptions: {
							               min: 100,
							               max: 500,
							               intervals:[200, 300, 400, 500],
							               intervalColors:['#66cc66', '#93b75f', '#E7E658', '#cc6666']
							           }
							       }
							   });
							}
							
							//워드클라우드
							var artistData = [];

							$.ajax({
								url : root+user_id+"/prefer_artist",
								method : "POST",
							//async:false //전역 변수에 담을 수 있도록 설정
							}).done(function(data) {

								$.each(data, function(key, val) {
									var $jobj = new Object();
									$jobj.text = val.artist_name;
									$jobj.weight = 11 - (1 + key);
									$jobj.link = context_root+"operation/search?search_keyword="+val.artist_name;
									artistData.push($jobj);
								});
								makeWordcloud(artistData);
							});
							var makeWordcloud = function(data) {
								$('#wordcloud').jQCloud(data, { //넣을 div
									autoResize : 'true',
									shape : 'elliptic', // elliptic, rectangular
									width : "450",
									height : "300"
								});
							}
							//워드클라우드 끝
							
							var makeCommentPanel = function(data){
								
								var result = $('<li/>',{class:'we-padding-16',click:function(){ $makeDetail(data.album_key, data.song_key)}})
								var title = "";
								
								if(data.song_name != null){
									title =  data.song_name;
								}else{
									title = "Album : "+data.album_name;
								}
								$('<span/>',{onclick:'this.parentElement.style.display="none"',class:'w3-closebtn w3-padding w3-margin-right w3-medium'}).text('X').appendTo(result);
								$('<img/>',{src:data.album_img,class:'w3-left w3-circle w3-margin-right',style:'width: 60px'}).appendTo(result);
								$('<span/>',{class:'w3-xlarge'}).text((data.artist_name + ' - ' +title).substring(0,65)).appendTo(result);
								$('<br/>').appendTo(result);
								$('<span/>',{class:'badge'}).text(data.star_point+'  ').appendTo(result);
								$('<span/>').text(' '+data.user_comment).appendTo(result);
								
								return result;
							}
							
							
							//평가 리스트 뿌리기
							$.ajax({
								url: root+'${target_user_id}/rec_list',
								method : 'GET'
							}).done(function(data){
								
								$.each(data.songList,function(item, index){
									$('#list_rec').append(makeCommentPanel(index));
								})
								$.each(data.albumList,function(item, index){
									$('#list_rec').append(makeCommentPanel(index));
								})
								
							});
								
							
							
// 							var prefer_artist_jsonURL = "${target_user_id}/prefer_artist";
// 							$.ajax({
// 								url : prefer_artist_jsonURL,
// 								type : "GET"
// 							}).done(
// 									function(data) {
// 										$('table')
// 										for ( var i in data) {
// 											var itemdiv = $('div',{id: 'item'+data[i].artist_key});
// 											itemdiv.append($('image',{src: ${cdn_url}+'/cdnimg/image/artist/'+data[i].artist_key},width:'20%'));
// 										}
// 									});
							
							
							
						};
						 
						var makeFollow = function(item){
							console.log(item);
							var user_nick = item.user_nick;
							var bool = false;
							
							if (item.user_id == '${user_id}'){
								bool = true;
							} else {
								bool = false;
							}
							
							var user_img = null;
							
							if(item.user_img != null){
								user_img = item.user_img
							} else {
								user_img = "http://cdn.mysitemyway.com/icons-watermarks/flat-circle-white-on-black/bfa/bfa_user/bfa_user_flat-circle-white-on-black_512x512.png";
							}
							
							var userImg = $('<img />', {
								src : user_img,
								"class":"img-rounded"
							})
							
							if(user_nick.length > 7){
								user_nick = user_nick.substring(0,6) + "..";
							}
							
							var userNick = $('<div />',{
								text : user_nick
							})
							
							var a_link = $('<a />',{
								href: root+"other/"+item.user_id,
								name:"a_tooltip",
								"title":"Go Home",
								"data-toggle":"tooltip",
								"data-placement":"right"
							}).append(userImg).append(userNick);
							
							
							var btnFollow = $('<button />',{
								type:"button",
								"class":"btn btn-info",
								text:"Follow",
								disabled:bool,
								click:function(){
									addFollow(item.user_id)
								}
							})
							
							var wellDiv = $('<div />',{
								"class" : "well",
								"name" : "follow_div",
							}).append(a_link).append(btnFollow);
							
							return wellDiv;
						}
						
						
						$('li[name=li_list]').on("click",function(){
							$('li[name=li_list]').removeClass('active');
							$('#'+this.id).addClass('active');
							
							if( this.id == 'user_comment_list'){
								
								$('#div_comment_list').show();
								$('#div_analysis').hide();
								$('#inputList').hide();
								$('#follower_list').hide();
								$('#following_list').hide();
								
							}
							
							if( this.id == 'analysis'){
								
								$('#div_comment_list').hide();
								$('#inputList').hide();
								$('#div_analysis').show();
								$('#follower_list').hide();
								$('#following_list').hide();
								
							}
							
							if( this.id == 'heard_music'){
								$('#div_comment_list').hide();
								$('#div_analysis').hide();
								$('#inputList').show();
								$('#follower_list').hide();
								$('#following_list').hide();
								
								
								$('#rtc').html("");
								default_url("user/analysis/usermusiclist/" + user_id);
								add_url(1);
								goAjax(1);
							}	
							
							if( this.id == 'li_follower_list'){
								
								$('#div_comment_list').hide();
								$('#inputList').hide();
								$('#div_analysis').hide();
								$('#follower_list').show();
								$('#following_list').hide();
								
								$.ajax({
									url : user_root + "get_follower_list",
									type : "post",
									data :{
										"user_id" : user_id
									}
								}).done(function(data){
									$('#follower_list').html("");
									$.each(data,function(index,item){
										$('#follower_list').append(makeFollow(item));
									})
									$('[data-toggle="tooltip"]').tooltip();
								});
							}
							
							if ( this.id == 'li_follwing_list'){
								
								$('#div_comment_list').hide();
								$('#inputList').hide();
								$('#div_analysis').hide();
								$('#follower_list').hide();
								$('#following_list').show();
								
								
								$.ajax({
									url : user_root + "get_following_list",
									type : "post",
									data :{
										"user_id" : user_id
									}
								}).done(function(data){
									$('#following_list').html("");
									$.each(data,function(index,item){
										$('#following_list').append(makeFollow(item));
									})
									$('[data-toggle="tooltip"]').tooltip();
								});
								
							}
								
								
						});
							
						
						var addFollow = function(u_id){
							
							$.ajax({
								url : user_root + "add_follow",
								type : "post",
								data :{
									"target_user_id" : u_id
								}
							}).done(function(data){
								swal({
									title : data,
									timer : 800,
									showConfirmButton : false
								});
							});
							
						}
						
							$('#addFollow').on("click",function(){
								addFollow(user_id);
							})
							
							starplot();
							
							
							
					});
</script>

</head>
<body style="background-color:#2d2d2d">

	<!-- 	배경 -->
	
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center"></div>

		<!-- 네비게이션 바 -->
		<nav id="navbar"></nav>
		
		<div class="container">
		<div class="row">
		
		
		
		<c:set var="user_id" value="${fn:substring(target_user_id, 0, 10)}" />
			<div style="color:white">
				<ul class="nav nav-tabs">
				  <li name="li_list" id="analysis" class="active"><a href="javascript:void(0);">취향분석</a></li>
				  <li name="li_list" id="heard_music"><a href="javascript:void(0);">들었어요</a></li>
				  <li name="li_list" id="user_comment_list"><a href="javascript:void(0);">Comment</a></li>
				  <li style="float:right;"  > <strong id="str" data-toggle="dropdown"> ${user_id}</strong>님  
				  	
						  <ul class="dropdown-menu dropdown-menu-right">
						    <li id="addFollow"><a href="javascript:void(0);"><strong>Follow </strong><span class="glyphicon glyphicon-plus-sign"></span></a></li>
						  </ul>
				  </li>
				  
				  
				  <li style="margin-right:20px ; float:right;" name="li_list" id="li_follower_list"><a href="javascript:void(0);">팔로워</a></li>
				  <li style="float:right;" name="li_list" id="li_follwing_list"><a href="javascript:void(0);">팔로잉</a></li>
				</ul>		
			</div>
		</div>
		</div>
		<br/><br/>
		
		
	
	<div class="container">
	<div id="div_analysis">
		<div class="row">
			<div class="well col-md-6 col-lg-6">
				<div id="chart_star"></div>
			</div>	
			<div class="well col-md-6 col-lg-6">
				
				<div id="chart_genre"></div>
			</div>
			</div>
			<div class="row">
			<div class="well col-md-6 col-lg-6">
				<div align="center"></div>
				<div id="wordcloud"></div>
			</div>
			
			<div id="boosterContainer" class="well col-md-6 col-lg-6">
				<div id="booster"></div>
			</div>
			</div>
		</div>
		<div style="height: 50px"></div>
		<div class="row">	
		<!-- 최근평가 리스트 -->
		<div class="well col-md-12" id="div_comment_list" hidden="true">
		
			<ul id="list_rec" class="w3-ul w3-card-4">
			  
	  		</ul>
		</div>	

	</div>
	
		<div id="follower_list" hidden="true" align="center"></div>
		
		<div id="following_list" hidden="true" align="center"></div>

	</div>
	
		
	<div id="defaultDiv"></div>
	
	

</body>
</html>