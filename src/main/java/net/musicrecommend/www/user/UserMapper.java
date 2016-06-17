package net.musicrecommend.www.user;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.PerferArtistVO;
import net.musicrecommend.www.vo.SongVO;
import net.musicrecommend.www.vo.StarPointVO;

public interface UserMapper {

	void insertUser(UserVO userVO) throws Exception;

	int getUserTotalCount() throws Exception;

	List<UserVO> selectUserList(PageNation pageNation) throws Exception;

	int checkCacaoUser(UserVO cacaoUser) throws Exception;

	void insertCacaoUser(UserVO userVO) throws Exception;

	UserVO checkUser(UserVO user) throws Exception;

	int idCheck(String user_id) throws Exception;

	long getUserNo(String user_id) throws Exception;

	List<PerferArtistVO> getPrefer_Artist(long user_no) throws Exception;
	
	List<String> getUserPrefGenre(long user_no)throws Exception;

	List<StarPointVO> getUserAvgStars(long user_no) throws Exception;

	long getUserPlaytime(Long user_no) throws Exception;

	long getUserRecCount(long user_no) throws Exception;

	UserVO getUserInfo(long user_no) throws Exception;
	

	List<SongVO> getUserRecommendList(long user_no) throws Exception;

	List<AlbumVO> getUserRecommendListAlbum(long user_no) throws Exception;



	long isFollow(Map<String, Long> mapData) throws Exception;

	void addFollow(Map<String, Long> mapData) throws Exception;

	List<Map<String, Object>> getFollowerList(long user_no) throws Exception;

	List<Map<String, Object>> getFollowingList(long user_no) throws Exception;

	int unFollow(Map<String, Object> mapData) throws Exception;

	List<UserVO> getSearchFollowList(String search_keyword) throws Exception;

}
