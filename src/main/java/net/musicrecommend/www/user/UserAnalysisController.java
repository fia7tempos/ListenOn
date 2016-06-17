package net.musicrecommend.www.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.musicrecommend.www.operation.OperationService;
import net.musicrecommend.www.recommend.RecommendService;
import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.GenreCntVO;
import net.musicrecommend.www.vo.PerferArtistVO;
import net.musicrecommend.www.vo.SongVO;
import net.musicrecommend.www.vo.StarPointVO;

@Controller
@RequestMapping("user/analysis")
public class UserAnalysisController {
	
	@Value("cdn_url")
	private String cdn_url;

	@Autowired
	UserService userService;
	
	@Autowired
	RecommendService recommendService;
	
	@Autowired
	OperationService operationService;
	
	@RequestMapping(value="")
	public String defaultAnalysis(HttpSession session){
		String user_id = null;
		if(session.getAttribute("user_id") != null){
			user_id = session.getAttribute("user_id").toString();
			return "redirect:/user/analysis/"+user_id;
		}else{
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "{target_user_id}", method = RequestMethod.GET)
	public String getUserAnalysis(@PathVariable String target_user_id, HttpSession session, Model model) {
		
		
		String loginUserId = session.getAttribute("user_id").toString();
		
		try {
			long user_recommend_total_count = recommendService.getUserRecommendTotalCount(userService.getUserNo(loginUserId));
			model.addAttribute("user_recommend_total_count",user_recommend_total_count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("login_user_id", loginUserId);
		model.addAttribute("target_user_id", loginUserId);
		model.addAttribute("cdn_url",cdn_url);
		return "user/analysis";
	}
	
	@RequestMapping(value = "other/{target_user_id}", method = RequestMethod.GET)
	public String getOtherUserAnalysis(@PathVariable String target_user_id, Model model, HttpSession session) {
		
		
		String loginUserId = session.getAttribute("user_id").toString();
		
		if ( loginUserId.equals(target_user_id)){
			model.addAttribute("login_user_id", loginUserId);
			model.addAttribute("target_user_id", loginUserId);
			model.addAttribute("cdn_url",cdn_url);
			return "user/analysis";
		} else {
			model.addAttribute("target_user_id", target_user_id);
			model.addAttribute("cdn_url",cdn_url);
			return "user/analysis_other";
		}
		
	
		
	}

	@RequestMapping(value = "{user_id}/prefer_artist", produces = "Application/json; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public List<PerferArtistVO> prefer_Artist_post(@PathVariable String user_id) {
		List<PerferArtistVO> prefer_Artist = null;

		try {
			long user_no = userService.getUserNo(user_id);
			prefer_Artist = userService.getPrefer_Artist(user_no);
			System.out.print(user_no);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prefer_Artist;
	}

	@RequestMapping(value = "{user_id}/prefer_artist", produces = "Application/json; charset=UTF-8",method = RequestMethod.GET)
	@ResponseBody
	public List<PerferArtistVO> prefer_Artist_get(@PathVariable String user_id) throws Exception{
		long user_no = userService.getUserNo(user_id);
		return userService.getPrefer_Artist(user_no);
	}

	// 유저별 선호 장르 Json 받아오기
	// PathVari
	@RequestMapping(value = "{user_id}/pref_genre", method = RequestMethod.GET, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public List<GenreCntVO> getUserPrefGenre(HttpSession session, @PathVariable String user_id) throws Exception {
		Long user_no = userService.getUserNo(user_id);
		List<GenreCntVO> list = userService.getUserPrefGenre(user_no);
		session.setAttribute("target_user_id", user_id);
		return list;
	}

	// 유저별 별점 가져오기
	@RequestMapping(value = "{target_user_id}/avg_stars", method = RequestMethod.GET, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public List<StarPointVO> getUserAvgStars(@PathVariable String target_user_id) throws Exception {
		
		long user_no = userService.getUserNo(target_user_id);
		return userService.getUserAvgStars(user_no);
	}

	// 그전에 유저별 좋아요 리스트를 앨범/아티스트/노래 링크
	@RequestMapping(value = "{user_id}/list_recommend", method = RequestMethod.GET, produces = "text/json")
	@ResponseBody
	public String getUserRecList(HttpSession session) throws Exception {
		Long user_no = Long.valueOf(session.getAttribute("user_no").toString());
		// List<RecommendVO> list = userService.getUserRecList(user_no);
		return "recommend/user_rec_list";
	}
	
	//유저별 노래 플레이타임 총합 가져오기
	@RequestMapping(value = "{target_user_id}/playtime", method=RequestMethod.GET, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public long getUserPlaytime(@PathVariable String target_user_id) throws Exception{
		Long user_no = Long.valueOf(userService.getUserNo(target_user_id));		
		return userService.getUserPlaytime(user_no);
	}
	
	//유저별 평가수 가져오기
	@RequestMapping(value = "{target_user_id}/rec_count", method=RequestMethod.GET, produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public long getUserRecCount(@PathVariable String target_user_id) throws Exception{
		long user_no = Long.valueOf(userService.getUserNo(target_user_id));
		return userService.getUserRecCount(user_no);
	}
	
	//유저 평가리스트 가져오기
		@RequestMapping(value="{target_user_id}/rec_list", method=RequestMethod.GET, produces="Application/json; charset=UTF-8")
		@ResponseBody
		public Map<String, Object> getUserRecommendList (@PathVariable String target_user_id) throws Exception{
			long user_no = userService.getUserNo(target_user_id);
			
			List<SongVO> songList = userService.getUserRecommendList(user_no);
			List<AlbumVO> albumList = userService.getUserRecommendListAlbum(user_no);
				
			Map<String, Object> mapData = new HashMap<String, Object>();
			
			mapData.put("songList", songList);
			mapData.put("albumList", albumList);
			
			return mapData;
		}
		
		@RequestMapping(value="usermusiclist/{user_id}/{pg}", method=RequestMethod.POST, produces={"Application/json; charset=UTF-8"})
		@ResponseBody
		public Map<String, Object> getUserMusicList(@PathVariable long pg, @PathVariable String user_id){
			Map<String, Object> mapData = new HashMap<String,Object>();
			
			try {
				long user_no = userService.getUserNo(user_id);
				long user_recommend_total_count = recommendService.getUserRecommendTotalCount(user_no);
				
				PageNation pgNation = new PageNation(pg, 21, 10, user_recommend_total_count);
				mapData.put("pgNation", pgNation);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pgNation", pgNation);
				map.put("user_no", user_no);
				List<SongVO> songVO = operationService.getUserSongList(map);
				
				mapData.put("list", songVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return mapData;
		}
}
