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
	$(document).ready(function(){
		$('.btn').click(function(event){
			var idxstr = this.id;
			idxstr = idxstr.slice(10,idxstr.length);
			
			//$('#'+idxstr).ajaxForm();
			
			$.post('${page}',$('#'+idxstr).serialize(),function(data){
				if(data==='existid'){
					$('#input_result_'+idxstr).html("이미 등록된 Artist입니다.");
				}else if(data==='success'){
					$('#input_result_'+idxstr).html("Artist 등록 성공");
				}
			});
			
// 			alert($('.artistForm['+idxstr+']').attr('id'));
// 			console.log($('.artistForm')[0]);
			
// 			 $('#form'+idxstr).ajaxForm(function() { 
// 				 alert('#form'+idxstr);
// 	         });
		});
	});
</script>
</head>
<script type="text/javascript"></script>
<body>
	<form action="${page}" method="GET">
		<div class="jumbotron"><h1>Insert Artists</h1></div>
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
		<form action="${page}" role="form" name="artistForm" id="${idx.index}" method="POST">
		<div class="well">${vo.artist_name}</div>
		<div class="well">
			<table class="table table-striped">
				<tr>
					<th>artist_key:</th>
					<td colspan="2"><input type="text"
						name="artist_key" value="${vo.artist_key}" /></td>
				</tr>
				<tr>
					<th>genre_names:</th>
					<td colspan="2"><input type="text"
						name="genre_names" value="${vo.genre_names}" /></td>
				</tr>
				<tr>
					<th>artist_key:</th>
					<td colspan="2"><input type="text"
						name="act_type_name" value="${vo.act_type_name}" /></td>
				</tr>
				<tr>
					<th>artist_sex:</th>
					<td colspan="2"><input type="text"
						name="artist_sex" value="${vo.sex}" /></td>
				</tr>
				<tr>
					<th>artist_name:</th>
					<td colspan="2"><input type="text"
						name="artist_name" value="${vo.artist_name}" /></td>
				</tr>
				<tr>
					<th>artist_img:</th>
					<td><input type="text" name="artist_img"
						value="${vo.artist_img=='http://cdnimg.melon.co.kr'?'':vo.artist_img}" /></td>
				</tr>
				<tr>
					<td colspan="3"><c:if
							test="${vo.artist_img!='http://cdnimg.melon.co.kr'}">
							<img src="${vo.artist_img}">
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