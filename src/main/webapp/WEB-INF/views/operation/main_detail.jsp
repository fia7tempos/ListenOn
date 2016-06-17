<%@ page language="java" contentType="text/text; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Detail start -->
	  	
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-lg">
	      <div class="modal-content">
	 
	        <div class="modal-header" align="left">
	          <button type="button" class="close" data-dismiss="modal">&times;</button> <!-- close -->
	          <div style="float:left; width: 42%; height: 350;">
	          	<img id="detail_album_img" class="img-thumbnail" width="350px" height="350px">
	          </div>
	     	  <div style="float: right; width: 58%; height: 350; ">
	     	  	<h2 align="center"><strong id="detail_song_name"></strong></h2>
	     	  	<div align="right"><small id="detail_issue_date"></small> <!-- 발매일 --></div>
	     	  	<hr/>
	     	  	
	 			<div style="float: left; width: 40%; height: 250px;">
	 		  		<img id="detail_artist_img" class="img-rounded" height="230" width="175"/> 
	 		  	</div>
	 		  	
	 		  	<div style="float: left; width: 60%; height: 250px;">
	 		  			<table>
	     	  		  <tr>
				        	<td> 
				        	<i id="detail_album_name"></i> <!-- Album -->
				        	</td>
				      </tr>
				      <tr>
				        	<td> 
				        		<strong id="detail_artist"></strong> <!-- Artist -->	
				        	</td>
				      </tr>
				        		
	 			 </table>
	 			 
	 			 
	 		  	</div>
	 		  </div>
	 		
	        </div>
	        
	        <div class="modal-body" style="height:220px;" >
				<div id="detailPlot" style="width:400px; height:200px; float:left;" ></div>
				<div style="float:left; margin-left:10px; width:200px" id="avg_star_point"> </div>
				<strong id="avg_star_point_cnt"></strong>
	        </div>
	        
	        <div class="modal-footer">
				<div align="left"> 
					<strong style="float : left; width:10%;">Comment </strong>
					<div id="detail_rating" style="float : left; width:10%;"></div>  
					<div style="float : left; width:20%; id="detail_caption"></div>
			</div>
			 	          
        	 <form role="form">
			    <div class="form-group">
			      <textarea class="form-control" rows="5" id="txtComment" style="float: left; width: 90%; height: 100px;"></textarea><button type="button" class="btn btn-primary btn-info" ID="btnComment" style="float:right; width: 9%; height: 100px;">입력</button>
			      <input type="hidden" id="hiddn_form_id"/>
			      
			    </div>
			  </form>
        </div>
        <div class="modal-footer"  align="left">
        
        <div id="commentList"></div>
        
      	
        
        </div>
         <div align="center">
        	<button type="button" class="btn btn-default" id="btnDetailCommentMore" style="width: 20%">More</button>
        </div>
        <br/>
      </div>
    </div>
    
  </div> 
  
  <!-- detail end -->