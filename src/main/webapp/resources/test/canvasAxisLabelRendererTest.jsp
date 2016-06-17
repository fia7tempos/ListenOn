<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>


<script src='<c:url value="/resources/js/jquery-2.2.2.js"/>'></script>
<!-- chart plugin -->
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/jqchart/css/jquery.jqplot.css'/>" />
<script src='<c:url value="/resources/jqchart/js/jquery.jqplot.js"/>'></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.canvasTextRenderer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jqchart/js/plugins/jqplot.canvasAxisLabelRenderer.js'/>"></script>






<script type="text/javascript">

$(document).ready(function(){
	
	  $.jqplot ('chart2', [[[1,2],[1.5,3],[2,4]]], 	{
			stackSeries : false,
			seriesColors : [ '#FFcc77', '#B9CDE5' ],
			seriesDefaults : {
				showMarker : false,
				fill : true,
				fillAndStroke : true
			},
			axes : {
				xaxis : {
					max : 5,
					min : 0.5, 
					numberTicks : 10,
					labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
					tickInterval : 0.5,
					tickOptions : {
						showGridline : false,
					}
				},
				yaxis : {
					max : 12,
					min : 0,
					labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
 					showTicks : false,
					tickOptions : {
						showGridline : false,
						
					}
				}
			},
			grid : {
				drawBorder : false,
				shadow : false,
				// background: 'rgba(0,0,0,0)'  works to make transparent.
				background : 'white'
			}
		});
	});

</script>
</head>
<body>
<div id="chart2" style="width: 300px; height: 300px"></div>
</body>
</html>