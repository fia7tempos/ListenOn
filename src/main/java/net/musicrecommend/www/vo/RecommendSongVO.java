package net.musicrecommend.www.vo;

import java.io.Serializable;

public class RecommendSongVO implements Serializable{
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
	
	
}
