package br.com.nozinho.model.charts;

import java.io.Serializable;

public class Export implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7978454209237379565L;
	
	private boolean enabled;
	
	public Export(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
