package net.musicrecommend.www.vo;

import java.io.Serializable;

public class DetailChartVO implements Serializable{
	
	private double avg_star_point;
	private double star_point;
	private long cnt;
	
	
	public double getAvg_star_point() {
		return avg_star_point;
	}
	public void setAvg_star_point(double avg_star_point) {
		this.avg_star_point = avg_star_point;
	}
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
	
	
}
