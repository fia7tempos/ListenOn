package net.musicrecommend.www.operation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musicrecommend.www.util.MashupPicker;
import net.musicrecommend.www.util.PagedAlbumList;
import net.musicrecommend.www.vo.AlbumChartVO;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.GenreVO;
import net.musicrecommend.www.vo.SongChartVO;
import net.musicrecommend.www.vo.SongVO;

@Controller
@RequestMapping("operation")
public class OperationController {
	
	
	@Autowired
	OperationService operationService;
	
	@Autowired
	MashupPicker mashupPicker;
	
	private static final Logger logger = LoggerFactory.getLogger(OperationController.class);
	
	
	// 가수 정보 리스트
		@RequestMapping(value = "artist/{page}", method = RequestMethod.GET)
		public String artistRead(Model model, @RequestParam(required = false, defaultValue = "ioi") String searchKeyword,@PathVariable int page) {
				
			model.addAttribute("list", mashupPicker.getArtistList(page,10, searchKeyword));
			model.addAttribute("page", page);
			return "operation/artist";
		}

		// 가수 정보 입력
		@RequestMapping(value = "artist/{page}", method = RequestMethod.POST, produces = "text/plain")
		@ResponseBody
		public String artistInsert(@ModelAttribute ArtistVO artistVO) throws Exception {
			boolean isJoined = operationService.checkArtistId(artistVO);
			if(isJoined){			
				return "existid";
			}else{
				operationService.insertArtist(artistVO);
				return "success";			
			}
		}

		// 앨범 정보 리스트
		@RequestMapping(value = "album/{page}", method = RequestMethod.GET)
		public String albumRead(Model model, @RequestParam(required = false, defaultValue = "kim") String searchKeyword,
				@PathVariable int page) {
			PagedAlbumList pagedAlbumList = mashupPicker.getAlbumList(page,10, searchKeyword);
			if(pagedAlbumList!=null){
				model.addAttribute("list", pagedAlbumList.getList());
				model.addAttribute("pageNation", pagedAlbumList.getPageNation());
				model.addAttribute("page", page);
			}else{
				
			}
			return "operation/album";
		}

		// 앨범 정보 입력
		@RequestMapping(value = "album/{page}", method = RequestMethod.POST,produces = "text/plain")
		@ResponseBody
		public String albumInsert(@ModelAttribute AlbumVO albumVO) throws Exception {
			boolean isJoined = operationService.checkAlbumId(albumVO);
			if(isJoined){			
				return "existid";
			}else{
				operationService.insertAlbum(albumVO);
				return "success";			
			}
		}
	
	@RequestMapping(value="test/{searchKeyword}", method=RequestMethod.GET)
	public String test(Model model,
			@PathVariable String searchKeyword){
		model.addAttribute("test",mashupPicker.getAlbumList(1, 10,searchKeyword));
//		model.addAttribute("test",picker.getAlbumList(1, searchKeyword));
		//model.addAttribute("test",picker.getArtistList(1, searchKeyword));
		//model.addAttribute("test",picker.getArtistProfileURL("686920"));
		//model.addAttribute("test",picker.getLyrics("8173044"));
		//model.addAttribute("test",picker.getLyrics("8173044"));
		
		//model.addAttribute("test",picker.getSongList(0, "pick me"));
		return "operation/test";
		
	}
	
	@RequestMapping(value="song", method = RequestMethod.GET)
	public String Song(){
		return "operation/song";
	}
	
	
	@RequestMapping(value="song/{searchKeyword}")
	@ResponseBody
	public Map<String, Object> listSong(@PathVariable String searchKeyword, @RequestParam(defaultValue="1") long page, Model model) throws Exception {
		Map<String, Object> mapData =  mashupPicker.getSongList(page, 21,searchKeyword);
		operationService.insertSearchKeyword(searchKeyword);
		return mapData;
	}
	
