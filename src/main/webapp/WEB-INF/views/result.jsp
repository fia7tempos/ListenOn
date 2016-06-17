<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script src='<c:url value="/resources/js/jquery-form.js"/>'></script>
<script src='<c:url value="/resources/sweetalert-master/sweetalert-dev.js"/>'></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/sweetalert-master/sweetalert.css"/>'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	swal({
		title : '${msg}',
		text : '${user_id}',
		timer : 500,
		showConfirmButton : false
	},function(){
		location.href='${url}';
	});
	
	
	
	
})
</script>


</body>
</html>