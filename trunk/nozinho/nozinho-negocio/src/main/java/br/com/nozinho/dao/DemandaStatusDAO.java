package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.DemandaStatus;

@Local
public interface DemandaStatusDAO extends GenericDAO<DemandaStatus, Long>{

}
