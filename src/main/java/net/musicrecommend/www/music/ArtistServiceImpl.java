package net.musicrecommend.www.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.SongVO;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	ArtistMapper artistMapper;

	@Override
	public int getArtistTotalCount() throws Exception {
		return artistMapper.getArtistTotalCount();
	}

	@Override
	public List<ArtistVO> selectArtistList() throws Exception {
		return artistMapper.selectArtistList();
	}

	@Override
	public ArtistVO getArtistInfo(ArtistVO artistVO) throws Exception {
		return artistMapper.getArtistInfo(artistVO);
	}

	@Override
	public SongVO getSongInfo(RecommendVO recommendVO) throws Exception {
		
		return artistMapper.getSongInfo(recommendVO);
	}

	@Override
	public AlbumVO getAlbumInfo(RecommendVO recommendVO) throws Exception {
		return artistMapper.getAlbumInfo(recommendVO);
	}

}
