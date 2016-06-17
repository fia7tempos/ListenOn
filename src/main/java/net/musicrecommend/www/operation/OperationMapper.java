package net.musicrecommend.www.operation;

import java.util.List;
import java.util.Map;

import net.musicrecommend.www.vo.AlbumChartVO;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.GenreVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.SongChartVO;
import net.musicrecommend.www.vo.SongVO;

public interface OperationMapper {

	void insertArtist(ArtistVO artistVO) throws Exception;

	int checkArtistId(ArtistVO artistVO) throws Exception;

	int checkAlbumId(AlbumVO albumVO) throws Exception;

	void insertAlbum(AlbumVO albumVO) throws Exception;
	
	//은석
	void insertSong(SongVO songVO) throws Exception;

	long checkSong(long song_key) throws Exception;

	List<GenreVO> getGenreList() throws Exception;

	void updateAlbum(AlbumVO albumVO) throws Exception;

	void updateArtist(ArtistVO artistVO) throws Exception;

	void updateSong(SongVO songVO) throws Exception;

	List<String> getImageList() throws Exception;

	List<SongVO> getSongList() throws Exception;

	List<AlbumVO> getAlbumList() throws Exception;

	List<SongChartVO> getSongChartList() throws Exception;

	List<AlbumChartVO> getAlbumChartList() throws Exception;

	void insertSearchKeyword(String searchKeyword) throws Exception;

	KeywordVO getKeyword(String searchKeyword) throws Exception;

	void updateSearchKeyword(String searchKeyword) throws Exception;

	List<SongVO> getUserSongList(Map<String, Object> map) throws Exception;

	void deleteSearchKeyword(String searchKeyword) throws Exception;
}
