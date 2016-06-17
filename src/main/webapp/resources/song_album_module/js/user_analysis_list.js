

var default_url = null;
var add_url = null;
var goAjax = null;
var insertCallback = null;
var $commentMaxPg = null;
var $commentNowPg = 1;
var $detail_id_index = -1; 
var $context_root = '/www/';
var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
var setContextRoot = null;
var $makeDetail = null;



var writeLib = function(){
	
	

	/*숫자 포맷 plugin*/	$('head').append('<script src="'+$context_root+'resources/numberFormat/jquery.number.js"></script>');
	/*rating css*/          	$('head').append('<link rel="stylesheet" type="text/css" href="'+$context_root+'resources/css/rating/star-rating.css"/>');
	/*rating plugin*/       	$('head').append('<script src="'+$context_root+'resources/js/star-rating.js"></script>');
	/*평가 alert plugin*/     $('head').append('<script src="'+$context_root+'resources/sweetalert-master/sweetalert-dev.js"></script>');
	/*평가 alert css*/        $('head').append('<link rel="stylesheet" type="text/css" href="'+$context_root+'resources/sweetalert-master/sweetalert.css">');
	/* jquery form plugin*/ $('head').append('<script src="'+$context_root+'resources/js/jquery-form.js"></script>');
	
};


