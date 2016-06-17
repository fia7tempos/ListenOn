package net.musicrecommend.www.recommend;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.DetailChartVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.RecommendUserVO;
import net.musicrecommend.www.vo.SongVO;

public interface RecommendMapper {

	int insertRecommend(RecommendVO recommendVO) throws Exception;

	int checkRecommendExist(RecommendVO recommendVO) throws Exception;

	int updateRecommend(RecommendVO recommendVO) throws Exception;

	long getTotalCount() throws Exception;

	List<RecommendVO> getUserRecommendList(long user_no) throws Exception;

	int insertComment(RecommendVO recommendVO) throws Exception;

	int checkCommentExist(RecommendVO recommendVO) throws Exception;

	List<SongVO> getNullalbmSongs() throws Exception;

	void insertNullalbum(SongVO songVO) throws Exception;

	int updateUserComment(RecommendVO recommendVO) throws Exception;

	RecommendVO getUseRecommendInfo(long user_no) throws Exception;

	List<RecommendUserVO> getCommentList(Map<String, Object> mapData) throws Exception;

	List<DetailChartVO> getStarPointSubtotal(RecommendVO recommendVO) throws Exception;

	List<DetailChartVO> getStarPointAvg(RecommendVO recommendVO) throws Exception;

	List<KeywordVO> getSearchKeywordList() throws Exception;

	List<SongVO> getHighRatedSongs() throws Exception;

	List<AlbumVO> getHighRatedAlbums() throws Exception;

	List<ArtistVO> getHighRatedArtists() throws Exception;

	List<ArtistVO> getHighRatedArtistsDefault(String genre) throws Exception;

	List<ArtistVO> getHighRatedArtistsUser(Map param) throws Exception;

	List<SongVO> getXCenturySongs(String year) throws Exception;

	List<Map<String, Object>> getRecommendRank() throws Exception;

	List<SongVO> getAdultSongList() throws Exception;
	
	List<SongVO> getSurvivalSongList() throws Exception;
	
	
	long getTotalCommentCount(RecommendVO recommendVO) throws Exception;

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
