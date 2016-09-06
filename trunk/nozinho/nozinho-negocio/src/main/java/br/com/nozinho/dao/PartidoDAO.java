package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Partido;

@Local
public interface PartidoDAO extends GenericDAO<Partido, Long>{

	List<Partido> findBySiglaLike(String name);
	
}
