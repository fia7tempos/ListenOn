<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<title>Listen On♬</title>


<script src="<c:url value='/resources/js/jquery-2.2.2.js'/>"></script>
<script src='<c:url value="/resources/js/jquery-form.js"/>'></script>
<script src='<c:url value="/resources/sweetalert-master/sweetalert-dev.js"/>'></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/sweetalert-master/sweetalert.css"/>'>

	
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons'>
<link rel="stylesheet" href="<c:url value='/resources/css/login/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/login/style.css'/>">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- <script src='http://codepen.io/andytran/pen/vLmRVp.js'></script> -->
<script src="<c:url value='/resources/js/index.js'/>"></script>
<!-- 쿠키저장 -->
<!-- jQuery 라이브러리 참조 -->

<!-- plugin 참조 -->
<script type="text/javascript" src="<c:url value='/resources/js/jquery.cookie.js'/>"></script>


<script type="text/javascript">
window.history.replaceState(null, "logout", "login");
var context_root = '<c:url value="/"/>';
$(document).ready(function(){
	$('#myModal').modal({
		keyboard : false,
		backdrop : "static"
	});
	
	var user_id_check = -2;
	var usercheck_id_check = 0;
	
	$("#usercheck_id, #user_id").keyup(function(event){ 
		   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		    var inputVal = $(this).val();
		    $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		   }
	});
	
	
	
	//아이디 체크를 클릭했을 때, 아이디를 입력하지 않은 경우
	$('#usercheck_id').focusout(function(){
			
		//아이디 미입력 확인
		var id = $('#usercheck_id').val();
		
		

		
		//아이디 중복체크
		$.ajax( context_root+"user/idcheck",{
			type : "POST",
			async: false,
			data:{
				"user_id" : $('#usercheck_id').val()
				},
			success: function(data){
				if(data >= 1){
					$('#idCheckResult').css("color","red");
					
					$('#idCheckResult').text('이미 사용중인 아이디입니다');
					usercheck_id_check = 1;
					$('#usercheck_id').focus();
					return;	
				} else if(data == 0) {
					usercheck_id_check = 0;	
					$('#idCheckResult').html('');
					
					return;
				}
			}
		}).fail(function(){
			swal({
				title : "SYSTEM ERROR!",
				timer : 1000,
				showConfirmButton : false
			});
		})
		
	});
	
	// 비밀번호 확인
	$('#usercheck_pw').focusout(function () {
        // $('#lblError').remove(); // 제거
        $('#lblError').text(''); // 클리어
        console.log('안녕!!');
        
        if($('#usercheck_pw2').val() != ""){
        	if($('#usercheck_pw').val() != $('#usercheck_pw2').val()){
	        	$('#lblError').text('비밀번호가 일치하지 않습니다.');
	        	$('#usercheck_pw2').val("");
			 }else{
			        
			    	$('#lblError').text('');
			 }
        }
        
    });
    //[2] 암호 확인 기능 구현
    $('#usercheck_pw2').focusout(function () {
        if ($('#usercheck_pw').val() != $('#usercheck_pw2').val()) {
            $('#lblError').text(''); // 클리어
            $('#lblError').css("color","red");
            $('#lblError').html("비밀번호가 일치하지 않습니다."); // 레이어에 HTML 출력
        }
        else {
            $('#lblError').text(''); // 클리어
            $('#lblError').css("color","red");
            $('#lblError').text(""); // 레이어에 텍스트 출력
        }
    });
    
    // 저장된 쿠키값을 읽어오기
    var c_user_id = $.cookie("user_id");
    var c_user_pwd = $.cookie("user_pw");

    //저장된 값이 있다면 입력 요소에 값 출력
    if( c_user_id && c_user_pwd ) {
        $("#user_id").val(c_user_id);
      
        //체크박스는 다시 체크
        $("#save_id").prop("checked", true);            
    }
    
    $("#save_id").click(function(e) {            
        if ($(this).is(":checked")) {
            // 사용자에게 저장여부 확인후 취소시 체크박스 해제
            if (!confirm("공용 PC에서 로그인정보를 저장할 경우, 다른 사람에게 노출될 위험이 있습니다. 정보를 저장하시겠습니까?")    ) {
                $("#save_id").prop("checked", false);                    
            }
        }
    });
    
    var login = function(){
    	 
        if (!$("#user_id").val()) { //아이디를 입력하지 않으면.
        	swal({
				title : "아이디를 입력해주세요.",
				timer : 700,
				showConfirmButton : false
			});
            $("#user_id").focus();
            return false;
        }
        if (!$("#user_pw").val()) { //패스워드를 입력하지 않으면.
        	swal({
				title : "비밀번호를 입력해주세요.",
				timer : 700,
				showConfirmButton : false
			});
            $("#user_pw").focus();
            return false;
        }

        if ($("#save_id").is(":checked")) {
            //체크 되어있다면, 해당 정보를 1년간 유효하도록 쿠키 저장
            $.cookie("user_id", $("#user_id").val(), {
                "expires" : 365                
            });                        
            $.cookie("user_pw", $("#user_pw").val(), {
                "expires" : 365
            });
        } else {
            //체크가 해제되었다면 쿠키 삭제.
            $.removeCookie("user_id");
            $.removeCookie("user_pw");
        }

        $("#login_form").submit();
    }
    
    $("#loginBtn").click(function(){
    	login();
    });
    
    $('#user_pw').focus(function(){
    	$('input').on('keyup',function(e){
    		if( e.keyCode == 13){
    			login();
    		}
    	});
    })
    

	//폼 전송전 확인
	$('#btn').click(function(){
		
		var id = $('#usercheck_id').val();
		
		if(usercheck_id_check == -2) {
			
			$('#idCheckResult').focus();
			return;
		} else if(usercheck_id_check == -1) {
			
			$('#idCheckResult').focus();
			return;

		} else if(usercheck_id_check == 1){
			swal({
				title : "중복된 아이디입니다.",
				timer : 700,
				showConfirmButton : false
			});
			$('usercheck_id').focus();
			return;
		} else if(usercheck_id_check == 0) {
								
 			  if($('#usercheck_pw').val()==""|| 
 					  $('#usercheck_pw2').val()=="" || 
 					  $('#user_nick').val()=="")   
 			  {
 				 swal({
 					title : "아이디와 비밀번호를 입력해주세요.",
 					timer : 700,
 					showConfirmButton : false
 				});
					return;
			}			
			
 			  $('#myform').ajaxSubmit();
 			  
 			 swal({   
 				title: "회원 가입 성공!", 
 				text : "아이디 : " + $('#usercheck_id').val(),
 				type: "info",   
 				showCancelButton: false,   
 				confirmButtonColor: "#DD6B55",   
 				confirmButtonText: "확인",   
 				}, 
 				function(){   
 					location.href="/www/user/login"
 				});
 		
			
			
		}
	});
});

