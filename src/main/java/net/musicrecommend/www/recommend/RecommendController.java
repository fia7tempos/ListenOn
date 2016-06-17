package net.musicrecommend.www.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musicrecommend.www.music.ArtistService;
import net.musicrecommend.www.operation.OperationService;
import net.musicrecommend.www.user.UserService;
import net.musicrecommend.www.util.MashupPicker;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.DetailChartVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.RecommendUserVO;
import net.musicrecommend.www.vo.SongVO;
import net.musicrecommend.www.vo.StarPointVO;

@Controller
@RequestMapping("/")
public class RecommendController {
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);

	@Autowired
	RecommendService recommendService;
	@Autowired
	UserService userService;
	@Autowired
	OperationService operationService;
	@Autowired
	MashupPicker mashupPicker;
	@Autowired
	ArtistService artistService;

	@RequestMapping(value = "recommend", method = RequestMethod.POST, produces = { "Application/plain; charset=UTF-8" })
	@ResponseBody
	public String recom(@ModelAttribute RecommendVO recommendVO, HttpSession session, String album_name,
			String artist_name, String song_name) throws Exception {
		String user_id = session.getAttribute("user_id").toString();
		System.out.println(recommendVO.toString());
		if (user_id == null) { // 로그인 안했을때
			return "로그인 필요";
		}
		String msg = "";
		recommendVO.setUser_no(userService.getUserNo(user_id));
		try {
			recommendService.insertRecommend(recommendVO);

			// 앨범정보랑 아티스트정보도 입력하자
			ArtistVO artistVO = mashupPicker.getArtist(recommendVO.getArtist_key(), artist_name, song_name);
			logger.info("키워드 " + artist_name + " " + song_name + "로 입력할 아티스트 : " + artistVO);
			if (artistVO == null)
				artistVO = mashupPicker.getArtist(recommendVO.getArtist_key(), artist_name);
			if (artistVO != null) {
				operationService.insertArtist(artistVO);
			} else {
				msg = "평가 완료!";
			}

			// logger.info(album_name,artist_name);
			AlbumVO albumVO = mashupPicker.getAlbumByTwoName(recommendVO.getAlbum_key(), artist_name, album_name);
			if (albumVO != null) {
				operationService.insertAlbum(albumVO);
			} else { // 두번째 방법
				albumVO = mashupPicker.getAlbumByArtistKey(recommendVO.getAlbum_key(), artist_name);
				if (albumVO == null) {
					return "평가 완료!";
				}
				operationService.insertAlbum(albumVO);
			}
			msg = "평가 완료!";
		} catch (Exception e) {
			msg = "오류";
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(value = "recommend/user/{user_id}", method = RequestMethod.GET, produces = {
			"Application/plain; charset=UTF-8" })
	@ResponseBody
	public List<RecommendVO> getUserRecommendList(@PathVariable String user_id) throws Exception {
		long user_no = userService.getUserNo(user_id);
		return recommendService.getUserRecommendList(user_no);
	}

	@RequestMapping(value = "recommendation/{user_id}", method = RequestMethod.GET)
	public String getRecommendation(@PathVariable String user_id) throws Exception {
		return "recommend/recommend";
	}

	// @RequestMapping(value="comment")
	// @ResponseBody
	// public List<CommentVO> comment(){
	//
	// List<CommentVO>
	//
	// }

	@RequestMapping(value = "/recommendation/song/insert", method = RequestMethod.POST)
	public String redirectToSong() {
		return "redirect:/operation/song/insert";
	}

	@RequestMapping(value = "recommend/detail/insert_comment", method = RequestMethod.POST, produces = {
			"Application/plain; charset=UTF-8" })
	@ResponseBody
	public String insert_comment(@ModelAttribute RecommendVO recommendVO, HttpSession session) {
		String msg = "";
		System.out.println("recommendVO : " + recommendVO.toString());
		try {
			String user_id = session.getAttribute("user_id").toString();
			long user_no = userService.getUserNo(user_id);
			recommendVO.setUser_no(user_no);
			msg = recommendService.insertComment(recommendVO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("msg : " + msg);
		return msg;
	}

	@RequestMapping(value = "nullalbum", method = RequestMethod.GET, produces = { "Application/plain; charset=UTF-8" })
	public String nullalbum() throws Exception {
		List<SongVO> nullSongs = recommendService.getNullalbmSongs();
		for (SongVO songVO : nullSongs) {
			Map<String, Object> map = mashupPicker.getSongList(1, 50,
					songVO.getArtist_name() + " " + songVO.getSong_name());
			for (SongVO getVO : (List<SongVO>) (map.get("list"))) {
				if (getVO.getSong_key() == songVO.getSong_key()) {
					songVO.setAlbum_name(getVO.getAlbum_name());
					recommendService.insertNullalbum(songVO);
				}
			}
		}
		return "";
	}

	@RequestMapping(value = "recommend/detail/get_comment/{pg}", method = RequestMethod.POST, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> getComment(@ModelAttribute RecommendVO recommendVO, @PathVariable long pg) {
		List<RecommendUserVO> commentList = new ArrayList<RecommendUserVO>();

		Map<String, Object> mapData = null;
		try {
			long commentCount = recommendService.getTotalCommentCount(recommendVO);
			PageNation pgNation = new PageNation(pg, 3, 10, commentCount);
			mapData = new HashMap<String, Object>();

			mapData.put("recommendVO", recommendVO);
			mapData.put("pgNation", pgNation);

			commentList = recommendService.getCommentList(mapData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mapData.put("commentList", commentList);

		return mapData;
	}

	@RequestMapping(value = "recommend/detail/get_chart_info", method = RequestMethod.POST, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<DetailChartVO> getChart(@ModelAttribute RecommendVO recommendVO) {

		List<DetailChartVO> detailChartVO = null;
		
		try {

			detailChartVO = recommendService.getStarPointSubtotal(recommendVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(float i=0.5f;i<=5.0f;i+=0.5f){
			map.put(String.valueOf(i), 0);
		}
		
		for(int i=0;i<detailChartVO.size();i++){
			map.put(String.valueOf(detailChartVO.get(i).getStar_point()),detailChartVO.get(i).getCnt());
		}
		
		detailChartVO = new ArrayList<DetailChartVO>();
		
		for(float i=0.5f;i<=5.0f;i+=0.5f){
			DetailChartVO sp = new DetailChartVO();
			
			String key = String.valueOf(i);
			if(!map.get(key).equals(0)){
				Long value = Long.valueOf(map.get(key).toString());
				sp.setCnt(value);
				sp.setStar_point(i);
			}else{
				sp.setCnt(0);
				sp.setStar_point(i);
			}
			detailChartVO.add(sp);
		}

		return detailChartVO;

	}

	@RequestMapping(value = "recommend/detail/get_chart_avg", method = RequestMethod.POST, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<DetailChartVO> getChartAvg(@ModelAttribute RecommendVO recommendVO) {

		List<DetailChartVO> detailChartVO = null;

		try {

			detailChartVO = recommendService.getStarPointAvg(recommendVO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return detailChartVO;

	}

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void main() throws Exception {
	}

	// 메인페이지 1단 추천목록
	@RequestMapping(value = "song/mainpagelist", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<Object> getMainPageList() throws Exception {
		return recommendService.getMainPageList();
	}

	// 메인페이지 2단 90년대 히트송
	@RequestMapping(value = "song/xcenturysongs", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<SongVO> getXCenturySongs(@RequestParam("year") String year) throws Exception {
		return recommendService.getXCenturySongs(year);
	}

	// 메인페이지 3단 추천목록 (로그인체크)
	@RequestMapping(value = "song/mainpagedownlist", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<ArtistVO> getMainPageDownList(HttpSession session) throws Exception {
		if (session.getAttribute("user_id") == null) {
			return recommendService.getMainPageDownList();
		} else {
			return recommendService.getMainPageDownList(session.getAttribute("user_id").toString());
		}
	}

	// 메인페이지 4단 19
	@RequestMapping(value = "song/adultsonglist", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<SongVO> getAdultSongList() throws Exception {
		return recommendService.getAdultSongList();
	}

	// 검색어 순위
	@RequestMapping(value = "searchKeyword", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<KeywordVO> getSearchKeywordList() throws Exception {
		return recommendService.getSearchKeywordList();
	}

	// 평점수 순위
	@RequestMapping(value = "recommendrank", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<Map<String, Object>> getRecommendRank() throws Exception {
		return recommendService.getRecommendRank();
	}

	// 아티스트 정보가져오기
	@RequestMapping(value = "recommend/detail/get_artist_info", method = RequestMethod.POST, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public ArtistVO getArtistInfo(@ModelAttribute ArtistVO artistVO) {

		try {
			artistVO = artistService.getArtistInfo(artistVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return artistVO;

	}

	// 권우 추천 페이지\\
	@RequestMapping(value = "recommend/main", method = RequestMethod.GET)
	public void recommendMain(HttpSession session, Model model) {
		long user_no;
		try {
			user_no = userService.getUserNo(session.getAttribute("user_id").toString());
			long user_recommend_total_count = recommendService.getUserRecommendTotalCount(user_no);
			model.addAttribute("user_recommend_total_count", user_recommend_total_count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}

	// 나라\\
	@RequestMapping(value = "recommend/state/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recommend_state(HttpSession session, @RequestParam int page,
			@PathVariable String keyword) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		String partition = null;

		if (keyword.equals("korea")) {
			map.put("keyword", "대한민국");
			partition = "대한민국";
		} else if (keyword.equals("america")) {
			map.put("keyword", "미국");
			map.put("keyword2", "캐나다");
			map.put("keyword3", "자메이카");
			partition = "아메리카";

		} else if (keyword.equals("asia")) {
			map.put("keyword", "일본");
			map.put("keyword2", "중국");
			partition = "아시아";
		} else if (keyword.equals("europe")) {
			map.put("keyword", "영국");
			map.put("keyword", "독일");
			map.put("keyword", "이탈리아");
			map.put("keyword", "노르웨이");
			map.put("keyword", "프랑스");
			map.put("keyword", "아일랜드");
			map.put("keyword", "스페인");
			map.put("keyword", "러시아");
			map.put("keyword", "그리스");
			map.put("keyword", "체코");
			map.put("keyword", "헝가리");
			map.put("keyword", "핀란드");
			map.put("keyword", "포르투갈");
			partition = "유럽";
		}

		map.put("user_id", user_id);
		long totalCount = recommendService.recommend_State_Count(map);

		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());
		List<SongVO> list = recommendService.recommend_State_List(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info(partition + " 리스트 : " + list.toString());

		return mapData;

	}

	// 그룹\\
	@RequestMapping(value = "recommend/group/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recommend_group(HttpSession session, @RequestParam int page,
			@PathVariable String keyword) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		String partition = null;

		if (keyword.equals("girl")) {
			map.put("keyword", "F");
			partition = "걸 그룹";
		} else if (keyword.equals("boy")) {
			map.put("keyword", "M");
			partition = "보이 그룹";
		} else if (keyword.equals("hybrid")) {
			map.put("keyword", "H");
			partition = "혼성 그룹";
		}

		map.put("user_id", user_id);
		long totalCount = recommendService.recommend_Group_Count(map);

		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());
		List<SongVO> list = recommendService.recommend_Group_List(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info(partition + " 리스트 : " + list.toString());

		return mapData;

	}

	// 솔로\\
	@RequestMapping(value = "recommend/solo/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recommend_solo(HttpSession session, @RequestParam int page, @PathVariable String keyword)
			throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		String partition = null;

		if (keyword.equals("girl")) {
			map.put("keyword", "F");
			partition = "솔로녀";
		} else if (keyword.equals("boy")) {
			map.put("keyword", "M");
			partition = "솔로남";
		}

		map.put("user_id", user_id);
		long totalCount = recommendService.recommend_Solo_Count(map);

		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());
		List<SongVO> list = recommendService.recommend_Solo_List(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info(partition + " 리스트 : " + list.toString());

		return mapData;

	}

	// 키워드\\
	@RequestMapping(value = "recommend/keyword/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recommend_keyword(HttpSession session, @RequestParam int page,
			@PathVariable String keyword) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		String partition = null;

		if (keyword.equals("lie")) {
			map.put("keywordA", "%거짓말%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("Remake")) {
			map.put("keywordA", "%리메이크%");
			map.put("keywordB", "%remake%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("Remix")) {
			map.put("keywordA", "%리믹스%");
			map.put("keywordB", "%remix%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("trust")) {
			map.put("keywordA", "%믿음%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("love")) {
			map.put("keywordA", "%사랑%");
			map.put("keywordB", "%love%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("birthday")) {
			map.put("keywordA", "%생일%");
			map.put("keywordB", "%birthday%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("wish")) {
			map.put("keywordA", "%소원%");
			map.put("keywordB", "%wish%");
			map.put("keywordC", "%hope%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("time")) {
			map.put("keywordA", "%시간%");
			map.put("keywordB", "%time%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("parting")) {
			map.put("keywordA", "%이별%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("story")) {
			map.put("keywordA", "%이야기%");
			map.put("keywordB", "%story%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("friend")) {
			map.put("keywordA", "%친구%");
			map.put("keywordB", "%friend%");
			partition = (String) map.get("keywordA");
		} else if (keyword.equals("talk")) {
			map.put("keywordA", "%톡%");
			map.put("keywordB", "%talk%");
			partition = (String) map.get("keywordA");
		}

		map.put("user_id", user_id);

		long totalCount = recommendService.recommend_Keyword_Count(map);

		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());
		List<SongVO> list = recommendService.recommend_Keyword_List(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info(partition + " 리스트 : " + list.toString());

		return mapData;

	}

	// 선호분석\\
	@RequestMapping(value = "recommend/favorite/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> recommend_favorite(HttpSession session, @RequestParam int page,
			@PathVariable String keyword) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String partition = null;
		long totalCount = 0;
		List<SongVO> list = null;
		PageNation pgNation = null;
		
		map.put("user_id", user_id);

		if (keyword.equals("singer")) {
			partition = "선호 가수";
			
			totalCount = recommendService.recommend_Singer_Count(map);
			
			pgNation = new PageNation(page, 21, 21, totalCount);
			map.put("start_num", pgNation.getStart_num());
			map.put("end_num", pgNation.getEnd_num());
			
			list = recommendService.recommend_Singer_List(map);
		} else if (keyword.equals("play_time")) {
			partition = "선호 플레이타임";

			pgNation = new PageNation(page, 21, 21, totalCount);
			map.put("start_num", pgNation.getStart_num());
			map.put("end_num", pgNation.getEnd_num());
						
			totalCount = recommendService.recommend_playTime_Count(map);
			list = recommendService.recommend_playTime_List(map);
		} else if (keyword.equals("issue_date")) {
			partition = "선호 출시연도";

			pgNation = new PageNation(page, 21, 21, totalCount);
			map.put("start_num", pgNation.getStart_num());
			map.put("end_num", pgNation.getEnd_num());
						
			totalCount = recommendService.recommend_issueDate_Count(map);
			 list = recommendService.recommend_issueDate_List(map);
		}	

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info(partition + " 리스트 : " + list.toString());

		return mapData;

	}

	// 메인페이지 5단 서바이벌 프로그램
	@RequestMapping(value = "song/survivalsonglist", method = RequestMethod.GET, produces = {
			"Application/json; charset=UTF-8" })
	@ResponseBody
	public List<SongVO> getSurvivalSongList() throws Exception {
		return recommendService.getSurvivalSongList();
	}

}
