package br.com.nozinho.model.charts;

import java.io.Serializable;
import java.math.BigDecimal;

public class Graphs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5759015342256584653L;

	private String balloonText;
	
	private BigDecimal fillAlphas;
	
	private BigDecimal lineAlpha;
	
	private String type;
	
	private String valueField;

	public String getBalloonText() {
		return balloonText;
	}

	public void setBalloonText(String balloonText) {
		this.balloonText = balloonText;
	}

	public BigDecimal getFillAlphas() {
		return fillAlphas;
	}

	public void setFillAlphas(BigDecimal fillAlphas) {
		this.fillAlphas = fillAlphas;
	}

	public BigDecimal getLineAlpha() {
		return lineAlpha;
	}

	public void setLineAlpha(BigDecimal lineAlpha) {
		this.lineAlpha = lineAlpha;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	
}
