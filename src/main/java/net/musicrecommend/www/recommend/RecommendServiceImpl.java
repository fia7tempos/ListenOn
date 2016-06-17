package net.musicrecommend.www.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.musicrecommend.www.user.UserService;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.DetailChartVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.RecommendUserVO;
import net.musicrecommend.www.vo.SongVO;

@Service
public class RecommendServiceImpl implements RecommendService {
	private static final Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);
	@Autowired
	RecommendMapper recommendMapper;
	@Autowired
	UserService userService;

	@Override
	public boolean insertRecommend(RecommendVO recommendVO) throws Exception {
		// 이미 입력된 평가가 있을시 업데이트
		if (recommendMapper.checkRecommendExist(recommendVO) > 0) {
			if (recommendMapper.updateRecommend(recommendVO) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (recommendMapper.insertRecommend(recommendVO) > 0) {
				return true;
			} else {
				return false;
			}

		}
	}

	@Override
	public long getTotalCount() throws Exception {
		return recommendMapper.getTotalCount();
	}

	@Override
	public List<RecommendVO> getUserRecommendList(long user_no) throws Exception {
		// 유저별 평가 목록 가져오기
		return recommendMapper.getUserRecommendList(user_no);
	}

	@Override
	public String insertComment(RecommendVO recommendVO) throws Exception {
		String msg = "";

		if (recommendMapper.checkCommentExist(recommendVO) > 0) {
			if (recommendMapper.updateUserComment(recommendVO) > 0) {
				msg = "등록 완료";
			}
		} else {
			msg = "평가를 먼저 해주세요!";
		}

		return msg;

	}

	@Override
	public List<SongVO> getNullalbmSongs() throws Exception {
		return recommendMapper.getNullalbmSongs();

	}

	@Override
	public void insertNullalbum(SongVO songVO) throws Exception {
		recommendMapper.insertNullalbum(songVO);
	}

	@Override
	public RecommendVO getUseRecommendInfo(long user_no) throws Exception {
		// TODO Auto-generated method stub
		return recommendMapper.getUseRecommendInfo(user_no);
	}

	@Override
	public List<RecommendUserVO> getCommentList(Map<String, Object> mapData) throws Exception {
		return recommendMapper.getCommentList(mapData);

	}

	@Override
	public List<DetailChartVO> getStarPointSubtotal(RecommendVO recommendVO) throws Exception {
		return recommendMapper.getStarPointSubtotal(recommendVO);
	}

	@Override
	public List<DetailChartVO> getStarPointAvg(RecommendVO recommendVO) throws Exception {

		return recommendMapper.getStarPointAvg(recommendVO);
	}

	@Override
	public List<Object> getMainPageList() throws Exception {

		List<SongVO> songlist = recommendMapper.getHighRatedSongs();
		List<AlbumVO> albumlist = recommendMapper.getHighRatedAlbums();
		List<ArtistVO> artistlist = recommendMapper.getHighRatedArtists();
		List<Object> list = new ArrayList<Object>();
		list.addAll(songlist);
		list.addAll(albumlist);
		list.addAll(artistlist);
		return list;
	}

	@Override
	public List<KeywordVO> getSearchKeywordList() throws Exception {
		return recommendMapper.getSearchKeywordList();
	}

	@Override
	public List<ArtistVO> getMainPageDownList() throws Exception {
		List<ArtistVO> resultList = new LinkedList<ArtistVO>();
		List<ArtistVO> danceList = recommendMapper.getHighRatedArtistsDefault("dance");
		List<ArtistVO> rockList = recommendMapper.getHighRatedArtistsDefault("rock");
		List<ArtistVO> balladList = recommendMapper.getHighRatedArtistsDefault("ballad");
		List<ArtistVO> dramaList = recommendMapper.getHighRatedArtistsDefault("drama");
		List<ArtistVO> rnBList = recommendMapper.getHighRatedArtistsDefault("R&B");
		List<ArtistVO> popList = recommendMapper.getHighRatedArtistsDefault("pop");
		List<ArtistVO> movieList = recommendMapper.getHighRatedArtistsDefault("movie");
		List<ArtistVO> newageList = recommendMapper.getHighRatedArtistsDefault("new age");
		List<ArtistVO> concertoList = recommendMapper.getHighRatedArtistsDefault("concerto");
		List<ArtistVO> jazzList = recommendMapper.getHighRatedArtistsDefault("jazz");
		for (int i = 0; i < 3; i++) {
			resultList.add(danceList.get(i));
			resultList.add(rockList.get(i));
			resultList.add(balladList.get(i));
			resultList.add(dramaList.get(i));
			resultList.add(rnBList.get(i));
			resultList.add(popList.get(i));
			resultList.add(movieList.get(i));
			resultList.add(newageList.get(i));
			resultList.add(concertoList.get(i));
			resultList.add(jazzList.get(i));
		}

		return resultList;
	}

	@Override
	public List<ArtistVO> getMainPageDownList(String user_id) throws Exception {
		List<ArtistVO> resultList = new LinkedList<ArtistVO>();
		List<ArtistVO> defaultList = getMainPageDownList();
		List<List<ArtistVO>> sourceLists = new ArrayList<List<ArtistVO>>();
		String[] genre_names = { "dance", "rock", "ballad", "drama", "R&B", "pop", "movie", "new age", "concerto",
				"jazz" };
		long user_no = userService.getUserNo(user_id);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_no", user_no);
		param.put("genre", "dance");

		for (int i = 0; i < 10; i++) {
			param.remove("genre");
			param.put("genre", genre_names[i]);
			sourceLists.add(recommendMapper.getHighRatedArtistsUser(param));
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0, k = 0; j < sourceLists.get(i).size(); j++) {
				List<ArtistVO> thisList = sourceLists.get(i);
				for (ArtistVO a : thisList) {
				}
				if (k > 2)
					break;

				boolean isContainKey = false;
				for (ArtistVO a : resultList) {
					if (a.getArtist_key() == thisList.get(j).getArtist_key()) {
						isContainKey = true;
						break;
					}
				}
				if (!isContainKey) {
					thisList.get(j).setGenre_names(genre_names[i]);
					resultList.add(thisList.get(j));
					k++;
				}
			}
		}
		return resultList;
	}

	@Override
	public List<SongVO> getXCenturySongs(String year) throws Exception {

		return recommendMapper.getXCenturySongs(year);
	}

	@Override
	public List<Map<String, Object>> getRecommendRank() throws Exception {
		return recommendMapper.getRecommendRank();
	}

	@Override
	public List<SongVO> getAdultSongList() throws Exception {
		return recommendMapper.getAdultSongList();
	}

	@Override
	public List<SongVO> getSurvivalSongList() throws Exception {
		return recommendMapper.getSurvivalSongList();
	}	
	

	@Override
	public long getTotalCommentCount(RecommendVO recommendVO) throws Exception {
		return recommendMapper.getTotalCommentCount(recommendVO);
	}

	@Override
	public long getRecommendCount(long user_no) throws Exception {
		return recommendMapper.getRecommendCount(user_no);
	}

	// 권우\\
	@Override
	public long recommend_State_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_State_Count(map);
	}
	@Override
	public List<SongVO> recommend_State_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_State_List(map);
	}
	@Override
	public long recommend_Group_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Group_Count(map);
	}
	@Override
	public List<SongVO> recommend_Group_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Group_List(map);
	}
	@Override
	public long recommend_Solo_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Solo_Count(map);
	}
	@Override
	public List<SongVO> recommend_Solo_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Solo_List(map);
	}
	@Override
	public long recommend_Keyword_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Keyword_Count(map);
	}
	@Override
	public List<SongVO> recommend_Keyword_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Keyword_List(map);
	}
	@Override
	public long recommend_Singer_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Singer_Count(map);
	}
	@Override
	public List<SongVO> recommend_Singer_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_Singer_List(map);
	}
	@Override
	public long recommend_playTime_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_playTime_Count(map);
	}
	@Override
	public List<SongVO> recommend_playTime_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_playTime_List(map);
	}
	@Override
	public long recommend_issueDate_Count(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_issueDate_Count(map);
	}
	@Override
	public List<SongVO> recommend_issueDate_List(Map<String, Object> map) throws Exception {
		return recommendMapper.recommend_issueDate_List(map);
	}

	@Override
	public long getUserRecommendTotalCount(long user_no) throws Exception {
		// TODO Auto-generated method stub
		return recommendMapper.getUserRecommendTotalCount(user_no);
	}
	
}
