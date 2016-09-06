package br.com.nozinho.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.ContatoEndereco;
import br.com.nozinho.model.TipoEnderecoEnum;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;

@ManagedBean
@ViewScoped
public class ContatoEnderecoController extends GenericControllerImpl<ContatoEndereco, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3130513684355338465L;

	@Override
	protected GenericService<ContatoEndereco, Long> getService() {
		return null;
	}

	public TipoEnderecoEnum[] getTiposEnderecoEnum(){		
		return TipoEnderecoEnum.values();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new ContatoEndereco());
	}
	
}
