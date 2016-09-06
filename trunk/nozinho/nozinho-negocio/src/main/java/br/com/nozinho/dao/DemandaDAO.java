package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Demanda;

@Local
public interface DemandaDAO extends GenericDAO<Demanda, Long>{

	List<Demanda> findAllFetchContatoInteressado();
	
	Demanda findByIdFetchHistorico(Long id);
	
	Long findMaxId();
	
}
