package br.com.nozinho.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.UsuarioService;
import br.com.nozinho.model.Usuario;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@SessionScoped
public class LoginController extends GenericControllerImpl<Usuario, Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711392875824408361L;
	
	@EJB
	private UsuarioService service;

	@Override
	protected GenericService<Usuario, Long> getService() {
		return service;
	}

	public String login(){
		try{
			((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).logout();
			((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).login(getEntidade().getLogin(), getEntidade().getSenha());
			setEntidade(service.findByLoginAndSenha(getEntidade().getLogin(), getEntidade().getSenha()));
			return "/app/index.xhtml?redirect=true";
		}
//		catch(BusinessException e){
//			MessagesUtil.addErrorMessage(e.getMessage());
//			return "";
//		} 
		catch (ServletException e) {
			setEntidade(new Usuario());
			e.printStackTrace();
			MessagesUtil.addErrorMessage("login.verifique.usuario.senha");
			return "";
		}
	}
	
	public String logout(){
		try {
			((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).logout();
			((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/index.xhtml?redirect=true";		
//		try {
//			((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendRedirect(path);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return path;
	}
	
	public StreamedContent getFoto(){
		if(getEntidade().getFoto() != null){
			StreamedContent content = new DefaultStreamedContent(new ByteArrayInputStream(getEntidade().getFoto()), "image/png");
			return content;
		}
		return null;
	}

}
