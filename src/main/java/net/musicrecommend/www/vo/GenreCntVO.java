package net.musicrecommend.www.vo;

import java.io.Serializable;

public class GenreCntVO implements Serializable {
	private String GenreName;
	private long cnt;
	
	public String getGenreName() {
		return GenreName;
	}

	public void setGenreName(String genreName) {
		GenreName = genreName;
	}

	public long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "GenreCntVO [GenreName=" + GenreName + ", cnt=" + cnt + "]";
	}
}
