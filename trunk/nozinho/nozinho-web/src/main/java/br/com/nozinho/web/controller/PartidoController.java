package br.com.nozinho.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.PartidoService;
import br.com.nozinho.model.Partido;
import br.com.nozinho.web.callers.PartidoCaller;
import br.com.nozinho.web.framwork.GenericController;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class PartidoController extends GenericControllerImpl<Partido, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6451209038569086392L;

	@EJB
	private PartidoService service;
	
	private static final String REDIRECT_TO = "list-partidos.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private PartidoCaller caller;
	
	@Override
	protected GenericService<Partido, Long> getService() {
		return service;
	}

	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new Partido());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalPartido').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public String processaEntidadeAtalho(){
		caller.setPartido(saveUpdate());
		this.setEntidades(null);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalPartido').hide()");
		return "";
	}
	
	private Partido saveUpdate(){
		if(getEntidade().getId() == null){
			return this.salvar();
		} else {
			return this.merge();
		}
	}
	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Partido());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Partido> listIterator = new ArrayList<Partido>(getEntidades());
		for(Partido e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Partido());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public PartidoCaller getCaller() {
		return caller;
	}

	public void setCaller(PartidoCaller caller) {
		this.caller = caller;
	}
	
}
