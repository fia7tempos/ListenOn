
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>


<script src="<c:url value='/resources/js/jquery-form.js'/>"></script>

<script src="/www/resources/js/star-rating.js"></script>

<link rel="stylesheet" type="text/css"
	href="/www/resources/css/rating/star-rating.css" />
<script type="text/javascript">
$(document).ready(function() {	
	var captions = {
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
		}
	
	for(var i=0; i < ${list.size()} ;i++){	
	$("#star_point_"+i).rating({min:0, max:5, step:0.5, size:5,
		captionElement: '#caption_div_'+i,
		showClear: false,
		starCaptions: captions
			});
	}
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

var submit_star = function(index){
	$('#form_'+index).ajaxForm(function(data){
		if(data=='success'){
			alert('입력 성공');
		}else{
			alert('실패');
		}
	});
}
</script>
</head>
<script type="text/javascript"></script>
<body>
	<form action="${page}" method="GET">
		<div class="jumbotron">
			<h1>Insert Albums</h1>
		</div>
		<table class="table table-striped">
			<tr>
				<th colspan="2">검색:</th>
				<td><input type="text" id="searchKeyword" name="searchKeyword"
					placeholder="search song" /></td>
				<td><input type="submit" id="btn_search_song" value="검색" /></td>
			</tr>
		</table>
	</form>

	<div id="contents">

		<c:forEach items="${list}" var="vo" varStatus="idx">

			<div class="well">${vo.song_name}</div>
			<div class="well">
				<table class="table table-striped">
					<tr>
						<th>song_no:</th>
						<td colspan="2"><input type="text" name="album_key"
							value="${vo.song_no}" /></td>
					</tr>
					<tr>
						<th>song_key:</th>
						<td colspan="2"><input type="text" name="artist_key"
							value="${vo.song_key}" /></td>
					</tr>
					<tr>
						<th>song_name:</th>
						<td colspan="2"><input type="text" name="album_name"
							value="${vo.song_name}" /></td>
					</tr>
					<tr>
						<th>album_key:</th>
						<td colspan="2"><input type="text" name="album_key"
							value="${vo.album_key}" /></td>
					</tr>
					<tr>
						<th>artist_key:</th>
						<td colspan="2"><input type="text" name="average_score"
							value="${vo.artist_key}" /></td>
					</tr>
					<tr>
						<th>artist_name:</th>
						<td colspan="2"><input type="text" name="artist_name"
							value="${vo.artist_name}" /></td>
					</tr>
					<tr>
						<th>play_time:</th>
						<td colspan="2"><input type="text" name="play_time"
							value="${play_time}" /></td>
					</tr>
					<tr>
						<th>issue_date:</th>
						<td colspan="2"><input type="text" name="issue_date"
							value="${vo.issue_date}" /></td>
					</tr>
					<tr>
						<th>is_hit_song:</th>
						<td colspan="2"><input type="text" name="is_hit_song"
							value="${vo.is_hit_song}" /></td>
					</tr>
					<tr>
						<th>is_adult:</th>
						<td colspan="2"><input type="text" name="is_adult"
							value="${vo.is_adult}" /></td>
					</tr>
					<tr>
						<th>is_free:</th>
						<td colspan="2"><input type="text" name="is_free"
							value="${is_free}" /></td>
					</tr>
					<tr>
						<th>composition:</th>
						<td colspan="2"><input type="text" name="composition"
							value="${vo.composition}" /></td>
					</tr>
					<tr>
						<th>lyricist:</th>
						<td colspan="2"><input type="text" name="lyricist"
							value="${vo.lyricist}" /></td>
					</tr>
					<tr>
						<th>album_name:</th>
						<td colspan="2"><input type="text" name="artist_name"
							value="${vo.album_name}" /></td>
					</tr>
					<tr>
						<th>album_img:</th>
						<td><input type="text" name="album_img"
							value="${vo.album_img}" /></td>
					</tr>
					<tr>
						<td colspan="3"><img src="${vo.album_img}" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<!-- 별점 코멘트 입력부 -->
							<form id="form_${idx.index}" action="recommend" method="POST">
								<div id="caption_div_${idx.index}"></div>
								<input id="star_point_${idx.index}" name="star_point"
									type="number" /> <input name="comment" type="text" /> <input
									type="hidden" name="song_key" value="${songVO.song_key}" /> <input
									type="hidden" name="album_key" value="${songVO.album_key}" />
								<input type="hidden" name="artist_key"
									value="${songVO.artist_key}" /> <input type="button"
									onclick="submit_star(${idx.index})" />
							</form>
						</td>
					</tr>
					<tr>
						<td colspan="3">========================================</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
	<!-- pagenation -->
	<table>
		<tr>
			<td>
				<ul class="pager">
					<c:choose>
						<c:when test="${pageNation.start_page == 1}">
							<li><a href="#">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageNation.start_page-1}">Previous</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</td>
			<td>

				<ul class="pagination">
					<c:forEach begin="${pageNation.start_page}"
						end="${pageNation.end_page}" step="1" varStatus="idx">
						<li><a href="${idx.index}">${idx.index}</a></li>
					</c:forEach>
				</ul>
			</td>
			<td>
				<ul class="pager">
					<c:choose>
						<c:when
							test="${pageNation.end_page == pageNation.total_page_count}">
							<li><a href="#">Next</a></li>
						</c:when>

						<c:otherwise>
							<li><a href="${pageNation.end_page+1}">Next</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</td>
		</tr>
	</table>

</body>
</html>
