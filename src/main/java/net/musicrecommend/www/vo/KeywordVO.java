package net.musicrecommend.www.vo;

import java.io.Serializable;
import java.util.Date;

public class KeywordVO implements Serializable{
	 private long keyword_no; 
	 private String keyword; 
	 private long count;
	 private Date regdate;
	 
	 
	 
	
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public long getKeyword_no() {
		return keyword_no;
	}
	public void setKeyword_no(long keyword_no) {
		this.keyword_no = keyword_no;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "KeywordVO [keyword_no=" + keyword_no + ", keyword=" + keyword + ", count=" + count + ", regdate="
				+ regdate + "]";
	}
	
}
