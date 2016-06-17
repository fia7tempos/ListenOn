package net.musicrecommend.www.vo;

public class PerferArtistVO {
	private String artist_name;
	private long cnt;
	private String artist_key;
	
	@Override
	public String toString() {
		return "PerferArtistVO [artist_name=" + artist_name + ", cnt=" + cnt + ", artist_key=" + artist_key + "]";
	}
	public String getArtist_key() {
		return artist_key;
	}
	public void setArtist_key(String artist_key) {
		this.artist_key = artist_key;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	
	
	
}
