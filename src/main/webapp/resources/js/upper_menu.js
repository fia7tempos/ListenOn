/**
 * 
 */
	
	var $context_root;
	
	var writeLibs = function(){
		$('head').append('<link type="text/css" rel="stylesheet" href="'+$context_root+'resources/css/lightslider.css" />');
		$('head').append('<script src="'+$context_root+'resources/js/lightslider.js"></script>');
		$('head').append('<script src="'+$context_root+'resources/js/jquery-ui.js"></script>');
		$('head').append('<link type="text/css" rel="stylesheet" href="'+$context_root+'resources/css/search.css" />');
		$('head').append('<link type="text/css" rel="stylesheet" href="'+$context_root+'resources/song_album_module/css/upper_menu_search.css" />');
	}
	
	var refresh_count = null;


//title 이미지슬라이드
//div id: #img_slide 필요
//호출: drawTitle(context_root) //context_root : 코어라이브러리로 루트 받을것
	var drawTitle = null;
	$(document).ready(function() {
		drawTitle = function $drawTitle(context_root){
			$context_root = this.context_root;
			writeLibs();
			$('#img_slider').append(
					$('<ul/>',{id:'autoplay'}).addClass('gallery content-slider list-unstyled clearfix'));
			getImages();
		}

		function getImages() {
			$.ajax({
				url : $context_root + 'operation/imagelist',
				type : 'get'
			}).done(function(data) {
				for (var i = 0; i < data.length; i += 3) {
					$('<li/>').append($('<img/>', {
						src : data[i],
						width : 300
					})).append($('<img/>', {
						src : data[i + 1],
						width : 300
					})).append($('<img/>', {
						src : data[i + 2],
						width : 300
					})).appendTo($('#autoplay'));
				}
				var autoplaySlider = $('#autoplay').lightSlider({
					autoWidth : false,
					auto : true,
					loop : true,
					pauseOnHover : true,
					mode : 'fade',
					speed : 800,
					verticalHeight : 300,
					adaptiveHeight : true
				});
			});
		};
	});
	
	//navbar 그리기	
	//nav id : navbar 필요
	//호출: drawMenu(context_root,activeMenu,user_id) // activeMenu : 활성 메뉴 인덱스. 홈메뉴가 0번;
	var drawMenu = null;	
	$(document).ready(function(){
		drawMenu = function(context_root,activeMenu,user_id){
			$context_root = this.context_root;
			var menuUrl = [
					context_root + 'main', //메인
					context_root + 'operation/chart', //인기차트
					context_root + 'recommend/main', //추천음악(수정필요)
					context_root + 'starPoint/main',
					context_root + 'user/analysis/'+user_id,
					context_root + 'user/join',
					context_root + 'user/login'
			];
			var spanSearch = $('<span />',{
				
			}).addClass('glyphicon glyphicon-search');
			
			
			
			var dropdown_li_divider = $('<li />',{
				"class":"divider"
			})
				
				var dropdown_span_logouticon = $('<span />',{
					"text" : " "+"Logout",
					"class" : "glyphicon glyphicon-off",
					css : {
						"font-weight": "bold"
					}
				})
				
				var dropdown_a_logout = $('<a />',{
					"href" : "javascript:void(0);",
					click : function(){
							swal({   
								title: "로그아웃 하시겠습니까?",   
										type: "warning",   
										showCancelButton: true,   
										confirmButtonColor: "#DD6B55",   
										confirmButtonText: "네",
										cancelButtonText: "아니오",
										closeOnConfirm: false 
							}, function(){   
									location.href="/www/user/logout"
						});
					}
					
				}).append(dropdown_span_logouticon)
			
				var dropdown_li_myhome = $('<li />',{
					
				}).append(dropdown_a_logout);
			
			
				var dropdown_span_homeicon = $('<span />',{
					"text" : " "+"Home",
					"class" : "glyphicon glyphicon-home",
					css : {
						"font-weight": "bold"
					}
				})
			
				var dropdown_a_myhome = $('<a />',{
					"href" : "/www/user/analysis/"+user_id
					
				}).append(dropdown_span_homeicon)
				
				var dropdown_li_logout = $('<li />',{
					
				}).append(dropdown_a_myhome);
				
				var dropdown_ul = $('<ul />',{
					"class":"dropdown-menu"
				}).append(dropdown_li_logout).append(dropdown_li_divider).append(dropdown_li_myhome);
			
			
			var searchMenu = $('<input />', 
					{		
							"type" : "text",
							"id":"txtSearchKeyWord",
							"size":"30",
							"placeholder":" Search Keyword",
							"css" : {
								"margin-top" : "12px"
							},
							focus:function(){
								$('input').keyup(function(e) {
								    if (e.keyCode == 13) {
								    	location.href=$context_root + "operation/search?search_keyword="+$('#txtSearchKeyWord').val();
								    }        
								});
							}
						}).addClass('form-control');
			
			
			var leftMenu = 
				$('<ul/>').addClass('nav navbar-nav navbar-right')
					.append($('<li/>')
									.append(searchMenu))
					.append($('<li/>')
							.append($('<a/>',{href:'javascript:void(0);'})
									.append($('<span/>',{text:user_id+" ","data-toggle":"dropdown"}))
									.append(dropdown_ul)
									.append($('<img/>',{src:'http://cdn.mysitemyway.com/icons-watermarks/flat-circle-white-on-black/bfa/bfa_user/bfa_user_flat-circle-white-on-black_512x512.png',class:'img-circle',style:'width:30px',"data-toggle":"dropdown"})
									
									)))
									
		
			
			$('#navbar').addClass('navbar navbar-inverse')
				.append($('<div/>').addClass('container-fluid')
						.append($('<div/>').addClass('navbar-header')
									.append($('<button/>',{type:'button','data-toggle':'collapse','data-target':'#myNavbar'}).addClass('navbar-toggle')
											.append($('<span/>').addClass('icon-bar'))
											.append($('<span/>').addClass('icon-bar'))
											.append($('<span/>').addClass('icon-bar')))
									.append($('<a/>',{href:menuUrl[0]}).addClass('navbar-brand').text('Listen ON♬')))
				.append($('<div/>',{id:'myNavbar'}).addClass('collapse navbar-collapse')
						.append($('<ul/>').addClass('nav navbar-nav')
							.append($('<li/>')
								.append($('<a/>',{href:menuUrl[1]}).text('차트')))
							.append($('<li/>')
								.append($('<a/>',{href:menuUrl[3]}).text('별점등록')))
							.append($('<li/>')
								.append($('<a/>',{href:menuUrl[2]}).text('추천음악')))
							.append($('<li/>')
								.append($('<a/>',{href:menuUrl[4]}).text('취향분석'))))
						.append(leftMenu)))
			if(activeMenu>0 && activeMenu<5){
				$('.navbar-nav > li:eq('+activeMenu+')').addClass('active');
			}

			//navbar 스크롤이벤트
			var scroll_animate = function() {
				var current_scrollTop = $(window).scrollTop();
				if (current_scrollTop > 300) {
					$('#navbar').addClass('navbar-fixed-top').switchClass(
							"navbar-inverse", "navbar-default", 1000, "easeInOutQuad");

				} else {
					$('#navbar').removeClass('navbar-fixed-top').switchClass(
							"navbar-default", "navbar-inverse", 1000, "easeInOutQuad");
				}
			}
			window.setInterval(scroll_animate, 100);
		}
		
	});
	//추천 카운터 그리기
	// div id = rec_counter 필요
	// 호출: drawCounter(context_root,user_id)
	/*<div class="well col-lg-12 col-md-12 "
		style="background-color: rgba(50, 50, 50, 1);">
		<div class="panel panel-primary">
			<div id="count_caption" class=" panel-heading"></div>
		</div>
		<div class="progress"
			style="background-color: rgba(150, 150, 150, 1);">
			<div id="rec_gauge" class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="40" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%">0%</div>
		</div>
	</div>*/
	
	
	
	var drawCounter = null;
	var count_caption = [[-1,'최소 10개 이상의 음악을 평가해야 취향분석이 가능해요'],[9,'평가를 많이 할수록 취향분석이 정확해져요.'],[15,'자, 25개 갑시다!'],[25,'40개는 찍어야 어디가서 나 음악좀 듣는다고 말하죠.'],[40,'와~ 정말 50개 찍을 기세네요!'],[69,'에이~ 살면서 설마 음악 100개 안들으셨겠어요?'],[89,'이제 웬만한 친구보다 제가 당신을 더 잘 알걸요?'],[99,'제가 무슨 말을 할지 궁금하지 않으세요?'],[119,'이왕 이렇게 된 거, 지금까지 들었던 음악 다 찾아보세요!'],[149,'당신의 플레이리스트, 7시간 돌파!'],[199,'오, 음악 많이 들으셨네요. 인정합니다! :)'],[249,'오예, 200개 돌파! 300개에 도전해보세요!'],[299,'당신은 Listen On 상위 10%의 음악 마니아입니다!'],[349,'300의 용사여... (무리수)'],[399,'이제부터 자기소개 취미 란에 음악감상이라고 적어도 됩니다.'],[449,'살면서 음악 들어 온 시간, 20시간 돌파!'],[499,'그래요. 기왕 이렇게 된 거 500개 갑시다!'],[549,'500개 달성! 혹시 별 그냥 막 누르시는 건 아니죠?'],[599,'이 정도면, 웬만한 명반들은 거의 다 들으셨겠는걸요?'],[699,'살면서 음악 들어 온 시간, 30시간 돌파! 무려 1800분!'],[799,'이야, 남들이 웬만해선 잘 모르는 음악도 많이 들으셨겠어요.'],[899,'별의별 음악을 다 들어보셨네요… 회원님에게 음악이란?'],[999,'900곡 달성! 천곡 달성하고 도전 1000곡에 출연 해 보세요.'],[99999,'전설적인 레전드이십니다. YOU WIN!']];
	$(document).ready(function(){		
		drawCounter = function(context_root,user_id){			
			$context_root = this.context_root;
			
			$('<div/>').addClass('well col-lg-12 col-md-12')
						.css('background-color','rgba(50,50,50,1)')
						.append($('<div/>').addClass('panel panel-primary')
								.append($('<div/>',{id:'count_caption'}).addClass('panel-heading')))
						.append($('<div/>').addClass('progress').css('background-color','rgba(150,150,150,1')
								.append($('<div/>',{id:'rec_gauge',role:'progressbar','aria-valuenow':'40','aria-valuemin':'0',
										'aria-valuemax':'100'}).addClass('progress-bar progress-bar-striped active').css('width','0%')))
						.appendTo($('#rec_counter'));
			
			if(user_id!=undefined){	
				refresh_count = function(){
					console.log('refresh_count 실행');
					$.ajax({
					url: $context_root+'user/analysis/'+user_id+'/rec_count',
					type: 'get'
				}).done(function(data){
					var curr = data; 
					var max,min,idx;
					for(var i=count_caption.length-1;i>=0;i--){
						if(curr>(count_caption[i-1][0])) {
							max = count_caption[i][0]+1;
							min = count_caption[i-1][0];
							$('#count_caption').text(count_caption[i][1]);
							idx=i;
							break;
						}
					}
					if(min<0) min=0;
					$('#rec_gauge').css('width',(curr-min)/(max-min)*100 + '%').text(data + '/' + max);
				});
				}
				if(typeof setInsertCallback != 'undefined'){
					setInsertCallback(function(){refresh_count()});
				}
				refresh_count();
			}
			
		};
	});
	