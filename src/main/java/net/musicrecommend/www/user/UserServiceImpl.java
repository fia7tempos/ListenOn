package net.musicrecommend.www.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.GenreCntVO;
import net.musicrecommend.www.vo.PerferArtistVO;
import net.musicrecommend.www.vo.SongVO;
import net.musicrecommend.www.vo.StarPointVO;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	UserMapper userMapper;

	@Override
	public void insertUser(UserVO userVO) throws Exception {
		
		userMapper.insertUser(userVO);
		
	}

	@Override
	public int getUserTotalCount() throws Exception {

		return userMapper.getUserTotalCount();
	}

	@Override
	public List<UserVO> selectUserList(PageNation pageNation) throws Exception {
		
		return userMapper.selectUserList(pageNation);
	}

	@Override
	public int checkCacaoUser(UserVO cacaoUser) throws Exception {
		return userMapper.checkCacaoUser(cacaoUser);
	}

	@Override
	public void insertCacaoUser(UserVO userVO) throws Exception {
		userMapper.insertCacaoUser(userVO);
	}

	@Override
	public UserVO checkUser(UserVO user) throws Exception {
		return userMapper.checkUser(user);
	}

	@Override
	public int idCheck(String user_id) throws Exception {
		return userMapper.idCheck(user_id);
	}

	@Override
	public long getUserNo(String user_id) throws Exception {
		
		return userMapper.getUserNo(user_id);
	}

	@Override
	public List<PerferArtistVO> getPrefer_Artist(long user_no) throws Exception {
		
		return userMapper.getPrefer_Artist(user_no);
	}

	@Override
	public List<GenreCntVO> getUserPrefGenre(long user_no) throws Exception {
		//쿼리 날려서 받아온 genre_names 스트링 콤마단위루 잘라서 빈도 계산하자
		List<String> list = userMapper.getUserPrefGenre(user_no);
		Map<String,Long> map = new HashMap<String, Long>();
		
		StringTokenizer tokenizer = null;
		for(String s: list){
			tokenizer = new StringTokenizer(s,",");
			while(tokenizer.hasMoreTokens()){
				String key = tokenizer.nextToken();
				map.put(key, map.containsKey(key)? map.get(key)+1:1);
			}		
		}
		List<GenreCntVO> resultList = new ArrayList<GenreCntVO>();
		Set<String> keySet = map.keySet();		
		for(String key : keySet){
			GenreCntVO genreCntVO = new GenreCntVO();
			genreCntVO.setGenreName(key);
			genreCntVO.setCnt(map.get(key));
			resultList.add(genreCntVO);
		}
		return resultList;
	}

	@Override
	public List<RecommendVO> getUserRecList(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StarPointVO> getUserAvgStars(long user_no) throws Exception {
		List<StarPointVO> list = userMapper.getUserAvgStars(user_no);
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(float i=0.5f;i<=5.0f;i+=0.5f){
			map.put(String.valueOf(i), 0);
			logger.info("map input:"+String.valueOf(i)+"map value:"+map.get(String.valueOf(i)));
		}
		
		for(int i=0;i<list.size();i++){
			map.put(String.valueOf(list.get(i).getStar_point()),list.get(i).getCnt());
			logger.info("map put:"+String.valueOf(i)+" "+list.get(i));
		}
		list = new ArrayList<StarPointVO>();
		
		for(float i=0.5f;i<=5.0f;i+=0.5f){
			StarPointVO sp = new StarPointVO();
			
			String key = String.valueOf(i);
			
			if(!map.get(key).equals(0)){
				Long value = Long.valueOf(map.get(key).toString());
				logger.info("value: "+value);
				sp.setCnt(value);
				sp.setStar_point(i);
			}else{
				sp.setCnt(0);
				sp.setStar_point(i);
			}
			list.add(sp);
		}
		
		return list; 
	}

	@Override
	public long getUserPlaytime(Long user_no) throws Exception {
		
		return userMapper.getUserPlaytime(user_no)*3;
	}

	@Override
	public long getUserRecCount(long user_no) throws Exception {
		return userMapper.getUserRecCount(user_no);
	}

	@Override
	public UserVO getUserInfo(long user_no) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserInfo(user_no);
	}

	@Override
	public List<SongVO> getUserRecommendList(long user_no) throws Exception {
		return userMapper.getUserRecommendList(user_no);
	}

	@Override
	public List<AlbumVO> getUserRecommendListAlbum(long user_no) throws Exception {
		return userMapper.getUserRecommendListAlbum(user_no);
	}

	@Override
	public String addFollow(Map<String, Long> mapData) throws Exception {
		
		String msg = null;
		long isFollowCnt = userMapper.isFollow(mapData);
		
		if ( isFollowCnt == 0  ){
			userMapper.addFollow(mapData);
			msg = "팔로우하였습니다.";
		} else if (isFollowCnt > 0) {
			msg = "이미 등록한 팔로우입니다.";
		}
		
		return msg;
	}

	@Override
	public List<Map<String, Object>> getFollowerList(long user_no) throws Exception {
		return userMapper.getFollowerList(user_no);
	}

	@Override
	public List<Map<String, Object>> getFollowingList(long user_no) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getFollowingList(user_no);
	}

	@Override
	public String unFollow(Map<String, Object> mapData) throws Exception {
		String msg ="";
		if( userMapper.unFollow(mapData) == 1 ){
			msg = "언팔로우하였습니다.";
		} else {
			msg = "SYSTEM ERROR";
		}
		
		return msg;
	}

	@Override
	public List<UserVO> getSearchFollowList(String search_keyword) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getSearchFollowList(search_keyword);
	}
	




}
