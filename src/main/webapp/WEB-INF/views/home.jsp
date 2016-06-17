<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap/bootstrap.css'/>"></link>
<link rel="stylesheet"
	href="<c:url value='/resources/css/fontawesome-stars.css'/>">
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.barrating.min.js'/>"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"></script>
<link href="http://fonts.googleapis.com/css?family=Lato:300,400"
	rel="stylesheet" type="text/css">

<style type="text/css">
div.polaroid {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	background:
		url('http://image.melon.co.kr/cm/album/images/026/86/198/2686198_org.jpg');
	min-height: 400px;
	background-size: contain;
	background-repeat: no-repeat;
	margin: 20px 10px;
	position: relative;
}

.info {
	width: 100%;
	text-align: center;
	position: absolute;
	top: 185px;
	left: 0px;
	background-color: rgba(200, 200, 200, 1.0);
}

.no-interest {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 30px;
	height: 30px;
	background-color: rgba(255, 255, 255, 0.7);
}

.ex-star {
	position: absolute;
	top: 0px;
	right: 0px;
	width: 30px;
	height: 30px;
	background-color: rgba(255, 255, 255, 0.7);
}

@font-face {
	font-family: 'Glyphicons Halflings';
	src:
		url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/fonts/glyphicons-halflings-regular.eot');
	src:
		url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/fonts/glyphicons-halflings-regular.eot?#iefix')
		format('embedded-opentype'),
		url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/fonts/glyphicons-halflings-regular.woff')
		format('woff'),
		url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/fonts/glyphicons-halflings-regular.ttf')
		format('truetype'),
		url('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/fonts/glyphicons-halflings-regular.svg#glyphicons-halflingsregular')
		format('svg');
}
</style>
<script type="text/javascript">
	$(function() {
		$('#starpoint').barrating();
	});
</script>
</head>
<body class="container">
	<div class="container container-fluid">
		<div class="container polaroid col-md-6 col-lg-4 col-sm-10 col-xs-11">
			<div class="info well">
				<ul class="list-group">
					<li class="list-group-item">곡 제목</li>
					<li class="list-group-item">아티스트명</li>
					<li class="list-group-item">설명들</li>
				</ul>
				<div>별점</div>

			</div>
			<div class="no-interest well">X</div>
			<div class="ex-star well">★</div>
		</div>
		<c:forEach begin="0" end="20" step="1" var="idx">
			<div class="container polaroid col-md-3 col-lg-2 col-sm-5 col-xs-11">
				<div class="info well">
					<ul class="list-group">
						<li class="list-group-item">곡 제목</li>
						<li class="list-group-item">아티스트명</li>
						<li class="list-group-item">설명들</li>
					</ul>
					<div class="br-wrapper br-theme-fontawesome-stars">
						<select id="starpoint">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>

				</div>
				<div class="no-interest well">X</div>
				<div class="ex-star well">★</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>