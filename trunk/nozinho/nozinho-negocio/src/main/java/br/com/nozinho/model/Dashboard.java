package br.com.nozinho.model;

import java.io.Serializable;

import br.com.nozinho.model.charts.PieChart;
import br.com.nozinho.model.charts.SerialChart;

public class Dashboard implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3475638487791699857L;

	private Long numDemandas15;
	
	private Long numDemandas30;
	
	private Long numDemandas60;
	
	private Long numDemandasMaior60;

	private Long totalDemandas;
	
	private SerialChart serialChart;
	
	private PieChart pieChart;

	public Long getNumDemandas15() {
		return numDemandas15;
	}

	public void setNumDemandas15(Long numDemandas15) {
		this.numDemandas15 = numDemandas15;
	}

	public Long getNumDemandas30() {
		return numDemandas30;
	}

	public void setNumDemandas30(Long numDemandas30) {
		this.numDemandas30 = numDemandas30;
	}

	public Long getNumDemandas60() {
		return numDemandas60;
	}

	public void setNumDemandas60(Long numDemandas60) {
		this.numDemandas60 = numDemandas60;
	}

	public Long getNumDemandasMaior60() {
		return numDemandasMaior60;
	}

	public void setNumDemandasMaior60(Long numDemandasMaior60) {
		this.numDemandasMaior60 = numDemandasMaior60;
	}

	public Long getTotalDemandas() {
		return totalDemandas;
	}

	public void setTotalDemandas(Long totalDemandas) {
		this.totalDemandas = totalDemandas;
	}

	public SerialChart getSerialChart() {
		return serialChart;
	}

	public void setSerialChart(SerialChart serialChart) {
		this.serialChart = serialChart;
	}

	public PieChart getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChart pieChart) {
		this.pieChart = pieChart;
	}
	
}
