package br.com.nozinho.model.charts;

import java.io.Serializable;

public class CategoryAxis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598211616031036170L;

	private String gridPosition;
	
	private int gridAlpha;
	
	private String tickPosition;
	
	private int tickLength;

	public String getGridPosition() {
		return gridPosition;
	}

	public void setGridPosition(String gridPosition) {
		this.gridPosition = gridPosition;
	}

	public int getGridAlpha() {
		return gridAlpha;
	}

	public void setGridAlpha(int gridAlpha) {
		this.gridAlpha = gridAlpha;
	}

	public String getTickPosition() {
		return tickPosition;
	}

	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}

	public int getTickLength() {
		return tickLength;
	}

	public void setTickLength(int tickLength) {
		this.tickLength = tickLength;
	}
	
}
