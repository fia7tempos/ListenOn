package net.musicrecommend.www.operation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.musicrecommend.www.vo.AlbumChartVO;
import net.musicrecommend.www.vo.AlbumVO;
import net.musicrecommend.www.vo.ArtistVO;
import net.musicrecommend.www.vo.GenreVO;
import net.musicrecommend.www.vo.KeywordVO;
import net.musicrecommend.www.vo.SongChartVO;
import net.musicrecommend.www.vo.SongVO;

@Service
public class OperationServiceImpl implements OperationService{
	private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);
	
	@Autowired 
	OperationMapper operationMapper;

	@Override
	public void insertArtist(ArtistVO artistVO) throws Exception {
		if(operationMapper.checkArtistId(artistVO)>0){
			operationMapper.updateArtist(artistVO);
		}else{
			operationMapper.insertArtist(artistVO);
		}
	}

	@Override
	public boolean checkArtistId(ArtistVO artistVO) throws Exception {
		if(operationMapper.checkArtistId(artistVO)>0){
			return true;
		}else{					
			return false;
		}
	}

	@Override
	public boolean checkAlbumId(AlbumVO albumVO) throws Exception {
		
		if(operationMapper.checkAlbumId(albumVO)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void insertAlbum(AlbumVO albumVO) throws Exception {		
		if(operationMapper.checkAlbumId(albumVO)>0){
			operationMapper.updateAlbum(albumVO);
		}else{
			operationMapper.insertAlbum(albumVO);
		}
	}

	@Override
	public void insertSong(SongVO songVO) throws Exception {
		if(operationMapper.checkSong(songVO.getSong_key())>0){
			operationMapper.updateSong(songVO);
		}else{
			operationMapper.insertSong(songVO);
		}
	}

	@Override
	public long checkSong(long song_key) throws Exception {
		
		return operationMapper.checkSong(song_key);
	}

	@Override
	public List<GenreVO> getGenreList() throws Exception {
		return operationMapper.getGenreList();
	}

	@Override
	public List<String> getImageList() throws Exception {
		return operationMapper.getImageList();
	}

	@Override
	public List<SongVO> getSongList() throws Exception {
		return operationMapper.getSongList();
	}

	@Override
	public List<AlbumVO> getAlbumList() throws Exception {
		return operationMapper.getAlbumList();
	}

	@Override
	public List<SongChartVO> getSongChartList() throws Exception {
		return operationMapper.getSongChartList();
	}

	@Override
	public List<AlbumChartVO> getAlbumChartList() throws Exception {
		return operationMapper.getAlbumChartList();
	}

	@Override
	public void insertSearchKeyword(String searchKeyword) throws Exception {
		KeywordVO keywordVO = operationMapper.getKeyword(searchKeyword);
		if(keywordVO==null){
			operationMapper.insertSearchKeyword(searchKeyword);
		}else{
			Calendar cal = Calendar.getInstance();
			int today = cal.get(Calendar.DAY_OF_MONTH);
			
			SimpleDateFormat format = new SimpleDateFormat("d");
			String regday = format.format(keywordVO.getRegdate());
			int daydiff = today - Integer.parseInt(regday);
			if(daydiff>1){
				operationMapper.deleteSearchKeyword(searchKeyword);
				operationMapper.insertSearchKeyword(searchKeyword);
			}else{
				operationMapper.updateSearchKeyword(searchKeyword);
			}
			
		}
	}

	@Override
	public List<SongVO> getUserSongList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return operationMapper.getUserSongList(map);
	}
}
