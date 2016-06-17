package net.musicrecommend.www.vo;

import java.io.Serializable;

public class ArtistVO implements Serializable {

	private long artist_no;
	private long artist_key;
	private String artist_name;
	private String sex;
	private String nationality_name;
	private String act_type_name;
	private String genre_names;
	private String artist_img;
	
	public long getArtist_no() {
		return artist_no;
	}
	public void setArtist_no(long artist_no) {
		this.artist_no = artist_no;
	}
	public long getArtist_key() {
		return artist_key;
	}
	public void setArtist_key(long artist_key) {
		this.artist_key = artist_key;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNationality_name() {
		return nationality_name;
	}
	public void setNationality_name(String nationality_name) {
		this.nationality_name = nationality_name;
	}
	public String getAct_type_name() {
		return act_type_name;
	}
	public void setAct_type_name(String act_type_name) {
		this.act_type_name = act_type_name;
	}
	public String getGenre_names() {
		return genre_names;
	}
	public void setGenre_names(String genre_names) {
		this.genre_names = genre_names;
	}
	public String getArtist_img() {
		return artist_img;
	}
	@Override
	public String toString() {
		return "ArtistVO [artist_no=" + artist_no + ", artist_key=" + artist_key + ", artist_name=" + artist_name
				+ ", sex=" + sex + ", nationality_name=" + nationality_name + ", act_type_name=" + act_type_name
				+ ", genre_names=" + genre_names + ", artist_img=" + artist_img + "]";
	}
	public void setArtist_img(String artist_img) {
		this.artist_img = artist_img;
	}
	
	
}
