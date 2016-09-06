package br.com.nozinho.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.UsuarioService;
import br.com.nozinho.model.Cargo;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.Usuario;
import br.com.nozinho.web.callers.CargoCaller;
import br.com.nozinho.web.callers.ContatoCaller;
import br.com.nozinho.web.callers.UsuarioCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class UsuarioController extends GenericControllerImpl<Usuario, Long> implements ContatoCaller, CargoCaller{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386918078082988611L;
	@EJB
	private UsuarioService service;
	
	private static final String REDIRECT_TO = "list-usuarios.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private UsuarioCaller caller;
		
	@Override
	protected GenericService<Usuario, Long> getService() {
		return service;
	}

	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new Usuario());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalUsuario').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}

	public String processaEntidadeAtalho(){
		caller.setUsuario(saveUpdate());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalUsuario').hide()");
		return "";
	}
	
	private Usuario saveUpdate(){
		if(getEntidade().getId() == null){
			getEntidade().setSenha("nozinho123");
			return this.salvar();
		} else {
			return this.merge();
		}
	}

	public String changeProfile(){
		if(getEntidade().getId() == null){
			this.salvar();
		} else {
			this.merge();
		}
		this.setEntidade(new Usuario());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('minhaContaModal').hide()");
		context.update("appForm");
		return "";
	}

	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Usuario());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Usuario> listIterator = new ArrayList<Usuario>(getEntidades());
		for(Usuario e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Usuario());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}
	
	@Override
	public List<Usuario> getEntidades() {
		if(this.entidades == null){
			this.entidades = service.findAllExceptAdm(); 
		}
		return this.entidades;
	}
	
	public void handleFileUpload(FileUploadEvent event){
		getEntidade().setFoto(event.getFile().getContents());
	}
	
	public StreamedContent getFoto() throws IOException{
		if(getEntidade().getFoto() != null){
			StreamedContent content = new DefaultStreamedContent(new ByteArrayInputStream(getEntidade().getFoto()), "image/png");
			return content;
		}
		return null;
	}

	@Override
	public void setContato(Contato contato) {
		getEntidade().setContato(contato);		
	}

	@Override
	public void setCargo(Cargo cargo) {
		getEntidade().setCargo(cargo);
	}
	
	public List<Usuario> findByNome(String query){
		return service.findByNomeContato(query);
	}

	@Override
	public void setContato(Contato contato, String property) {
		throw new NotImplementedException();		
	}

	public UsuarioCaller getCaller() {
		return caller;
	}

	public void setCaller(UsuarioCaller caller) {
		this.caller = caller;
	}
	
}
