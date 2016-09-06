package br.com.nozinho.model.charts;

import java.io.Serializable;

public class ChartCursor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3729156268006670998L;

	private boolean categoryBalloonEnabled;
	
	private int cursorAlpha;
	
	private boolean zoomable;

	public boolean isCategoryBalloonEnabled() {
		return categoryBalloonEnabled;
	}

	public void setCategoryBalloonEnabled(boolean categoryBalloonEnabled) {
		this.categoryBalloonEnabled = categoryBalloonEnabled;
	}

	public int getCursorAlpha() {
		return cursorAlpha;
	}

	public void setCursorAlpha(int cursorAlpha) {
		this.cursorAlpha = cursorAlpha;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}
	
}
