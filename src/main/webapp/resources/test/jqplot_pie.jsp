<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset="UTF-8">


<link type="text/css" rel="stylesheet" href="<c:url value='/resources/jqchart/css/jquery.jqplot.css'/>"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src='<c:url value="/resources/jqchart/js/jquery.jqplot.js"/>'></script>
<!-- 위 3가지 기본 import -->
<script type="text/javascript"  src='<c:url value="/resources/jqchart/js/plugins/jqplot.pieRenderer.js"/>'></script>
 


<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	var data = [[
			['김씨', 4], ['오씨', 3], ['배씨', 2], ['신씨', 2],
			['윤씨', 2], ['한씨', 2], ['기타', 6]
		]];
	
	$.jqplot ('graph', data, 
			  {
	      title :{
	    	text : '1001호 성씨 비율',
	    	textColor : 'black',
	    	textAlign : 'right'
	      },
	      
	      seriesDefaults: {
	        renderer: jQuery.jqplot.PieRenderer,
	        
	        rendererOptions: { 
	          sliceMargin: 4, 
	          showDataLabels: true,
	        },
	        
	        shadow: true
	      }, 
	      
	      legend: { 
	    	  show:true, 
	    	  location: 'w', //east,west,north,south
	    	  placement: 'insideGrid',
	    	  rendererOptions: {
	                numberColumn : 1 //열
	          },
	          marginTop: '15px'
	      }
	    }
	);
});
</script>
</head>
<body>
<div id="graph" style="width:300px; height:300px;"></div>
</body>
</html>