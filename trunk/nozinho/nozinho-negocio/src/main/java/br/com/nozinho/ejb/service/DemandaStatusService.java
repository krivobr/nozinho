package br.com.nozinho.ejb.service;

import javax.ejb.Local;

import br.com.nozinho.model.DemandaStatus;

@Local
public interface DemandaStatusService extends GenericService<DemandaStatus, Long>{

}
