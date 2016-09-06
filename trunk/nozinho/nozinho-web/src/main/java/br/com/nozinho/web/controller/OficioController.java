package br.com.nozinho.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.NotImplementedException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.OficioService;
import br.com.nozinho.model.Oficio;
import br.com.nozinho.model.OrgaoEntidade;
import br.com.nozinho.web.callers.OficioCaller;
import br.com.nozinho.web.callers.OrgaoEntidadeCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class OficioController extends GenericControllerImpl<Oficio, Long> implements OrgaoEntidadeCaller{

	/**
	 * 
	 */
	private static final long serialVersionUID = 335929694168990545L;

	@EJB
	private OficioService service;
	
	private static final String REDIRECT_TO = "list-oficios.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private OficioCaller caller;
	
	private String property;
	
	private String update;
	
	
	@Override
	protected GenericService<Oficio, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new Oficio());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalOficio').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public String processaEntidadeAtalho(){		
		caller.setOficio(saveUpdate(), property);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalOficio').hide()");
		if(update != null){
			context.update(update);
		}
		return "";
	}
	
	private Oficio saveUpdate(){
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
		setEntidade(new Oficio());
		getEntidade().setNumero(service.getSugestaoNumeroOficio());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Oficio> listIterator = new ArrayList<Oficio>(getEntidades());
		for(Oficio e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Oficio());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}
	
	 public void handleFileUpload(FileUploadEvent event){
		 getEntidade().setDocumento(event.getFile().getContents());
	 }
	 
	 public List<Oficio> findByNumeroLike(String numero){
		 return service.findByNumeroLike(numero);
	 }

	@Override
	public void setOrgaoEntidade(OrgaoEntidade orgaoEntidade) {
		getEntidade().setOrgao(orgaoEntidade);
	}

	public OficioCaller getCaller() {
		return caller;
	}

	public void setCaller(OficioCaller caller) {
		this.caller = caller;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public void setOrgaoEntidade(OrgaoEntidade orgaoEntidade, String property) {
		throw new NotImplementedException();
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	
}
