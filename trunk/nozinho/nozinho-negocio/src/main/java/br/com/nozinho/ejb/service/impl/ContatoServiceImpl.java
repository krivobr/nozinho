package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.ContatoDAO;
import br.com.nozinho.ejb.service.ContatoService;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.TipoPessoaEnum;

@Stateless
public class ContatoServiceImpl extends GenericServiceImpl<Contato, Long> implements ContatoService {

	@Override
	public List<Contato> findByNameLike(String name) {
		return ((ContatoDAO)getDAO()).findByNameLike(name);
	}

	@Override
	public List<Contato> findAllFetchAll() {
		return ((ContatoDAO)getDAO()).findAllFetchAll();
	}

	@Override
	public Contato findByIdFetchAll(Long id) {
		return ((ContatoDAO)getDAO()).findByIdFetchAll(id);
	}

	@Override
	public List<Contato> findPFByName(String name) {
		return ((ContatoDAO)getDAO()).findByNameLikeAndType(name, TipoPessoaEnum.PESSOA_FISICA);
	}

	@Override
	public List<Contato> findPJByName(String name) {
		return ((ContatoDAO)getDAO()).findByNameLikeAndType(name, TipoPessoaEnum.PESSOA_JURIDICA);
	}

}
