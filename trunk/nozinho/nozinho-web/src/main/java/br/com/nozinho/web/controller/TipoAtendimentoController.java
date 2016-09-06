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
import br.com.nozinho.ejb.service.TipoAtendimentoService;
import br.com.nozinho.model.TipoAtendimento;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class TipoAtendimentoController extends GenericControllerImpl<TipoAtendimento, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8601124917496047831L;
	
	private static final String REDIRECT_TO = "cadastro-tipoatendimento.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	
	@EJB
	private TipoAtendimentoService service;
	
	@Override
	protected GenericService<TipoAtendimento, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		if(getEntidade().getId() == null){
			this.salvar();
		} else {
			this.merge();
		}
		setEntidade(new TipoAtendimento());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new TipoAtendimento());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<TipoAtendimento> listIterator = new ArrayList<TipoAtendimento>(getEntidades());
		for(TipoAtendimento e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new TipoAtendimento());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}


}
