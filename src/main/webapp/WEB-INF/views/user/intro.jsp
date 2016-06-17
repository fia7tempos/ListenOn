<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/resources/js/stackblur.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script type="text/javascript">
var context_root = '<c:url value="/"/>';
	$(document).ready(function(){
		var images = [context_root+'resources/img/concert1.jpg',   //0
		              context_root+'resources/img/concert2.jpg',   //1
		              context_root+'resources/img/concert3.jpg',   //2
		              context_root+'resources/img/guitar0.jpg',    //3
		              context_root+'resources/img/guitar1.jpg',    //4
		              context_root+'resources/img/guitar2.jpg',    //5
		              context_root+'resources/img/mixer1.jpg',     //6
		              context_root+'resources/img/mixer2.jpg',     //7
		              context_root+'resources/img/note1.jpg',      //8
		              context_root+'resources/img/phone1.jpg',     //9
		              context_root+'resources/img/phone2.jpg',     //10
		              context_root+'resources/img/piano1.jpg',     //11
		              context_root+'resources/img/typo1.jpg'       //12
		              ];
		var imageObjs = [];
		
		for(var i=0;i<images.length;i++){
			var image = new Image();
			image.src = images[i];
			imageObjs.push(image);
		}
		var canvas = document.getElementById('myCanvas');
		var canvasContext = canvas.getContext("2d");
		var testImage = new Image();
		image.src = images[7];
		var drawBlur = function(index,radius) {
			  var w = canvas.width;
			  var h = canvas.height;
			  canvasContext.drawImage(imageObjs[index], 0, 0, w, h);
			  stackBlurCanvasRGBA("myCanvas", 0, 0, w, h, radius);
			}
		
		var page = 0;
		var rad = 0, radup=true;
		var drawScreen = function(){
			if(page>12){ page = 0;}			
			if(rad>=255){radup=false;}
			if(radup && rad==0){page++;}
			if(radup) {
				drawBlur(page,rad+=5);			
			}else{
				drawBlur(page,rad-=5);
			}
		}
		
		setInterval(() => {
			drawScreen();
		}, 10);
	});
	
</script>
</head>
<body style="background-color:#000000">
	<div id="image_div" align="center">
		<canvas id="myCanvas" width="1024" height="768" style="border:1px solid #000000;">
		</canvas>
		<div id="concert1"></div>
		<div id="concert2"></div>
		<div id="concert3"></div>
		<div id="guitar0"></div>
		<div id="guitar1"></div>
		<div id="guitar2"></div>
		<div id="mixer1"></div>
		<div id="mixer2"></div>
		<div id="note1"></div>
		<div id="phone1"></div>
		<div id="phone2"></div>
		<div id="piano1"></div>
		<div id="typo1"></div>
	</div>

</body>
</html>