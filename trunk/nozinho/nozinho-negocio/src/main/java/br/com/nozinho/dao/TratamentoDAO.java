package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Tratamento;

@Local
public interface TratamentoDAO extends GenericDAO<Tratamento, Long>{

}
