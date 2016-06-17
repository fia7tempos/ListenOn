package net.musicrecommend.www.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.musicrecommend.www.vo.AlbumChartVO;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.SongChartVO;
import net.musicrecommend.www.vo.SongVO;

@Component
public class MashupPicker {
private static final Logger logger = LoggerFactory.getLogger(MashupPicker.class);
	
	@Value("${cdn_url}")
	private String cdn_url;
	
	// 앨범커버 이미지 가져오기
	// input = melon albumId
	// output = 앨범커버 이미지 url
	public String getCoverURL(String albumId) {
		return cdn_url+"/cdnimg/image/album/"+albumId;
	}
	public String getCoverURL(long albumId){
		return getCoverURL(String.valueOf(albumId));
	}

	// 아티스트 이미지 가져오기
	// input = melon artistId
	// output = 아티스트 이미지 url
	public String getArtistProfileURL(String artistId) {
		return cdn_url+"/cdnimg/image/artist/"+artistId;
	}


	// 노래별 작곡가 가져오기
	// input = melon songId
	// output = 아티스트 이미지 url
	public String getComposition(String songId) {
		StringBuffer sb = new StringBuffer();
		String html = null;
		//List<Map<String, Object>
		//
		try {
			Content c = Request.Get("http://www.melon.com/song/detail.htm?songId=" + songId).execute().returnContent();
			html = c.asString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (html.contains("<!-- 작곡가 정보 -->")) {
			html = html.substring(html.indexOf("<!-- 작곡가 정보 -->"), html.indexOf("<!-- 편곡곡가 정보 -->"));
			while (html.contains("<dt>작곡</dt>")) {
				String resultStr = html.substring(html.indexOf("<dt>작곡</dt>"), html.length());
				System.out.println("1" + resultStr);
				resultStr = resultStr.substring(resultStr.indexOf("title=\"") + 7, resultStr.length());
				System.out.println("2" + resultStr);
				resultStr = resultStr.substring(0, resultStr.indexOf("\""));
				System.out.println("3" + resultStr);
				sb.append(resultStr + ",");
				html = html.substring(html.indexOf("<dt>작곡</dt>") + 11, html.length());
			}
			return sb.substring(0, sb.length() - 1).toString();
		} else {
			return "작곡가 정보 없음";
		}

	}

	// 노래별 작사가 가져오기
	// input = melon songid
	// output = 작사가 리스트
	public String getLyricist(String songId) {
		StringBuffer sb = new StringBuffer();
		String html = null;
		try {
			Content c = Request.Get("http://www.melon.com/song/detail.htm?songId=" + songId).execute().returnContent();
			html = c.asString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (html.contains("<!-- 작사가 정보-->")) {
			html = html.substring(html.indexOf("<!-- 작사가 정보-->"), html.indexOf("<!-- 작곡가 정보 -->"));
			while (html.contains("<dt>작사</dt>")) {
				String resultStr = html.substring(html.indexOf("<dt>작사</dt>"), html.length());
				System.out.println("1" + resultStr);
				resultStr = resultStr.substring(resultStr.indexOf("title=\"") + 7, resultStr.length());
				System.out.println("2" + resultStr);
				resultStr = resultStr.substring(0, resultStr.indexOf("\""));
				System.out.println("3" + resultStr);
				sb.append(resultStr + ",");
				html = html.substring(html.indexOf("<dt>작사</dt>") + 11, html.length());
			}
			if(sb.length()==0) return "작사가 정보 없음";
			return sb.substring(0, sb.length() - 1).toString();
		} else {
			return "작사가 정보 없음";
		}

	}

	// 가사가져오기
	// input = melon songid
	// output = 가사 문자열
	public String getLyrics(String songId) {
		// 작업중
		StringBuffer sb = new StringBuffer();
		String html = null;
		try {
			Content c = Request.Get("http://www.melon.com/song/detail.htm?songId=" + songId).execute().returnContent();
			html = c.asString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (html.contains("[가사 준비중]")) {
			return "가사 준비중";
		} else {
			html = html.substring(html.indexOf("확장됨 -->") + 8, html.length());
			html = html.substring(0, html.indexOf("</div>"));
		}
		return html;
	}

	
	//  *** MELON API ***
	
	// 아티스트 리스트 가져오기
	// input = page, melon artist search keyword
	// output = 검색된 맵 리스트
	public List<ArtistVO> getArtistList(int page, int count,String searchKeyword) {
		String jsondata = null;
		List<ArtistVO> list = new ArrayList<ArtistVO>();
		try {
			Content content = Request
					.Get("http://apis.skplanetx.com/melon/artists?count="+count+"&page=" + page + "&searchKeyword="
							+ URLEncoder.encode(searchKeyword, "UTF-8") + "&version=1")
					.setHeader("Accept", "application/json")
					.addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
					.addHeader("x-skpop-userId", "ok0412").addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
					.execute().returnContent();
			jsondata = content.asString();
			// ObjectMapper mapper = new ObjectMapper();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(jsondata);
			JsonNode melon = root.get("melon");
			JsonNode artist = melon.path("artists").get("artist");
			if(artist != null){
				for(JsonNode item:artist){
					ArtistVO artistVO = new ArtistVO();
					artistVO.setAct_type_name(item.get("actTypeName").asText());
					artistVO.setArtist_key(item.get("artistId").asLong());
					artistVO.setArtist_name(item.get("artistName").asText());
					artistVO.setGenre_names(item.get("genreNames").asText());
					artistVO.setNationality_name(item.get("nationalityName").asText());
					artistVO.setSex(item.get("sex").asText());
					artistVO.setArtist_img(getArtistProfileURL(Long.toString(artistVO.getArtist_key()))); //페이지 막힘 주의
					list.add(artistVO);
				}
			}
			
			// artistVO = mapper.readValue(content.asBytes(), ArtistVO.class);
		} catch (ClientProtocolException e) {
			logger.info("!ClientProtocolException!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("!IOException!");
			e.printStackTrace();
		}
	
		return list;
	}

	// 곡 리스트 가져오기
	// input = page, 검색어
	// output = 검색된 맵 리스트
	// 곡 리스트 가져오기
		// input = page, 검색어
		// output = 검색된 맵 리스트
		public Map<String, Object> getSongList(long page, int count,String searchKeyword) {
			String jsondata = null;
			List<SongVO> list = new ArrayList<SongVO>();
			PageNation pgNation = null;
			Map<String, Object> map = new HashMap<String,Object>();
			try {
				Content content = Request
						.Get("http://apis.skplanetx.com/melon/songs?count="+count+"&page=" + page + "&searchKeyword="
								+ URLEncoder.encode(searchKeyword, "UTF-8") + "&version=1")
						.setHeader("Accept", "application/json")
						.addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
						.addHeader("x-skpop-userId", "ok0412").addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
						.execute().returnContent();
				jsondata = content.asString();
				
				
					ObjectMapper mapper = new ObjectMapper();
					JsonNode root = mapper.readTree(jsondata);
					
					JsonNode melon = root.get("melon");
					
					pgNation = new PageNation(page, count, 10, melon.get("totalCount").asLong());
					map.put("pgNation", pgNation);
					JsonNode song = melon.path("songs").get("song");

				if(song != null){
					for(JsonNode item:song){
						SongVO songVO = new SongVO();
						
						JsonNode artist = item.path("artists").findValue("artist");
						
						songVO.setAlbum_key(item.get("albumId").asLong());
						songVO.setAlbum_name(item.get("albumName").asText());
						
						songVO.setArtist_key(artist.findValue("artistId").asLong());
						songVO.setArtist_name(artist.findValue("artistName").asText());
						
						songVO.setIs_adult(item.get("isAdult").asBoolean());
						songVO.setIs_free(item.get("isFree").asBoolean());
						songVO.setIs_hit_song(item.get("isHitSong").asBoolean());
						songVO.setIs_title_song(item.get("isTitleSong").asBoolean());
						songVO.setIssue_date(item.get("issueDate").asText());
						
						songVO.setPlay_time(item.get("playTime").asLong());
						songVO.setSong_key(item.get("songId").asLong());
						songVO.setSong_name(item.get("songName").asText());
						songVO.setAlbum_img(getCoverURL(songVO.getAlbum_key()));
	//					songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//					songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
						list.add(songVO);
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			map.put("list", list);
			return map;
				
			
		}


	
	
	public PagedAlbumList getAlbumList(int page, int count, String searchKeyword) {
		String jsondata = null;	
		PageNation pageNation = null;
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		try {
			Content content = Request
					.Get("http://apis.skplanetx.com/melon/albums?count="+count+"&page=" + page + "&searchKeyword="
							+ URLEncoder.encode(searchKeyword, "UTF-8") + "&version=1")
					.setHeader("Accept", "application/json")
					.addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
					.addHeader("x-skpop-userId", "ok0412").addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
					.execute().returnContent();
			jsondata = content.asString();

		} catch (ClientProtocolException e) {
			logger.info("!ClientProtocolException!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("!IOException!");
			e.printStackTrace();
		}
		//json tree 형태로 읽기
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(jsondata); // 루트
			
			//페이지네이션 뽑기
			JsonNode melon = root.get("melon");
			pageNation = new PageNation(melon.get("page").asInt(), melon.get("totalCount").asInt());
			
			JsonNode album = melon.path("albums").get("album");
			if(album != null){
				for(JsonNode item: album){
					AlbumVO albumVO = new AlbumVO();
					albumVO.setAlbum_key(item.get("albumId").asLong());
					albumVO.setAlbum_name(item.get("albumName").asText());
					JsonNode artist = item.path("artists").findValue("artist").get(0);
					
					albumVO.setArtist_key(artist.get("artistId").asLong());
					albumVO.setAverage_score(item.get("averageScore").asLong());
					albumVO.setIssue_date(item.get("issueDate").asText());
					albumVO.setAlbum_img(getCoverURL(albumVO.getAlbum_key()));
					albumVO.setArtist_name(artist.get("artistName").asText());
					list.add(albumVO);
					
					//logger.info("albumVO:"+albumVO.toString());
					
					//logger.info(artist.toString());
					
					//충격! 한곡에 가수가 두개이상! 디비...어떻게?
					//우선 첫번째것만 넣기로...
	//				for(JsonNode artist: artists){
	//					str_artists += artist.get("artistId").asText();					
	//					str_artists += ",";
	//				}
					//logger.info(artists.get("artistId").toString());
					
					
				}
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new PagedAlbumList(list, pageNation);
	}
	
	//최신곡 가져오기 ★
	public Map<String, Object> getNewSongList(long pg, long count){
		
		Map<String, Object> mapData = new HashMap<String,Object>(); //1
		
		String jsondata = "";
		NewPageNation pgNation = null;
		
		//
		List<SongVO> list = new ArrayList<SongVO>();
		
		
		try {
			Content content = Request.Get("http://apis.skplanetx.com/melon/newreleases/songs?count="+count+"&page="+pg+"&version=1")
							 .setHeader("Accept", "application/json")
							 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
							 .addHeader("x-skpop-userId", "ok0412")
							 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
							 .execute().returnContent();
			jsondata = content.asString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(jsondata);
			
			JsonNode melon = root.get("melon");
			JsonNode song = melon.path("songs").get("song");
			pgNation = new NewPageNation(pg, count, 10, melon.get("totalPages").asLong());
			if(song != null){
				for(JsonNode item : song){
					SongVO songVO = new SongVO();
					songVO.setSong_key(item.get("songId").asLong());
					songVO.setSong_name(item.get("songName").asText());
					
					JsonNode artist = item.path("artists").findValue("artist");
					songVO.setArtist_key(artist.findValue("artistId").asLong());
					songVO.setArtist_name(artist.findValue("artistName").asText());
					
					songVO.setAlbum_key(item.get("albumId").asLong());
					songVO.setAlbum_name(item.get("albumName").asText());
					songVO.setIs_adult(item.findValue("isAdult").asBoolean());
					songVO.setIs_free(item.findValue("isFree").asBoolean());
					songVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
					songVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
					songVO.setIssue_date(item.findValue("issueDate").asText());
					songVO.setPlay_time(item.findValue("playTime").asLong());
					songVO.setAlbum_img(getCoverURL(songVO.getAlbum_key()));
	//				songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//				songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
					list.add(songVO);
					logger.info("songVO : " + songVO.toString());
					
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapData.put("list", list);
		mapData.put("pgNation", pgNation);
		return mapData;
	}
	
	//최신곡 장르별 가져오기★
	public Map<String, Object> getNewSongGenreList(long pg,long count, String genre_key ){
		Map<String, Object> mapData = new HashMap<String, Object>();
		String jsondata = "";
		List<SongVO> list = new ArrayList<SongVO>();
		NewPageNation pgNation = null;
		
		try {
			Content content = Request.Get("http://apis.skplanetx.com/melon/newreleases/songs/"+genre_key+"?count="+count+"&page="+pg+"&version=1")
							 .setHeader("Accept", "application/json")
							 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
							 .addHeader("x-skpop-userId", "ok0412")
							 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
							 .execute().returnContent();
			jsondata = content.asString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(jsondata);
			
			JsonNode melon = root.get("melon");
			JsonNode song = melon.path("songs").get("song");
			//pgNation = new NewPageNation(pg, count, 10, melon.get("totalPages").asLong());
			if(song != null){
				pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
				for(JsonNode item : song){
					SongVO songVO = new SongVO();
					songVO.setSong_key(item.get("songId").asLong());
					songVO.setSong_name(item.get("songName").asText());
					
					JsonNode artist = item.path("artists").findValue("artist");
					songVO.setArtist_key(artist.findValue("artistId").asLong());
					songVO.setArtist_name(artist.findValue("artistName").asText());
					songVO.setAlbum_name(item.get("albumName").asText());
					songVO.setAlbum_key(item.get("albumId").asLong());
					songVO.setIs_adult(item.findValue("isAdult").asBoolean());
					songVO.setIs_free(item.findValue("isFree").asBoolean());
					songVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
					songVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
					songVO.setIssue_date(item.findValue("issueDate").asText());
					songVO.setPlay_time(item.findValue("playTime").asLong());
					
					songVO.setAlbum_img(getCoverURL(songVO.getAlbum_key()));
	//				songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//				songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
					list.add(songVO);
					logger.info("songVO : " + songVO.toString());
					
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapData.put("list", list);
		mapData.put("pgNation", pgNation);
		return mapData;
	}
	
	
	//최신앨범 가져오기 ★
	public Map<String, Object> getNewAlbumList(long pg, long count){
		Map<String, Object> mapData = new HashMap<String, Object>();
		String jsonData = null;
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		NewPageNation pgNation = null;
		
		try {
			Content content = Request.Get("http://apis.skplanetx.com/melon/newreleases/albums?count="+count+"&page="+pg+"&version=1")
							 .setHeader("Accept", "application/json")
							 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
							 .addHeader("x-skpop-userId", "ok0412")
							 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
							 .execute().returnContent();
			jsonData = content.asString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(jsonData);
			
			JsonNode melon = root.get("melon");
			JsonNode album = melon.path("albums").get("album");
			if(album != null){
				pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
				for(JsonNode item:album){
					AlbumVO albumVO = new AlbumVO();
					
					JsonNode repArtist = item.path("repArtists").findValue("artist");
					
					
					albumVO.setAlbum_key(item.findValue("albumId").asLong());
					albumVO.setAlbum_name(item.findValue("albumName").asText());
					albumVO.setArtist_key(repArtist.findValue("artistId").asLong());
					albumVO.setArtist_name(repArtist.findValue("artistName").asText());
					albumVO.setAverage_score(Float.parseFloat(item.findValue("averageScore").asText()));
					albumVO.setIssue_date(item.findValue("issueDate").asText());
					
					albumVO.setAlbum_img(getCoverURL(albumVO.getAlbum_key())); //페이지 막힘 주의
					list.add(albumVO);
				}
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapData.put("list", list);
		mapData.put("pgNation", pgNation);
		return mapData;
	}
	
	
	//최신앨범 장르별 가져오기 ★
	public Map<String, Object> getNewAlbumGenreList(long pg, long count, String genre_key){
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		String jsonData = null;
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		NewPageNation pgNation= null;
		
		try {
			Content content = Request.Get("http://apis.skplanetx.com/melon/newreleases/albums/"+genre_key+"?count="+count+"&page="+pg+"&version=1")
							 .setHeader("Accept", "application/json")
							 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
							 .addHeader("x-skpop-userId", "ok0412")
							 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
							 .execute().returnContent();
			jsonData = content.asString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode root = mapper.readTree(jsonData);
			
			JsonNode melon = root.get("melon");
			JsonNode album = melon.path("albums").get("album");
			if(album != null){
				pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
				for(JsonNode item:album){
					AlbumVO albumVO = new AlbumVO();
					
					JsonNode repArtist = item.path("repArtists").findValue("artist");
					
					
					albumVO.setAlbum_key(item.findValue("albumId").asLong());
					albumVO.setAlbum_name(item.findValue("albumName").asText());
					albumVO.setArtist_key(repArtist.findValue("artistId").asLong());
					albumVO.setArtist_name(repArtist.findValue("artistName").asText());
					albumVO.setAverage_score(Float.parseFloat(item.findValue("averageScore").asText()));
					albumVO.setIssue_date(item.findValue("issueDate").asText());
					
					albumVO.setAlbum_img(getCoverURL(albumVO.getAlbum_key())); //페이지 막힘 주의
					list.add(albumVO);
				}
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mapData.put("list", list);
		mapData.put("pgNation", pgNation);
		return mapData;
	}
	
	//실시간 차트 ★
	public Map<String, Object> getRealtimeSongChartList(long pg, long count){
		
		Map<String, Object> mapData = new HashMap<String,Object>();
		
		List<SongChartVO> list = new ArrayList<SongChartVO>();
		String jsondata = null;
		NewPageNation pgNation = null;
		
		try {
			Content content = Request.Get("http://apis.skplanetx.com/melon/charts/realtime?version=1&page="+pg+"&count="+count)
					  .setHeader("Accept", "application/json")
					 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
					 .addHeader("x-skpop-userId", "ok0412")
					 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
					 .execute().returnContent();
			
			jsondata = content.asString();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(jsondata);
			JsonNode melon = root.get("melon");
			
			
			JsonNode song = melon.path("songs").get("song");
			if(song != null){
				pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
				for(JsonNode item:song){
					SongChartVO songChartVO = new SongChartVO();
					
					songChartVO.setAlbum_key(item.findValue("albumId").asLong());
					songChartVO.setAlbum_name(item.findValue("albumName").asText());
					
					JsonNode artist = item.path("artists").findValue("artist");
					songChartVO.setArtist_key(artist.findValue("artistId").asLong());
					songChartVO.setArtist_name(artist.findValue("artistName").asText());
					
					songChartVO.setIs_adult(item.findValue("isAdult").asBoolean());
					songChartVO.setIs_free(item.findValue("isFree").asBoolean());
					songChartVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
					songChartVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
					songChartVO.setIssue_date(item.findValue("issueDate").asText());
					
					songChartVO.setPlay_time(item.findValue("playTime").asLong());
					songChartVO.setSong_key(item.findValue("songId").asLong());
					songChartVO.setSong_name(item.findValue("songName").asText());
					
					
					songChartVO.setPast_rank(item.findValue("currentRank").asLong());
					songChartVO.setCurrent_rank(item.findValue("currentRank").asLong());
					
					songChartVO.setAlbum_img(getCoverURL(songChartVO.getAlbum_key()));
	//				songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//				songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
					
					list.add(songChartVO);
				}
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mapData.put("list", list);
		mapData.put("pgNation", pgNation);
		
		return mapData;
	}
	
	//일간 차트 ★
		public Map<String, Object> getTodayTopSongChartList(long pg, long count){
			
			Map<String, Object> mapData = new HashMap<String,Object>();
			
			List<SongChartVO> list = new ArrayList<SongChartVO>();
			String jsondata = null;
			NewPageNation pgNation = null;
			
			try {
				Content content = Request.Get("http://apis.skplanetx.com/melon/charts/todaytopsongs?version=1&page="+pg+"&count="+count)
						  .setHeader("Accept", "application/json")
						 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
						 .addHeader("x-skpop-userId", "ok0412")
						 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
						 .execute().returnContent();
				
				jsondata = content.asString();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(jsondata);
				JsonNode melon = root.get("melon");
				JsonNode song = melon.path("songs").get("song");
				if(song != null){
					pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
					for(JsonNode item:song){
						SongChartVO songChartVO = new SongChartVO();
						
						songChartVO.setAlbum_key(item.findValue("albumId").asLong());
						songChartVO.setAlbum_name(item.findValue("albumName").asText());
						
						JsonNode artist = item.path("artists").findValue("artist");
						songChartVO.setArtist_key(artist.findValue("artistId").asLong());
						songChartVO.setArtist_name(artist.findValue("artistName").asText());
						
						songChartVO.setIs_adult(item.findValue("isAdult").asBoolean());
						songChartVO.setIs_free(item.findValue("isFree").asBoolean());
						songChartVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
						songChartVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
	
						
						songChartVO.setPlay_time(item.findValue("playTime").asLong());
						songChartVO.setSong_key(item.findValue("songId").asLong());
						songChartVO.setSong_name(item.findValue("songName").asText());
						
						
						songChartVO.setPast_rank(item.findValue("currentRank").asLong());
						songChartVO.setCurrent_rank(item.findValue("currentRank").asLong());
						songChartVO.setAlbum_img(getCoverURL(songChartVO.getAlbum_key()));
						
	//					songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//					songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
						
						list.add(songChartVO);
					}
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mapData.put("list", list);
			mapData.put("pgNation", pgNation);
			
			return mapData;
		}
		
		
		
		//앨범 차트 ★
		public Map<String, Object> getTop20AlbumChartList(long pg, long count){
			
			Map<String, Object> mapData = new HashMap<String,Object>();
			
			List<AlbumChartVO> list = new ArrayList<AlbumChartVO>();
			String jsondata = null;
			NewPageNation pgNation = null;
			
			try {
				Content content = Request.Get("http://apis.skplanetx.com/melon/charts/topalbums?version=1&page="+pg+"&count="+count)
						  .setHeader("Accept", "application/json")
						 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
						 .addHeader("x-skpop-userId", "ok0412")
						 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
						 .execute().returnContent();
				
				jsondata = content.asString();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(jsondata);
				JsonNode melon = root.get("melon");
				JsonNode album = melon.path("albums").get("album");
				if(album != null){
					pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
					for(JsonNode item:album){
						AlbumChartVO albumChartVO = new AlbumChartVO();
						
						
						albumChartVO.setAlbum_key(item.findValue("albumId").asLong());
						albumChartVO.setAlbum_img(getCoverURL(albumChartVO.getAlbum_key()));
						albumChartVO.setAlbum_name(item.findValue("albumName").asText());
						albumChartVO.setAlbum_type(item.findValue("albumType").asText());
						albumChartVO.setIssue_date(item.findValue("issueDate").asText());
						albumChartVO.setSong_current_rank(item.findValue("repSongCurrentRank").asLong());
						albumChartVO.setSong_key(item.findValue("repSongId").asLong());
						albumChartVO.setSong_name(item.findValue("repSongName").asText());
						albumChartVO.setSong_past_rank(item.findValue("repSongPastRank").asLong());
						
						JsonNode artist = item.path("artists").findValue("artist");
						albumChartVO.setArtist_key(artist.findValue("artistId").asLong());
						albumChartVO.setArtist_name(artist.findValue	("artistName").asText());
						
						list.add(albumChartVO);
					}
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mapData.put("list", list);
			mapData.put("pgNation", pgNation);
			
			return mapData;
		}
		
	
		//Top 100 음악 차트 ★
		public Map<String, Object> getTop100SongChartList(long pg, long count){
			
			Map<String, Object> mapData = new HashMap<String,Object>();
			
			List<SongChartVO> list = new ArrayList<SongChartVO>();
			String jsondata = null;
			NewPageNation pgNation = null;
			
			try {
				Content content = Request.Get("http://apis.skplanetx.com/melon/charts/topgenres?version=1&page="+pg+"&count="+count)
						  .setHeader("Accept", "application/json")
						 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
						 .addHeader("x-skpop-userId", "ok0412")
						 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
						 .execute().returnContent();
				
				jsondata = content.asString();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(jsondata);
				JsonNode melon = root.get("melon");
				JsonNode song = melon.path("songs").get("song");
				if(song != null){
					pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
					for(JsonNode item:song){
						SongChartVO songChartVO = new SongChartVO();
						
						songChartVO.setAlbum_key(item.findValue("albumId").asLong());
						songChartVO.setAlbum_name(item.findValue("albumName").asText());
						
						JsonNode artist = item.path("artists").findValue("artist");
						songChartVO.setArtist_key(artist.findValue("artistId").asLong());
						songChartVO.setArtist_name(artist.findValue("artistName").asText());
						
						songChartVO.setIs_adult(item.findValue("isAdult").asBoolean());
						songChartVO.setIs_free(item.findValue("isFree").asBoolean());
						songChartVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
						songChartVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
	
						
						songChartVO.setPlay_time(item.findValue("playTime").asLong());
						songChartVO.setSong_key(item.findValue("songId").asLong());
						songChartVO.setSong_name(item.findValue("songName").asText());
						
						
						songChartVO.setPast_rank(item.findValue("currentRank").asLong());
						songChartVO.setCurrent_rank(item.findValue("currentRank").asLong());
						songChartVO.setAlbum_img(getCoverURL(songChartVO.getAlbum_key()));
						
	//					songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//					songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
						
						list.add(songChartVO);
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mapData.put("list", list);
			mapData.put("pgNation", pgNation);
			
			return mapData;
		}
		
		
		//장르별 Top 100 음악 차트 
		public Map<String, Object> getTop100GenreSongChartList(long pg, long count, String genre){
			
			Map<String, Object> mapData = new HashMap<String,Object>();
			
			List<SongChartVO> list = new ArrayList<SongChartVO>();
			String jsondata = null;
			NewPageNation pgNation = null;
			
			try {
				Content content = Request.Get("http://apis.skplanetx.com/melon/charts/topgenres/"+genre+"?version=1&page="+pg+"&count="+count)
						  .setHeader("Accept", "application/json")
						 .addHeader("access_token", "961f2ece-e8c2-4558-b7e4-abc141edc6a8")
						 .addHeader("x-skpop-userId", "ok0412")
						 .addHeader("appKey", "16622697-b1a7-3cac-ac4c-97c3dfe161cc")
						 .execute().returnContent();
				
				jsondata = content.asString();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(jsondata);
				JsonNode melon = root.get("melon");
				JsonNode song = melon.path("songs").get("song");
				if(song != null){
					pgNation = new NewPageNation(pg,count, 10, melon.get("totalPages").asLong());
					for(JsonNode item:song){
						SongChartVO songChartVO = new SongChartVO();
						
						songChartVO.setAlbum_key(item.findValue("albumId").asLong());
						songChartVO.setAlbum_name(item.findValue("albumName").asText());
						
						JsonNode artist = item.path("artists").findValue("artist");
						songChartVO.setArtist_key(artist.findValue("artistId").asLong());
						songChartVO.setArtist_name(artist.findValue("artistName").asText());
						songChartVO.setIs_adult(item.findValue("isAdult").asBoolean());
						songChartVO.setIs_free(item.findValue("isFree").asBoolean());
						songChartVO.setIs_hit_song(item.findValue("isHitSong").asBoolean());
						songChartVO.setIs_title_song(item.findValue("isTitleSong").asBoolean());
	
						
						songChartVO.setPlay_time(item.findValue("playTime").asLong());
						songChartVO.setSong_key(item.findValue("songId").asLong());
						songChartVO.setSong_name(item.findValue("songName").asText());
						
						
						songChartVO.setPast_rank(item.findValue("currentRank").asLong());
						songChartVO.setCurrent_rank(item.findValue("currentRank").asLong());
						songChartVO.setAlbum_img(getCoverURL(songChartVO.getAlbum_key()));
						
	//					songVO.setComposition(getComposition(item.get("songId").asText())); //페이지 막힘 주의
	//					songVO.setLyricist(getLyricist(item.get("songId").asText())); //페이지 막힘 주의
						
						list.add(songChartVO);
					}
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mapData.put("list", list);
			mapData.put("pgNation", pgNation);
			
			return mapData;
		}
		
		// song에 있는 albumKey,albumName으로 앨범정보 가져오기
		// 직접 가져올수가 없어 검색API 거쳐야함
		public AlbumVO getAlbum(long albumKey,String albumName){
			if(albumName==null) return null; // 앨범이름 못받아오면 null
			PagedAlbumList pagedAlbumList = getAlbumList(1, 50, albumName); //앨범네임 01 이딴식으로 짓는놈들땜에 검색안됨
			List<AlbumVO> albumList = pagedAlbumList.getList();
			if(albumList==null) return null; // 검색결과가 없으면 null
			for(AlbumVO albumVO : albumList){
				if(albumVO.getAlbum_key() == albumKey){
					return albumVO;
				}				
			}
			return null;
		}
		// song에 있는 albumKey,albumName으로 앨범정보 아티스트 검색 거쳐 가져오기
		// 위방법 안통하는 앨범에 씀
		public AlbumVO getAlbumByArtistKey(long albumKey,String artistName){
			if(artistName==null) return null; // 앨범이름 못받아오면 null
			PagedAlbumList pagedAlbumList = getAlbumList(1, 50, artistName); //앨범네임 01 이딴식으로 짓는놈들땜에 검색안됨
			List<AlbumVO> albumList = pagedAlbumList.getList();
			if(albumList==null) return null; // 검색결과가 없으면 null
			for(AlbumVO albumVO : albumList){
				if(albumVO.getAlbum_key() == albumKey){
					return albumVO;
				}
			}
			return null;
		}
		// song에 있는 albumKey,albumName으로 앨범정보 아티스트 검색 거쳐 가져오기
		// 위방법 안통하는 앨범에 씀
		public AlbumVO getAlbumByTwoName(long albumKey,String artistName, String albumName){
			if(artistName==null) return null; // 앨범이름 못받아오면 null
			PagedAlbumList pagedAlbumList = getAlbumList(1, 50, artistName+" "+albumName); //앨범네임 01 이딴식으로 짓는놈들땜에 검색안됨
			List<AlbumVO> albumList = pagedAlbumList.getList();
			if(albumList==null) return null; // 검색결과가 없으면 null
			for(AlbumVO albumVO : albumList){
				if(albumVO.getAlbum_key() == albumKey){
					return albumVO;
				}
			}
			return null;
		}
		// song에 있는 artistKey,artistName으로 아티스트정보 가져오기
		// 직접 가져올수가 없어 검색API 거쳐야함
		public ArtistVO getArtist(long artistKey,String artistName){
			if(artistName==null) return null; // 아티스트이름 못받아오면 null
			List<ArtistVO> artistList = getArtistList(1, 10, artistName);			
			if(artistList==null) return null; // 검색결과가 없으면 null
			for(ArtistVO artistVO : artistList){
				if(artistVO.getArtist_key() == artistKey){
					return artistVO;
				}
			}
			return null;
		}
		public ArtistVO getArtist(long artist_key, String artist_name, String song_name) {
			if(artist_name==null) return null; // 아티스트이름 못받아오면 null
			List<ArtistVO> artistList = getArtistList(1, 50, artist_name+" "+song_name);			
			if(artistList==null) return null; // 검색결과가 없으면 null
			logger.info("getArtist : "+artist_key+artist_name+song_name);
			for(ArtistVO artistVO : artistList){
				if(artistVO.getArtist_key() == artist_key){
					return artistVO;
				}
			}
			return null;
		}
		
}
