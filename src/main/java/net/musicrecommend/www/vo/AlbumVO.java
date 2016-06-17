package net.musicrecommend.www.vo;

import java.io.Serializable;

public class AlbumVO implements Serializable{
	
	
	
	private long album_no;
	private long album_key;
	private String album_name;
	private long artist_key;
	private String artist_name;
	private float average_score;
	private String issue_date;
	private String album_img;
	
	public long getAlbum_no() {
		return album_no;
	}
	public void setAlbum_no(long album_no) {
		this.album_no = album_no;
	}
	public long getAlbum_key() {
		return album_key;
	}
	public void setAlbum_key(long album_key) {
		this.album_key = album_key;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
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
	public float getAverage_score() {
		return average_score;
	}
	public void setAverage_score(float average_score) {
		this.average_score = average_score;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getAlbum_img() {
		return album_img;
	}
	public void setAlbum_img(String album_img) {
		this.album_img = album_img;
	}
	@Override
	public String toString() {
		return "AlbumVO [album_no=" + album_no + ", album_key=" + album_key + ", album_name=" + album_name
				+ ", artist_key=" + artist_key + ", artist_name=" + artist_name + ", average_score=" + average_score
				+ ", issue_date=" + issue_date + ", album_img=" + album_img + "]";
	} 
	

}
