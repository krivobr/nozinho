package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.TipoDemanda;

@Local
public interface TipoDemandaDAO extends GenericDAO<TipoDemanda, Long>{

}
