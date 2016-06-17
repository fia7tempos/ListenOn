<!DOCTYPE html>
<html lang="en">
<head>
<title>제이쿼리 레이어테스트</title>
<meta charset="utf-8">
<style type="text/css">
body {font-size:15px; font-weight:600}
.layer{display: none; width: 180px; height: 80px; border: 2px solid gray; background-color:#FFF; padding:10px; font-size:12px; line-height:15px; font-weight:100}
.ta td {height:30px}
  
</style>

<script src="http://code.jquery.com/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
  $(
  function(){
  $('.data').mouseover(function(e) { // 마우스 오버시 좌표값을 받는다 -> e
    $(this).mousemove(function(e) {
    $('#layer #title').empty().append($(this).attr("l_name"));// layer안에있는 title의 값을 data의 wr_name 값으로 치환한다.
	$('#layer #name').empty().append($(this).attr("wr_name"));
	$('#layer #conttent').empty().append($(this).text());
	
        var t=e.pageY-15;
        var l=e.pageX+20;
        $('#layer').css({"top":t, "left":l,"position":"absolute","opacity":"0,8" }).show();
     }); 
  });
  $('.data').mouseout(function() {
   $('#layer').hide();
  });
  }   
  );
</script>
</head>


<body>
<div id="layer" class="layer">
  <div class="title">
    <strong>레이어이름 :</strong> <span id="title">제목이 들어오는 곳</span>
  </div>
  <div class="name">
    <strong>글쓴이 :</strong> <span id="name">글쓴이 들어오는 곳</span>
  </div>
  
  <br>
  
  <div class="conttent">
    <strong>내용 :</strong> <span id="conttent">내용 들어오는 곳</span>
  </div>
</div>

<!-- 글자에 마우스를 올리면 레이어가 뜨게 하는 예제 시작 -->
<a href='#' style='cursor:pointer'><span class="data" l_name="1번레이어" wr_name="도치즈1">안녕하세요 ㅎ 1 도치즈입니다 ㅎㅎ</span></a><br/>
<a href='#' style='cursor:pointer'><span class="data" l_name="2번레이어" wr_name="도치즈2">안녕하세요 ㅎ 2 지금 시각은 오후 6시 40분이군요^^</span></a><br/>
<!-- 글자에 마우스를 올리면 레이어가 뜨게 하는 예제 끝 -->

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<!-- 표에 마우스를 올리면 레이어가 뜨게 하는 예제 시작 -->
<table width="100%" border="1" cellpadding="0" cellspacing="0" class="ta">
  <tr>
    <td align="center" valign="middle" class="data" l_name="1번레이어" wr_name="도치즈1">
    표에 마우스를 올려보세요
    </td>
  </tr>
  <tr>
    <td align="center" valign="middle" class="data" l_name="2번레이어" wr_name="도치즈2">간단하죠?? ㅎㅎ</td>
  </tr>
</table>
<!-- 표에 마우스를 올리면 레이어가 뜨게 하는 예제 끝 -->

</body>
</html>

