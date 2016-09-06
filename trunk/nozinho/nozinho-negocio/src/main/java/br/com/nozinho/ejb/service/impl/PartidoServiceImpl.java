package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.PartidoDAO;
import br.com.nozinho.ejb.service.PartidoService;
import br.com.nozinho.model.Partido;

@Stateless
public class PartidoServiceImpl extends GenericServiceImpl<Partido, Long> implements PartidoService{

	@Override
	public List<Partido> findBySiglaLike(String query) {
		return ((PartidoDAO)getDAO()).findBySiglaLike(query);
	}

}
