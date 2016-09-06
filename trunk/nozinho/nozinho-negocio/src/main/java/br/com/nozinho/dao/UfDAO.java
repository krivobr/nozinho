package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Uf;

@Local
public interface UfDAO extends GenericDAO<Uf, String> {

}
