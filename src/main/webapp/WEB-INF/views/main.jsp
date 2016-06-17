<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang ="ko">
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Listen On♬</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<script src="<c:url value='/resources/song_album_module/js/main_detail.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script type="text/javascript">
	var context_root = '<c:url value="/" />';
	var user_id = '${user_id}';
	 var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	 
	$(document).ready(function(){
		drawTitle(context_root);
		drawMenu(context_root, 0, user_id);
		drawCounter(context_root, user_id);
	});	
	
	$(window).load(function(){
		
		//1단 인기검색어 뿌리기
		$.ajax({
			url:context_root+'searchKeyword',
			type:'GET'
		}).done(function(data) {
			for(var i=0;i<data.length;i++){
				$('#keyword_rank').append(
						$('<li/>').addClass('list-group-item')
							.append($('<a/>',{href:context_root+"operation/search?search_keyword="+data[i].keyword})
									.text(data[i].keyword))
							.append($('<span/>').addClass('badge').text(data[i].count)));
			}
		});
		//3단 별점순위 뿌리기
		$.ajax({
			url:context_root+'recommendrank',
			type:'GET'
		}).done(function(data) {
			for(var i=0;i<data.length;i++){
				$('#keyword_rec_rank').append(
						$('<li/>').addClass('list-group-item')
							.append($('<a/>',{href:context_root+"user/analysis/other"+data[i].user_id})
									.text(data[i].user_nick))
							.append($('<span/>').addClass('badge').text(data[i].count)));
			}
		});
		
		//1단 추천항목 넣기
		$.ajax({
			url:context_root+'song/mainpagelist',
			type:'GET'
			}).done(function(data){
				var page=3,totalCount=0;
				
				for(var i=data.length -1;i>=0;i--){
					$('#main_upper_slider > .slider-item > .row:eq('+page+')').append(makePanel(data[i],i));
					totalCount++;
					if(totalCount==8){
						page--;
						totalCount=0;
					}
				}
					//1단 슬라이더 생성
					var main_upper_slider = $('#main_upper_slider').lightSlider({
						autoWidth : false,
						pauseOnHover : true,
						item:1,
						controls:true,
						mode : 'slide',
						speed : 800,
						verticalHeight : 300,
						adaptiveHeight : true
					}).refresh();
			});
		//2단 추천항목 넣기
		$.ajax({
			url:context_root+'song/xcenturysongs?year=199',
			type: 'GET'
		}).done(function(data){
			var page=0,count=0;
			for(var i=0;i<data.length;i++){
				$('#main_middle_slider > .slider-item > .row:eq('+page+')')
					.append(makeCard(data[i]));
				count++;
				if(count>5) {
					count=0;
					page++;
				}
			}			
			$('#main_middle_slider').lightSlider({
					autoWidth : false,
					pauseOnHover : true,
					item:1,
					controls:true,
					mode : 'slide',
					speed : 800,
					verticalHeight : 900,
					vertical: true
			});
		})
		
		//3단 추천항목 넣기
		$.ajax({
			url:context_root+'song/mainpagedownlist',
			type:'GET'
		}).done(function(data){
			var page=0
			for(var i=0;i<6;i++){
				$('#main_down_slider > .slider-item > .row:eq('+page+')').append(makeArtistPanel(data[i],data[i].genre_names));
			}
			page++;
			for(var i=6,count=1;i<data.length;i++,count++){
				$('#main_down_slider > .slider-item > .row:eq('+page+')').append(makeArtistPanel(data[i],data[i].genre_names));
				if(count==8) {
					page++;
					count=0;
				}
			}
			
			
			//3단 슬라이더 생성
			var main_down_slider = $('#main_down_slider').lightSlider({
				autoWidth : false,
				pauseOnHover : true,
				item:1,
				controls:true,
				mode : 'slide',
				speed : 800,
				verticalHeight : 600,
				adaptiveHeight : true
			}).refresh();
			
			
		});
		
		//4단 추천항목 넣기
		$.ajax({
			url:context_root+'song/adultsonglist',
			type: 'GET'
		}).done(function(data){	
			var page=0,count=0;
			for(var i=0;i<data.length;i++){
				$('#main_fourth_slider > .slider-item > .row:eq('+page+')')
					.append(makeCard(data[i]));
				count++;
				if(count>5) {
					count=0;
					page++;
				}
			}
			$('#main_fourth_slider').lightSlider({
					autoWidth : false,
					pauseOnHover : true,
					item:1,
					controls:true,
					mode : 'slide',
					speed : 800,
					verticalHeight : 900,
					vertical: true
			});
		})
		//5단 추천항목 넣기
		$.ajax({
			url:context_root+'song/survivalsonglist',
			type:'GET'
		}).done(function(data){
			var page=0,count=0;
			for(var i=0;i<32;i++){
				$('#main_5th_slider > .slider-item > .row:eq('+page+')')
					.append(makeSongPanel(data[i],data[i].album_name))
					count++;
				if(count>7){
					count=0;
					page++
				}
			}
			//5단 슬라이더 생성
			var main_5th_slider = $('#main_5th_slider').lightSlider({
				autoWidth : false,
				pauseOnHover : true,
				item:1,
				controls:true,
				mode : 'slide',
				speed : 800,
				verticalHeight : 600,
				adaptiveHeight : true
			}).refresh();
		});
		
		//19금 모자이크
		$('#div_19_toggle').on('click',function(ev){
			$(ev.currentTarget).fadeOut('slow');
		});
		
		//카드 생성 함수
		var makeCard = function(data){			
			var result = $('<div/>',{class:'col-sm-4 col-md-4 w3-card-6 w3-dark-grey',style:'maxHeight:350;'})
			$('<div/>',{class:'w3-container w3-center'})
				.append($('<h3/>',{class:'w3-text-shadow '}).text(data.artist_name.substring(0,10)))
				.append($('<img/>',{src:data.album_img,style:'width:80% ;height:35%;',click:function(){makeDetail2(data.album_key,data.song_key)}}))
				.append($('<h5/>',{class:'w3-text-shadow '}).text(data.song_name.substring(0,10)))
				.appendTo(result);
				return result;
		};
		
		
		//패널 생성 함수
		var makePanel = function(data,index){
			if(index>=20) return makeArtistPanel(data,'평점이 높은 아티스트');
			else if(index>=10) return makeAlbumPanel(data,'평점이 높은 앨범');
			else return makeSongPanel(data,'평점이 높은 음악');
		}		
		var makeSongPanel = function(songData,headText){
			var result = $('<div/>').addClass('col-sm-3');
			$('<div/>').addClass('panel panel-info')
				.append($('<div/>').addClass('panel-heading').text(headText.substring(0,18)))
				.append($('<div/>').addClass('panel-body')
						.append($('<img/>',{src:songData.album_img,class:'img-responsive',style:'width: 100%',click:function(){makeDetail2(songData.album_key,songData.song_key)}})))
				.append($('<div/>',{
					class:'panel-footer'
				}).text((songData.song_name + ' - ' +songData.artist_name).substring(0,17))).appendTo(result);
			return result;
		};
		var makeAlbumPanel = function(albumData,headText){
			var result = $('<div/>').addClass('col-sm-3');
			$('<div/>').addClass('panel panel-danger')
				.append($('<div/>').addClass('panel-heading').text(headText.substring(0,18)))
				.append($('<div/>').addClass('panel-body')
						.append($('<img/>',{src:albumData.album_img,class:'img-responsive',style:'width: 100%',click:function(){makeDetail2(albumData.album_key,albumData.song_key)}})))
				.append($('<div/>',{
					class:'panel-footer'
				}).text(albumData.album_name.substring(0,20))).appendTo(result);
			return result;
		};
		var makeArtistPanel = function(artistData,headText){
			var result = $('<div/>').addClass('col-sm-3');
			$('<div/>').addClass('panel panel-success')
				.append($('<div/>').addClass('panel-heading').text(headText.substring(0,18)))
				.append($('<div/>').addClass('panel-body')
						.append($('<img/>',{src:artistData.artist_img,class:'img-responsive',style:'width: 100%',click:function(){location.href=context_root+'operation/search?search_keyword='+artistData.artist_name}})))
				.append($('<div/>',{
					class:'panel-footer'
				}).text(artistData.artist_name.substring(0,20))).appendTo(result);
			return result;
		};
		
	});