$(document).ready(function(){
	$('body').css({
		'background-image': 'url("/www/resources/img/loginbg.jpg")',
		'background-size' : 'cover',
		'background-color' : '#000000'
		});
	$('#bg-wrapper').css({
		'background-image': 'url("/www/resources/img/loginbg2.jpg")',
		'background-size' : 'cover'
	});
	$('#bg-wrapper-gray').css({
		'background-image': 'url("/www/resources/img/loginbg3.jpg")',
		'background-size' : 'cover'
	});
	
	
	$('#bg-wrapper-gray').hide()
	$('#bg-wrapper').hide().fadeIn(1000,'linear')
	var wrapShown = false;
	
	setInterval(function() {
		if(wrapShown) {
			$('#bg-wrapper').fadeOut(1500,'linear')
			$('#bg-wrapper-gray').fadeIn(250).fadeOut(250);
			wrapShown = false;
		}else{
			$('#bg-wrapper').fadeIn(1500,'linear')
			$('#bg-wrapper-gray').fadeIn(250).fadeOut(250);
			wrapShown = true;
		}
	}, 2000);
});
</script>

<style type="text/css">
.wrapper-div{
  	  position:absolute; 
  	  top:0;left:0; 
  	  width:100%; height:100%;
  	  background-image : url(context_root+'resources/img/loginbg2.jpg'); 
  	  z-index:4;
    }
