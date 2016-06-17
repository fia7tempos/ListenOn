
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

<script src="<c:url value='/resources/song_album_module/js/song_album_module.js'/>"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">





<script type="text/javascript">

$(document).ready(function(){
	
	default_url("operation/newsonggenrelist/DP0000");
	add_url(1);
	goAjax(1);
	
	$('.genre_key').click(function(){
		$('#genreName').text( $('#'+this.id).text() );
		default_url("operation/newsonggenrelist/"+this.id);
		add_url(1);
		$('#total_article').text("");
		$('#rtc').html("");
		goAjax(1);
	});
});



</script>

</head>
<body>

		
<!-- 	배경 -->
	<div class="bg-1"> 
		<div class="container" >
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					<strong id="genreName">Genre</strong> <span class="caret"></span>
				</button>
				
				<ul class="dropdown-menu">
					<c:forEach items="${genreList}" var="vo" varStatus="idx">
						<li><a id="${vo.genre_key}" class="genre_key" >${vo.genre_name}</a></li>
					</c:forEach>	
    			</ul>
    			
			</div>
		</div>
	</div>
<div id="defaultDiv"></div>

</body>
</html>