package br.com.nozinho.model.charts;

import java.io.Serializable;

public class Balloon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9187661117026052286L;
	
	private boolean fixedPosition;

	public Balloon(boolean fixedPosition){
		this.fixedPosition = fixedPosition;
	}
	
	public boolean isFixedPosition() {
		return fixedPosition;
	}

	public void setFixedPosition(boolean fixedPosition) {
		this.fixedPosition = fixedPosition;
	}

}
