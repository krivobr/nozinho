package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Demanda;

@Local
public interface DemandaService extends GenericService<Demanda, Long>{
	List<Demanda> findAllFetchContatoInteressado();
	
	Demanda findByIdFetchHistorico(Long id);
	
	String getSugestaoNumeroDemanda();
}
