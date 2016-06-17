package net.musicrecommend.www.util;

import java.util.List;

import net.musicrecommend.www.vo.AlbumVO;

public class PagedAlbumList {
	private List<AlbumVO> list = null;
	private PageNation pageNation = null;
	
	public List<AlbumVO> getList() {
		return list;
	}
	public void setList(List<AlbumVO> list) {
		this.list = list;
	}
	public PageNation getPageNation() {
		return pageNation;
	}
	public void setPageNation(PageNation pageNation) {
		this.pageNation = pageNation;
	}
	public PagedAlbumList(){
	}
	public PagedAlbumList(List<AlbumVO> list, PageNation pageNation) {
		this.list = list;
		this.pageNation = pageNation;
	}
}
