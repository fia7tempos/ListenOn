package net.musicrecommend.www.operation;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.recommend.RecommendVO;
import net.musicrecommend.www.vo.AlbumChartVO;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.GenreVO;
import net.musicrecommend.www.vo.SongChartVO;
import net.musicrecommend.www.vo.SongVO;

public interface OperationService {

	void insertArtist(ArtistVO artistVO) throws Exception;

	boolean checkArtistId(ArtistVO artistVO) throws Exception;

	boolean checkAlbumId(AlbumVO albumVO) throws Exception;

	void insertAlbum(AlbumVO albumVO) throws Exception;	

	void insertSong(SongVO songVO) throws Exception;

	long checkSong(long song_key) throws Exception;

	List<GenreVO> getGenreList() throws Exception;

	List<String> getImageList() throws Exception;	

	List<SongVO> getSongList() throws Exception;

	List<AlbumVO> getAlbumList() throws Exception;

	List<SongChartVO> getSongChartList() throws Exception;

	List<AlbumChartVO> getAlbumChartList() throws Exception;

	void insertSearchKeyword(String searchKeyword) throws Exception;

	List<SongVO> getUserSongList(Map<String, Object> map) throws Exception;

}
