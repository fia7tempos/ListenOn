package net.musicrecommend.www.music;

import java.util.List;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.util.PageNation;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.SongVO;

public interface ArtistMapper {

	int getArtistTotalCount() throws Exception;

	List<ArtistVO> selectArtistList() throws Exception;

	ArtistVO getArtistInfo(ArtistVO artistVO) throws Exception;

	SongVO getSongInfo(RecommendVO recommendVO) throws Exception;

	AlbumVO getAlbumInfo(RecommendVO recommendVO) throws Exception;

	
	

}
