package net.musicrecommend.www.starpoint;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.user.UserVO;
import net.musicrecommend.www.vo.SongVO;

public interface StarpointService {

	/* List<String> getGenrename() throws Exception; */

	long hitsongCount(String user_id) throws Exception;

	List<SongVO> hitsongList(Map<String, Object> map) throws Exception;

	long titlesongCount(String user_id) throws Exception;

	List<SongVO> titlesongList(Map<String, Object> map) throws Exception;

	long topsongCount(String user_id) throws Exception;

	List<SongVO> topsongList(Map<String, Object> map) throws Exception;

	List<SongVO> freesongList(Map<String, Object> map) throws Exception;

	long freesongCount(String user_id) throws Exception;

	long balladCount(String user_id) throws Exception;

	List<SongVO> balladList(Map<String, Object> map) throws Exception;

	List<SongVO> danceList(Map<String, Object> map) throws Exception;

	long danceCount(String user_id) throws Exception;

	long dramaCount(String user_id) throws Exception;

	List<SongVO> dramaList(Map<String, Object> map) throws Exception;

	long popCount(String user_id) throws Exception;

	List<SongVO> popList(Map<String, Object> map) throws Exception;

	long movieCount(String user_id) throws Exception;

	List<SongVO> movieList(Map<String, Object> map) throws Exception;

	long animationCount(String user_id) throws Exception;

	List<SongVO> animationList(Map<String, Object> map) throws Exception;

	long musicalCount(String user_id) throws Exception;

	List<SongVO> musicalList(Map<String, Object> map) throws Exception;

	List<SongVO> electronicaList(Map<String, Object> map) throws Exception;

	long electronicaCount(String user_id) throws Exception;

	long rockCount(String user_id) throws Exception;

	List<SongVO> rockList(Map<String, Object> map) throws Exception;

	long soulCount(String user_id) throws Exception;

	List<SongVO> soulList(Map<String, Object> map) throws Exception;

	long rapCount(String user_id) throws Exception;

	List<SongVO> rapList(Map<String, Object> map) throws Exception;

	long jazzCount(String user_id) throws Exception;

	List<SongVO> jazzList(Map<String, Object> map) throws Exception;

	long newageCount(String user_id) throws Exception;

	List<SongVO> newageList(Map<String, Object> map) throws Exception;

	long ccmCount(String user_id) throws Exception;

	List<SongVO> ccmList(Map<String, Object> map) throws Exception;

	long worldCount(String user_id) throws Exception;

	List<SongVO> worldList(Map<String, Object> map) throws Exception;

}
