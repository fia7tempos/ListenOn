package net.musicrecommend.www.recommend;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.DetailChartVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.RecommendUserVO;
import net.musicrecommend.www.vo.SongVO;

public interface RecommendService {
	boolean insertRecommend(RecommendVO recommendVO) throws Exception;
	
	
	long getTotalCount() throws Exception;

	List<RecommendVO> getUserRecommendList(long user_no) throws Exception;

	String insertComment(RecommendVO recommendVO) throws Exception;

	void insertNullalbum(SongVO songVO) throws Exception;

	List<SongVO> getNullalbmSongs() throws Exception;

	RecommendVO getUseRecommendInfo(long user_no) throws Exception;
	List<RecommendUserVO> getCommentList(Map<String, Object> mapData) throws Exception;


	List<DetailChartVO> getStarPointSubtotal(RecommendVO recommendVO) throws Exception;

	List<DetailChartVO> getStarPointAvg(RecommendVO recommendVO) throws Exception;

	List<Object> getMainPageList() throws Exception;

	List<KeywordVO> getSearchKeywordList() throws Exception;

	List<ArtistVO> getMainPageDownList() throws Exception;

	List<ArtistVO> getMainPageDownList(String string) throws Exception;

	List<SongVO> getXCenturySongs(String year) throws Exception;

	List<Map<String, Object>> getRecommendRank() throws Exception;

	List<SongVO> getAdultSongList() throws Exception;
	long getTotalCommentCount(RecommendVO recommendVO) throws Exception;

	
	List<SongVO> getSurvivalSongList() throws Exception;

	long getRecommendCount(long user_no) throws Exception;
	
	// 권우\\
	long recommend_State_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_State_List(Map<String, Object> map) throws Exception;

	long recommend_Group_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_Group_List(Map<String, Object> map) throws Exception;

	long recommend_Solo_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_Solo_List(Map<String, Object> map) throws Exception;

	long recommend_Keyword_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_Keyword_List(Map<String, Object> map) throws Exception;


	long recommend_Singer_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_Singer_List(Map<String, Object> map) throws Exception;

	long recommend_playTime_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_playTime_List(Map<String, Object> map) throws Exception;

	long recommend_issueDate_Count(Map<String, Object> map) throws Exception;

	List<SongVO> recommend_issueDate_List(Map<String, Object> map) throws Exception;
	
	long getUserRecommendTotalCount(long user_no) throws Exception;

}
