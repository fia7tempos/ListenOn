
var $context_root = '/www/';
var $commentNowPg = 1;

var writeLib = function(){
	/*숫자 포맷 plugin*/       $('head').append('<link rel="stylesheet" type="text/css" href="'+$context_root+'resources/song_album_module/css/song_album_module.css">');
//	/*song_album_module css*/$('head').append('<script src="'+$context_root+'resources/numberFormat/jquery.number.js"></script>');
	/*rating css*/          $('head').append('<link rel="stylesheet" type="text/css" href="'+$context_root+'resources/css/rating/star-rating.css"/>');
	/*rating plugin*/       $('head').append('<script src="'+$context_root+'resources/js/star-rating.js"></script>');
	/*평가 alert plugin*/     $('head').append('<script src="'+$context_root+'resources/sweetalert-master/sweetalert-dev.js"></script>');
	/*평가 alert css*/        $('head').append('<link rel="stylesheet" type="text/css" href="'+$context_root+'resources/sweetalert-master/sweetalert.css">');
	/*jqplot */ $('head').append('<script src="'+$context_root+'resources/jqchart/js/jquery.jqplot.js"></script>');
	/*jqplot css */ $('head').append('<link type="text/css" rel="stylesheet" href="'+$context_root+'resources/jqchart/css/jquery.jqplot.css" />')
	/*jqplot 별점차트 */ $('head').append('<script src="'+$context_root+'/resources/jqchart/js/plugins/jqplot.canvasTextRenderer.js"></script>')
	/*jqplot 별점차트 */ $('head').append('<script src="'+$context_root+'resources/jqchart/js/plugins/jqplot.canvasAxisLabelRenderer.js"></script>')
};

var makeDetail2 = null ;
var song_key = null;
var album_key = null;

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

$(document).ready(function(){
	
	writeLib();
	
	$.ajax({
		url : $context_root + "operation/main_detail",
		async : false
	}).done(function(data){
		$("#defaultDiv").append(data);
	})
	
	$('#myModal').on('shown.bs.modal', function () {
					
		 			
		 			
					var $detail_chart_info = [];
					var $chart_ticks = [];
					var max = 0;
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
	
	var setDetailDefault = function(){
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
	}
	
	$('#myModal').on('hidden.bs.modal',function(){ //detail 창 닫을 때 실행
		
		setDetailDefault();
		
		
	});
	
	var setRating = function(input_id, updateValue, item) { //detail rating 작업
		
	
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

	
	
	var inputDetailInfo = function(item){ 
		
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
	}
	
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
					setRating("detail_rating", 0, data.albumVO);
			} else {
					inputDetailInfo(data.songVO);
					setRating("detail_rating", 0, data.songVO);
			}
			
		})
		
	} //end getSongAlbumInfo
	
	
	var $detailCommentListIdKey = 0;
	var makeCommentList = function(val) { //코멘트 list div 작업
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
		}).addClass('well well-lg').attr("readonly", "true").attr("disabled", "true");

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
	}//end makeComment List
	
	var setCommentList = function(album_key, song_key) { //코멘트 리스트 불러옴
		
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
	} //end setCommentList
	

	var detail_chart_avg = function(album_key, song_key){
		
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
	
	$('#btnComment').on("click",function() {
		$('#commentList').html("");
		$commentNowPg = 1;

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

	
	
	makeDetail = function(album_key, song_key){ // 클릭 시 디테일 화면 띄움 (DB에서 불러옴)
		this.song_key = song_key;
		this.album_key = album_key;
		$('#myModal').modal();
		$('#myModal').on('shown.bs.modal',function(){
		});
		
		getSongAlbumInfo(album_key, song_key); //song or album 서버에서 정보 불러옴
		setCommentList(album_key, song_key); //코멘트 리스트 불러옴
		detail_chart_avg(album_key, song_key);
	} //end makeDetail
	
	makeDetail2 = makeDetail;
	
	$('#btnDetailCommentMore').on('click',function(){
		$commentNowPg++
		setCommentList(album_key, song_key);
	});

	
	
}); //document ready end