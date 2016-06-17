
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
<script src="<c:url value='/resources/js/jquery-form.js'/>"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.btn').click(function(event) {
			var idxstr = this.id;
			idxstr = idxstr.slice(10, idxstr.length);
			$.post('${page}', $('#' + idxstr).serialize(), function(data) {
				if (data === 'existid') {
					$('#input_result_' + idxstr).html("이미 등록된 Album입니다.");
				} else if (data === 'success') {
					$('#input_result_' + idxstr).html("Album 등록 성공");
				}
			});
		});
	});
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
					placeholder="search artist" /></td>
				<td><input type="submit" id="btn_search_artist" value="검색" /></td>
			</tr>
		</table>
	</form>

	<div id="contents">

		<c:forEach items="${list}" var="vo" varStatus="idx">
			<form action="${page}" role="form" name="albumForm" id="${idx.index}"
				method="POST">
				<div class="well">${vo.album_name}</div>
				<div class="well">
					<table class="table table-striped">
						<tr>
							<th>album_key:</th>
							<td colspan="2"><input type="text" name="album_key"
								value="${vo.album_key}" /></td>
						</tr>
						<tr>
							<th>artist_key:</th>
							<td colspan="2"><input type="text" name="artist_key"
								value="${vo.artist_key}" /></td>
						</tr>
						<tr>
							<th>album_name:</th>
							<td colspan="2"><input type="text" name="album_name"
								value="${vo.album_name}" /></td>
						</tr>
						<tr>
							<th>average_score:</th>
							<td colspan="2"><input type="text" name="average_score"
								value="${vo.average_score}" /></td>
						</tr>
						<tr>
							<th>issue_date:</th>
							<td colspan="2"><input type="text" name="issue_date"
								value="${vo.issue_date}" /></td>
						</tr>
						<tr>
							<th>album_cover:</th>
							<td><input type="text" name="album_cover"
								value="${vo.album_cover=='http://cdnimg.melon.co.kr'?'':vo.album_cover}" /></td>
						</tr>
						<tr>
							<td colspan="3"><c:if
									test="${vo.album_cover!='http://cdnimg.melon.co.kr'}">
									<img src="${vo.album_cover}">
								</c:if></td>
						</tr>
						<tr>
							<th>명령</th>
							<td colspan="2"><input type="button" class="btn"
								id="btn_input_${idx.index}" value="입력" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="input_result_${idx.index}"></span></td>
						</tr>
						<tr>
							<td colspan="3">========================================</td>
						</tr>
					</table>
				</div>
			</form>
		</c:forEach>
	</div>
	<!-- pagenation -->
	<table>
		<tr><td>
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
						<li><a href="${getData.pageNation.end_page+1}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			</td>
			</tr>
	</table>

</body>
</html>
