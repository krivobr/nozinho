package br.com.nozinho.model.charts;

public class PieChart extends BaseChart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6596335845334600071L;
	
	private String valueField;
	
	private String titleField;
	
	private Balloon balloon;
	
	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTitleField() {
		return titleField;
	}

	public void setTitleField(String titleField) {
		this.titleField = titleField;
	}

	public Balloon getBalloon() {
		return balloon;
	}

	public void setBalloon(Balloon balloon) {
		this.balloon = balloon;
	}
	
}
