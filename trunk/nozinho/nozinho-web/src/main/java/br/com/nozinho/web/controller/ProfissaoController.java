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
import br.com.nozinho.ejb.service.ProfissaoService;
import br.com.nozinho.model.Profissao;
import br.com.nozinho.web.callers.ProfissaoCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class ProfissaoController extends GenericControllerImpl<Profissao, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8727745094695782100L;
	
	private static final String REDIRECT_TO = "list-profissoes.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	@EJB
	private ProfissaoService service;
	
	private ProfissaoCaller caller;
	
	@Override
	protected GenericService<Profissao, Long> getService() {
		return service;
	}

	public String processaEntidade(){
		this.saveUpdate();
		this.setEntidade(new Profissao());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalProfissao').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public String processaEntidadeAtalho(){
		caller.setProfissao(this.saveUpdate());
		this.setEntidades(null);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalProfissao').hide()");
		return "";
	}
	
	private Profissao saveUpdate(){
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
		setEntidade(new Profissao());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Profissao> listIterator = new ArrayList<Profissao>(getEntidades());
		for(Profissao e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Profissao());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public ProfissaoCaller getCaller() {
		return caller;
	}

	public void setCaller(ProfissaoCaller caller) {
		this.caller = caller;
	}
	
}
