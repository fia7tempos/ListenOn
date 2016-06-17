package net.musicrecommend.www.user;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserVO implements Serializable{
	
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_gender;
	private String user_nick;
	private String user_img; //카카오계정 이미지 저장용
		
	private String user_uuid;//일반계정 이미지 저장용
	
	private CommonsMultipartFile user_img_file;

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = DigestUtils.sha512Hex(user_pw);;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
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

	public CommonsMultipartFile getUser_img_file() {
		return user_img_file;
	}

	public void setUser_img_file(CommonsMultipartFile user_img_file) {
		this.user_img_file = user_img_file;
	}

	@Override
	public String toString() {
		return "UserVO [user_no=" + user_no + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_gender="
				+ user_gender + ", user_nick=" + user_nick + ", user_img=" + user_img + ", user_uuid=" + user_uuid
				+ ", user_img_file=" + user_img_file + "]";
	}
	
	
	
	
	
}