$(document).ready(function() {

	var $song_key = null;
	var $album_key = null;
	
	writeLib();

	var detail_chart_avg = function(id_index){
		var album_key = $('#insertSong_' + id_index).find("[name=album_key]").val();
		var song_key = $('#insertSong_' + id_index).find("[name=song_key]").val();					
		
		$.ajax({
			url : $context_root+"recommend/detail/get_chart_avg",
			type : "post",
			async : false,
			data : {
				"song_key" : song_key,
				"album_key" : album_key
			}
		}).done(function(data){
			
			$('#avg_star_point').rating({
				min : 0,
				max : 5,
				step : 0.1,
				size : 6,
				displayOnly : true,
			}).rating('update', data[0].avg_star_point);
			
			
			$('#avg_star_point_cnt').text(data[0].cnt + "명이 참여했어요.");
			
			
		})
	}
	
	
	

					// 평가 완료후 코멘트 함수 설정
					setInsertCallback = function(insertCallback) {
						this.insertCallback = insertCallback;
						
					}

					// context_root 설정
					setContextRoot = function(context_root) {
						$context_root = this.context_root;
					}

					var defaultDiv = $.ajax({
						url : $context_root + "operation/detail",
						async : false
					}).done(function(data){
						$("#defaultDiv").append(data);
					})
				
				
				
				$('#myModal').on('shown.bs.modal', function () {	
					var $detail_chart_info = [];
					var $chart_ticks = [];
					
					var album_key = null;
					var song_key = null;
					var max = 0;
					if($album_key != null){
						album_key = $album_key;
						song_key = $song_key;
					} else {
						album_key = $('#insertSong_' + $detail_id_index).find("[name=album_key]").val();
						song_key = $('#insertSong_' + $detail_id_index).find("[name=song_key]").val();
					}
					
					$.ajax({
						url : $context_root+"recommend/detail/get_chart_info",
						type : "post",
						async : false,
						data : {
							"song_key" : song_key,
							"album_key" : album_key
						}
					}).done(function(data){
						
						$.each(data, function(key, val){
							if (val.cnt > max) {
								max = val.cnt;
							}
							$chart_ticks.push(val.star_point);
							$detail_chart_info.push( [val.star_point, val.cnt] );
						});
					})
					
					
					if ( $detail_chart_info.length > 1){
						
						var plot = $.jqplot('detailPlot' ,[ $detail_chart_info  ],{
								
								stackSeries : false,
								animate: true,
								 animateReplot: true,
								seriesColors : [ '#FFcc77',
										'#B9CDE5' ],
								seriesDefaults : {
									showMarker : false,
									fill : true,
									fillAndStroke : true,
									rendererOptions: {
					                    smooth: true,
					                }
								},
								axes : {
									xaxis : {
										
										ticks : $chart_ticks, 
										labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
										tickOptions : {
											showGridline : false,
										}
									},
									yaxis : {
										max : max + 3,
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
									background : 'white'
								}
							});
					} else {
						
					var nodataImg = $('<img />',{
						src : $context_root+"resources/img/no_data.png",
						width : "220px",
						height : "220px"
					});
						$('#detailPlot').html(nodataImg);
					}
					
				});
				
				$('#myModal').on('hidden.bs.modal',function(){
					$('#commentList').html("");
					$('#txtComment').html("");
					$('#avg_star_point_cnt').text("");
					$('#avg_star_point').html("");
					$('#detailPlot').html("");
					$('#detail_artist').text("");
					$('#detail_album_name').text("");
					$('#detail_artist_img').attr("src","");
					$('#detail_issue_date').text("");
					$('#detail_song_name').text("");
					$('#detail_album_img').attr("src","");
					$detailCommentListIdKey = 0;
					$commentNowPg = 1;
					$commentMaxPg = 1;
					$album_key  = null;
					$song_key = null;

				});
						
					

					$('#btnMore').hide(); // 더보기
					$('#loadimg').hide(); // 로딩

					var $maxPage = 1; // 맥스 페이지 default 1
					var $nowPage = 1; // 현재 페이지 default 1
					var $id_key = 0; // id값을 주기위한 index
					var $url; // 기본 url
					var $urlVal; // 기본 url 뒷 부분의 url

					var $captions = {
						'0' : '평가없음',
						'0.5' : '재앙이에요',
						'1' : '듣기싫어요',
						'1.5' : '안좋아요',
						'2' : '글쎄요',
						'2.5' : '뭐 그럭저럭',
						'3' : '들을만 해요',
						'3.5' : '괜찮은 노래',
						'4' : '좋아해요',
						'4.5' : '정말 좋은 곡',
						'5' : '전설의 레전드'
					}; // 별점 caption

					// 인풋 텍스트박스 만들기
					var makerInput = function(key, value) {
						var makeInput = $("<input />", {
							'type' : "hidden",
							'name' : key,
							'value' : value
						})
						return makeInput;
					} // end makerInput

					// jqForm submit (recommend)
					var insertSong = function(idx) {
						$('#insertSong_' + idx).ajaxSubmit({
							url : $context_root + 'operation/song/insert',
							success : function(data) {
								insertCallback();
							}
						});
							
					} // end insertSong

					// 평점 등록 시 아래 정보들 저장
					var insertRecommend = function(idx, starValue) {
						
						
						
						insertSong(idx); // 인설트 송

						var album_key = $('#insertSong_' + idx).find(
								"[name=album_key]").val();
						var album_name = $('#insertSong_' + idx).find(
								"[name=album_name]").val();
						var song_key = $('#insertSong_' + idx).find(
								"[name=song_key]").val();
						var artist_key = $('#insertSong_' + idx).find(
								"[name=artist_key]").val();
						var artist_name = $('#insertSong_' + idx).find(
								"[name=artist_name]").val();
						var star_point = starValue;

						$.ajax({
							url : $context_root + "recommend",
							type : "post",
							async: false,
							data : {
								'album_key' : album_key,
								'album_name' : album_name,
								'song_key' : song_key,
								'artist_key' : artist_key,
								'artist_name' : artist_name,
								'star_point' : star_point,
								'comment' : $captions[star_point]
							}
						}).done(function(data) {
							swal({
								title : data,
								timer : 1000,
								showConfirmButton : false
							});// 평점 등록 시 모달 alter 창
						})
					} // end insertRecommend

					// 노래정보 저장할 폼 만들기
					var makeHiddenForm = function(val) {
						
						var $artistList = new Array();

						$(val).each(function(idx, item) { // key값 추출을 위해 for
							// each문 2번 실행
							$.each(item, function(key, value) {

								var $inputHidden = makerInput(key, value);
								$artistList.push($inputHidden);
							})
						})

						var inputHidden_submit = $('<input />', {
							type : "button",
							value : val.artist_name.substring(0, 21),
							id : "btnInsert_" + $id_key,
							click : function() {
								insertSong((this.id).substring(10));
							}
						}).addClass('btn btn-link');

						var inputForm = $("<form />", {
							id : "insertSong_" + $id_key,
							method : "post",
							action : $context_root + "operation/song/insert"

						}).append($artistList).append(inputHidden_submit);

						return inputForm;
					} // end makeHiddenForm

					var setRating = function(input_id, bool, id_index, updateValue) {
						
						if (bool) {
							$('#' + input_id).rating({
								min : 0,
								max : 5,
								step : 0.5,
								size : 6,
								captionElement : '#caption_div' + id_index,
								showClear : false,
								starCaptions : $captions
							}).on('rating.change', function(event, value, caption) {
								
										insertRecommend(id_index, value,caption);
							}).rating('update', updateValue);
						} else {
							$('#' + input_id).rating({
								min : 0,
								max : 5,
								step : 0.5,
								size : 6,
								captionElement : '#detail_caption',
								showClear : false,
								starCaptions : $captions
							}).on('rating.change', function(event, value, caption) {
										
										insertRecommend(id_index, value, caption);
										
									
										
							}).rating('update', 0);
							
							
						}

					}
					
					var $detailCommentListIdKey = 0;
					var makeCommentList = function(val) {
						var imgDiv = $('<div />', {
							align : "center",
							css : {
								float : "left",
								width : "5%"
							}
						})
						var user_img = null;
						
						if(val.user_img != null){
							user_img = val.user_img
						} else {
							user_img = "http://cdn.mysitemyway.com/icons-watermarks/flat-circle-white-on-black/bfa/bfa_user/bfa_user_flat-circle-white-on-black_512x512.png";
						}


						var profile_img = $(
								'<img />',
								{
									src : user_img,
									width : "50px",
									height : "50px",
									css : {
										"margin-top" : "15px"
									}
								}).addClass('img-circle')
								
						var detail_profile_img_a = $('<a />', {
							href: $context_root+"user/analysis/other/"+val.user_id
						}).append(profile_img)

						var commentTxt = $('<textarea />', {
							id : $detailCommentListIdKey + "_commentTxt",
							align : "left",
							text : val.user_comment,
							css : {
								"float" : "left",
								"margin-left" : "25px",
								"width" : "90%"
							}
						}).addClass('well well-lg').attr("readonly", "true")
								.attr("disabled", "true");

						var userStarPoint = $('<label />', {
							"for" : $detailCommentListIdKey + "_commentTxt",
							id : "userStarPoint_" + $detailCommentListIdKey,
							css : {
								"margin-left" : "50px"
							}
						})
						
					
						
						var userNick = $('<label />', {
							"for" : "userStarPoint_" + $detailCommentListIdKey,
							css : {
								"margin-left" : "25px",
								"margin-right" : "10px",
								"float" : "left"
							},
							text : val.user_nick
						})
						
						var detail_userNick_a = $('<a />', {
							href: $context_root+"user/analysis/other/"+val.user_id
						}).append(userNick)
						

						var commentDiv = $('<div />', {
							id : $detailCommentListIdKey + "_commentDiv",
							align : "left"
						}).append(detail_userNick_a).append(userStarPoint).append(commentTxt);

						$('#commentList').append(imgDiv.append(detail_profile_img_a)).append(commentDiv);

						$('#userStarPoint_' + $detailCommentListIdKey).rating({
							min : 0,
							max : 5,
							step : 0.5,
							size : 6,
							displayOnly : true,
						}).rating('update', val.star_point);
						
						$detailCommentListIdKey++;
					}

					var setCommentList = function(album_key, song_key) {
						
						$.ajax({
							url : $context_root+ "recommend/detail/get_comment/"+$commentNowPg,
								    async: false,
									type : "POST",
									data : {
										"album_key" : album_key,
										"song_key" : song_key
									}
								}).done(function(data) {
									
									
									
									data.pgNation.pg = $commentNowPg;
									 
									
									if ( data.pgNation.total_page_count <= $commentNowPg){
										$('#btnDetailCommentMore').hide();
									} else {
										$('#btnDetailCommentMore').show();
									}
									
								$.each(data.commentList, function(key, val) {
									
								makeCommentList(val);
							})
						})
					}
						
					

					// 항목 하나
					var makeHtml = function(index, item) {
						
						
						var $album_name = item.album_name;
						
						
						
						var $song_name = "";
						

						if (item.song_name != null) {
							$song_name = item.song_name;
						} else {
							$song_name = "-- Album --";
						}

						if ($album_name.length > 22) {
							$album_name = $album_name.substring(0, 20) + "..";
						}

						if ($song_name.length > 22) {
							$song_name = $song_name.substring(0, 20) + "..";
						}

						var div2 = $("<div />", {
							id : 'caption_div' + $id_key
						});

						var p2 = $("<p />", {
							id : 'input-id' + $id_key

						});

						var strongAlbum_name = $("<strong />", {

							text : $album_name
						});

						var strongSong_name = $("<h5 />", {
							text : $song_name
						});

						var p1 = $("<p>", {}).append(strongAlbum_name).append(
								strongSong_name);

						var img = $("<img >", {
							src : item.album_img,
							id : "album_img_" + $id_key,
							height : "300",
							width : "400"

						})
						
						
					
						var thumb = $("<div />",{
									id : $id_key,
									click : function() { // detail 작업
										
										var song_name = item.song_name;
										var album_name = item.album_name;
										
										if( album_name.length > 23){
											album_name = album_name.substring(0,22) +".."
										}
										
										
										$detail_id_index = this.id;
										$('#detailPlot').html("");
										$('#myModal').modal();
										setRating("detail_rating", false, $detail_id_index);
										detail_chart_avg(this.id);
										$('#myModal').on('shown.bs.modal',function(){
											
										});
										
										
										$('#txtComment').text("");
										
										
										var album_key = $('#insertSong_' + this.id).find("[name=album_key]").val();
										var song_key = $('#insertSong_' + this.id).find("[name=song_key]").val();
										
										
										setCommentList(album_key, song_key);
										
										
										
										if (song_name != null) {
											if( song_name.length > 22){
												song_name = song_name.substring(0,20) +".."
											}
											$('#detail_song_name').text(song_name);
											$('#detail_album_name').text(album_name)
										} else {
											$('#detail_song_name').text(album_name);
										}
										
										$.ajax({
											url : $context_root+"operation/get_artist_img",
											type : "post",
											data : {
												"artist_key" : item.artist_key
											}
										}).done(function(data){
											$('#detail_artist_img').attr("src", data);
										})
										
										
										$('#detail_artist').text(item.artist_name);
										$('#detail_issue_date').text(item.issue_date);
										$('#hiddn_form_id').text(this.id);
										$('#detail_album_img').attr("src",item.album_img);
										
										
									}
								}).addClass('thumbnail').append(img).append(p1)
								.append(p2).append(div2).append(
										makeHiddenForm(item)); // artist,album,song별
						// 폼 분류해야함

						var sm_4 = $("<div />", {
							id : 'sm_4_' + $id_key
							
						}).addClass('col-sm-4').append(thumb);

						$('#rtc').append(sm_4);

						setRating("input-id" + $id_key, true, $id_key);

						$id_key++;
					}; // end makeHtml();

					// "song/"

					var $goAjax = function(page, Bool) {
					

						$('#btnMore').hide();
						$('#loadimg').show();
						
						$.ajax({
									url : $url + "/" + $urlVal,
									type : 'post',
									async : false,
									data : {
										'page' : page
									}
								})
								.done(
										function(data) {
											if (Bool) {
												$('#total_article')
														.text(
																$urlVal
																		+ " 검색결과는 총 "
																		+ $
																				.number(data.pgNation.total_article_count)
																		+ "개 입니다.");
											} else {
												$('#total_article').text("");
											}
											$('#loadimg').hide();
											if (data.pgNation != null
													&& data.pgNation.total_page_count != null) {
												$maxPage = data.pgNation.total_page_count;
												
												if (data.pgNation.total_page_count <= $nowPage) {
													$('#btnMore').hide();
												} else {
													$('#btnMore').show();
												}
											} else {
												$('#btnMore').hide();

											}

											$.each(data.list, function(index,
													item) {
												makeHtml(index, item);

											})

										});
					} // end goAjax()

					goAjax = $goAjax;
					
					
					
					
					var searchClick = function() {
						$('#btnMore').hide();
						$('#rtc').html("");
						$nowPage = 1;
						$id_key = 0;
						$url = $context_root + "operation/song/";
						$urlVal = $('#txtSearchKeyWord').val();
						goAjax(1, true);
					};

					$('#txtSearchKeyWord').on("focus",function(){
						
						$('input').keyup(function(e) {
						    if (e.keyCode == 13) {
						    	searchClick();
						    }        
						});
					})

					

					var $default_url = function(url) {
						$url = $context_root + url;
					} // url 설정

					default_url = $default_url;

					$('#btnComment').on("click",function() {
										$('#commentList').html("");
										$commentNowPg = 1;
										var id_index = $('#hiddn_form_id')
												.text();

										var album_key = $('#insertSong_' + id_index)
												.find("[name=album_key]").val();
										var song_key = $(
												'#insertSong_' + id_index)
												.find("[name=song_key]").val();

										$.ajax(
														{
															url : $context_root+ "recommend/detail/insert_comment",
															async : false,
															type : "POST",
															data : {
																"album_key" : album_key,
																"song_key" : song_key,
																"user_comment" : $('#txtComment').val()
															}
														})
												.done(function(data) {
															
													var album_key = $('#insertSong_' + id_index).find("[name=album_key]").val();
													var song_key = $('#insertSong_' + id_index).find("[name=song_key]").val();
													
													setCommentList(album_key, song_key);
													
															
															swal({
																title : data,
																timer : 700,
																showConfirmButton : false
															});// 평점 등록 시 모달
															// alter 창
															$('#txtComment')
																	.val("");
														})

									}); // 코멘트 insert

					var $add_url = function(urlVal) {
						$urlVal = urlVal
					} // 추가 url 설정

					add_url = $add_url;

					$('#btnMore').click(function() {
						$('#btnMore').hide();
						if ($maxPage >= $nowPage) {

							if (typeof $urlVal === 'number') {
								$urlVal = $urlVal + 1;
							} else {
								$nowPage = $nowPage + 1;
							}
							goAjax($nowPage);
						}
					});
					
			$('#btnDetailCommentMore').on('click',function(){
				$commentNowPg++
				var album_key = $('#insertSong_' + $detail_id_index).find("[name=album_key]").val();
				var song_key = $('#insertSong_' + $detail_id_index).find("[name=song_key]").val();
				setCommentList(album_key, song_key);
			});//
			//
			var detail_chart_avg2 = function(album_key, song_key){
				
				$.ajax({
					url : $context_root+"recommend/detail/get_chart_avg",
					type : "post",
					async : false,
					data : {
						"song_key" : song_key,
						"album_key" : album_key
					}
				}).done(function(data){
					
					$('#avg_star_point').rating({
						min : 0,
						max : 5,
						step : 0.1,
						size : 6,
						displayOnly : true,
					}).rating('update', data[0].avg_star_point);
					
					$('#avg_star_point_cnt').text(data[0].cnt + "명이 참여했어요.");
					
					
				})
			}
			//
			//
			var setRating2 = function(input_id, updateValue, item) { //detail rating 작업
				
				
				$('#' + input_id).rating({
					min : 0,
					max : 5,
					step : 0.5,
					size : 6,
					captionElement : '#detail_caption',
					showClear : false,
					starCaptions : $captions
				}).on('rating.change', function(event, value, caption) {
					
				
					$.ajax({
						url : $context_root + "recommend",
						type : "post",
						async: false,
						data : {
							'album_key' : item.album_key,
							'album_name' : item.album_name,
							'song_key' : item.song_key,
							'artist_key' : item.artist_key,
							'artist_name' : item.artist_name,
							'star_point' : value,
							'comment' : $captions[value]
						}
					}).done(function(data) {
						
						
						swal({
							title : data,
							timer : 1000,
							showConfirmButton : false
						}); // 평점 등록 시 모달 alter 창
					})

					$.ajax({
						url : $context_root + 'operation/song/insert',
						data : {

											"song_key" : item.song_key,
											"song_name" : item.song_name,
											"album_key" : item.album_key,
											"artist_key" : item.artist_key,
											"artist_name" : item.artist_name,
											"play_time" : item.play_time,
											"issue_date" : item.issue_date,
											"is_title_song" : item.is_title_song,
											"is_hit_song" : item.is_hit_song,
											"is_adult" : item.is_adult,
											"is_free" : item.is_free,
											"composition" : item.composition,
											"lyricist" : item.lyricist,
											"album_name" : item.album_name,
											"album_img" : item.album_img	
						}
					}).done(function(){
					});
							
				}).rating('update', updateValue);
				
			} //end setRating
			//
			
			//
			var inputDetailInfo = function(item){ 
				
				console.log(item);
				var song_name = "";
				var album_name = item.album_name;
				
			
			
				if( album_name.length > 22){
					album_name = album_name.substring(0,20) +".."
				}
			
				
				$('#detail_artist').text(item.artist_name);
				$('#detail_issue_date').text(item.issue_date);
				$('#detail_album_img').attr("src",item.album_img);
				
				if (item.song_name != null) {
					 song_name= item.song_name;
					if( song_name.length > 22){
						song_name = song_name.substring(0,20) +".."
					}
					$('#detail_song_name').text(song_name);
					$('#detail_album_name').text(album_name)
				} else {
					$('#detail_song_name').text(album_name);
				}
				
				$('#detailPlot').html("");
				
				$('#txtComment').text("");
				
				$.ajax({
					url : $context_root+"operation/get_artist_img",
					type : "post",
					data : {
						"artist_key" : item.artist_key
					}
				}).done(function(data){
					$('#detail_artist_img').attr("src", data);
				})
			}//
			
			//
			var getSongAlbumInfo = function(album_key, song_key){ //서버에서 앨범 or 송 정보 가져옴
				
				$.ajax({
					url : $context_root+"artist/get_album_song_info",
					type : "post",
					async : false,
					data : {
						"album_key" : album_key,
						"song_key" : song_key
					}
				}).done(function(data){
					
					if(data.albumVO != null){
							inputDetailInfo(data.albumVO);
							setRating2("detail_rating", 0, data.albumVO);
					} else {
							inputDetailInfo(data.songVO);
							setRating2("detail_rating", 0, data.songVO);
					}
					
				})
				
			} //end getSongAlbumInfo
			
			$makeDetail = function(album_key, song_key){ // 클릭 시 디테일 화면 띄움 (DB에서 불러옴)
				$song_key = song_key;
				$album_key = album_key;
				$('#myModal').modal();
				$('#myModal').on('shown.bs.modal',function(){
				});
				
				getSongAlbumInfo(album_key, song_key); //song or album 서버에서 정보 불러옴
				setCommentList(album_key, song_key); //코멘트 리스트 불러옴
				detail_chart_avg2(album_key, song_key);
			} //end makeDetail
			
			
			

})