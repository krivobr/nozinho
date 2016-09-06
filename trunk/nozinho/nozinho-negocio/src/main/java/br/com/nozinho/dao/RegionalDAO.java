package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Regional;

@Local
public interface RegionalDAO extends GenericDAO<Regional, Long>{

}
