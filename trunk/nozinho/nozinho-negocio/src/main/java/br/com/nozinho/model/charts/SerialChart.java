package br.com.nozinho.model.charts;

import java.util.ArrayList;
import java.util.List;


public class SerialChart extends BaseChart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6520245532315958789L;

	private List<ValueAxes> valueAxes;
	
	private boolean gridAboveGraphs;
	
	private int startDuration;
	
	private String categoryField;
	
	private List<Graphs> graphs;
	
	private ChartCursor chartCursor;
	
	private CategoryAxis categoryAxis;
	
	public List<ValueAxes> getValueAxes() {
		if(valueAxes == null){
			valueAxes = new ArrayList<ValueAxes>();
		}
		return valueAxes;
	}

	public void setValueAxes(List<ValueAxes> valueAxes) {
		this.valueAxes = valueAxes;
	}

	public boolean isGridAboveGraphs() {
		return gridAboveGraphs;
	}

	public void setGridAboveGraphs(boolean gridAboveGraphs) {
		this.gridAboveGraphs = gridAboveGraphs;
	}

	public int getStartDuration() {
		return startDuration;
	}

	public void setStartDuration(int startDuration) {
		this.startDuration = startDuration;
	}

	public String getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}

	public List<Graphs> getGraphs() {
		if(graphs == null){
			graphs = new ArrayList<Graphs>();
		}
		return graphs;
	}

	public void setGraphs(List<Graphs> graphs) {
		this.graphs = graphs;
	}

	public ChartCursor getChartCursor() {
		return chartCursor;
	}

	public void setChartCursor(ChartCursor chartCursor) {
		this.chartCursor = chartCursor;
	}

	public CategoryAxis getCategoryAxis() {
		return categoryAxis;
	}

	public void setCategoryAxis(CategoryAxis categoryAxis) {
		this.categoryAxis = categoryAxis;
	}
	
}
