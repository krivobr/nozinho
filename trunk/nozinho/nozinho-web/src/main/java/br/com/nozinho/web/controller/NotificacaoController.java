package br.com.nozinho.web.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.NotificacaoService;
import br.com.nozinho.model.Notificacao;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;

@ManagedBean
@ViewScoped
public class NotificacaoController extends GenericControllerImpl<Notificacao, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4860611142620480345L;

	@EJB
	private NotificacaoService service;
	
	@ManagedProperty(value = "#{loginController}")
	private LoginController loginController;
	
	private Long numNaoLidas;
	
	@Override
	protected GenericService<Notificacao, Long> getService() {
		return service;
	}
	
	@Override
	public List<Notificacao> getEntidades() {
		if(this.entidades == null){
			this.entidades = service.findByUsuarioNotificado(loginController.getEntidade());
		}
		return this.entidades;
	}
	
	public void atualizaNotificacoes(){
		this.entidades.clear();
		this.entidades = null;
		this.numNaoLidas = null;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public Long getNumNaoLidas() {
		if(numNaoLidas == null){
			numNaoLidas = service.findNumNotificacaoByUsuario(loginController.getEntidade());
		}
		return numNaoLidas;
	}

	public void setNumNaoLidas(Long numNaoLidas) {
		this.numNaoLidas = numNaoLidas;
	}
	
	public void updateNotificacaoAsLida(ActionEvent e) throws IOException{
		String idNotificacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idNotificacao");
		Notificacao notificacao = service.recuperarPorId(Long.parseLong(idNotificacao));
		notificacao.setLida(true);
		setEntidade(notificacao);
		this.merge();
		switch(notificacao.getTipoNotificacao()){
			case DEMANDA:
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/app/demanda/list-demandas.xhtml?idDemanda=" + notificacao.getIdObjeto());
				break;
				case AGENDA:
					break;
				default:
					break;
		}
	}
	
}
