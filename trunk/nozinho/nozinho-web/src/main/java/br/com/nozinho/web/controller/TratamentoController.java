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
import br.com.nozinho.ejb.service.TratamentoService;
import br.com.nozinho.model.Tratamento;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class TratamentoController extends GenericControllerImpl<Tratamento, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3991823672150924952L;
	
	private static final String REDIRECT_TO = "list-tratamentos.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	@EJB
	private TratamentoService service;

	@Override
	protected GenericService<Tratamento, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		if(getEntidade().getId() == null){
			this.salvar();
		} else {
			this.merge();
		}
		this.setEntidade(new Tratamento());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Tratamento());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Tratamento> listIterator = new ArrayList<Tratamento>(getEntidades());
		for(Tratamento e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Tratamento());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

}
