package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Cidade;

@Local
public interface CidadeDAO extends GenericDAO<Cidade, Long> {
	
	List<Cidade> findByNomeLike(String nome);
	
}