</style>

</head>
<body>
<div id="bg-wrapper" class='wrapper-div'></div>
<div id="bg-wrapper-gray" class='wrapper-div' style='z-index:5;'></div>

<div class="modal fade" id="myModal" role="dialog" style='opacity:0.9;'>

<div class="form">
  <div class="form-toggle"></div>
  
  <!-- 로그인 -->
  <div class="form-panel one">
    <div class="form-header">
      <h1>Login</h1>
    </div>
    <div class="form-content">
    
      <form id="login_form" method="post">
        <div class="form-group">
          <label for="username">ID</label>
          <input type="text" id="user_id" name="user_id" required="required" />
        </div>
        <div class="form-group">
          <label for="password">PASSWORD</label>
          <input type="password" id="user_pw" name="user_pw" required="required" />
        </div>
        <div class="form-group">
          <label class="form-remember">
            <input type="checkbox" id="save_id" name="save_id"/>아이디 저장
          </label><a href="#" class="form-recovery" id="findPassWord">비밀번호 찾기</a>
        </div>
        <div class="form-group">
          <button type="button" id="loginBtn">LOGIN</button>
        </div>
      </form>
      
      <form id="cacaoLogin_form" method="post" align="center">
		<br /> <a id="kakao-login-btn"></a>
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<script type='text/javascript'>
			Kakao.init('8b07f32f4a78bd193b0fca8391102d8d');
			Kakao.Auth.logout();

			Kakao.Auth.createLoginButton({
				container : '#kakao-login-btn',
				success : function(authObj) {
					Kakao.API.request({
						url : '/v1/user/me',
						success : function(res) {
							var cacaoID = res.id
							var cacaoNICK	= res.properties.nickname	
							var cacaoPROFILE = res.properties.profile_image
		
							location.href = "http://70.12.110.69/www/user/login?user_id=" + cacaoID
									+ "&user_nick=" + cacaoNICK + "&user_img="
									+ cacaoPROFILE;

						},
						fail : function(error) {
							//alert(JSON.stringify(error));
						}
					});
				},
				fail : function(err) {
					//alert(JSON.stringify(err));
				}
			});
		</script>
	</form>
	
	
    </div>
  </div>
  
  <!-- 회원가입 -->
  <div class="form-panel two">
    <div class="form-header">
      <h1>Register</h1>
    </div>
    <div class="form-content">
      <form id="myform" method="post" action="/www/user/join" enctype="multipart/form-data">
      	
      	 
        
        <div class="form-group">
         <label for="usercheck_id">ID</label>
          <input type="text" id="usercheck_id" name="user_id" required/>
          <div id="idCheckResult" style="margin-top:3px"></div> 
        </div>
        
         <div class="form-group">
         <label for="user_nick">NICK NAME</label>
          <input type="text" id="user_nick" name="user_nick" required/>
        </div>
        
        <div class="form-group">
          <label for="usercheck_pw">PASSWORD</label>
          <input type="password" id="usercheck_pw" name="user_pw" required="required"/>
        </div>
        
        <div class="form-group">
          <label for="usercheck_pw2">PASSWORD CHECK</label>
          <input type="password" id="usercheck_pw2" name="usercheck_pw2" required="required"/>
          <div id="lblError" style="margin-top: 3px"></div>
        </div>
        
       	<br/>
        <div class="form-group">
          <button type="button" id="btn">SIGN UP</button>
        </div>
      </form>
    </div>
  </div>
</div>

</div> <!-- modal end -->


</body>

</html>