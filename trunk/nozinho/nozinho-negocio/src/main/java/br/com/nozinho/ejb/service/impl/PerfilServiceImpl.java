package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.PerfilDAO;
import br.com.nozinho.ejb.service.PerfilService;
import br.com.nozinho.model.Perfil;

@Stateless
public class PerfilServiceImpl extends GenericServiceImpl<Perfil, Long> implements PerfilService{

	@Override
	public List<Perfil> findPerfis() {
		return ((PerfilDAO) getDAO()).findPerfis();
	}

	@Override
	public Perfil findByIdFetchPermissao(Long id) {
		return ((PerfilDAO) getDAO()).findByIdFetchPermissao(id);
	}

}
