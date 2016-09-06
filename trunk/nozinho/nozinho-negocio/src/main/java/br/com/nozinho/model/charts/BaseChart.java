package br.com.nozinho.model.charts;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseChart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7533174595522246835L;

	private String type;
	
	private String theme;
	
	private List<Map<String, String>> dataProvider;
	
	private Export export;
	
	public BaseChart(){
		this.export = new Export(false);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<Map<String, String>> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(List<Map<String, String>> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public Export getExport() {
		return export;
	}

	public void setExport(Export export) {
		this.export = export;
	}

}
