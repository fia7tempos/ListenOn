package net.musicrecommend.www.vo;

import java.io.Serializable;

public class StarPointVO implements Serializable {
	private double star_point;
	private long cnt;
	public double getStar_point() {
		return star_point;
	}
	public void setStar_point(double star_point) {
		this.star_point = star_point;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "StarPointVO [star_point=" + star_point + ", cnt=" + cnt + "]";
	}
	
}
