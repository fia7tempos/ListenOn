<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
</head>
<body>
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
							<th>album_img:</th>
							<td><input type="text" name="album_img"
								value="${vo.album_img=='http://cdnimg.melon.co.kr'?'':vo.album_img}" /></td>
						</tr>
						<tr>
							<td colspan="3"><c:if
									test="${vo.album_img!='http://cdnimg.melon.co.kr'}">
									<img src="${vo.album_img}">
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

</body>
</html>