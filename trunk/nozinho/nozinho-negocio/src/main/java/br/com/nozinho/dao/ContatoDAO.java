package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.TipoPessoaEnum;

@Local
public interface ContatoDAO extends GenericDAO<Contato, Long> {
	
	List<Contato> findByNameLike(String name);
	
	List<Contato> findByNameLikeAndType(String name, TipoPessoaEnum tipoPessoa);
	
	List<Contato> findAllFetchAll();
	
	Contato findByIdFetchAll(Long id);

}
