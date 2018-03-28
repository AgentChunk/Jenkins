package com.revature.pojo;

public class GradingFormat {
	private int gId;
	private int presentation;
	private String format;
	private double cutoff;
	
	public GradingFormat() {};
	
	public GradingFormat(int gId, int presentation, String format, double cutoff) {
		super();
		this.gId = gId;
		this.presentation = presentation;
		this.format = format;
		this.cutoff = cutoff;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public int getPresentation() {
		return presentation;
	}

	public void setPresentation(int presentation) {
		this.presentation = presentation;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public double getCutoff() {
		return cutoff;
	}

	public void setCutoff(double cutoff) {
		this.cutoff = cutoff;
	}
	
	
}
