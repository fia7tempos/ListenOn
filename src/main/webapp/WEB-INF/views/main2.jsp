<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/upper_menu.js'/>"></script>
<script type="text/javascript">
	var context_root = '<c:url value="/" />';
	var user_id = '${user_id}';
	$(document).ready(function(){
		drawTitle(context_root);
		drawMenu(context_root, 5, user_id);
		drawCounter(context_root, user_id);
		$('#main_upper_slider').lightSlider({
	        item:4,
	        loop:false,
	        slideMove:2,
	        easing: 'cubic-bezier(0.25, 0, 0.25, 1)',
	        speed:600
	    });  
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
  </style>
</head>
<body>
		<!-- 이미지 슬라이더 -->
		<div id="img_slider" align="center"></div>

		<!-- 네비게이션 바 -->
		<nav id="navbar"></nav>

	<div class="container">
		<ul id="main_upper_slider">
		<li>
			<div class="row">
				<div class="col-sm-3">
					<div class="panel panel-warning">
						<div class="panel-heading">인기검색어 순위</div>
						<div class="panel-body">인기검색어 순위</div>
						<div class="panel-footer" style="min-height: 200;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">평점이 높은 곡</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer" style="min-height: 175;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
			</div>
			</li>
		<li>
			<div class="row">
				<div class="col-sm-3">
					<div class="panel panel-warning">
						<div class="panel-heading">인기검색어 순위</div>
						<div class="panel-body">인기검색어 순위</div>
						<div class="panel-footer" style="min-height: 200;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">평점이 높은 곡</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer" style="min-height: 175;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
			</div>
			</li>
		<li>
			<div class="row">
				<div class="col-sm-3">
					<div class="panel panel-warning">
						<div class="panel-heading">인기검색어 순위</div>
						<div class="panel-body">인기검색어 순위</div>
						<div class="panel-footer" style="min-height: 200;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">평점이 높은 곡</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer" style="min-height: 175;">Buy 50
							mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-danger">
						<div class="panel-heading">평점이 높은 앨범</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-success">
						<div class="panel-heading">평점이 높은 가수</div>
						<div class="panel-body">
							<img src="http://placehold.it/150x80?text=IMAGE"
								class="img-responsive" style="width: 100%" alt="Image">
						</div>
						<div class="panel-footer">Buy 50 mobiles and get a gift card</div>
					</div>
				</div>
			</div>
			</li>
			</ul>
		</div>
	<br>

	<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br><br>
<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br><br>

<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>