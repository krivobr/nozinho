package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.DemandaStatusDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.DemandaStatus;

@Stateless
public class DemandaStatusDAOImpl extends GenericDAOImpl<DemandaStatus, Long> implements DemandaStatusDAO{

}
