package br.com.nozinho.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class Listener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2476897416811793356L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("After phase: " + event.getPhaseId().toString());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Before phase: " + event.getPhaseId().toString());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
