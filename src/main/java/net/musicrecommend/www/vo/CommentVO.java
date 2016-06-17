package net.musicrecommend.www.vo;

import java.io.Serializable;

public class CommentVO implements Serializable {

	private long user_no;
	private String user_nick;
	private String user_img;
	private String comment;
	private double star_point;
	private String user_comment;
	

	@Override
	public String toString() {
		return "CommentVO [user_no=" + user_no + ", user_nick=" + user_nick + ", user_img=" + user_img + ", comment="
				+ comment + ", star_point=" + star_point + ", user_comment=" + user_comment + "]";
	}
	public String getUser_comment() {
		return user_comment;
	}
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getStar_point() {
		return star_point;
	}
	public void setStar_point(double star_point) {
		this.star_point = star_point;
	}
	
	
}
