package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Oficio;

@Local
public interface OficioService extends GenericService<Oficio, Long>{

	List<Oficio> findByNumeroLike(String numero);
	
	String getSugestaoNumeroOficio();
	
}
