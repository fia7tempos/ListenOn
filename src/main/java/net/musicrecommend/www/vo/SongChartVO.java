package net.musicrecommend.www.vo;

import java.io.Serializable;

public class SongChartVO implements Serializable{
	private long song_no;
	private long song_key;
	private long play_time;
	private long artist_key;
	private String song_name;
	private long album_key;
	private String artist_name;
	private String issue_date;
	private boolean is_title_song;
	private boolean is_hit_song;
	private boolean is_adult;
	private boolean is_free;
	private String composition;
	private String lyricist;
	private String album_name;
	private String album_img;
	private long current_rank;
	private long past_rank;

	public String getAlbum_img() {
		return album_img;
	}
	public void setAlbum_img(String album_img) {
		this.album_img = album_img;
	}

	@Override
	public String toString() {
		return "SongChartVO [song_no=" + song_no + ", song_key=" + song_key + ", play_time=" + play_time
				+ ", artist_key=" + artist_key + ", song_name=" + song_name + ", album_key=" + album_key
				+ ", artist_name=" + artist_name + ", issue_date=" + issue_date + ", is_title_song=" + is_title_song
				+ ", is_hit_song=" + is_hit_song + ", is_adult=" + is_adult + ", is_free=" + is_free + ", composition="
				+ composition + ", lyricist=" + lyricist + ", album_name=" + album_name + ", album_img=" + album_img
				+ ", current_rank=" + current_rank + ", past_rank=" + past_rank + "]";
	}
	public long getSong_no() {
		return song_no;
	}
	public void setSong_no(long song_no) {
		this.song_no = song_no;
	}
	public long getSong_key() {
		return song_key;
	}
	public void setSong_key(long song_key) {
		this.song_key = song_key;
	}
	public long getPlay_time() {
		return play_time;
	}
	public void setPlay_time(long play_time) {
		this.play_time = play_time;
	}
	public long getArtist_key() {
		return artist_key;
	}
	public void setArtist_key(long artist_key) {
		this.artist_key = artist_key;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public long getAlbum_key() {
		return album_key;
	}
	public void setAlbum_key(long album_key) {
		this.album_key = album_key;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public boolean isIs_title_song() {
		return is_title_song;
	}
	public void setIs_title_song(boolean is_title_song) {
		this.is_title_song = is_title_song;
	}
	public boolean isIs_hit_song() {
		return is_hit_song;
	}
	public void setIs_hit_song(boolean is_hit_song) {
		this.is_hit_song = is_hit_song;
	}
	public boolean isIs_adult() {
		return is_adult;
	}
	public void setIs_adult(boolean is_adult) {
		this.is_adult = is_adult;
	}
	public boolean isIs_free() {
		return is_free;
	}
	public void setIs_free(boolean is_free) {
		this.is_free = is_free;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getLyricist() {
		return lyricist;
	}
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public long getCurrent_rank() {
		return current_rank;
	}
	public void setCurrent_rank(long current_rank) {
		this.current_rank = current_rank;
	}
	public long getPast_rank() {
		return past_rank;
	}
	public void setPast_rank(long past_rank) {
		this.past_rank = past_rank;
	}
	

	
	
	
}
