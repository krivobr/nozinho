package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Cargo;

@Local
public interface CargoService extends GenericService<Cargo, Long> {

	List<Cargo> findByNomeLike(String nome);
	
}
