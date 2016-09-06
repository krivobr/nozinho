package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Oficio;

@Local
public interface OficioDAO extends GenericDAO<Oficio, Long> {

	List<Oficio> findByNumeroLike(String numero);
	
	Long findMaxId();
	
}
