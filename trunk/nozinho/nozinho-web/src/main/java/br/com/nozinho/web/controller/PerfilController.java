package br.com.nozinho.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.PerfilService;
import br.com.nozinho.ejb.service.PermissaoService;
import br.com.nozinho.model.Perfil;
import br.com.nozinho.model.Permissao;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class PerfilController extends GenericControllerImpl<Perfil, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4985798514551447768L;
	@EJB
	private PerfilService service;
	
	@EJB
	private PermissaoService permissaoService;
	
	private static final String REDIRECT_TO = "list-perfis.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private List<Permissao> selectedPermissions;
	
	private List<Permissao> listPermissoes = new ArrayList<>();
	
	private boolean selectAll;
	
	@Override
	protected GenericService<Perfil, Long> getService() {
		return service;
	}

	public String processaEntidade(){
		getEntidade().setPermissoes(selectedPermissions);
		if(getEntidade().getId() == null){
			this.salvar();
		} else {
			this.merge();
		}
		this.setEntidade(new Perfil());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("list-perfis");
		return REDIRECT_TO;
	}
	
	public void prepareUpdate(ActionEvent e){
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPerfil") != null){
			Long id = new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPerfil"));
			setEntidade(service.findByIdFetchPermissao(id));
			getSelectedPermissions().clear();
			for(Permissao permissao : listPermissoes){
				if(getEntidade().getPermissoes().contains(permissao)){
					getSelectedPermissions().add(permissao);
				}
			}
			setSelectAll(getSelectedPermissions().size() == listPermissoes.size());							
		}
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Perfil());
		getSelectedPermissions().clear();
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Perfil> listIterator = new ArrayList<Perfil>(getEntidades());
		for(Perfil e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Perfil());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	@Override
	public List<Perfil> getEntidades() {
		if(this.entidades == null){
			this.entidades = service.findPerfis(); 
		}
		return this.entidades;
	}

	public List<Permissao> getPermissoes(){
		if(listPermissoes == null || listPermissoes.isEmpty()){
			listPermissoes = permissaoService.findPermissoes();
		}
		return listPermissoes;
	}

	public List<Permissao> getSelectedPermissions() {
		if(selectedPermissions == null){
			selectedPermissions = new ArrayList<>();
		}
		return selectedPermissions;
	}

	public void setSelectedPermissions(List<Permissao> selectedPermissions) {
		this.selectedPermissions = selectedPermissions;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public void changeCheckBox(){
		if(this.selectAll){
			this.selectedPermissions = getPermissoes();
		}else{
			this.selectedPermissions.clear();
		}
	}
}
