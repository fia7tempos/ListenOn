package net.musicrecommend.www.starpoint;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.musicrecommend.www.user.UserVO;
import net.musicrecommend.www.vo.SongVO;

@Service
public class StarpointServiceImpl implements StarpointService {
	private static final Logger logger = LoggerFactory.getLogger(StarpointServiceImpl.class);

	@Inject
	StarpointMapper starpointMapper;
	
	/*@Override
	public List<String> getGenrename() throws Exception {
		return starpointMapper.getGenrename();
	}*/
	
	@Override
	public long topsongCount(String user_id) throws Exception {
		return starpointMapper.topsongCount(user_id);
	}

	@Override
	public List<SongVO> topsongList(Map<String, Object> map) throws Exception {
		return starpointMapper.topsongList(map);
	}

	@Override
	public long hitsongCount(String user_id) throws Exception {
		return starpointMapper.hitsongCount(user_id);
	}

	@Override
	public List<SongVO> hitsongList(Map<String, Object> map) throws Exception {
		return starpointMapper.hitsongList(map);
	}

	@Override
	public long titlesongCount(String user_id) throws Exception {
		return starpointMapper.titlesongCount(user_id);
	}

	@Override
	public List<SongVO> titlesongList(Map<String, Object> map) throws Exception {
		return starpointMapper.titlesongList(map);
	}	

	@Override
	public long freesongCount(String user_id) throws Exception {
		return starpointMapper.freesongCount(user_id);
	}

	@Override
	public List<SongVO> freesongList(Map<String, Object> map) throws Exception {
		return starpointMapper.freesongList(map);
	}

	@Override
	public long balladCount(String user_id) throws Exception {
		return starpointMapper.balladCount(user_id);
	}

	@Override
	public List<SongVO> balladList(Map<String, Object> map) throws Exception {
		return starpointMapper.balladList(map);
	}

	@Override
	public List<SongVO> danceList(Map<String, Object> map) throws Exception {
		return starpointMapper.danceList(map);
	}

	@Override
	public long danceCount(String user_id) throws Exception {
		return starpointMapper.danceCount(user_id);
	}

	@Override
	public long dramaCount(String user_id) throws Exception {
		return starpointMapper.dramaCount(user_id);
	}

	@Override
	public List<SongVO> dramaList(Map<String, Object> map) throws Exception {
		return starpointMapper.dramaList(map);
	}

	@Override
	public long popCount(String user_id) throws Exception {
		return starpointMapper.popCount(user_id);
	}

	@Override
	public List<SongVO> popList(Map<String, Object> map) throws Exception {
		return starpointMapper.popList(map);
	}

	@Override
	public long movieCount(String user_id) throws Exception {
		return starpointMapper.movieCount(user_id);
	}

	@Override
	public List<SongVO> movieList(Map<String, Object> map) throws Exception {
		return starpointMapper.movieList(map);
	}

	@Override
	public long animationCount(String user_id) throws Exception {
		return starpointMapper.animationCount(user_id);
	}

	@Override
	public List<SongVO> animationList(Map<String, Object> map) throws Exception {
		return starpointMapper.animationList(map);
	}

	@Override
	public long musicalCount(String user_id) throws Exception {
		return starpointMapper.musicalCount(user_id);
	}

	@Override
	public List<SongVO> musicalList(Map<String, Object> map) throws Exception {
		return starpointMapper.musicalList(map);
	}

	@Override
	public long electronicaCount(String user_id) throws Exception {
		return starpointMapper.electronicaCount(user_id);
	}

	@Override
	public List<SongVO> electronicaList(Map<String, Object> map) throws Exception {
		return starpointMapper.electronicaList(map);
	}

	@Override
	public long rockCount(String user_id) throws Exception {
		return starpointMapper.rockCount(user_id);
	}

	@Override
	public List<SongVO> rockList(Map<String, Object> map) throws Exception {
		return starpointMapper.rockList(map);
	}

	@Override
	public long soulCount(String user_id) throws Exception {
		return starpointMapper.soulCount(user_id);
	}

	@Override
	public List<SongVO> soulList(Map<String, Object> map) throws Exception {
		return starpointMapper.soulList(map);
	}

	@Override
	public long rapCount(String user_id) throws Exception {
		return starpointMapper.rapCount(user_id);
	}
	
	@Override
	public List<SongVO> rapList(Map<String, Object> map) throws Exception {
		return starpointMapper.rapList(map);
	}

	@Override
	public long jazzCount(String user_id) throws Exception {
		return starpointMapper.jazzCount(user_id);
	}

	@Override
	public List<SongVO> jazzList(Map<String, Object> map) throws Exception {
		return starpointMapper.jazzList(map);
	}

	@Override
	public long newageCount(String user_id) throws Exception {
		return starpointMapper.newageCount(user_id);
	}

	@Override
	public List<SongVO> newageList(Map<String, Object> map) throws Exception {
		return starpointMapper.newageList(map);
	}

	@Override
	public long ccmCount(String user_id) throws Exception {
		return starpointMapper.ccmCount(user_id);
	}

	@Override
	public List<SongVO> ccmList(Map<String, Object> map) throws Exception {
		return starpointMapper.ccmList(map);
	}

	@Override
	public long worldCount(String user_id) throws Exception {
		return starpointMapper.worldCount(user_id);
	}

	@Override
	public List<SongVO> worldList(Map<String, Object> map) throws Exception {
		return starpointMapper.worldList(map);
	}


}
