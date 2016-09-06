package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Partido;

@Local
public interface PartidoService extends GenericService<Partido, Long>{

	List<Partido> findBySiglaLike(String query);
	
}
