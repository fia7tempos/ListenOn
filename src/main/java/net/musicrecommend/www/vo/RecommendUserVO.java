package net.musicrecommend.www.vo;

import java.io.Serializable;

public class RecommendUserVO implements Serializable{
	@Override
	public String toString() {
		return "RecommendUserVO [recommend_no=" + recommend_no + ", user_no=" + user_no + ", album_key=" + album_key
				+ ", song_key=" + song_key + ", artist_key=" + artist_key + ", star_point=" + star_point + ", comment="
				+ comment + ", user_comment=" + user_comment + ", user_id=" + user_id + ", user_gender=" + user_gender
				+ ", user_nick=" + user_nick + ", user_img=" + user_img + ", user_uuid=" + user_uuid + "]";
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
	public String getUser_comment() {
		return user_comment;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public long getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(long user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getUser_uuid() {
		return user_uuid;
	}
	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
	private long recommend_no;
	private long user_no;
	private long album_key;
	private long song_key;
	private long artist_key;
	private double star_point;
	private String comment;
	private String user_comment;
	private String user_id;
	private long user_gender;
	private String user_nick;
	private String user_img;
	private String user_uuid;
}
