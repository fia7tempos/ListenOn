<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>


<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$('#btn').click(function(){
		$('#myModal').modal();
	});
})
</script>
<body>
<input type="button" value="모달띄우기" id="btn"/>

  

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header" align="left">
          <button type="button" class="close" data-dismiss="modal">&times;</button> <!-- close -->
          <div style="float:left; width: 42%; height: 350;">
          	<img src="http://70.12.110.69/cdnimg/image/album/2683309" class="img-thumbnail" width="350" >
          </div>
     	  <div style="float: right; width: 58%; height: 350; ">
     	  	<h2 align="center"><strong>Dream Girls</strong></h2><hr/>
 			<div style="float: left; width: 40%; height: 250px;">
 		  		<img  id="detail_artist_img" class="img-rounded" height="250"/> <!-- artist img -->
 		  	</div>
 		  	
 		  	<div style="float: left; width: 60%; height: 250px;">
 		  			<table>
     	  		  <tr>
			        	<td> 
			        		<h4>아이오아이(I.O.I)</h4> <!-- Artist -->
			        	</td>
			      </tr>
			      <tr>
			        	<td> 
			        		<h4>I.O.I (Intro)</h4> <!-- Album -->
			        	</td>
			      </tr>
			        <tr>
			        	<td>
			        		<h5><strong>2016.05.12</strong></h5> <!-- 발매일 -->
			        	</td>
			     	 </tr>
			      <tr>
			        <td><h6>2분 17초</h6></td> <!-- play time -->
			      </tr>
 			 </table>
 		  	</div>
 		  </div>
        </div>
        <div class="modal-body">
          <p>This is a large modal.</p>
        </div>
        <div class="modal-footer">
        	 <form role="form">
        	 	<p align="left"><strong>Comment</strong></p>
			    <div class="form-group">
			      <textarea class="form-control" rows="5" id="comment" style="float: left; width: 90%; height: 100px;"></textarea><button type="button" class="btn btn-primary btn-info" style="float:right; width: 9%; height: 100px;">입력</button>
			    </div>
			  </form>
        </div>
        <div class="modal-footer" id="commentList">
        	Comment List
        </div>
      </div>
    </div>
  </div> <!-- modal end -->
  
  <label style="margin-left: 50px"></label>

</body>
</html>