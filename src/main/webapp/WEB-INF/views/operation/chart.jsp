<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listen On♬</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>
<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<script src="<c:url value='/resources/song_album_module/js/song_album_module.js'/>"></script>

<script
   src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
   
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
   
<!-- 사이드 메뉴 -->


<script type="text/javascript">

//세션 있는가 보자
var context_root = '<c:url value="/" />';
var user_id = '${user_id}';

   
   //상단 컴포넌트 그리기
   $(document).ready(function() {
      drawTitle(context_root);
      drawMenu(context_root,5,user_id);
      drawCounter(context_root,user_id);
   });
   
   $(document).ready(function(){
      $('#rtc').css("margin-left","30px");
      
      var makeList = function(thisid, url, addUrl){
         $('li[name=ul_list]').removeClass("active");
         $('#'+thisid).addClass("active");
         
         $('#rtc').html("");
         
         default_url("operation/" + url);
         add_url(addUrl);
         goAjax(1);
         
      }
      
      makeList("realtimesongchartlist","realtimesongchartlist",1);
   
      // 실시간 인기차트 ㅇ
      $('#realtimesongchartlist').on("click", function(){
         makeList(this.id,this.id,1);
      })
      
      // 오늘의 TOP 곡 ㅇ
      $('#todaytopsongchartlist').on("click",function(){
         makeList('todaytopsongchartlist','todaytopsongchartlist',1);
      })
      
      // 장르별 TOP100 곡
      $('li[name=genreSongTopList]').on("click",function(){
         makeList('gettop100genresongchartlist','gettop100genresongchartlist/'+this.id,1);
      });
      
      // TOP20 앨범
      $('#gettop20albumchartlist').on("click", function(){
         makeList('gettop20albumchartlist','gettop20albumchartlist',1);
         $('#btnMore').hide();
      })
      
      // 장르별 최신 곡 ㅇ
      $('li[name=genreNewSongList]').on("click",function(){
         makeList("newsonggenrelist","newsonggenrelist/" + this.id,1);
      })
      
      // 장르별 최신 앨범 ㅇ
      $('li[name=genreNewAlbumList]').on("click",function(){
         makeList("newalbumgenrelist","newalbumgenrelist/" + this.id,1);
      })
      
      $('#rtc').css("margin-left", "40px");
         
   })
   
</script>
</head>
<body style="background-color:#2D2D2D">

   <!-- 이미지 슬라이더 -->
      <div id="img_slider" align="center"></div>

      <!-- 네비게이션 바 -->
      <nav id="navbar"></nav>
      
      <!-- 평가수 게이지 -->
      <div id="rec_counter"></div>

   <!-- 사이드 메뉴 -->
   <div id="defaultDiv">
   
         <ul class="nav nav-pills nav-stacked"  style="float:left; margin-left:20px;" align="right">
         
                <li name="ul_list" class="active" id="realtimesongchartlist"><a href="javascript:void(0);">실시간 인기 차트!</a></li>
                
                <li name="ul_list"  id="todaytopsongchartlist"><a href="javascript:void(0);">오늘의 HOT SONG!</a></li>
                
              
              <li class="dropdown" name="ul_list" id="gettop100genresongchartlist">
                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">장르별 HOT SONG!
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                   <c:forEach items="${genreList}" var="vo" varStatus="idx">
                     <li id="${vo.genre_key}" name="genreSongTopList"><a  href="javascript:void(0);" >${vo.genre_name}</a></li>
                  </c:forEach>   
                </ul>
              </li>
              
              <li name="ul_list"  id="gettop20albumchartlist"><a href="javascript:void(0);">인기 앨범을 모았어요</a></li>
              
              <li class="dropdown" name="ul_list" id="newsonggenrelist">
                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">장르별 최신 SONG!
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                   <c:forEach items="${genreList}" var="vo" varStatus="idx">
                     <li id="${vo.genre_key}" name="genreNewSongList"><a  href="javascript:void(0);" >${vo.genre_name}</a></li>
                  </c:forEach>   
                </ul>
              </li>
              
              <li class="dropdown" name="ul_list" id="newalbumgenrelist">
                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">장르별 최신 앨범들!
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                   <c:forEach items="${genreList}" var="vo" varStatus="idx">
                     <li id="${vo.genre_key}" name="genreNewAlbumList"><a  href="javascript:void(0);" >${vo.genre_name}</a></li>
                  </c:forEach>   
                </ul>
              </li>
         </ul>
   </div>

</body>
</html>