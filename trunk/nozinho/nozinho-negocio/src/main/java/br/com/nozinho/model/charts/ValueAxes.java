package br.com.nozinho.model.charts;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValueAxes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1614256331918683033L;

	private String gridColor;
	
	private BigDecimal gridAlpha;
	
	private Integer dashLength;

	public String getGridColor() {
		return gridColor;
	}

	public void setGridColor(String gridColor) {
		this.gridColor = gridColor;
	}

	public BigDecimal getGridAlpha() {
		return gridAlpha;
	}

	public void setGridAlpha(BigDecimal gridAlpha) {
		this.gridAlpha = gridAlpha;
	}

	public Integer getDashLength() {
		return dashLength;
	}

	public void setDashLength(Integer dashLength) {
		this.dashLength = dashLength;
	}
	
}