</script>
<style>
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
     .jumbotron {
      margin-bottom: 0;
    }
   
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    
    .wrapper-div{
  	  position:absolute; 
  	  top:0;left:0; 
  	  width:100%; height:100%; 
  	  background-color:RGBA(255,119,119,0.95); 
  	  z-index:4;
    }
  </style>
</head>
<body>
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center"></div>

		<!-- 네비게이션 바 -->
		<nav id="navbar"></nav>
		
		<div class="jumbotron" align="center">
    		<h2>오늘의</h2><h1> Listen On Chart</h1> 
    		<p>사람들이 높게 평가한 음악을 들어보세요</p> 
  		</div>
  		<br/><br/>
		
		<!-- 메인화면 1단 슬라이더 -->
		<ul id="main_upper_slider">
			<li class='slider-item'><div class="row"><hr/>
				<div class="col-sm-3">
					<div class="panel panel-warning">
						<div class="panel-heading">오늘의 인기검색어 순위
          <span class="glyphicon glyphicon-hand-up"></span> Up
        </div>
						<div class="panel-body">
							<ul id="keyword_rank" class="list-group"></ul>
						</div>
					</div>
				</div>
				</div>
			</li>
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
		</ul>
	<hr/>
	
	<div class="jumbotron" align="center">
    		<h2>다시 한번 90s</h2><h1> Listen On Chart</h1> 
    		<p>90년대 히트곡 총출동!</p> 
  		</div>
  		<br/><br/>

	
	<!-- 메인화면 2단 90년대 슬라이더 -->
		<ul id="main_middle_slider" class="col-sm-12">
			<li class='slider-item'><div class="row"></div><hr/></li>
			<li class='slider-item'><div class="row"></div><hr/></li>				
			<li class='slider-item'><div class="row"></div><hr/></li>				
			<li class='slider-item'><div class="row"></div><hr/></li>				
			<li class='slider-item'><div class="row"></div><hr/></li>				
		</ul>
		<hr/>
		<br/><br/>
		
		<div class="jumbotron" align="center" style="position:relative; z-index:3;">
    		<h2>장르별</h2><h1> Listen On Chart</h1> 
    		<p>다른사람들은 어떤 장르의 어떤 곡을 듣고 있을까</p> 
  		</div>
  		<br/><br/>
		
	<!-- 메인화면 3단 선호장르 슬라이더 -->
		<ul id="main_down_slider">
			<li class='slider-item'><div class="row">  <hr/>
				<div class="col-sm-3">
					<div class="panel panel-warning">
						<div class="panel-heading">평가를 많이 한 유저 Top 10</div>
						<div class="panel-body">
							<ul id="keyword_rec_rank" class="list-group"></ul>
						</div>
					</div>
				</div>
				</div>
			</li>
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
		</ul>
		<br/><br/>
		<!-- 메인화면 4단 19금 슬라이더  -->
		<div class="jumbotron" align="center" style="background-color:#FF7777;">
    		
    		<h2>쉿</h2><h1> Listen On ADULT Chart</h1> 
    		<p>19세 이하 접근 금지</p> 
  		</div>
  		<br/><br/>
  		
		<ul id="main_fourth_slider" class="col-sm-12">
			<li class='slider-item'><div class="row"><hr/><div id="div_19_toggle" class="container wrapper-div"><div align="center"><H1>19</H1></div></div></div></li>
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
		</ul>
		<hr/>
  		<br/><br/>
		
		<!-- 메인화면 5단 서바이벌 슬라이더 -->
		<div class="jumbotron" align="center" style="position:relative; z-index:3;">
    		<h2>서바이벌!</h2><h1> Listen On Chart</h1> 
    		<p>서바이벌 프로그램을 통해 음악을 '보다'</p> 
  		</div>
  		<br/><br/>
		<ul id="main_5th_slider">
			<li class='slider-item'><div class="row"><hr/></div></li>
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
			<li class='slider-item'><div class="row"><hr/></div></li>				
		</ul>
		
		<div id="defaultDiv"></div>
		


<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>