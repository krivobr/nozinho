package br.com.nozinho.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.ContatoForma;
import br.com.nozinho.model.FormaContatoEnum;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;

@ManagedBean
@ViewScoped
public class ContatoFormaController extends GenericControllerImpl<ContatoForma, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086955381497136660L;

	@Override
	protected GenericService<ContatoForma, Long> getService() {
		return null;
	}
	
	public FormaContatoEnum[] getTiposContato(){
		return FormaContatoEnum.values();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new ContatoForma());
	}

}
