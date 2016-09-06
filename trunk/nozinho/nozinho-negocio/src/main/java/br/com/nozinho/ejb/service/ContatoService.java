package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Contato;

@Local
public interface ContatoService extends GenericService<Contato, Long>{
	
	List<Contato> findByNameLike(String name);
	
	List<Contato> findPFByName(String name);
	
	List<Contato> findPJByName(String name);
	
	List<Contato> findAllFetchAll();
	
	Contato findByIdFetchAll(Long id);

}
