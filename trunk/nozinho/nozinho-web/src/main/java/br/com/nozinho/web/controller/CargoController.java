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

import br.com.nozinho.ejb.service.CargoService;
import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.Cargo;
import br.com.nozinho.web.callers.CargoCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class CargoController extends GenericControllerImpl<Cargo, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996103900144326168L;
	
	private static final String REDIRECT_TO = "list-cargosfuncao.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private CargoCaller caller;
	
	@EJB
	private CargoService service;
	
	@Override
	protected GenericService<Cargo, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new Cargo());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalCargo').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public String processaEntidadeAtalho(){
		caller.setCargo(saveUpdate());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalContato').hide()");
		return "";
	}
	
	private Cargo saveUpdate(){
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
		setEntidade(new Cargo());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Cargo> listIterator = new ArrayList<Cargo>(getEntidades());
		for(Cargo e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Cargo());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}	

	public List<Cargo> findByQuery(String query){
		return service.findByNomeLike(query);
	}

	public CargoCaller getCaller() {
		return caller;
	}

	public void setCaller(CargoCaller caller) {
		this.caller = caller;
	}
	
}
