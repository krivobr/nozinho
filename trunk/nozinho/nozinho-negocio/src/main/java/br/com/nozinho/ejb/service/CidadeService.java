package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Cidade;

@Local
public interface CidadeService extends GenericService<Cidade, Long>{
	
	List<Cidade> findByNomeLike(String nome);
	
}
