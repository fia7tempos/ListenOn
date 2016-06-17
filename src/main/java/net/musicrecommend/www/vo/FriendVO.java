package net.musicrecommend.www.vo;

import java.io.Serializable;

public class FriendVO implements Serializable{
	
	private long friend_no;
	private long user_no;
	private long friend_user_no;
	
	public long getFriend_no() {
		return friend_no;
	}
	public void setFriend_no(long friend_no) {
		this.friend_no = friend_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public long getFriend_user_no() {
		return friend_user_no;
	}
	public void setFriend_user_no(long friend_user_no) {
		this.friend_user_no = friend_user_no;
	}
	@Override
	public String toString() {
		return "FriendVO [friend_no=" + friend_no + ", user_no=" + user_no + ", friend_user_no=" + friend_user_no + "]";
	}
	
	
}