	@RequestMapping(value="song/insert", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public String insertSong(@ModelAttribute SongVO songVO) throws Exception {
		
		System.out.println("★ : " + songVO.toString());
		operationService.insertSong(songVO);

		return "입력 성공";
	}
	
	//타이틀 이미지
	@RequestMapping(value="imagelist", produces="Application/json; charset=UTF-8", method=RequestMethod.GET)
	@ResponseBody
	public List<String> getImageList() throws Exception{
		return operationService.getImageList();
	}
	
	@RequestMapping(value="testNew", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> testNew(){
		
		Map<String, Object> mapData = mashupPicker.getTop100GenreSongChartList(1, 50, "DP0000");
		
		return mapData;
	}
	
	@RequestMapping(value="chartList", method=RequestMethod.GET)
	public void chartList(Model model){
		
		Map<String, Object> mapData = null;
		
		List<GenreVO> genreVO;
		
		try {
			genreVO = operationService.getGenreList();
			mapData = mashupPicker.getTop100GenreSongChartList(1, 21, "DP0000");
			mapData.put("genreVO", genreVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("mapData", mapData);
		
	}
	
	@RequestMapping(value="chartList/{genre_key}", produces="Application/json; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> chartList(@PathVariable String genre_key, @RequestParam(defaultValue="1") int page){
		
		Map<String, Object> mapData = null;
		List<GenreVO> genreVO;
		try {
			genreVO = operationService.getGenreList();
			mapData = mashupPicker.getTop100GenreSongChartList(page, 21, genre_key);
			mapData.put("genreVO", genreVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	//최신곡 가져오기 o
	@RequestMapping(value="newsonglist/{page}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getNewSongList(@PathVariable long page){
		Map<String, Object> mapData = null;
		mapData = mashupPicker.getNewSongList(page, 21);
		
		return mapData;
	}
	@RequestMapping(value="newsonglist")
	public void getNewSongList(){
		
	}
	
	//최신곡 장르별 가져오기 o
	@RequestMapping(value="newsonggenrelist/{genre_key}/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getnewsonggenrelist(@PathVariable long pg, @PathVariable String genre_key){
		Map<String, Object> mapData = null;
		List<SongVO> songVO;
		try {
			songVO = operationService.getSongList();
			mapData = mashupPicker.getNewSongGenreList(pg, 21, genre_key);
			mapData.put("songVO", songVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	
	@RequestMapping(value="newsonggenrelist")
	public void getnewsonggenrelist(Model model){
		
		List<GenreVO> genreList = null;
		
		try {
			genreList = operationService.getGenreList();
			System.out.println("genreList : " + genreList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("genreList",genreList);
		
	}
	
	
	//최신앨범 가져오기 o
	@RequestMapping(value="newalbumlist/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getnewalbumlist(@PathVariable long pg) {
		Map<String, Object> mapData = null;
		List<AlbumVO> albumVO;
		try {
			albumVO = operationService.getAlbumList();
			mapData = mashupPicker.getNewAlbumList(pg, 21);
			mapData.put("albumVO", albumVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	
	@RequestMapping(value="newalbumlist")
	public void getnewalbumlist() {

	}
	
	//최신앨범 장르별 가져오기 o
	@RequestMapping(value="newalbumgenrelist/{genre_key}/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getnewalbumgenrelist(@PathVariable long pg, @PathVariable String genre_key) {
		Map<String, Object> mapData = null;
		List<AlbumVO> albumVO;
		try {
			albumVO = operationService.getAlbumList();
			mapData = mashupPicker.getNewAlbumGenreList(pg, 21, genre_key);
			mapData.put("albumVO", albumVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return mapData;
	}
	
	@RequestMapping(value="newalbumgenrelist")
	public void getnewalbumgenrelist(Model model) {
		List<GenreVO> genreList = null;
		
		try {
			genreList = operationService.getGenreList();
			System.out.println("genreList : " + genreList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("genreList", genreList);
	}
	
	//실시간 차트 o
	@RequestMapping(value="realtimesongchartlist/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getrealtimesongchartlist(@PathVariable long pg) {
		Map<String, Object> mapData = null;
		List<SongChartVO> songchartVO;
		try {
			songchartVO = operationService.getSongChartList();
			mapData = mashupPicker.getRealtimeSongChartList(pg, 21);
			mapData.put("songchartVO", songchartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@RequestMapping(value="realtimesongchartlist")
	public void getrealtimesongchartlist() {
		
	}
	
	//일간 차트 o
	@RequestMapping(value="todaytopsongchartlist/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> gettodaytopsongchartlist(@PathVariable long pg) {
		Map<String, Object> mapData = null;
		List<SongChartVO> songchartVO;
		try {
			songchartVO = operationService.getSongChartList();
			mapData = mashupPicker.getTodayTopSongChartList(pg, 21);
			mapData.put("songchartVO", songchartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@RequestMapping(value="todaytopsongchartlist")
	public void gettodaytopsongchartlist() {
		
	}
	
	//앨범 차트 -- 아직
	@RequestMapping(value="gettop20albumchartlist/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> gettop20albumchartlist(@PathVariable long pg) {
		Map<String, Object> mapData = null;
		List<AlbumChartVO> albumchartVO;
		try {
			albumchartVO = operationService.getAlbumChartList();
			mapData = mashupPicker.getTop20AlbumChartList(pg, 21);
			mapData.put("albumchartVO", albumchartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@RequestMapping(value="gettop20albumchartlist")
	public void gettop20albumchartlist() {
		
	}
	
	//TOP100 음악 차트 o
	@RequestMapping(value="gettop100songchartlist/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> gettop100songchartlist(@PathVariable long pg) {
		Map<String, Object> mapData = null;
		List<SongChartVO> songchartVO;
		try {
			songchartVO = operationService.getSongChartList();
			mapData = mashupPicker.getTop100SongChartList(pg, 21);
			mapData.put("songchartVO", songchartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@RequestMapping(value="gettop100songchartlist")
	public void gettop100songchartlist() {
		
	}
	
	//장르별 TOP100 음악 차트 o
	@RequestMapping(value="gettop100genresongchartlist/{genre_key}/{pg}", produces="Application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> gettop100genresongchartlist(@PathVariable long pg, @PathVariable String genre_key) {
		Map<String, Object> mapData = null;
		List<SongChartVO> songchartVO;
		try {
			songchartVO = operationService.getSongChartList();
			mapData = mashupPicker.getTop100GenreSongChartList(pg, 21, genre_key);
			mapData.put("songchartVO", songchartVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@RequestMapping(value="gettop100genresongchartlist")
	public void gettop100genresongchartlist(Model model) {
		List<GenreVO> genreList = null;
		try {
			genreList = operationService.getGenreList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("genreList", genreList);
		
	}
	
	@RequestMapping(value="detail")
	public String detail_album_song(){
		return "operation/detail_song_album";
		
	}
	
	
	@RequestMapping(value="main_detail")
	public String detail_main(){
		return "operation/main_detail";
	}
	
	@RequestMapping(value="newmusic")
	public void newmusic(){
		
	}
	
	@RequestMapping(value="get_artist_img", method=RequestMethod.POST)
	@ResponseBody
	public String getArtistImg(@RequestParam String artist_key){
		
		return mashupPicker.getArtistProfileURL(artist_key);
		
	}
	

	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public void search(@RequestParam String search_keyword, Model model){
		model.addAttribute("search_keyword", search_keyword);
		
	}
	
	@RequestMapping(value="chart", method=RequestMethod.GET)
	public void chart(Model model){
		List<GenreVO> genreList = null;
		try {
			genreList = operationService.getGenreList();
			model.addAttribute("genreList",genreList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
