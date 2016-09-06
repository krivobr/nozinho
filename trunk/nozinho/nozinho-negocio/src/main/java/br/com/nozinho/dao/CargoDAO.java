package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Cargo;

@Local
public interface CargoDAO extends GenericDAO<Cargo, Long>{

	List<Cargo> findByNoneLike(String nome);
	
}
