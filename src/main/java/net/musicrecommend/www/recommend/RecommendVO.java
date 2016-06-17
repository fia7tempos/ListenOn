package net.musicrecommend.www.recommend;

import java.io.Serializable;

public class RecommendVO implements Serializable{
	private long recommend_no;
	private long user_no;
	private long album_key;
	private long song_key;
	private long artist_key;
	private double star_point;
	private String comment;
	private String user_comment;
	
	public String getUser_comment() {
		return user_comment;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	public long getRecommend_no() {
		return recommend_no;
	}
	public void setRecommend_no(long recommend_no) {
		this.recommend_no = recommend_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	
	public long getAlbum_key() {
		return album_key;
	}
	public void setAlbum_key(long album_key) {
		this.album_key = album_key;
	}
	public long getSong_key() {
		return song_key;
	}
	public void setSong_key(long song_key) {
		this.song_key = song_key;
	}
	public long getArtist_key() {
		return artist_key;
	}
	public void setArtist_key(long artist_key) {
		this.artist_key = artist_key;
	}
	public double getStar_point() {
		return star_point;
	}
	public void setStar_point(double star_point) {
		this.star_point = star_point;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "RecommendVO [recommend_no=" + recommend_no + ", user_no=" + user_no + ", album_key=" + album_key
				+ ", song_key=" + song_key + ", artist_key=" + artist_key + ", star_point=" + star_point + ", comment="
				+ comment + ", user_comment=" + user_comment + "]";
	}

}
