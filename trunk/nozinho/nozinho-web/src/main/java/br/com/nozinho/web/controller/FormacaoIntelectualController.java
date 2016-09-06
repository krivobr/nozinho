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

import br.com.nozinho.ejb.service.FormacaoIntelectualService;
import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.FormacaoIntelectual;
import br.com.nozinho.web.callers.FormacaoIntelectualCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class FormacaoIntelectualController extends GenericControllerImpl<FormacaoIntelectual, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5815342694255712663L;

	private static final String REDIRECT_TO = "list-fintelectual.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	@EJB
	private FormacaoIntelectualService service;
	
	private FormacaoIntelectualCaller caller;

	@Override
	protected GenericService<FormacaoIntelectual, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		this.saveUpdate();
		this.setEntidade(new FormacaoIntelectual());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalFintelec').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}

	public String processaEntidadeAtalho(){
		caller.setFormacaoIntelectual(this.saveUpdate());
		this.setEntidades(null);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalFintelec').hide()");
		return "";
	}
	
	private FormacaoIntelectual saveUpdate(){
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
		setEntidade(new FormacaoIntelectual());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<FormacaoIntelectual> listIterator = new ArrayList<FormacaoIntelectual>(getEntidades());
		for(FormacaoIntelectual e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new FormacaoIntelectual());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public FormacaoIntelectualCaller getCaller() {
		return caller;
	}

	public void setCaller(FormacaoIntelectualCaller caller) {
		this.caller = caller;
	}
	
}
