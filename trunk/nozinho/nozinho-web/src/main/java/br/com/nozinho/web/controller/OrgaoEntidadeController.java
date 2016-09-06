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
import br.com.nozinho.ejb.service.OrgaoEntidadeService;
import br.com.nozinho.model.OrgaoEntidade;
import br.com.nozinho.web.callers.OrgaoEntidadeCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class OrgaoEntidadeController extends GenericControllerImpl<OrgaoEntidade, Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8537446901034206571L;
	@EJB
	private OrgaoEntidadeService service;

	private static final String REDIRECT_TO = "list-orgaoentidade.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private OrgaoEntidadeCaller caller;
	
	@Override
	protected GenericService<OrgaoEntidade, Long> getService() {
		return service;
	}

	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new OrgaoEntidade());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalOrgao').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}

	public String processaEntidadeAtalho(){
		caller.setOrgaoEntidade(saveUpdate());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalOrgao').hide()");
		return "";
	}
	
	private OrgaoEntidade saveUpdate(){
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
		setEntidade(new OrgaoEntidade());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<OrgaoEntidade> listIterator = new ArrayList<OrgaoEntidade>(getEntidades());
		for(OrgaoEntidade e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new OrgaoEntidade());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}
	
	public List<OrgaoEntidade> findOrgaosByQuery(String query){
		return service.findByNameLike(query);
	}

	public OrgaoEntidadeCaller getCaller() {
		return caller;
	}

	public void setCaller(OrgaoEntidadeCaller caller) {
		this.caller = caller;
	}
	
}
