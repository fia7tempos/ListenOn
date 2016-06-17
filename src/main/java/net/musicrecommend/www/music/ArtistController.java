package net.musicrecommend.www.music;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.SongVO;

@Controller
@RequestMapping(value="artist")
public class ArtistController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);
	
	@Autowired
	ArtistService artistService;
	
	@RequestMapping(value="list")
	public void articleList(Model model){
		List<ArtistVO> list = null;
		
		ArtistVO artistVO = new ArtistVO();
		
		try {
			list = artistService.selectArtistList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list.toString());
		 
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="get_album_song_info", method=RequestMethod.POST, produces={"Application/json; charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> getAlbumSongInfo(@ModelAttribute RecommendVO recommendVO){
		Map<String, Object> mapData = new HashMap<String,Object>();
		
			try {
				if(recommendVO.getSong_key() != 0){
					SongVO songVO = artistService.getSongInfo(recommendVO);
					mapData.put("songVO", songVO);
				}else{
					AlbumVO albumVO = artistService.getAlbumInfo(recommendVO);
					mapData.put("albumVO", albumVO);
					System.out.print("★ : " + albumVO.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return mapData;
	}
}
