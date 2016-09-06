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
import br.com.nozinho.ejb.service.TipoDemandaService;
import br.com.nozinho.model.TipoDemanda;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class TipoDemandaController extends GenericControllerImpl<TipoDemanda, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4923572161192422860L;
	@EJB
	private TipoDemandaService service;
	
	private static final String REDIRECT_TO = "list-tipodemanda.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	@Override
	protected GenericService<TipoDemanda, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		if(getEntidade().getId() == null){
			this.salvar();
		} else {
			this.merge();
		}
		setEntidade(new TipoDemanda());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new TipoDemanda());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<TipoDemanda> listIterator = new ArrayList<TipoDemanda>(getEntidades());
		for(TipoDemanda e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new TipoDemanda());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

}
