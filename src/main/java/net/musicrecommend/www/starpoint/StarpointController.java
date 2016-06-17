package net.musicrecommend.www.starpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.musicrecommend.www.user.UserVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.SongVO;

@Controller
@RequestMapping("starPoint")
public class StarpointController {

	private static final Logger logger = LoggerFactory.getLogger(StarpointController.class);

	@Inject
	StarpointService starpointService;

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void mainPage() throws Exception {

		/*
		 * List<String> list = starpointService.getGenrename();
		 * 
		 * model.addAttribute("list", list);
		 * 
		 * logger.info("장르 리스트 확인 : " + list);
		 */

	}

	// 메인 페이지&평균평점 TOP 출력\\
	@RequestMapping(value = "top", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> topSong(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.topsongCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.topsongList(map);

		logger.info("페이지 네이션 : " + pgNation);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("TOP곡 리스트 : " + list.toString());

		return mapData;

	}

	// 히트곡 출력\\
	@RequestMapping(value = "hitsong", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> hitSong(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.hitsongCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.hitsongList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("히트곡 리스트 : " + list.toString());

		return mapData;

	}

	// 타이틀곡 출력\\
	@RequestMapping(value = "titlesong", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> titleSong(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.titlesongCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.titlesongList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("타이틀곡 리스트 : " + list.toString());

		return mapData;

	}

	// 무료곡 출력\\
	@RequestMapping(value = "freesong", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> freeSong(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.freesongCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.freesongList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("무료곡 리스트 : " + list.toString());

		return mapData;

	}

	// 댄스\\
	@RequestMapping(value = "dance", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dance(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.danceCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.danceList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("발라드 리스트 : " + list.toString());

		return mapData;

	}

	// 발라드\\
	@RequestMapping(value = "ballad", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ballad(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.balladCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.balladList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("발라드 리스트 : " + list.toString());

		return mapData;

	}

	// POP\\
	@RequestMapping(value = "pop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pop(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.popCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.popList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("팝 리스트 : " + list.toString());

		return mapData;

	}

	// 영화\\
	@RequestMapping(value = "movie", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> movie(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.movieCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.movieList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("영화ost 리스트 : " + list.toString());

		return mapData;

	}

	// 드라마\\
	@RequestMapping(value = "drama", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> drama(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.dramaCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.dramaList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("드라마ost 리스트 : " + list.toString());

		return mapData;

	}

	// 애니메이션\\
	@RequestMapping(value = "animation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> animation(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.animationCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.animationList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("애니메이션ost 리스트 : " + list.toString());

		return mapData;

	}

	// 뮤지컬\\
	@RequestMapping(value = "musical", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> musical(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.musicalCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.musicalList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("뮤지컬 음악 리스트 : " + list.toString());

		return mapData;

	}

	// 일렉트로니카\\
	@RequestMapping(value = "electronica", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> electronia(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.electronicaCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.electronicaList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("일렉트로니카 리스트 : " + list.toString());

		return mapData;

	}

	// 락\\
	@RequestMapping(value = "rock", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rock(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.rockCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.rockList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("락 리스트 : " + list.toString());

		return mapData;

	}

	// R&B/소울\\
	@RequestMapping(value = "soul", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> soul(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.soulCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.soulList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("R&B 리스트 : " + list.toString());

		return mapData;

	}

	// 랩 / 힙합\\
	@RequestMapping(value = "rap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rap(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.rapCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.rapList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("랩/힙합 리스트 : " + list.toString());

		return mapData;

	}

	// 재즈\\
	@RequestMapping(value = "jazz", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jazz(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.jazzCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.jazzList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("재즈 리스트 : " + list.toString());

		return mapData;

	}

	// 뉴에이지\\
	@RequestMapping(value = "newage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> newage(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.newageCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.newageList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("뉴에이지 리스트 : " + list.toString());

		return mapData;

	}

	// CCM\\
	@RequestMapping(value = "ccm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ccm(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.ccmCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.ccmList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("ccm 리스트 : " + list.toString());

		return mapData;

	}

	// 월드뮤직\\
	@RequestMapping(value = "world", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> world(HttpSession session, @RequestParam int page) throws Exception {

		String user_id = (String) session.getAttribute("user_id");
		Map<String, Object> mapData = new HashMap<String, Object>();

		long totalCount = starpointService.worldCount(user_id);
		PageNation pgNation = new PageNation(page, 21, 21, totalCount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("start_num", pgNation.getStart_num());
		map.put("end_num", pgNation.getEnd_num());

		List<SongVO> list = starpointService.worldList(map);

		mapData.put("list", list);
		mapData.put("pgNation", pgNation);

		logger.info("ccm 리스트 : " + list.toString());

		return mapData;

	}
}
