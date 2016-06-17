package net.musicrecommend.www.vo;

import java.io.Serializable;

public class AlbumChartVO implements Serializable{
	private long album_no;
	private long album_key;
	private String album_name;
	private long artist_key;
	private String artist_name;
	private float average_score;
	private String issue_date;
	private String album_img;
	private long song_key;
	private String song_name;
	private long song_current_rank;
	private long song_past_rank;
	private String album_type;
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
	public long getSong_key() {
		return song_key;
	}
	public void setSong_key(long song_key) {
		this.song_key = song_key;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public long getSong_current_rank() {
		return song_current_rank;
	}
	public void setSong_current_rank(long song_current_rank) {
		this.song_current_rank = song_current_rank;
	}
	public long getSong_past_rank() {
		return song_past_rank;
	}
	public void setSong_past_rank(long song_past_rank) {
		this.song_past_rank = song_past_rank;
	}
	public String getAlbum_type() {
		return album_type;
	}
	public void setAlbum_type(String album_type) {
		this.album_type = album_type;
	}
	@Override
	public String toString() {
		return "AlbumChartVO [album_no=" + album_no + ", album_key=" + album_key + ", album_name=" + album_name
				+ ", artist_key=" + artist_key + ", artist_name=" + artist_name + ", average_score=" + average_score
				+ ", issue_date=" + issue_date + ", album_img=" + album_img + ", song_key=" + song_key + ", song_name="
				+ song_name + ", song_current_rank=" + song_current_rank + ", song_past_rank=" + song_past_rank
				+ ", album_type=" + album_type + "]";
	}
	

	
	
}
