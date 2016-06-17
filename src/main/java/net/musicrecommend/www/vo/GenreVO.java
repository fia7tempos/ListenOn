package net.musicrecommend.www.vo;

import java.io.Serializable;

public class GenreVO implements Serializable{
	private long genre_no;
	private String genre_name;
	private String genre_key;
	public long getGenre_no() {
		return genre_no;
	}
	public void setGenre_no(long genre_no) {
		this.genre_no = genre_no;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public String getGenre_key() {
		return genre_key;
	}
	public void setGenre_key(String genre_key) {
		this.genre_key = genre_key;
	}
	@Override
	public String toString() {
		return "GenreVO [genre_no=" + genre_no + ", genre_name=" + genre_name + ", genre_key=" + genre_key + "]";
	}
	

	
	
}
