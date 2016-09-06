package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.PermissaoDAO;
import br.com.nozinho.ejb.service.PermissaoService;
import br.com.nozinho.model.Permissao;

@Stateless
public class PermissaoServiceImpl extends GenericServiceImpl<Permissao, Long> implements PermissaoService{

	@Override
	public List<Permissao> findPermissoes() {
		return ((PermissaoDAO)getDAO()).findPermissoes();
	}

}
