/**
 * 
 */
package br.com.nozinho.web.framwork.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.Entidade;
import br.com.nozinho.web.framwork.GenericController;
import br.com.nozinho.web.messages.MessagesUtil;

/**
 * @author AndreLA
 *
 */
@SuppressWarnings("unchecked")
public abstract class GenericControllerImpl<T extends Entidade, C extends Serializable> implements GenericController<T, C> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4682376750479665795L;

	private Class<T> entidadeClass = null;

	private T entidade;

	protected List<T> entidades;
	
	protected List<T> filteredEntidades;

	protected abstract GenericService<T, C> getService();
	
	private boolean isVisualiza;

	public T getEntidade() {
		if (entidade == null) {
			try {
				entidade = getEntidadeClass().newInstance();
				inicializaObjetoNovo();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public List<T> getEntidades() {
		if (this.entidades == null) {
			recuperarTodos();
		}
		return this.entidades;
	}

	public void setEntidades(List<T> entidades) {
		this.entidades = entidades;
	}		

	public List<T> getFilteredEntidades() {
		return filteredEntidades;
	}

	public void setFilteredEntidades(List<T> filteredEntidades) {
		this.filteredEntidades = filteredEntidades;
	}

	public boolean isVisualiza() {
		return isVisualiza;
	}

	public void setVisualiza(boolean isVisualiza) {
		this.isVisualiza = isVisualiza;
	}

	public T recuperarPorId(Object id) {
		this.entidade = getService().recuperarPorId(id);
		return this.entidade;
	}

	public List<T> recuperarTodos() {
		this.entidades = getService().recuperarTodos();
		return this.entidades;
	}

	public void remover() {
		try{
			getService().remover(this.entidade);
			if(!MessagesUtil.existeMensagem()){
				MessagesUtil.addSuccessMessage("registro.apagado");
			}
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("message.apagar.erro");
		}
	}

	public T merge() {
		this.entidade = getService().merge(this.entidade);
		MessagesUtil.addSuccessMessage("registro.atualizado");
		return this.entidade;
	}

	public T salvar() {
		this.entidade = getService().salvar(entidade);
		MessagesUtil.addSuccessMessage("registro.salvo");
		return this.entidade;
	}

	public T salvarClear() {
		this.entidade = getService().salvar(entidade);
		MessagesUtil.addSuccessMessage("registro.salvo");
		return this.entidade;
	}

	public List<T> salvarLista() {
		this.entidades = getService().salvarLista(this.entidades);
		MessagesUtil.addSuccessMessage("registros.salvos");
		return this.entidades;
	}

	protected Class<T> getEntidadeClass() {
		if (entidadeClass == null) {
			this.entidadeClass =
					(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entidadeClass;

	}
	
	protected void inicializaObjetoNovo() {

	}
	
	@PostConstruct
	public void iniciaEntidadeEdicao(){
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("entidade") != null){
			this.setEntidade((T) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("entidade"));
		}
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("visualiza") != null){
			this.setVisualiza((Boolean) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("visualiza"));
		}
	}
	
	public String telaEdicao(){
		return getTelaCRUD(false);
	}
	
	public String telaVisao(){
		return getTelaCRUD(true);
	}
	
	private String getTelaCRUD(Boolean visualiza){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("entidade",getEntidade());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("visualiza",visualiza);
		return "./crud/index.xhtml";		
	}
}
