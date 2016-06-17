package net.musicrecommend.www.vo;

import java.io.Serializable;

public class SearchVO implements Serializable {

	private long search_no;
	private String search_keyword;
	private String regdate;
	
	
	@Override
	public String toString() {
		return "SearchVO [search_no=" + search_no + ", search_keyword=" + search_keyword + ", regdate=" + regdate + "]";
	}
	public long getSearch_no() {
		return search_no;
	}
	public void setSearch_no(long search_no) {
		this.search_no = search_no;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
