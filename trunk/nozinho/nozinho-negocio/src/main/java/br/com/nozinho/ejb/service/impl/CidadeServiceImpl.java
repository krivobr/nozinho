package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.CidadeDAO;
import br.com.nozinho.ejb.service.CidadeService;
import br.com.nozinho.model.Cidade;

@Stateless
public class CidadeServiceImpl extends GenericServiceImpl<Cidade, Long> implements CidadeService{

	@Override
	public List<Cidade> findByNomeLike(String nome) {
		return ((CidadeDAO) getDAO()).findByNomeLike(nome);
	}

}
